package hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @RequestMapping("/wap")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
