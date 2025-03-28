package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    private int count;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu() {
    }

    public void addItem(Cheese item) {cheeses.add(item);}

    public int getId() {return id;}

    public String getName() {return name;}

    public void setName(String aName) {this.name=aName;}

    public List<Cheese> getCheeses() {return cheeses;}

    public int getCount() {
        this.count=this.cheeses.size();
        return count;
    }

}
