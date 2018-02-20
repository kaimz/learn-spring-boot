package com.wuwii.common.exception;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/2/16 15:33</pre>
 */
@Controller
@RequestMapping("/error")
public class ErrorPage implements ErrorController {
    @Override
    public String getErrorPath() {
        return "404-page.html";
    }

    @GetMapping
    public String error() {
        return getErrorPath();
    }
}
