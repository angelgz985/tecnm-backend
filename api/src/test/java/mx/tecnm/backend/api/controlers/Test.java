package mx.tecnm.backend.api.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/test")
@RestController
public class Test {

    @GetMapping("/hello")

    public String helloworld() {

    return "hello API Test";
    }
     

}
