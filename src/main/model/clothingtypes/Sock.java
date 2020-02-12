package model.clothingtypes;

import model.Clothing;

import java.awt.*;

//represents a clothing of type sock
public class Sock extends Clothing {

    private String height;

    public Sock(String name, String size, String color, String brand, String fabric, String height) {
        super(name, size, color, brand, fabric);
        this.height = height;
        type = "Sock";
    }

}
