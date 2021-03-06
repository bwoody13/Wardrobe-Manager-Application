package model;

import exceptions.InWardrobeException;
import model.clothingtypes.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// holds all individual clothing items owned, sold/thrown out, and outfits made
public class Wardrobe implements Serializable {


    private ArrayList<Clothing> currentWardrobe; //clothes currently owned
    private ArrayList<Clothing> oldWardrobe;  //clothes previously owned, but sold or threw away
    private ArrayList<Outfit> outfits; //list of outfits made

    //MODIFIES:
    //EFFECTS: creates a new and empty wardrobe
    public Wardrobe() {
        currentWardrobe = new ArrayList<>();
        oldWardrobe = new ArrayList<>();
        outfits = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeBottoms(String name, String size, String color, String brand,
                                String fabric, String length) throws InWardrobeException {
        return makeItem("bottoms", name,size,color,brand,fabric,length);
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeHat(String name, String size, String color, String brand,
                            String fabric) throws InWardrobeException {
        return makeItem("hat", name,size,color,brand,fabric,"");
    }



    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeJacket(String name, String size, String color, String brand,
                               String fabric) throws InWardrobeException {
        return makeItem("jacket", name,size,color,brand,fabric,"");
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeShirt(String name,
                              String size,
                              String color,
                              String brand,
                              String fabric,
                              String sleeveLength) throws InWardrobeException {
        return makeItem("shirt", name,size,color,brand,fabric,sleeveLength);
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeShoe(String name, String size, String color, String brand,
                             String fabric, String model) throws InWardrobeException {
        return makeItem("shoe", name,size,color,brand,fabric,model);
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeSock(String name, String size, String color, String brand,
                             String fabric, String height) throws InWardrobeException {
        return makeItem("sock", name,size,color,brand,fabric,height);
    }

    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, throws exception
    public Clothing makeSweater(String name, String size, String color,
                                String brand, String fabric) throws InWardrobeException {
        return makeItem("sweater", name,size,color,brand,fabric,"");
    }

    //MODIFIES: this
    //EFFECTS: will complete the making of a clothing item based on type
    public Clothing makeItem(String type, String name, String size, String color, String brand,
                             String fabric, String customAtt) throws InWardrobeException {
        type = type.toLowerCase();
        Clothing newItem;
        if (type.equals("bottoms")) {
            newItem = new Bottoms(name, size, color, brand, fabric, customAtt);
        } else if (type.equals("hat")) {
            newItem = new Hat(name, size, color, brand, fabric);
        } else if (type.equals("jacket")) {
            newItem = new Jacket(name, size, color, brand, fabric);
        } else if (type.equals("shirt")) {
            newItem = new Shirt(name, size, color, brand, fabric, customAtt);
        } else if (type.equals("shoe")) {
            newItem = new Shoe(name, size, color, brand, fabric, customAtt);
        } else if (type.equals("sock")) {
            newItem = new Sock(name, size, color, brand, fabric, customAtt);
        } else {
            newItem = new Sweater(name, size, color, brand, fabric);
        }
        if (findCurrentItem(name) == null) {
            currentWardrobe.add(newItem);
            return newItem;
        } else {
            throw new InWardrobeException();
        }
    }

    //MODIFIES: this
    //EFFECTS: removes clothing item if it is in the current wardrobe, and adds it to the old wardrobe, returning true
    // otherwise returns false
    public boolean removeClothing(String name) {
        Clothing item = findCurrentItem(name);
        if (currentWardrobe.contains(item)) {
            oldWardrobe.add(item);
            currentWardrobe.remove(item);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES:
    //MODIFIES: this & outfit object
    //EFFECTS: creates a new outfit and adds it to outfit library
    public Outfit makeOutfit(String name,
                             Clothing hat,
                             Clothing shirt,
                             Clothing sweater,
                             Clothing jacket,
                             Clothing bottoms,
                             Clothing socks,
                             Clothing shoes) throws InWardrobeException {
        if (findOutfit(name) == null) {
            Outfit newOutfit = new Outfit(name, hat, shirt, sweater, jacket, bottoms, socks, shoes);
            outfits.add(newOutfit);
            return newOutfit;
        } else {
            throw new InWardrobeException();
        }
    }

    //MODIFIES:
    //EFFECTS: returns clothes meeting brand criteria
    public String filterBrandWardrobe(String brand) {
        brand = brand.toLowerCase();
        ArrayList<Clothing> filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.brand.toLowerCase().equals(brand)) {
                filterList.add(c);
            }
        }
        String output = "";
        for (Clothing c : filterList) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: returns clothes meeting colour criteria
    public String filterColorWardrobe(String color) {
        color = color.toLowerCase();
        ArrayList<Clothing> filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.colour.toLowerCase().equals(color)) {
                filterList.add(c);
            }
        }
        String output = "";
        for (Clothing c : filterList) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: returns clothes meeting clothing type criteria
    public String filterTypeWardrobe(String type) {
        type = type.toLowerCase();
        ArrayList<Clothing> filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.type.toLowerCase().equals(type)) {
                filterList.add(c);
            }
        }
        String output = "";
        for (Clothing c : filterList) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: returns clothes meeting fabric criteria
    public String filterFabricWardrobe(String fabric) {
        fabric = fabric.toLowerCase();
        ArrayList<Clothing> filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.fabric.toLowerCase().equals(fabric)) {
                filterList.add(c);
            }
        }
        String output = "";
        for (Clothing c : filterList) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: shows size of current wardrobe
    public int currentSize() {
        return currentWardrobe.size();
    }


    //MODIFIES:
    //EFFECTS: shows size of old wardrobe
    public int oldSize() {
        return oldWardrobe.size();
    }


    //MODIFIES:
    //EFFECTS: show size of outfits saved
    public int outfitSize() {
        return outfits.size();
    }


    //MODIFIES:
    //EFFECTS: produces the clothing item with given name in current wardrobe, or if not found, null
    public Clothing findCurrentItem(String name) {
        name = name.toLowerCase();
        Clothing found = null;
        for (Clothing c : currentWardrobe) {
            String lowerName = c.name.toLowerCase();
            if (lowerName.equals(name)) {
                found = c;
            }
        }
        return found;
    }


    //MODIFIES:
    //EFFECTS: produces the clothing item in old wardrobe with given name, or if not found, null
    public Clothing findOldItem(String name) {
        name = name.toLowerCase();
        Clothing found = null;
        for (Clothing c : oldWardrobe) {
            if (c.name.toLowerCase().equals(name)) {
                found = c;
            }
        }
        return found;
    }


    //MODIFIES:
    //EFFECTS: produces the outfit with given name, or if not found, null
    public Outfit findOutfit(String name) {
        name = name.toLowerCase();
        Outfit found = null;
        for (Outfit o : outfits) {
            if (o.getName().toLowerCase().equals(name)) {
                found = o;
            }
        }
        return found;
    }


    //MODIFIES:
    //EFFECTS: creates a string of items in the current wardrobe
    public String currentToString() {
        String output = "";
        for (Clothing c : currentWardrobe) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: creates a string of items in the old wardrobe
    public String oldToString() {
        String output = "";
        for (Clothing c : oldWardrobe) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }


    //MODIFIES:
    //EFFECTS: creates a string of outfits in the wardrobe
    public String outfitsToString() {
        String output = "";
        for (Outfit o : outfits) {
            output += o.outfitToString();
        }
        return output;
    }

    public List<Clothing> getCurrentWardrobe() {
        return currentWardrobe;
    }

    public List<Clothing> getOldWardrobe() {
        return oldWardrobe;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }

}
