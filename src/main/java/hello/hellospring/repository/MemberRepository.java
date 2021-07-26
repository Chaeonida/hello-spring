package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); //멤버 저장
    //optional은 자바8에 있는 것으로 만약 findById의 값이 null인 경우
    //null을 감싸는것 -> 뒤에서 자세히
    Optional<Member> findById(Long id); //id를 찾음
    Optional<Member> findByName(String name); //이름
    List<Member> findAll(); //지금까지 저장한 멤버 리스트 모두 찾음
}