package model;

import model.clothingtypes.*;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

// holds all individual clothing items owned, sold/thrown out, and outfits made
public class Wardrobe {


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
    //EFFECTS: adds given clothing item to wardrobe, if already in wardrobe, adds one to quantity
    public void addClothing(Clothing c) {
        if (findCurrentItem(c.name)) {
            c.quantity++;
        } else {
            currentWardrobe.add(c);
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: removes clothing item if it is in the current wardrobe, and adds it to the old wardrobe, returning true
    // otherwise returns false
    public boolean removeClothing(Clothing c) {
        if (findCurrentItem(c.name)) {
            currentWardrobe.remove(c);
            oldWardrobe.add(c);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES:
    //MODIFIES: this & outfit object
    //EFFECTS: creates a new outfit and adds it to outfit library
    public Outfit makeOutfit(String name,
                             Hat hat,
                             Shirt shirt,
                             Sweater sweater,
                             Jacket jacket,
                             Sock socks,
                             Shoe shoes) {
        Outfit newOutfit = new Outfit(name, hat, shirt, sweater, jacket, socks, shoes);
        outfits.add(newOutfit);
        return newOutfit;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting brand criteria
    public List filterBrandWardrobe(String brand) {
        List filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.brand == brand) {
                filterList.add(c);
            }
        }
        return filterList;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting colour criteria
    public List filterColorWardrobe(String color) {
        List filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.colour == color) {
                filterList.add(c);
            }
        }
        return filterList;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting clothing type criteria
    public List filterTypeWardrobe(String type) {
        List filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.type == type) {
                filterList.add(c);
            }
        }
        return filterList;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns clothes meeting fabric criteria
    public List filterFabricWardrobe(String fabric) {
        List filterList = new ArrayList<>();
        for (Clothing c : currentWardrobe) {
            if (c.fabric == fabric) {
                filterList.add(c);
            }
        }
        return filterList;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int currentSize() {
        return currentWardrobe.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int oldSize() {
        return oldWardrobe.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int outfitSize() {
        return outfits.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean findCurrentItem(String name) {
        boolean isFound = false;
        for (Clothing c : currentWardrobe) {
            if (c.name == name) {
                isFound = true;
            }
        }
        return isFound;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean findOldItem(String name) {
        boolean isFound = false;
        for (Clothing c : oldWardrobe) {
            if (c.name == name) {
                isFound = true;
            }
        }
        return isFound;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean findOutfit(String name) {
        boolean isFound = false;
        for (Outfit o : outfits) {
            if (o.getName() == name) {
                isFound = true;
            }
        }
        return isFound;
    }
}


