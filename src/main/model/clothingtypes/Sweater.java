package model.clothingtypes;

import model.Clothing;

import java.awt.*;

//represents a clothing of type sweater
public class Sweater extends Clothing {

    private boolean hasHood;
    private boolean hasZipper;

    public Sweater(String name, String size, String color, String brand, String fabric) {
        super(name, size, color, brand, fabric);
        hasZipper = false;
        hasHood = false;
        type = "sweater";
    }

    public void setHasHood(boolean b) {
        hasHood = b;
    }

    public void setHasZipper(boolean b) {
        hasZipper = b;
    }

}
