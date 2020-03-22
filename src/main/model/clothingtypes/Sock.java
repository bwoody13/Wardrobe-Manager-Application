package model.clothingtypes;

import model.Clothing;


//represents a clothing of type sock
public class Sock extends Clothing {

    private String height;

    public Sock(String name, String size, String color, String brand, String fabric, String height) {
        super(name, size, color, brand, fabric);
        this.height = height;
        type = "sock";
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
