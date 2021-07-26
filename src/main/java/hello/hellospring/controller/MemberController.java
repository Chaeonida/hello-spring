package hello.hellospring.controller;

import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //MemberService를 가져다 쓰기 위해 이렇게 new로 생성 가능, 스프링이 관리하면 스프링 컨테이너에 등록하고 가져다 쓰는게 좋다. 그래서 바로 아랫줄 처럼 사용할때마다 new를 쓰는건 별로 좋지 못해.
    // private final MemberService memberService = new MemberService();

    //그래서 아래처럼 생성자를 만들고 @Autowired해주면 됨. 생성자에 @Autowired가 붙어 있으면 스프링이 이 memberService를 스프링 컨테이너에 있는 memberService를 가져와 연결시킴
    //그런데 이게 되려면 memberService도 @Service 애노테이션이 붙어 있어야함.
    private MemberService memberService;



    //memberController가 생성이 될때 스프링 빈에 등록되어 있는 memberService 객체를 가져다 넣어줌  ==> DI
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        //회원가입 끝나면 홈화면으로 다시 돌려보내기
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}