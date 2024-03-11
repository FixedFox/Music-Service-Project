package ru.fixedfox.musicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getLandingPage(){
        return "index";
    }

    @GetMapping("/loginpage")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/mainpage")
    public String getStartPage(){
        return "mainpage";
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @GetMapping("/whatsnew")
    public String getWhatsNewPage() {
        return "whatsnew";
    }

    @GetMapping("/recomendation")
    public String getRecomendation() {
        return "recomendation";
    }

}
