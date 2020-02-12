package model.clothingtypes;

import model.Clothing;

import java.awt.*;

//represents a clothing of type jacket
public class Jacket extends Clothing {

    private boolean hasZipper;

    public Jacket(String name, String size, String color, String brand, String fabric) {
        super(name, size, color, brand, fabric);
        hasZipper = false;
        type = "Jacket";
    }

    public void setHasZipper(boolean hasZipper) {
        this.hasZipper = hasZipper;
    }
}
