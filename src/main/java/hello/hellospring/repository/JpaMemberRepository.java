package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //jpa는 EntityManager로 모든게 동작함
    //아까 build.gradle에서 implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 입력해주면
    //스프링 부트가 알아서 application.properties등에 입력했던 정보들이랑 결합하고 현재 db랑 연결해서 EntityManager를 생성해줌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //이렇게 하면 jpa가 insert쿼리도 해주고 db에 넣어주고 member에 setid도 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}