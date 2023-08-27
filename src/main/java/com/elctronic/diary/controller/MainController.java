package com.elctronic.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    /*
    @GetMapping("/")
    public String index(Model models) {
        return "redirect:/hello";
    }
*/
    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null) return null;
        return principal.getName();
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/error")
    public String notFound() {
        return "404";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
