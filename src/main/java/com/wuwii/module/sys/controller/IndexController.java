package com.wuwii.module.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/8 18:11</pre>
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    /*@GetMapping("/{html:((?!\\.html$).)*$}")
    public String html(@PathVariable String html) {
        return html;
    }*/

}
