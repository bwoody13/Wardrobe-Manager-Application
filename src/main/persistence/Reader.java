package persistence;

import model.Clothing;
import model.Wardrobe;
import model.clothingtypes.Hat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read/deserialize wardrobe data from a file
// from TellerApp
public class Reader {
    public static final String DELIMITER = ",";
    private static ObjectInputStream inputStream;

    public static Wardrobe readWardrobe(FileInputStream file) throws IOException, ClassNotFoundException {
        inputStream = new ObjectInputStream(file);
        Wardrobe found = (Wardrobe) inputStream.readObject();
        return found;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public static List<Clothing> readClothing(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private static List<Clothing> parseContent(List<String> fileContent) {
        List<Clothing> clothing = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            clothing.add(parseClothing(lineComponents));
        }
        return clothing;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private static Clothing parseClothing(List<String> components) {
        int nextId = Integer.parseInt(components.get(0));
        int id = Integer.parseInt(components.get(1));
        String name = components.get(2); // maybe wrong
        // add fields
        return null;
    }

}
