package model;

import model.clothingtypes.*;

//represents an outfit containing different pieces of clothing per outfit
public class Outfit {
    //contains clothing items from distinct groups to make an outfit
    private String name;
    private Clothing hat;
    private Clothing shirt;
    private Clothing sweater;
    private Clothing jacket;
    private Clothing bottoms;
    private Clothing socks;
    private Clothing shoes;

    public Outfit(String name,
                  Clothing hat,
                  Clothing shirt,
                  Clothing sweater, Clothing jacket, Clothing bottoms, Clothing socks, Clothing shoes) {
        this.name = name;
        this.hat = hat;
        this.shirt = shirt;
        this.sweater = sweater;
        this.jacket = jacket;
        this.bottoms = bottoms;
        this.socks = socks;
        this.shoes = shoes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Clothing getHat() {
        return hat;
    }

    public Clothing getJacket() {
        return jacket;
    }

    public Clothing getShirt() {
        return shirt;
    }

    public Clothing getShoes() {
        return shoes;
    }

    public Clothing getSocks() {
        return socks;
    }

    public Clothing getSweater() {
        return sweater;
    }

    public Clothing getBottoms() {
        return bottoms;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the string of details and name of outfit, unless an item is null
    public String itemName(Clothing c) {
        String output = "";
        if (c != null) {
            String type = c.type;
            output = type + ": " + c.getName();
        }
        return output;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: converts an outfit to a string of clothing items within
    public String outfitToString() {
        String output = "";
        output += "\n" + getName() + "(";
        output += itemName(getHat()) + " " + itemName(getShirt()) + " " + itemName(getSweater())
                + " " + itemName(getJacket()) + " " + itemName(getBottoms()) + " "
                + itemName(getSocks()) + " " + itemName(getShoes()) + ")";
        return output;
    }


}

