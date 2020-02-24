package persistence;

import java.io.*;
import java.io.Serializable;

// A writer that can write/serialize wardrobe data to a file
// From TellerApp
public class Writer {
    private ObjectOutputStream outputStream;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Creates the file destination for objects to be serialized to
    public Writer(FileOutputStream file) throws IOException {
        outputStream = new ObjectOutputStream(file);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: writes the object to the output stream
    public void write(Serializable serializable) throws IOException {
        outputStream.writeObject(serializable);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: closes off the output stream
    public void close() throws IOException {
        outputStream.close();
    }
}
