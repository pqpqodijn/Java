package src.main.java.com.example.accountbook.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity// 테이블 매핑시키기
@Table(
    name = "transactions",
    indexes = {
        @Index(name = "idx_transaction_date", columnList = "date"),
        @Index(name = "idx_transaction_category", columnList = "category_id")
    }
)// 테이블에서 자추 가져오는 것들에 대해서 지정(category 클래스의 id 가져오기)
@EntityListeners(AuditingEntityListener.class)//createdat., updatedat 없이도 자동으로 DB에 들어가게 해줌

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, precision = 19,  scale = 2)// null값 입하못하게 막고 숫자 사이즈 및 소수점 2자리까지로 설정
    private BigDecimal amount;//소수점 정학하기 위해 금융에서는 대부분 BigDecimal 사용

    
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String source;

        /**
     * 다대일 관계 (여러 Transaction -> 하나의 UserCategory)
     * fetch = LAZY : Transaction을 조회할 때 category는 즉시 로드하지 않음.
     */
    @ManyToOne(fetch = FetchType.LAZY)//이래야 나중에 category가 필요할때만 로드하기 때문에 n+1만큼 필요하지 않음
    @JoinColumn(name = "category_id")
    private UserCategory category;

    @Column(nullable = false)
    public String memo;

    @Enumerated(EnumType.STRING)//enum을 DB에 문자열로 저장
    @Column(nullable = false)//null 값 불가
    private TransactionType type;

    public Transaction(){}

        public Transaction(BigDecimal amount, TransactionType type, String source, String memo, LocalDate date, UserCategory category) {
        this.amount = amount;
        this.type = type;
        this.source = source;
        this.memo = memo;
        this.date = date;
        this.category = category;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) {
        if (amount == null) {
            this.amount = null; // 또는 IllegalArgumentException을 던질 수 있음
            return;
        }
        // scale(소수점 자리) 통일: 소수점 2자리(화폐)로 반올림 HALF_EVEN 권장
        this.amount = amount.setScale(2, java.math.RoundingMode.HALF_EVEN);
    }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public UserCategory getCategory() { return category; }
    public void setCategory(UserCategory category) { this.category = category; }

    @Override
    public boolean equals(Object o){//나중에 같은 객체인지 확인하기위한 equal override
        if(this == o) return true;// 맞는지 화인
        if (!(o instanceof Transaction)) return false; //transaction으로 구성된건지 확인
        Transaction that = (Transaction) o;  //강제 변환
        // id가 null인 경우는 영속화 전 상태이므로 동등성 판단에 신중
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);//id를 이용해서 hashCode만들기
    }

    @Override
    public String toString() {//나중에 검토하거 확인하기 쉽게 toString override해놓ㅁ
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", memo='" + memo + '\'' +
                ", category=" + (category != null ? category : null) +
                ", source='" + source + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }

}
