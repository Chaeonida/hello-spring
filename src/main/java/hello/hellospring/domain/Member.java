package hello.hellospring.domain;

import javax.persistence.*;

//jpa가 관리하는 entity구나
@Entity
public class Member {

    //오라클의 시퀀스처럼 이름만 넣어주면 알아서 pk가 자동으로 생성 -> 이걸 identity라고 함.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //db의 컬럼명이 username이다.
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}