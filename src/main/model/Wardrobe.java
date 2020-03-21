package model;

import model.clothingtypes.*;
import persistence.Writer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// holds all individual clothing items owned, sold/thrown out, and outfits made
public class Wardrobe implements Serializable {


    private ArrayList<Clothing> currentWardrobe; //clothes currently owned
    private ArrayList<Clothing> oldWardrobe;  //clothes previously owned, but sold or threw away
    private ArrayList<Outfit> outfits; //list of outfits made

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a new and empty wardrobe
    public Wardrobe() {
        currentWardrobe = new ArrayList<>();
        oldWardrobe = new ArrayList<>();
        outfits = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeBottoms(String name, String size, String color, String brand, String fabric, String length) {
        if (findCurrentItem(name) == null) {
            Bottoms newBottoms = new Bottoms(name, size, color, brand, fabric, length);
            currentWardrobe.add(newBottoms);
            return newBottoms;
        } else {
            return findCurrentItem(name);
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeHat(String name, String size, String color, String brand, String fabric) {
        if (findCurrentItem(name) == null) {
            Hat newHat = new Hat(name, size, color, brand, fabric);
            currentWardrobe.add(newHat);
            return newHat;
        } else {
            return findCurrentItem(name);

        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeJacket(String name, String size, String color, String brand, String fabric) {
        if (findCurrentItem(name) == null) {
            Jacket newJacket = new Jacket(name, size, color, brand, fabric);
            currentWardrobe.add(newJacket);
            return newJacket;
        } else {
            return findCurrentItem(name);

        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeShirt(String name,
                              String size,
                              String color,
                              String brand,
                              String fabric,
                              String sleeveLength) {
        if (findCurrentItem(name) == null) {
            Shirt newShirt = new Shirt(name, size, color, brand, fabric, sleeveLength);
            currentWardrobe.add(newShirt);
            return newShirt;
        } else {
            return findCurrentItem(name);

        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeShoe(String name, String size, String color, String brand, String fabric, String model) {
        if (findCurrentItem(name) == null) {
            Shoe newShoe = new Shoe(name, size, color, brand, fabric, model);
            currentWardrobe.add(newShoe);
            return newShoe;
        } else {
            return findCurrentItem(name);
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeSock(String name, String size, String color, String brand, String fabric, String height) {
        if (findCurrentItem(name) == null) {
            Sock newSock = new Sock(name, size, color, brand, fabric, height);
            currentWardrobe.add(newSock);
            return newSock;
        } else {
            return findCurrentItem(name);
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns and adds given clothing item to wardrobe, if already in wardrobe, return item trying to add
    public Clothing makeSweater(String name, String size, String color, String brand, String fabric) {
        if (findCurrentItem(name) == null) {
            Sweater newSweater = new Sweater(name, size, color, brand, fabric);
            currentWardrobe.add(newSweater);
            return newSweater;
        } else {
            return findCurrentItem(name);
        }
    }

    //REQUIRES:
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
                             Clothing shoes) {
        Outfit newOutfit = new Outfit(name, hat, shirt, sweater, jacket, bottoms, socks, shoes);
        outfits.add(newOutfit);
        return newOutfit;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting brand criteria
    public String filterBrandWardrobe(String brand) {
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting colour criteria
    public String filterColorWardrobe(String color) {
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting clothing type criteria
    public String filterTypeWardrobe(String type) {
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting fabric criteria
    public String filterFabricWardrobe(String fabric) {
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows size of current wardrobe
    public int currentSize() {
        return currentWardrobe.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: shows size of old wardrobe
    public int oldSize() {
        return oldWardrobe.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: show size of outfits saved
    public int outfitSize() {
        return outfits.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: produces the clothing item with given name in current wardrobe, or if not found, null
    public Clothing findCurrentItem(String name) {
        Clothing found = null;
        for (Clothing c : currentWardrobe) {
            String lowerName = c.name.toLowerCase();
            if (lowerName.equals(name)) {
                found = c;
            }
        }
        return found;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: produces the clothing item in old wardrobe with given name, or if not found, null
    public Clothing findOldItem(String name) {
        Clothing found = null;
        for (Clothing c : oldWardrobe) {
            if (c.name.equals(name)) {
                found = c;
            }
        }
        return found;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: produces the outfit with given name, or if not found, null
    public Outfit findOutfit(String name) {
        Outfit found = null;
        for (Outfit o : outfits) {
            if (o.getName().equals(name)) {
                found = o;
            }
        }
        return found;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a string of items in the current wardrobe
    public String currentToString() {
        String output = "";
        for (Clothing c : currentWardrobe) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a string of items in the old wardrobe
    public String oldToString() {
        String output = "";
        for (Clothing c : oldWardrobe) {
            output += "\n" + c.type + " named " + c.name + " made by " + c.brand;
        }
        return output;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: creates a string of outfits in the wardrobe
    public String outfitsToString() {
        String output = "";
        for (Outfit o : outfits) {
            output += o.outfitToString();
        }
        return output;
    }

    public void serialize(Writer writer) throws IOException {
        writer.write(this);
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
