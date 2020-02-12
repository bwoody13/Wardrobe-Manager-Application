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

    public Outfit(String name, Clothing hat, Clothing shirt, Clothing sweater, Clothing jacket, Clothing bottoms, Clothing socks, Clothing shoes) {
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

    public void setHat(Hat h) {
        hat = h;
    }

    public void setShirt(Shirt s) {
        shirt = s;
    }

    public void setSweater(Sweater s) {
        sweater = s;
    }

    public void setJacket(Jacket j) {
        jacket = j;
    }

    public void setSocks(Sock s) {
        socks = s;
    }

    public void setShoes(Shoe s) {
        shoes = s;
    }

    public void setBottoms(Bottoms bottoms) {
        this.bottoms = bottoms;
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
}

