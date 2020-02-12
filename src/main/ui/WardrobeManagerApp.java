package ui;

import model.Clothing;
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
        String command = null;
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
        System.out.println("\tr -> remove a clothing item");
        System.out.println("\to -> add an outfit");
        System.out.println("\tf -> find an item");
        System.out.println("\tfilter -> filter wardrobe on criteria");
        System.out.println("\tq -> quit");
    }

    private void doAddClothing() {
        Clothing added = selectType();

        System.out.println("You have added " + added.getName() + " to your wardrobe.");
    }

    private Clothing selectType() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("bottoms") || selection.equals("hat") || selection.equals("jacket") || selection.equals("shirt") || selection.equals("shoe") || selection.equals("sock") || selection.equals("sweater"))) {
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

        if (selection.equals("bottoms")) {
            System.out.println("Length: ");
            String length;
            length = input.next();
            length = length.toLowerCase();
            return myWardrobe.makeBottoms(name, size, color, brand, fabric, length);
        } else if (selection.equals("hat")) {
            return myWardrobe.makeHat(name, size, color, brand, fabric);
        } else if (selection.equals("jacket")) {
            return myWardrobe.makeJacket(name, size, color, brand, fabric);
        } else if (selection.equals("shirt")) {
            System.out.println("Sleeve Length: ");
            String sleeveLength;
            sleeveLength = input.next();
            sleeveLength = sleeveLength.toLowerCase();
            return myWardrobe.makeShirt(name, size, color, brand, fabric, sleeveLength);
        } else if (selection.equals("shoe")) {
            System.out.println("Model: ");
            String model;
            model = input.next();
            model = model.toLowerCase();
            return myWardrobe.makeShoe(name, size, color, brand, fabric, model);
        } else if (selection.equals("sock")) {
            System.out.println("Height: ");
            String height;
            height = input.next();
            height = height.toLowerCase();
            return myWardrobe.makeSock(name, size, color, brand, fabric, height);
        } else {
            return myWardrobe.makeSweater(name, size, color, brand, fabric);
        }
    }


    private void doOutfit() {
        String selection = "";  // force entry into loop
        String next;
        next = "hat";
        Clothing hatO = null;
        Clothing shirtO = null;
        Clothing sweaterO = null;
        Clothing jacketO = null;
        Clothing bottomsO = null;
        Clothing socksO = null;
        Clothing shoeO = null;

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the hat: ");
            String hat = input.next();
            hat = hat.toLowerCase();
            hatO = myWardrobe.findCurrentItem(hat);
        }
        next = "shirt";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("y")) {
            System.out.println("\nEnter name of the shirt: ");
            String shirt = input.next();
            shirt = shirt.toLowerCase();
            shirtO = myWardrobe.findCurrentItem(shirt);
        }

        next = "sweater";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the sweater: ");
            String sweater = input.next();
            sweater = sweater.toLowerCase();
            sweaterO = myWardrobe.findCurrentItem(sweater);
        }

        next = "jacket";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the jacket: ");
            String jacket = input.next();
            jacket = jacket.toLowerCase();
            jacketO = myWardrobe.findCurrentItem(jacket);
        }

        next = "bottoms";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the bottoms: ");
            String bottoms = input.next();
            bottoms = bottoms.toLowerCase();
            bottomsO = myWardrobe.findCurrentItem(bottoms);
        }
        next = "sock";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the sock: ");
            String sock = input.next();
            sock = sock.toLowerCase();
            socksO = myWardrobe.findCurrentItem(sock);
        }
        next = "shoe";
        selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Do you want to add a " + next + "?");
            System.out.println("\ty or n");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            System.out.println("\nEnter name of the shoe: ");
            String shoe = input.next();
            shoe = shoe.toLowerCase();
            shoeO = myWardrobe.findCurrentItem(shoe);
        }
        System.out.println("Enter the name of this Outfit");
        String name = input.next();
        name = name.toLowerCase();

        String outfitName;
        outfitName = myWardrobe.makeOutfit(name, hatO, shirtO, sweaterO, jacketO, bottomsO, socksO, shoeO).getName();
        System.out.println("You have successfully added outfit " + outfitName + " to your wardrobe");
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

    private void doFind() {
        System.out.println("Enter the name of the item you want to find: ");
        String name;
        name = input.next();
        name = name.toLowerCase();
        if (null != myWardrobe.findCurrentItem(name)) {
            System.out.println("There was an item named " + name + " in your wardrobe.");
        } else {
            System.out.println("item of name " + name + " was not found.");
        }
    }

    private void filterMenu() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("brand") || selection.equals("color") || selection.equals("type") || selection.equals("fabric"))) {
            System.out.println("\nHow would you like to filter");
            System.out.println("\tbrand");
            System.out.println("\tcolor");
            System.out.println("\ttype");
            System.out.println("\tfabric");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("brand")) {
            String brand;
            System.out.println("What is the name of the brand: ");
            brand = input.next();
            brand = brand.toLowerCase();
            System.out.println(myWardrobe.filterBrandWardrobe(brand));
        } else if (selection.equals("color")) {
            String color;
            System.out.println("What is the color: ");
            color = input.next();
            color = color.toLowerCase();
            System.out.println(myWardrobe.filterColorWardrobe(color));
        } else if (selection.equals("type")) {
            String type;
            System.out.println("What is the type: ");
            type = input.next();
            type = type.toLowerCase();
            System.out.println(myWardrobe.filterTypeWardrobe(type));
        } else {
            String fabric;
            System.out.println("What is the color: ");
            fabric = input.next();
            fabric = fabric.toLowerCase();
            System.out.println(myWardrobe.filterFabricWardrobe(fabric));
        }

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
