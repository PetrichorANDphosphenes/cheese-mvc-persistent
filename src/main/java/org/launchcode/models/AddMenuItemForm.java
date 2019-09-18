package org.launchcode.models;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {



    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;
    @NotNull
    private int cheeseId;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Iterable<Cheese> aCheeses, Menu aMenu) {
        this.cheeses=aCheeses;
        this.menu=aMenu;
    }

    public int getMenuId() {return menuId;}

    public void setMenuId(int menuId) {this.menuId = menuId;}

    public int getCheeseId() {return cheeseId; }

    public void setCheeseId(int cheeseId) {this.cheeseId = cheeseId;}

    public Menu getMenu() {return menu;}

    public Iterable<Cheese> getCheeses() {return cheeses; }


}
