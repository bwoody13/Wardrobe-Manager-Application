package model.clothingtypes;

import model.Clothing;



//represents a clothing of type shoe
public class Shoe extends Clothing {
    private String model;


    public Shoe(String name, String size, String color, String brand, String fabric, String model) {
        super(name, size, color, brand, fabric);
        this.model = model;
        type = "shoe";
    }

    public String getModel() {
        return model;
    }
}
