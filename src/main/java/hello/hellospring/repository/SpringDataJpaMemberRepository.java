package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//다중상속 받고 있는 인터페이스
//스프링 데이터 JPA가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어줌. 스프링 빈에 자동 등록해줌.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}