package org.launchcode.controllers;

import org.launchcode.models.Menu;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "add";
        }
        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();
    }


    @RequestMapping(value="view", method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuTag) {
        model.addAttribute("title", "Menu View");

        return "view";
    }
}
