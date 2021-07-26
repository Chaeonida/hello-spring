package hello.hellospring.service;


// 자바 코드로 직접 스프링 빈 등록하기
//회원 서비스(Service)와 회원 레피지톨의 @Service, @Autowired,@Repository의 애노테이션 제거
// Controller는 애노테이션을 지우지 말기
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
//스프링이 뜰떄 이 애노테이션을 보고 스프링에 등록하라는 듰으로 알아들어서
//memberService를 return문을 호출을 해서 스프링 빈에 등록
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        //스프링 컨테이너에서 MemberRepository 찾음. SpringDataJpaMemberRepository에
        //SpringDataJpaMemberRepository extends JpaRepository 이걸보고 스프링 데이터 JPA가 구현체를 생성함
        //그걸 스프링 빈에 등록해주니 우리는 그걸 받을 수 있다.

        this.memberRepository = memberRepository;
    }


//    //@Autowired  DataSource dataSource;
//    private DataSource dataSource;  //이렇게 해놓으면 스프링부트는 application.properties설정을 보고
//   // 자체적인 bean생성, db와 연결할 수 있는 정보가 있는 dataSource를 만들어준다.
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }


    //jpa 이용할때
//    private final DataSource dataSource;
//    private final EntityManager em;
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }



    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

    // TimeTraceApp에서 @Component 대신 이렇게 써도 댐
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }


//    @Bean
//    public MemberRepository memberRepository(){

        // return new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource); //순수한 JDbv방법
       // return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }


//DB방식이 메모리 저장에서 h2로 변경
//MemoryMemberRepository()로 구현되던 것이 JdbvMemberRepository로 바꿔줌
//다른 소스코드들은 수정하지 않고 오로지 바뀐 db부분만 파일을 변경해줌