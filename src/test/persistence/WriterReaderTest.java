package persistence;

import model.Clothing;
import model.Wardrobe;
import model.clothingtypes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterReaderTest {

    private static final String TEST_FILE = "./data/testWardrobes.txt";
    private Writer testWriter;
    private Wardrobe testWardrobe;
    Hat ovoOwl = new Hat("ovo_owl", "s", "black", "ovo", "polyester");
    Shirt busyWorks = new Shirt("bape_busy_works", "m", "white", "bape", "cotton", "short");
    Shirt octopus = new Shirt("supreme_octopus", "l", "red", "bape", "cotton", "short");
    Sweater kappaCharms = new Sweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
    Shoe defiant = new Shoe("defiant", "xl", "white", "air_jordan", "leather", "jordan_1");
    Sock stormtrooper = new Sock("stormtrooper", "l", "white", "stance", "cotton", "tall");
    Jacket bomberAlpha = new Jacket("bomber", "l'", "brown", "alpha_industries", "corduroy");
    Bottoms blueJean = new Bottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");

    @BeforeEach
    void runBefore() throws IOException {
        testWriter = new Writer(new FileOutputStream(TEST_FILE));
        testWardrobe = new Wardrobe();
        testWardrobe.makeShirt("bape_busy_works", "m", "white", "bape", "cotton", "short");
        testWardrobe.makeShoe("defiant", "xl", "white", "air_jordan", "leather", "jordan_1");
        testWardrobe.makeHat("ovo_owl", "s", "black", "ovo", "polyester");
        testWardrobe.makeSock("stormtrooper", "l", "white", "stance", "cotton", "tall");
        testWardrobe.makeJacket("bomber", "l'", "brown", "alpha_industries", "corduroy");
        testWardrobe.makeShirt("supreme_octopus", "l", "red", "bape", "cotton", "short");
        testWardrobe.removeClothing("bomber");
        testWardrobe.removeClothing("supreme_octopus");
        testWardrobe.makeOutfit("test_outfit", ovoOwl, busyWorks, kappaCharms,null, blueJean, stormtrooper, defiant);
    }

    @Test
    void testWriteWardrobe() {
        try {
            testWriter.write(testWardrobe);
            testWriter.close();

            Wardrobe readWardrobe = Reader.readWardrobe(new FileInputStream(TEST_FILE));
            assertEquals(4, readWardrobe.currentSize());
            assertNotEquals(null, readWardrobe.findCurrentItem("defiant"));
            assertEquals(2, readWardrobe.oldSize());
            assertNotEquals(null, readWardrobe.findOldItem("bomber"));
            assertEquals(1, readWardrobe.outfitSize());
            assertNotEquals(null, readWardrobe.findOutfit("test_outfit"));
        } catch (IOException | ClassNotFoundException e) {
            fail("IOException or ClassNotFoundException should not have been throw");
        }
    }

}
