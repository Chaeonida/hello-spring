package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;import java.util.Optional;

////@Service
//스프링 빈을 등록하는 2가지 방법
// 1. 컴포넌트 스캔과 자동 의존관계 설정(@Controller, @Service, @Repository)
// @Component 가 스프링 빈으로 자동 등록
//위에 3개는 component 포함되어있기때문에 자동등록 해줌
// 2. 자바 코드로 직접 스프링 빈 등록

//jpa쓰려면 Transactional이 있어야함. 데이터를 저장 . 변경할때 필요
@Transactional
public class MemberService {

    //private final MemberRepository memoryRepository = new MemoryMemberRepository();
    //MemberServiceTest와 같은 MemoryMemberRepository를 검사하기 위해 위와 같이 쓰지 않고 아래처럼 만들어 준다

    private final MemberRepository memberRepository;

    ////@Autowired
    //컨트롤러가 서비스와 의존관계를 가지기 위해 (의존성 주입) 컨트롤러에서 memberService 정의
    //new memberService로 객체 생성해도 되지만 스프링 컨테이너에서 한번 등록하면 필요할때마다 불러서 사용하기 떄문에
    //new보다는 생성자에 @AutoWired 를 붙여 사용

    public MemberService(MemberRepository memberRepository) {
        //memberRepository를 직접 new로 넣어주는게 아니라 외부에서 넣어주도록
        this.memberRepository = memberRepository; //여기서 외부는 MemberServiceTest 26번쨰 줄에서 넣어주고 있음
    }

    /**
     * 회원가입
     */
    // memberRepository.findByName(member.getName()) 적어주고 ctrl + alt + v 하면 자동으로 반환값 만들어줌
    //optional<Member> result = memberRepository.findByName(member.getName());

    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        //ctrl+T:리패토링 관련 여러가지 나옴 위의 4줄을 드래그하고 단축키 눌러 method 검색하면 Extract method
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        //만약 null이 아니라 값이 있다면 아래 동장을 해라. optional 덕분에 isPresent 사용가능
        //result.isPresent(m-> {
        //      throw new IllegalStateException("이미 존재하는 회원입니다.")

        //위에 코드는 optional로 받았기 때문에 아래 코드처럼 작성
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
