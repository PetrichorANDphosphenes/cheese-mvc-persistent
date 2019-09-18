package org.launchcode.controllers;

import org.launchcode.models.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
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

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        //this handler should list all the menu names and link to their individual menu pages
        model.addAttribute("title", "menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a Menu");
        model.addAttribute("newMenu", new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }
        menuDao.save(newMenu);

        return "redirect:add-item/" + newMenu.getId();
    }

    @RequestMapping(value="view/{menuId}", method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu current = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(
                cheeseDao.findAll(), current);

        model.addAttribute("menu", current);
        model.addAttribute("title", current.getName());
        model.addAttribute("menuId", current.getId());
        model.addAttribute("cheeses", current.getCheeses());

        return "menu/view";
    }

    @RequestMapping(value="add-item/{menuId}", method=RequestMethod.GET)
    public String addItemView(Model model, @PathVariable int menuId) {
        Menu current = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(
                cheeseDao.findAll(), current);
        model.addAttribute("title", "Add items to this menu - " + current.getName());
        model.addAttribute("form", form);
        //consider redirecting to ... instead
        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItemProcess(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
        Cheese bCheese = cheeseDao.findOne(form.getCheeseId());
        Menu bMenu = menuDao.findOne(form.getMenuId());
        bMenu.addItem(bCheese);
        menuDao.save(bMenu);

        return "redirect:view/" + form.getMenuId();
    }

}
