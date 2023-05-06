package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/school")
    public String School() {
        return "school";
    }

    @RequestMapping("/major")
    public String Major() {
        return "major";
    }

    @RequestMapping("/student")
    public String Student() {
        return "student";
    }

    @RequestMapping("")
    public String Index() {
        return "index";
    }

    @RequestMapping("/search")
    public String Search() {
        return "search";
    }
}
