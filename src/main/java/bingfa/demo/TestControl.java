package bingfa.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestControl {
    int i =0;
    @RequestMapping("/test")
    public String test(){
        System.out.println(i++);
        return "hello world";
    }
}
