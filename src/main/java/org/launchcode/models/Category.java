package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn
    private List<Cheese> cheeses = new ArrayList<>();

    public Category() {}

    public Category(String aName) {this.name = aName;}

    public int getId() {return id;}
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
