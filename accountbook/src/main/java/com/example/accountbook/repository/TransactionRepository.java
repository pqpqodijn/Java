//Transaction파일에 있는걸 가져오거나 저장을 하는 중간계층 역할
//Transaction 엔티티를 DB에 접근하는 역할인 인페이스

package com.examlpe.accountbook.domain.repository;

import com.example.accountbook.damain.entity.Transaction;
import com.example.accountbook.domain.entity.UserCategory;
import org.springframework.data.jpa.repository.Jparepository;// Jparepository 를 상속하면서 기본 필요 요소들 자동으로 받아 쓰기
import org.springframework.stereotype.Repository;//이 파일이 데이터 접근게층이라는걸 알리는 어노테이션 사용을 위해서
import java.util.List;//찾은걸 리스트 기반으로 바꾸기 위해
import java.time.LocalDate;//날짜 기반으로 찾기 위해

@Repository
//< Transaction 의 엔티티 클래스, 엔티티 기본키(타입은 long) >
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 특정 날짜 범위로 거래 내역 조회
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // 특정 카테고리의 거래만 조회
    List<Transaction> findByCategory(UserCategory category);

    // 거래 타입별(지출/수입 등)로 조회
    List<Transaction> findByType(Enum type);
}
// method 이름이 findBy+ 속성명 형태로 되어있어야 함
//List<Transaction> : 여러개의 Transaction 객체를 리스트 형태로 반환
//List는 반환타입이고 제네릭스 안에 Transaction을 넣어서 Transaction 객체들의 리스트임을 명시함과 동시에 Transaction 타입의 객체들만 담을 수 있도록 제한
                                      