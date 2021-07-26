package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {

    //MemberService memberService = new MemberService();

    //clear해주기 위해 선언
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트 하기 전에 수행
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }



    //하나 실행하고 나면 이어서 실행되는 것
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given (어떤게 주어졌을때)
        Member member = new Member();
        member.setName("hello");

        //when (이걸 실행했을때)
        Long saveId = memberService.join(member);

        //then (결과가 뭐가 나온다)
        Member findMember = memberService.findOne(saveId).get();
        //Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); 은 static import해준다.
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //memberService.join(member2)) 이 로직을 실행할건데 이 IllegalStateException.class예외가 터져야 한다는 뜻

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //validateDuplicateMember에 걸려 예외가 발생하는 경우 try~catch로 예외를 잡을 수 있다.
       /* try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            //예외가 터져 정상적으로 성공한 경우
            //이미 존재하는 회원~~이 메세지는 MemberService의 validateDuplicateMember 메시지임!! 그 메시지랑 동일한지 묻는 것.
           // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 테스트 성공
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.깔깔깔!! 테스트 실패 유도 메시지");
        }
*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}