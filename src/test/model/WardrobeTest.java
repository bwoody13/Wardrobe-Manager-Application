package model;

import model.clothingtypes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WardrobeTest {

    private Wardrobe testWardrobe;
    private Hat ovoOwl;
    private Shirt busyWorks;
    private Shirt octopus;
    private Sweater kappaCharms;
    private Shoe toro;
    private Shoe defiant;
    private Sock mk;
    private Sock stormtrooper;
    private Jacket bomberAlpha;
    private Bottoms blueJean;

    @BeforeEach
    public void runBefore() {
        testWardrobe = new Wardrobe();
        ovoOwl = new Hat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        busyWorks = new Shirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        octopus = new Shirt("Supreme Octopus", Clothing.LARGE, "Red", "Bape", "Cotton", "Short");
        kappaCharms = new Sweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
        toro = new Shoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
        kappaCharms.setHasHood(true);
        kappaCharms.setCollaboration("Charms");
        defiant = new Shoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        mk = new Sock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
        stormtrooper = new Sock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        bomberAlpha = new Jacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        bomberAlpha.setHasZipper(true);
        blueJean = new Bottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
    }
    //Make methods are tested implicitly throughout tests
    @Test
    public void testAddSingleItem() {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        assertEquals(1, testWardrobe.currentSize());
    }

    @Test
    public void testAddMultipleItems() {
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
        testWardrobe.makeShoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
        assertEquals(3, testWardrobe.currentSize());
    }

    @Test
    public void testAddDuplicateItem() {
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals(1, testWardrobe.currentSize());
    }

    @Test
    public void testRemoveThere() {
        testWardrobe.makeSock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
        testWardrobe.removeClothing("Mortal Kombat");
        assertEquals(1, testWardrobe.oldSize());

    }

    @Test
    public void testRemoveMultipleThere() {
        testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        testWardrobe.makeBottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
        testWardrobe.removeClothing("Stormtrooper");
        testWardrobe.removeClothing("Distressed Jeans");
        assertEquals(2, testWardrobe.oldSize());

    }

    @Test
    public void testRemoveNotThere() {
        assertFalse(testWardrobe.removeClothing("Bomber"));
    }

    @Test
    public void testMakeOutfit() {
        testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
        assertEquals(1, testWardrobe.outfitSize());
    }

    @Test
    public void testMakeMultipleOutfits() {
        testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
        testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
        assertEquals(2, testWardrobe.outfitSize());

    }

    @Test
    public void testFilterBrand() {
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
        testWardrobe.makeShoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
        assertEquals("\nShirt named Bape Busy Works made by Bape", testWardrobe.filterBrandWardrobe("Bape"));
    }

    @Test
    public void testFilterColor() {
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        assertEquals("\nShirt named Bape Busy Works made by Bape\n" +
                "Shoe named Defiant made by Air Jordan\n" +
                "Sock named Stormtrooper made by Stance", testWardrobe.filterColorWardrobe("White"));
    }

    @Test
    public void testFilterType() {
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        testWardrobe.makeShirt("Supreme Octopus", Clothing.LARGE, "Red", "Bape", "Cotton", "Short");
        assertEquals("\nShirt named Bape Busy Works made by Bape\n" +
                "Shirt named Supreme Octopus made by Bape", testWardrobe.filterTypeWardrobe("Shirt"));
    }

    @Test
    public void testFilterFabric() {
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        testWardrobe.makeShirt("Supreme Octopus", Clothing.LARGE, "Red", "Bape", "Cotton", "Short");
        assertEquals("\nShirt named Bape Busy Works made by Bape\n" +
                "Sock named Stormtrooper made by Stance\n" +
                "Shirt named Supreme Octopus made by Bape", testWardrobe.filterFabricWardrobe("Cotton"));
    }

    @Test
    public void testFindCurrentThere(){
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals("Bape Busy Works", testWardrobe.findCurrentItem("Bape Busy Works").name);
    }

    @Test
    public void testFindCurrentNotThere(){
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals(null, testWardrobe.findCurrentItem("Toro Bravo"));
    }

    @Test
    public void testCurrentToString() {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals("\nHat named OVO Basic Owl made by OVO\n" +
                "Shirt named Bape Busy Works made by Bape\n" +
                "Shoe named Defiant made by Air Jordan", testWardrobe.currentToString());
    }

    @Test
    public void testOldToString() {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.removeClothing("OVO Basic Owl");
        testWardrobe.removeClothing("Bape Busy Works");
        testWardrobe.removeClothing("Defiant");
        assertEquals("\nHat named OVO Basic Owl made by OVO\n" +
                "Shirt named Bape Busy Works made by Bape\n" +
                "Shoe named Defiant made by Air Jordan", testWardrobe.oldToString());
    }

    @Test
    public void testOutfitsToString() {
        testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
        testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
        assertEquals("\nTester\nTest Outfit", testWardrobe.outfitsToString());
    }

}