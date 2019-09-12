package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class MainController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "home screen");
        return "index";

    }
}
