package model;


import java.io.Serializable;

//represents a typical clothing item that will be used to extend other clothing items
public abstract class Clothing implements Serializable {

    public static final String SMALL = "S";
    public static final String MEDIUM = "M";
    public static final String LARGE = "L";
    public static final String EXTRA_LARGE = "XL";


    protected String name;
    protected String size;
    protected String colour;
    protected String brand;
    protected String fabric;
    protected boolean isCollaboration;
    protected String collaboration;
    protected String type;


    public Clothing(String name, String size, String color, String brand, String fabric) {
        this.name = name;
        this.size = size;
        colour = color;
        this.brand = brand;
        this.fabric = fabric;
        isCollaboration = false;
        collaboration = "";
    }

    public void setCollaboration(String brand) {
        isCollaboration = true;
        collaboration = brand;
    }


    public String getColour() {
        return colour;
    }

    public String getBrand() {
        return brand;
    }

    public String getCollaboration() {
        return collaboration;
    }

    public String getFabric() {
        return fabric;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public void setType(String type) {
        this.type = type;
    }
}




