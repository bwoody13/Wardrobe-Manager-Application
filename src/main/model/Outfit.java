package model;

import model.clothingtypes.*;

//represents an outfit containing different pieces of clothing per outfit
public class Outfit {
    //contains clothing items from distinct groups to make an outfit
    private String name;
    private Hat hat;
    private Shirt shirt;
    private Sweater sweater;
    private Jacket jacket;
    private Sock socks;
    private Shoe shoes;

    public Outfit(String name, Hat hat, Shirt shirt, Sweater sweater, Jacket jacket, Sock socks, Shoe shoes) {
        this.name = name;
        this.hat = hat;
        this.shirt = shirt;
        this.sweater = sweater;
        this.jacket = jacket;
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

    public String getName() {
        return name;
    }

    public Hat getHat() {
        return hat;
    }

    public Jacket getJacket() {
        return jacket;
    }

    public Shirt getShirt() {
        return shirt;
    }

    public Shoe getShoes() {
        return shoes;
    }

    public Sock getSocks() {
        return socks;
    }

    public Sweater getSweater() {
        return sweater;
    }
}
