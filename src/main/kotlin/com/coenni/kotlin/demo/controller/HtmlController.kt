package com.coenni.kotlin.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/web")
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        model.addAttribute("name", "coenni");
        return "index";
    }


}