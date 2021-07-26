package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

////@Repository
public class MemoryMemberRepository implements MemberRepository {

   //alt + enter -> import 불러옴
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //alt + shift + enter -> 한번에 오버라이드 메소드 불러올수 있음
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //사용자가 만드는 아이디가 아닌 시스템에서 만들어 주는 아이디
        store.put(member.getId(), member); //map에 저장
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        //store에서 id가져왔을때 그게 null인 경우 그것을 감싸기 위해 optional.ofnullabe. 해준거
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {
        //store에 있는 values 는 Member들을 뚯한다
        return new ArrayList<>(store.values()); }
    @Override
    public Optional<Member> findByName(String name) {
        //자바 람다식
        //루프를 돌려서 찾기
        //member.getName()이 파라미터로 넘어온 name과 같은지 비교
        //같을 때만 필터링
        //그걸 하나 찾아서 반환해라 없으면 null값을 optional로 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore(){
        store.clear();
    }

}