package model.clothingtypes;

import model.Clothing;

//represents a clothing of type jacket
public class Jacket extends Clothing {


    public Jacket(String name, String size, String color, String brand, String fabric) {
        super(name, size, color, brand, fabric);
        type = "jacket";
    }

}
