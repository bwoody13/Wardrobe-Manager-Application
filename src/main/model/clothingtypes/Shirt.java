package model.clothingtypes;

import model.Clothing;

//represents clothing of type shirt
public class Shirt extends Clothing {

    private String sleeveLength;

    public Shirt(String name, String size, String color, String brand, String fabric, String sleeveLength) {
        super(name, size, color, brand, fabric);
        this.sleeveLength = sleeveLength;
        type = "shirt";
    }


    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public String getSleeveLength() {
        return sleeveLength;
    }
}
