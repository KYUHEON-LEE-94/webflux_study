package com.study.webflux_study.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.study.webflux_study.mongoDB.SSERestController
 * fileName       : SSEController
 * author         : LEE KYUHEON
 * date           : 2023-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-31        LEE KYUHEON       최초 생성
 */
@Controller
public class SSEController {

    @GetMapping("/sse")
    public String hello(Model model) {
        model.addAttribute("pageTitle", "Hello Page");
        return "sseStudy/home";
    }
}
