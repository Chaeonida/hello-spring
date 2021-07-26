package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        //set을 통해 외부에서 입력된 name값 등록
    }
}
