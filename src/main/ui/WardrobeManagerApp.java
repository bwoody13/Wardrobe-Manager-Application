package ui;

import model.Clothing;
import model.Outfit;
import model.Wardrobe;


import java.util.Scanner;

public class WardrobeManagerApp {

    private Wardrobe myWardrobe;
    private Scanner input;

    public WardrobeManagerApp() {
        runWardrobe();
    }

    //from TellerApp
    private void runWardrobe() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            openingMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for visiting your Wardrobe");
    }

    private void init() {
        myWardrobe = new Wardrobe();
    }

    //from TellerApp
    private void processCommand(String command) {
        if (command.equals("current")) {
            printCurrentWardrobe();
        } else if (command.equals("old")) {
            printOldWardrobe();
        } else if (command.equals("vo")) {
            printOutfits();
        } else if (command.equals("a")) {
            doAddClothing();
        } else if (command.equals("e")) {
            doEdit();
        } else if (command.equals("r")) {
            doRemove();
        } else if (command.equals("o")) {
            doOutfit();
        } else if (command.equals("f")) {
            doFind();
        } else if (command.equals("filter")) {
            filterMenu();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void openingMenu() {
        System.out.println("\nWelcome to your Wardrobe!");
        System.out.println("\tcurrent -> view all items in current wardrobe");
        System.out.println("\told -> view items in your old wardrobe");
        System.out.println("\tvo -> view all your outfits");
        System.out.println("\ta -> add a clothing item");
        System.out.println("\te -> edit a clothing item");
        System.out.println("\tr -> remove a clothing item");
        System.out.println("\to -> add an outfit");
        System.out.println("\tf -> find an item");
        System.out.println("\tfilter -> filter wardrobe on criteria");
        System.out.println("\tq -> quit");
    }

    private void doAddClothing() {
        Clothing added = selectType();

        System.out.println("You have added " + added.getName() + " to your wardrobe.");
        clothingAttributes(added);
    }

    private Clothing makeClothing(String slctn, String name, String size, String color, String brand, String fabric) {
        if (slctn.equals("bottoms")) {
            System.out.println("Length: ");
            String length = input.next().toLowerCase();
            return myWardrobe.makeBottoms(name, size, color, brand, fabric, length);
        } else if (slctn.equals("hat")) {
            return myWardrobe.makeHat(name, size, color, brand, fabric);
        } else if (slctn.equals("jacket")) {
            return myWardrobe.makeJacket(name, size, color, brand, fabric);
        } else if (slctn.equals("shirt")) {
            System.out.println("Sleeve Length: ");
            String sleeveLength = input.next().toLowerCase();
            return myWardrobe.makeShirt(name, size, color, brand, fabric, sleeveLength);
        } else if (slctn.equals("shoe")) {
            System.out.println("Model: ");
            String model = input.next().toLowerCase();
            return myWardrobe.makeShoe(name, size, color, brand, fabric, model);
        } else if (slctn.equals("sock")) {
            System.out.println("Height: ");
            String height = input.next().toLowerCase();
            return myWardrobe.makeSock(name, size, color, brand, fabric, height);
        } else {
            return myWardrobe.makeSweater(name, size, color, brand, fabric);
        }
    }

    private Clothing enterFields(String selection) {
        String name;
        String size;
        String color;
        String brand;
        String fabric;

        System.out.println("Name: ");
        name = input.next();
        name = name.toLowerCase();
        System.out.println("Size: ");
        size = input.next();
        size = size.toLowerCase();
        System.out.println("Color: ");
        color = input.next();
        color = color.toLowerCase();
        System.out.println("Brand: ");
        brand = input.next();
        brand = brand.toLowerCase();
        System.out.println("Fabric: ");
        fabric = input.next();
        fabric = fabric.toLowerCase();

        return makeClothing(selection, name, size, color, brand, fabric);

    }

    private Clothing selectType() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("bottoms")
                || selection.equals("hat")
                || selection.equals("jacket")
                || selection.equals("shirt")
                || selection.equals("shoe")
                || selection.equals("sock")
                || selection.equals("sweater"))) {
            System.out.println("\nWhat type of clothing do you want to add?");
            System.out.println("\tbottoms");
            System.out.println("\that");
            System.out.println("\tjacket");
            System.out.println("\tshirt");
            System.out.println("\tshoe");
            System.out.println("\tsock");
            System.out.println("\tsweater");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        return enterFields(selection);
    }

    private Clothing shouldAdd(String item) {
        String selection = "";  // force entry into loop
        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + item + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the " + item + ": ");
            String name = input.next();
            name = name.toLowerCase();
            return myWardrobe.findCurrentItem(name);
        } else {
            return null;
        }
    }

    private void doOutfit() {
        Clothing hatO = shouldAdd("hat");
        Clothing shirtO = shouldAdd("shirt");
        Clothing sweaterO = shouldAdd("sweater");
        Clothing jacketO = shouldAdd("jacket");
        Clothing bottomsO = shouldAdd("bottoms");
        Clothing socksO = shouldAdd("sock");
        Clothing shoeO = shouldAdd("shoe");

        System.out.println("Enter the name of this Outfit");
        String name = input.next();
        name = name.toLowerCase();

        Outfit outfit;
        outfit = myWardrobe.makeOutfit(name, hatO, shirtO, sweaterO, jacketO, bottomsO, socksO, shoeO);
        System.out.println("You have successfully added outfit " + outfit.getName() + " to your wardrobe");
        System.out.println(outfit.outfitToString());
    }

    private void doRemove() {
        System.out.println("Enter the name of the item you want to remove: ");
        String name = input.next();
        name = name.toLowerCase();
        if (myWardrobe.removeClothing(name)) {
            System.out.println(name + " was successfully removed from your wardrobe, and is now in your old wardrobe");
        } else {
            System.out.println("There was no item of name " + name + " in your wardrobe");
        }
    }

    private void clothingAttributes(Clothing item) {
        System.out.println("Attributes:");
        System.out.println("\t size: " + item.getSize());
        System.out.println("\t color: " + item.getColour());
        System.out.println("\t brand: " + item.getBrand());
        System.out.println("\t fabric: " + item.getFabric());
        System.out.println("\t collaboration: " + item.getCollaboration());
    }

    private void doFind() {
        System.out.println("Enter the name of the item you want to find: ");
        String name;
        name = input.next();
        name = name.toLowerCase();
        if (null != myWardrobe.findCurrentItem(name)) {
            Clothing item = myWardrobe.findCurrentItem(name);
            System.out.println("There was an item named " + name + " in your wardrobe.");
            clothingAttributes(item);
        } else {
            System.out.println("item of name " + name + " was not found.");
        }
    }

    private void editMenu(Clothing item) {
        String selection = "";

        while (!(selection.equals("name")
                || selection.equals("size")
                || selection.equals("color")
                || selection.equals("brand")
                || selection.equals("fabric")
                || selection.equals("collaboration"))) {
            System.out.println("what attribute would you like to edit?");
            System.out.println("\tname: " + item.getName());
            clothingAttributes(item);
            selection = input.next();
            selection = selection.toLowerCase();
        }
        completeEdit(selection, item);
    }

    private void completeEdit(String selection, Clothing item) {
        System.out.println("What do you want to change the " + selection + " to?");
        String newAtt;
        if (selection.equals("name")) {
            newAtt = input.next().toLowerCase();
            item.setName(newAtt);
        } else if (selection.equals("size")) {
            newAtt = input.next().toLowerCase();
            item.setSize(newAtt);
        } else if (selection.equals("color")) {
            newAtt = input.next().toLowerCase();
            item.setColour(newAtt);
        } else if (selection.equals("brand")) {
            newAtt = input.next().toLowerCase();
            item.setBrand(newAtt);
        } else if (selection.equals("fabric")) {
            newAtt = input.next().toLowerCase();
            item.setFabric(newAtt);
        } else {
            newAtt = input.next().toLowerCase();
            item.setCollaboration(newAtt);
        }
        System.out.println(selection + " was changed to " + newAtt);
    }

    private void doEdit() {
        System.out.println("Enter the name of the item you want to edit:");
        String name = input.next();
        name = name.toLowerCase();
        if (null != myWardrobe.findCurrentItem(name)) {
            Clothing item = myWardrobe.findCurrentItem(name);
            editMenu(item);
        } else {
            System.out.println("item of name " + name + " was not found.");
        }
    }

    private void doFilter(String selection) {
        if (selection.equals("brand")) {
            String brand;
            System.out.println("What is the name of the brand: ");
            brand = input.next().toLowerCase();
            System.out.println(myWardrobe.filterBrandWardrobe(brand));
        } else if (selection.equals("color")) {
            String color;
            System.out.println("What is the color: ");
            color = input.next().toLowerCase();
            System.out.println(myWardrobe.filterColorWardrobe(color));
        } else if (selection.equals("type")) {
            String type;
            System.out.println("What is the type: ");
            type = input.next().toLowerCase();
            System.out.println(myWardrobe.filterTypeWardrobe(type));
        } else {
            String fabric;
            System.out.println("What is the fabric: ");
            fabric = input.next().toLowerCase();
            System.out.println(myWardrobe.filterFabricWardrobe(fabric));
        }
    }

    private void filterMenu() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("brand")
                || selection.equals("color")
                || selection.equals("type")
                || selection.equals("fabric"))) {
            System.out.println("\nHow would you like to filter");
            System.out.println("\tbrand");
            System.out.println("\tcolor");
            System.out.println("\ttype");
            System.out.println("\tfabric");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        doFilter(selection);

    }

    private void printCurrentWardrobe() {
        String currentWardrobe;
        currentWardrobe = myWardrobe.currentToString();
        System.out.println(currentWardrobe);

    }

    private void printOldWardrobe() {
        String oldWardrobe;
        oldWardrobe = myWardrobe.oldToString();
        System.out.println(oldWardrobe);
    }

    private void printOutfits() {
        String outfits;
        outfits = myWardrobe.outfitsToString();
        System.out.println(outfits);
    }


}
