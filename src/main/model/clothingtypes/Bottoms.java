package model.clothingtypes;

import model.Clothing;

public class Bottoms extends Clothing {

    private String length;

    public Bottoms(String name, String size, String color, String brand, String fabric, String length) {
        super(name, size, color, brand, fabric);
        this.length = length;
        type = "bottoms";
    }

    public String getLength() {
        return length;
    }
}

