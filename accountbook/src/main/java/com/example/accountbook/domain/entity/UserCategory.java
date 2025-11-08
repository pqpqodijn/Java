package src.main.java.com.example.accountbook.domain.entity;

import java.lang.annotation.Inherited;

import jakarta.persistence.*;//JPA package 불러오기 @ID , @Entity 이런거 불러오기

//DB 테이블 역할
@Entity //JPA 엔티티 지정 — 이 클래스가 DB 테이블과 매핑됨.
@Table(name = "user_category")//실제 테이블명 지정
public class UserCategory {
    @id//기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 증가 전략(키 값이 자동으로 DB에서 생성됨) (MySQL의 AUTO_INCREMENT와 같음) 키값이 달라야 매번 다른 객체 생성됨
    private long id;
    @Column(nullable = false, unique = true) // null 불가, 중복 불가
    private String name;// 예를 들어 식비인지 교통인지 이름 작성
    public UserCategory(){}// 기본 생성자
    public UserCategory(String name){
        this.name = name;// 생성자 오버로딩 해서 만든거
    }
    //값이 private이니 setter,getter 만들기
    public long getid() {return id;}
    public void setid(long id){
        this.id = id;
    } 
    public String getname() {return name;}
    public void setname(String name){this.name = name;}
}
