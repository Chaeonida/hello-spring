package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/") //localhost:8080 하고 들어가면 제일 처음 보이는 화면
    public String home() {
        return "home";
    }
}