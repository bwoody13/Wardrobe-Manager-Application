package model;

import model.clothingtypes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClothingTest {

    private Bottoms b;
    private Hat h;
    private Jacket j;
    private Shirt shirt;
    private Shoe shoe;
    private Sock sock;
    private Sweater sweater;

    @BeforeEach
    void runBefore() {
        b = new Bottoms("test", "m", "white", "me", "cotton", "32");
        h = new Hat("test", "s", "white", "me", "cotton");
        j = new Jacket("test", "s", "white", "me", "leather");
        shirt = new Shirt("test", "m", "white", "me", "cotton", "short");
        shoe = new Shoe("test", "12", "white", "me", "cotton", "4");
        sock = new Sock("test", "l", "white", "me", "cotton", "mid");
        sweater = new Sweater("test", "s", "white", "me", "cotton");
    }

    @Test
    void testClothingGetters() {
        assertEquals("test", b.getName());
        assertEquals("m",shirt.getSize());
        assertEquals("white", j.getColour());
        assertEquals("me",h.getBrand());
        assertEquals("cotton",sweater.getFabric());
        assertEquals("",shirt.getCollaboration());
    }

    @Test
    void testClothingSetters() {
        b.setName("new");
        assertEquals("new",b.getName());
        h.setSize("adjustable");
        assertEquals("adjustable",h.getSize());
        j.setColour("brown");
        assertEquals("brown",j.getColour());
        shirt.setBrand("eye");
        assertEquals("eye",shirt.getBrand());
        shoe.setFabric("leather");
        assertEquals("leather",shoe.getFabric());
        sock.setCollaboration("you");
        assertEquals("you",sock.getCollaboration());
    }

    @Test
    void testUniqueSettersGetters() {
        assertEquals("32",b.getLength());
        b.setLength("30");
        assertEquals("30",b.getLength());
        assertEquals("short",shirt.getSleeveLength());
        shirt.setSleeveLength("long");
        assertEquals("long",shirt.getSleeveLength());
        assertEquals("4",shoe.getModel());
        shoe.setModel("6");
        assertEquals("6",shoe.getModel());
        assertEquals("mid",sock.getHeight());
        sock.setHeight("low");
        assertEquals("low",sock.getHeight());
    }

}
