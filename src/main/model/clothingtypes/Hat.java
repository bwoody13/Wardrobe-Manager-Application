package model.clothingtypes;

import model.Clothing;

import java.awt.*;

//represents a clothing of type hat
public class Hat extends Clothing {

    private boolean isFitted;

    public Hat(String name, String size, String color, String brand, String fabric) {
        super(name, size, color, brand, fabric);
        isFitted = false;
        type = "hat";
    }

    public void setFitted(boolean fitted) {
        isFitted = fitted;
    }
}


