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

    @Test
    public void testAddSingleItem() {
        testWardrobe.addClothing(ovoOwl);
        assertEquals(1, testWardrobe.currentSize());
        assertTrue(testWardrobe.findCurrentItem("OVO Basic Owl"));
    }

    @Test
    public void testAddMultipleItems() {
        testWardrobe.addClothing(busyWorks);
        testWardrobe.addClothing(kappaCharms);
        testWardrobe.addClothing(toro);
        assertEquals(3, testWardrobe.currentSize());
        assertTrue(testWardrobe.findCurrentItem("Bape Busy Works"));
        assertTrue(testWardrobe.findCurrentItem("Kappa X Charms Flames"));
        assertTrue(testWardrobe.findCurrentItem("Toro Bravo"));

    }

    @Test
    public void testAddDuplicateItem() {
        testWardrobe.addClothing(defiant);
        testWardrobe.addClothing(defiant);
        assertEquals(2, defiant.quantity);
    }

    @Test
    public void testRemoveThere() {
        testWardrobe.addClothing(mk);
        testWardrobe.removeClothing(mk);
        assertEquals(1, testWardrobe.oldSize());
        assertTrue(testWardrobe.findOldItem("Mortal Kombat"));
    }

    @Test
    public void testRemoveMultipleThere() {
        testWardrobe.addClothing(stormtrooper);
        testWardrobe.addClothing(blueJean);
        testWardrobe.removeClothing(stormtrooper);
        testWardrobe.removeClothing(blueJean);
        assertEquals(2, testWardrobe.oldSize());
        assertTrue(testWardrobe.findOldItem("Stormtrooper"));
        assertTrue(testWardrobe.findOldItem("Distressed Jeans"));
    }

    @Test
    public void testRemoveNotThere() {
        assertFalse(testWardrobe.removeClothing(bomberAlpha));
    }

    @Test
    public void testMakeOutfit() {
        testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, mk, toro);
        assertEquals(1, testWardrobe.outfitSize());
        assertTrue(testWardrobe.findOutfit("Test Outfit"));
    }

    @Test
    public void testMakeMultipleOutfits() {
        testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, stormtrooper, defiant);
        testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, mk, toro);
        assertEquals(2, testWardrobe.outfitSize());
        assertTrue(testWardrobe.findOutfit("Test Outfit"));
        assertTrue(testWardrobe.findOutfit("Tester"));
    }

    @Test
    public void testFilterBrand() {
        testWardrobe.addClothing(busyWorks);
        testWardrobe.addClothing(kappaCharms);
        testWardrobe.addClothing(toro);
        assertEquals(1, testWardrobe.filterBrandWardrobe("Bape").size());
    }

    @Test
    public void testFilterColor() {
        testWardrobe.addClothing(busyWorks);
        testWardrobe.addClothing(defiant);
        testWardrobe.addClothing(ovoOwl);
        testWardrobe.addClothing(stormtrooper);
        testWardrobe.addClothing(bomberAlpha);
        assertEquals(3, testWardrobe.filterColorWardrobe("White").size());
    }

    @Test
    public void testFilterType() {
        testWardrobe.addClothing(busyWorks);
        testWardrobe.addClothing(defiant);
        testWardrobe.addClothing(ovoOwl);
        testWardrobe.addClothing(stormtrooper);
        testWardrobe.addClothing(bomberAlpha);
        testWardrobe.addClothing(octopus);
        assertEquals(2, testWardrobe.filterTypeWardrobe("Shirt").size());
    }

    @Test
    public void testFilterFabric() {
        testWardrobe.addClothing(busyWorks);
        testWardrobe.addClothing(defiant);
        testWardrobe.addClothing(ovoOwl);
        testWardrobe.addClothing(stormtrooper);
        testWardrobe.addClothing(bomberAlpha);
        testWardrobe.addClothing(octopus);
        assertEquals(3, testWardrobe.filterFabricWardrobe("Cotton").size());
    }



}