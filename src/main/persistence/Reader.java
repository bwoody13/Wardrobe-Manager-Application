package persistence;

import model.Wardrobe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


// A reader that can read/deserialize wardrobe data from a file
// from TellerApp
public class Reader {

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: reads a wardrobe from a file, and returns the object if found
    public static Wardrobe readWardrobe(FileInputStream file) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(file);
        return (Wardrobe) inputStream.readObject();
    }



}
