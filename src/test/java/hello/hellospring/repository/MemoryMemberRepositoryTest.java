package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    //테스트는 서로 순서와 관계없이, 의존관계없이 설계가 되어야함
    //findAll, findByName 모두 member객체 만들고 있기때문에 에러뜬다
    //이전 데이터 clear 해줄 필요성 -> afterEach 사용
    //afterEach는 현재 test claa 각각의 @Test 이후에 이 메서드 실행되어야 함을 알림
    public void afterEach() {
        repository.clearStore();
    }

    //테스트코드는 아래 어노테이션을 붙여줘야함
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then

        //optional에 있는 것을 꺼낼라고 get()을 씀. 바로 쓰는건 좋지않지만 테스트라씀
        Member result = repository.findById(member.getId()).get();
        // 테스트 방법 1) System.out.println("result = " + (result == member));
        // 이렇게 하면 result = true 라고 나오는데 이렇게만 보면 둘이 같은지 모르지
        //그래서 Assertions를 이용함 junit이 제공해주는 것

        //테스트방법2 )Assertions.assertEquals(member, result);
        //같은지 기대하는 값을 앞에 적어주고 비교대상을 뒤에 적어줘

        //assertj의 Assertions를 이용
        //아래 assertThat에서 alt + enter를 누른뒤 static import를 해줘야 한다.
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        //shift + f6 리네임
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}