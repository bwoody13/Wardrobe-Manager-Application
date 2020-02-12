package model.clothingtypes;

import model.Clothing;

public class Shirt extends Clothing {

    private String sleeveLength;
    private boolean isButtoned;

    public Shirt(String name, String size, String color, String brand, String fabric, String sleeveLength) {
        super(name, size, color, brand, fabric);
        this.sleeveLength = sleeveLength;
        isButtoned = false;
        type = "Shirt";
    }


    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public void setButtoned(boolean buttoned) {
        isButtoned = buttoned;
    }
}
