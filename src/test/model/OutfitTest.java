package model;

import model.clothingtypes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitTest {

    private Outfit testOutfit;
    private Hat hat;
    private Shirt shirt;
    private Sweater sweater;
    private Jacket jacket;
    private Bottoms bottoms;
    private Sock sock;
    private Shoe shoe;

    @BeforeEach
    public void runBefore() {
        hat = new Hat("OVO Basic Owl", Clothing.SMALL, "Black", "OVO", "polyester");
        shirt = new Shirt("Bape Busy Works", Clothing.MEDIUM, "White", "Bape", "Cotton", "Short");
        sweater = new Sweater("Kappa X Charms Flames", Clothing.MEDIUM, "Red", "Kappa", "Cotton");
        shoe = new Shoe("Toro Bravo", Clothing.EXTRA_LARGE, "Red", "Air Jordan", "Nubuck", "Jordan 4");
        sock = new Sock("Mortal Kombat", Clothing.LARGE, "Yellow", "Stance", "Cotton", "Tall");
        jacket = new Jacket("Bomber", Clothing.LARGE, "Brown", "Alpha Industries", "Courdoroy");
        bottoms = new Bottoms("Distressed Jeans", Clothing.LARGE, "Blue", "Le 31", "Denim", "Full");
        testOutfit = new Outfit("test outfit",hat, shirt, sweater, jacket, bottoms, sock, shoe);
    }

    @Test
    public void testOutfitToString() {
        assertEquals("\ntest outfit(hat: OVO Basic Owl shirt: Bape Busy Works sweater: Kappa X Charms Flames jacket: " +
                "Bomber bottoms: Distressed Jeans sock: Mortal Kombat shoe: Toro Bravo)", testOutfit.outfitToString());
    }

    @Test
    public void testItemName() {
        assertEquals("hat: OVO Basic Owl", testOutfit.itemName(hat));
        assertEquals("shirt: Bape Busy Works", testOutfit.itemName(shirt));
    }
}
