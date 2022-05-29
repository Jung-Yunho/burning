package com.burning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/yh")
    public String indexYh() {
        return "yh/hello.html";
    }

    @GetMapping("/dh")
    public String indexDh() {
        return "dh/hello.html";
    }
}
