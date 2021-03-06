package model;

import exceptions.InWardrobeException;
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
        kappaCharms.setCollaboration("Charms");
        defiant = new Shoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        mk = new Sock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
        stormtrooper = new Sock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
        bomberAlpha = new Jacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        blueJean = new Bottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
    }
    //Make methods are tested implicitly throughout tests
    @Test
    public void testAddSingleItem() {
        try {
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        } catch (InWardrobeException e) {
            fail("should not have thrown");
        }
        assertEquals(1, testWardrobe.currentSize());
    }

    @Test
    public void testAddMultipleItems() {
        try {
            Clothing c = testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            Clothing c2 = testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
            Clothing c3 = testWardrobe.makeShoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
            Clothing c4 = testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            Clothing c5 = testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
            Clothing c6 = testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            Clothing c7 = testWardrobe.makeBottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
            assertEquals(7, testWardrobe.currentSize());
            assertEquals("Bape Busy Works", c.getName());
            assertEquals("Kappa X Charms Flames", c2.getName());
            assertEquals("Toro Bravo", c3.getName());
            assertEquals("OVO Basic Owl", c4.getName());
            assertEquals("Stormtrooper", c5.getName());
            assertEquals("Bomber", c6.getName());
            assertEquals("Distressed Jeans", c7.getName());
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testAddDuplicateItem() {
        try {
            testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
            testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
            fail("should have thrown");
        } catch (Exception e) {
        }
        try {
            testWardrobe.makeSock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
            testWardrobe.makeSock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
            fail("should have thrown");
        } catch (InWardrobeException ex) {
        }
        try {
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            fail("should have thrown");
        } catch (Exception e) {
        }
        try {
            testWardrobe.makeBottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
            testWardrobe.makeBottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
            fail("should have thrown");
        } catch (Exception e) {
        }
        try {
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            fail("should have thrown");
        } catch (Exception e) {
        }
        try {
            testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Bape", "Cotton");
            testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Bape", "Cotton");
            fail("should have thrown");
        } catch (Exception e) {
        }
        try {
            testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            fail("should have thrown");
        } catch (Exception e) {
        }
        assertEquals(7, testWardrobe.currentSize());
    }

    @Test
    public void testRemoveThere() {
        try {
            testWardrobe.makeSock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
        } catch (InWardrobeException e) {
            fail("should not have thrown");
        }
        testWardrobe.removeClothing("mortal kombat");
        assertEquals(1, testWardrobe.oldSize());

    }

    @Test
    public void testRemoveMultipleThere() {
        try {
            testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
            testWardrobe.makeBottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
            testWardrobe.removeClothing("stormtrooper");
            testWardrobe.removeClothing("distressed jeans");
            assertEquals(2, testWardrobe.oldSize());
        } catch (Exception e) {
            fail("should not have thrown");
        }

    }

    @Test
    public void testRemoveNotThere() {
        assertFalse(testWardrobe.removeClothing("Bomber"));
    }

    @Test
    public void testMakeOutfit() {
        try {
            testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
        } catch (InWardrobeException e) {
            fail("should not have thrown");
        }
        assertEquals(1, testWardrobe.outfitSize());
    }

    @Test
    public void testMakeMultipleOutfits() {
        try {
            testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
            testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
            assertEquals(2, testWardrobe.outfitSize());
        } catch (Exception e) {
            fail("sjould not have thrown");
        }

    }

    @Test
    void testFindOutfitNotThere() {
        try {
            testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
            assertNull(testWardrobe.findOutfit("na"));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testFilterBrand() {
        try {
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            testWardrobe.makeSweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Bape", "Cotton");
            testWardrobe.makeShoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
            assertEquals("\nshirt named Bape Busy Works made by Bape" +
                    "\nsweater named Kappa X Charms Flames made by Bape", testWardrobe.filterBrandWardrobe("bape"));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testFilterColor() {
        try {
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
            testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            assertEquals("\nshirt named Bape Busy Works made by Bape\n" +
                    "shoe named Defiant made by Air Jordan\n" +
                    "sock named Stormtrooper made by Stance", testWardrobe.filterColorWardrobe("white"));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testFilterType() {
        try {
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
            testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "Cotton", "Tall");
            testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            testWardrobe.makeShirt("Supreme Octopus", Clothing.LARGE, "Red", "Bape", "Cotton", "Short");
            assertEquals("\nshirt named Bape Busy Works made by Bape\n" +
                    "shirt named Supreme Octopus made by Bape", testWardrobe.filterTypeWardrobe("shirt"));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testFilterFabric() {
        try {
            testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "cotton", "Short");
            testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
            testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
            testWardrobe.makeSock("Stormtrooper", Clothing.LARGE, "White", "Stance", "cotton", "Tall");
            testWardrobe.makeJacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
            testWardrobe.makeShirt("Supreme Octopus", Clothing.LARGE, "Red", "Bape", "cotton", "Short");
            assertEquals("\nshirt named Bape Busy Works made by Bape\n" +
                    "sock named Stormtrooper made by Stance\n" +
                    "shirt named Supreme Octopus made by Bape", testWardrobe.filterFabricWardrobe("cotton"));
        } catch (Exception e) {
            fail("should not have thrown");
        }

}

    @Test
    public void testFindCurrentThere(){
        try {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals("Bape Busy Works", testWardrobe.findCurrentItem("bape busy works").name);
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testFindCurrentNotThere(){
        try {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertNull(testWardrobe.findCurrentItem("Toro Bravo"));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testCurrentToString() {
        try {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        assertEquals("\nhat named OVO Basic Owl made by OVO\n" +
                "shirt named Bape Busy Works made by Bape\n" +
                "shoe named Defiant made by Air Jordan", testWardrobe.currentToString());
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testOldToString() {
        try {
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        testWardrobe.makeShoe("Defiant", Clothing.EXTRA_LARGE, "White", "Air Jordan", "Leather", "Jordan 1");
        testWardrobe.removeClothing("OVO Basic Owl");
        testWardrobe.removeClothing("Bape Busy Works");
        testWardrobe.removeClothing("Defiant");
        assertEquals("\nhat named OVO Basic Owl made by OVO\n" +
                "shirt named Bape Busy Works made by Bape\n" +
                "shoe named Defiant made by Air Jordan", testWardrobe.oldToString());
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    public void testOutfitsToString() {
        try {
            testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
            testWardrobe.makeOutfit("Test Outfit", null, octopus, null, bomberAlpha, blueJean, mk, toro);
            assertEquals("\nTester(hat: OVO Basic Owl shirt: Bape Busy Works sweater: Kappa X Charms Flames  bottoms: Distressed Jeans sock: Stormtrooper shoe: Defiant)\n" +
                    "Test Outfit( shirt: Supreme Octopus  jacket: Bomber bottoms: Distressed Jeans sock: Mortal Kombat shoe: Toro Bravo)", testWardrobe.outfitsToString());
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

    @Test
    void testDuplicateOutfitName() {
        try{
            testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
            testWardrobe.makeOutfit("Tester", null, octopus, null, bomberAlpha, blueJean, mk, toro);
            fail("should have thrown exeception");
        } catch (Exception e) {
            //good
        }
        assertEquals(1,testWardrobe.outfitSize());
    }

    @Test
    void testGetLists() {
        try {
        testWardrobe.makeOutfit("Tester", ovoOwl, busyWorks, kappaCharms, null, blueJean, stormtrooper, defiant);
        assertTrue(testWardrobe.getOutfits().contains(testWardrobe.findOutfit("tester")));
        testWardrobe.makeHat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        testWardrobe.makeShirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        assertTrue(testWardrobe.getCurrentWardrobe().contains(testWardrobe.findCurrentItem("ovo basic owl")));
        testWardrobe.removeClothing("Bape Busy Works");
        assertTrue(testWardrobe.getOldWardrobe().contains(testWardrobe.findOldItem("bape busy works")));
        } catch (Exception e) {
            fail("should not have thrown");
        }
    }

}