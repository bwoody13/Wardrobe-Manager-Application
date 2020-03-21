package ui;

import model.Clothing;
import model.Outfit;
import model.Wardrobe;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WardrobeManagerProgram extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final ImageIcon BOTTOMS = scaleImage("./data/images/Bottoms.png");
    private static final ImageIcon HAT = scaleImage("./data/images/Hat.jpg");
    private static final ImageIcon JACKET = scaleImage("./data//images/Jacket.jpg");
    private static final ImageIcon SHIRT = scaleImage("./data/images/Shirt.png");
    private static final ImageIcon SHOE = scaleImage("./data/images/Shoe.png");
    private static final ImageIcon SOCK = scaleImage("./data/images/Sock.png");
    private static final ImageIcon SWEATER = scaleImage("./data/images/Sweater.png");
    private static final ImageIcon OUTFIT = scaleImage("./data/images/Outfit.png");
    private static final String WARDROBE_FILE = "./data/wardrobe.txt";
    private static final String SHIRT_AUDIO_FILE = "./data/sounds/Shirt Added.wav";
    private static final String BOTTOMS_AUDIO_FILE = "./data/sounds/Bottoms Added.wav";
    private static final String HAT_AUDIO_FILE = "./data/sounds/Hat Added.wav";
    private static final String JACKET_AUDIO_FILE = "./data/sounds/Jacket Added.wav";
    private static final String SHOE_AUDIO_FILE = "./data/sounds/Shoe Added.wav";
    private static final String SOCK_AUDIO_FILE = "./data/sounds/Sock Added.wav";
    private static final String SWEATER_AUDIO_FILE = "./data/sounds/Sweater Added.wav";
    private static final String OUTFIT_AUDIO_FILE = "./data/sounds/Outfit Added.wav";


    private Wardrobe myWardrobe;
    private JMenuBar menuBar;
    private JMenu clothing;
    private JMenu outfit;
    private JMenu wardrobe;
    private JMenu file;
    private JList<String> clothingStringList;
    private List<Clothing> clothingList;
    private JList<String> outfitStringList;
    private List<Outfit> outfits;
    private JPanel buttonPane;
    private JButton openButton;
    private boolean isOutfitsDisplayed;

    //The wardrobe manager application in GUI form
    public WardrobeManagerProgram() {
        super("Wardrobe Manager");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        isOutfitsDisplayed = false;
        enlargeFont();
        createMenus();
        initializeData();
        createClothingList(myWardrobe.getCurrentWardrobe());
    }

    //https://stackoverflow.com/questions/16372241/run-function-on-jframe-close
    //MODIFIES: this
    //EFFECTS: closes the window and saves the wardrobe
    private void exitProcedure() {
        saveWardrobe();
        dispose();
        System.exit(0);
    }

    private void enlargeFont() {
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 24));
        UIManager.put("MenuBar.font", new Font("System", Font.PLAIN,20));
        UIManager.put("MenuItem.font", new Font("System", Font.PLAIN,20));
        UIManager.put("Menu.font", new Font("System", Font.PLAIN,20));
        UIManager.put("TextField.font", new Font("System", Font.PLAIN,20));
    }

    //MODIFIES: this
    //EFFECTS: creates the current clothing list and adds to main frame
    public void createClothingList(List<Clothing> toAdd) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        clothingList = new ArrayList<>();
        for (Clothing c : toAdd) {
            listModel.addElement(c.getType().toUpperCase() + " - Name: " + c.getName() + ", Made by: " + c.getBrand());
            clothingList.add(c);
        }
        JScrollPane listScroller = new JScrollPane();
        clothingStringList = new JList<>(listModel);
        clothingStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clothingStringList.setFont(new Font("System", Font.PLAIN, 24));
        listScroller.setViewportView(clothingStringList);
        clothingStringList.setLayoutOrientation(JList.VERTICAL);
        add(listScroller);
        addButtonClothing();
        setVisible(true);
    }

    //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    //https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing/5911621 <- for all button actions
    //MODIFIES: this
    //EFFECTS: adds a button to bottom panel that opens the selected clothing item
    private void addButtonClothing() {
        openButton = new JButton(new AbstractAction("Open Item") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = clothingStringList.getSelectedIndex();
                Clothing selectedItem = clothingList.get(index);
                displayItem(selectedItem);
            }
        });
        openButton.setFont(new Font("System", Font.PLAIN, 24));
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(openButton);
        add(buttonPane, BorderLayout.PAGE_END);
        openButton.setVisible(true);
        buttonPane.setVisible(true);
    }

    //https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
    //MODIFIES: this
    //EFFECTS: creates the menu bar with items
    public void createMenus() {
        menuBar = new JMenuBar();
        clothing = new JMenu("Clothing");
        outfit = new JMenu("Outfit");
        wardrobe = new JMenu("Wardrobe");
        file = new JMenu("File");
        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(clothing);
        menuBar.add(outfit);
        menuBar.add(wardrobe);
        fileMenu();
        JMenu addClothing = new JMenu("Add Clothing");
        clothingMenu(addClothing);
        outfitMenu();
        wardrobeMenu();
    }

    //MODIFIES: this
    //EFFECTS: adds items to file menu
    private void fileMenu() {
        JMenuItem save = new JMenuItem(new AbstractAction("Save Wardrobe") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWardrobe();
            }
        });
        file.add(save);
        JMenuItem load = new JMenuItem(new AbstractAction("Load Wardrobe") {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWardrobe();
            }
        });
        file.add(load);
    }

    //MODIFIES: this
    //EFFECTS: adds items to clothing menu
    private void clothingMenu(JMenu addClothing) {
        clothing.add(addClothing);
        addClothingSubmenu(addClothing);
        JMenuItem editClothing = new JMenuItem(new AbstractAction("Edit Clothing") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doEdit();
            }
        });
        clothing.add(editClothing);
        JMenuItem removeClothing = new JMenuItem(new AbstractAction("Remove Clothing") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRemove();
            }
        });
        clothing.add(removeClothing);
        JMenuItem findClothing = new JMenuItem(new AbstractAction("Find Clothing") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFind();
            }
        });
        clothing.add(findClothing);
    }

    //MODIFIES: this
    //EFFECTS: adds items to addClothing submenu
    private void addClothingSubmenu(JMenu addClothing) {
        JMenuItem addBottoms = new JMenuItem(new AbstractAction("Bottoms") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBottoms();
            }
        });
        addClothing.add(addBottoms);
        JMenuItem addHat = new JMenuItem(new AbstractAction("Hat") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHat();
            }
        });
        addClothing.add(addHat);
        JMenuItem addJacket = new JMenuItem(new AbstractAction("Jacket") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJacket();
            }
        });
        addClothing.add(addJacket);
        continueAddClothingSubMenu(addClothing);
    }

    //MODIFIES: this
    //EFFECTS: adds items to addClothing submenu
    private void continueAddClothingSubMenu(JMenu addClothing) {
        JMenuItem addShirt = new JMenuItem(new AbstractAction("Shirt") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addShirt();
            }
        });
        addClothing.add(addShirt);
        JMenuItem addShoe = new JMenuItem(new AbstractAction("Shoe") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addShoe();
            }
        });
        addClothing.add(addShoe);
        continueTwoAddClothingSubMenu(addClothing);
    }

    //MODIFIES: this
    //EFFECTS: adds items to addClothing submenu
    private void continueTwoAddClothingSubMenu(JMenu addClothing) {
        JMenuItem addSock = new JMenuItem(new AbstractAction("Sock") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSock();
            }
        });
        addClothing.add(addSock);
        JMenuItem addSweater = new JMenuItem(new AbstractAction("Sweater") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSweater();
            }
        });
        addClothing.add(addSweater);
    }

    //MODIFIES: this
    //EFFECTS: adds items to outfit menu
    private void outfitMenu() {
        JMenuItem addOutfit = new JMenuItem(new AbstractAction("Add Outfit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOutfit();
            }
        });
        outfit.add(addOutfit);
        JMenuItem findOutfit = new JMenuItem(new AbstractAction("Find Outfit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                findOutfit();
            }
        });
        outfit.add(findOutfit);
    }

    //MODIFIES: this
    //EFFECTS: adds items to wardrobe menu
    private void wardrobeMenu() {
        JMenuItem currentWardrobe = new JMenuItem(new AbstractAction("Display Current Wardrobe") {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClothingList();
            }
        });
        wardrobe.add(currentWardrobe);
        JMenuItem oldWardrobe = new JMenuItem(new AbstractAction("Display Old Wardrobe") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOldClothingList();
            }
        });
        wardrobe.add(oldWardrobe);
        JMenuItem outfits = new JMenuItem(new AbstractAction("Display Outfits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOutfitList();
            }
        });
        wardrobe.add(outfits);
        JMenu filterWardrobe = new JMenu("Filter Wardrobe by");
        filterSubmenu(filterWardrobe);
    }

    //MODIFIES: this, filterWardrobe
    //EFFECTS: creates the filter submenu
    private void filterSubmenu(JMenu filterWardrobe) {
        wardrobe.add(filterWardrobe);
        JMenuItem filterBrand = new JMenuItem(new AbstractAction("Brand") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFilterBrand();
            }
        });
        filterWardrobe.add(filterBrand);
        JMenuItem filterColor = new JMenuItem(new AbstractAction("Color") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFilterColor();
            }
        });
        filterWardrobe.add(filterColor);
        filterSubmenuContinued(filterWardrobe);
    }

    //MODIFIES: this, filterWardrobe
    //EFFECTS: continues to creates the filter submenu
    private void filterSubmenuContinued(JMenu filterWardrobe) {
        JMenuItem filterType = new JMenuItem(new AbstractAction("Type") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFilterType();
            }
        });
        filterWardrobe.add(filterType);
        JMenuItem filterFabric = new JMenuItem(new AbstractAction("Fabric") {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFilterFabric();
            }
        });
        filterWardrobe.add(filterFabric);
    }

    //MODIFIES: this
    //EFFECTS: creates the wardrobe and loads the existing one form file (if any)
    private void initializeData() {
        myWardrobe = new Wardrobe();
        loadWardrobe();
    }


    private void informationMessage(String message, String title) {
        JOptionPane.showMessageDialog(WardrobeManagerProgram.this, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(WardrobeManagerProgram.this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: loads wardrobe from WARDROBE_FILE, if that file exists;
    // otherwise initializes wardrobe with default values
    private void loadWardrobe() {
        try {
            myWardrobe = Reader.readWardrobe(new FileInputStream(WARDROBE_FILE));
            informationMessage("Successfully loaded a wardrobe from " + WARDROBE_FILE, "File Loaded");
        } catch (IOException | ClassNotFoundException e) {
            errorMessage("Unable to load a wardrobe from " + WARDROBE_FILE);
        }
    }

    //EFFECTS: saves state of the wardrobe to the WARDROBE_FILE
    private void saveWardrobe() {
        Writer writer;
        try {
            writer = new Writer(new FileOutputStream(WARDROBE_FILE));
            writer.write(myWardrobe);
            writer.close();
            informationMessage("Wardrobe saved to file " + WARDROBE_FILE, "File Saved");
        } catch (IOException e) {
            errorMessage("Unable to save wardrobe to " + WARDROBE_FILE);
        }
    }

    //MODIFIES: this
    //EFFECTS: updates the current clothing list to display any changes made
    private void updateClothingList() {
        whatToRemove();
        createClothingList(myWardrobe.getCurrentWardrobe());
    }

    //MODIFIES: this
    //EFFECTS: updates main frame to only display clothing items that are of brand specified
    private void doFilterBrand() {
        String brand = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the brand you want to filter by?", null);
        brand = brand.toLowerCase();
        whatToRemove();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        clothingList = new ArrayList<>();
        for (Clothing c : myWardrobe.getCurrentWardrobe()) {
            if (c.getBrand().toLowerCase().equals(brand)) {
                String line = c.getType().toUpperCase() + " - Name: " + c.getName() + ", Made by: " + c.getBrand();
                listModel.addElement(line);
                clothingList.add(c);
            }
        }
        clothingStringList = new JList<>(listModel);
        clothingStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(clothingStringList);
        addButtonClothing();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: updates main frame to only display clothing items that are of color specified
    private void doFilterColor() {
        String color = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the color you want to filter by?", null);
        color = color.toLowerCase();
        whatToRemove();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        clothingList = new ArrayList<>();
        for (Clothing c : myWardrobe.getCurrentWardrobe()) {
            if (c.getColour().toLowerCase().equals(color)) {
                String line = c.getType().toUpperCase() + " - Name: " + c.getName() + ", Made by: " + c.getBrand();
                listModel.addElement(line);
                clothingList.add(c);
            }
        }
        clothingStringList = new JList<>(listModel);
        clothingStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(clothingStringList);
        addButtonClothing();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: updates main frame to only display clothing items that are of type specified
    private void doFilterType() {
        String type = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the type you want to filter by?", null);
        type = type.toLowerCase();
        whatToRemove();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        clothingList = new ArrayList<>();
        for (Clothing c : myWardrobe.getCurrentWardrobe()) {
            if (c.getType().toLowerCase().equals(type)) {
                String line = c.getType().toUpperCase() + " - Name: " + c.getName() + ", Made by: " + c.getBrand();
                listModel.addElement(line);
                clothingList.add(c);
            }
        }
        clothingStringList = new JList<>(listModel);
        clothingStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(clothingStringList);
        addButtonClothing();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: updates main frame to only display clothing items that are of fabric specified
    private void doFilterFabric() {
        String fabric = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the fabric you want to filter by?", null);
        fabric = fabric.toLowerCase();
        whatToRemove();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        clothingList = new ArrayList<>();
        for (Clothing c : myWardrobe.getCurrentWardrobe()) {
            if (c.getFabric().toLowerCase().equals(fabric)) {
                String line = c.getType().toUpperCase() + " - Name: " + c.getName() + ", Made by: " + c.getBrand();
                listModel.addElement(line);
                clothingList.add(c);
            }
        }
        clothingStringList = new JList<>(listModel);
        clothingStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(clothingStringList);
        addButtonClothing();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: removes the correct list of either clothing or outfit
    private void whatToRemove() {
        if (isOutfitsDisplayed) {
            remove(outfitStringList);
            isOutfitsDisplayed = false;
        } else {
            remove(clothingStringList);
        }
        buttonPane.remove(openButton);
        remove(buttonPane);
    }

    //MODIFIES: this
    //EFFECTS: changes the clothing list to display the old wardrobe
    private void createOldClothingList() {
        whatToRemove();
        createClothingList(myWardrobe.getOldWardrobe());
    }

    //MODIFIES: this
    //EFFECTS: creates the list of outfits in the wardrobe and displays on main frame
    private void createOutfitList() {
        whatToRemove();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        outfits = new ArrayList<>();
        for (Outfit o : myWardrobe.getOutfits()) {
            listModel.addElement(o.outfitToString());
            outfits.add(o);
        }
        JScrollPane listScroller = new JScrollPane();
        outfitStringList = new JList<>(listModel);
        outfitStringList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        outfitStringList.setFont(new Font("System", Font.PLAIN, 24));
        listScroller.setViewportView(clothingStringList);
        clothingStringList.setLayoutOrientation(JList.VERTICAL);
        add(listScroller);
        addButtonOutfit();
        isOutfitsDisplayed = true;
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds a button to button pane that opens the selected outfit
    private void addButtonOutfit() {
        openButton = new JButton(new AbstractAction("Open Outfit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = outfitStringList.getSelectedIndex();
                Outfit selectedItem = outfits.get(index);
                displayOutfit(selectedItem);
            }
        });
        openButton.setFont(new Font("System", Font.PLAIN, 20));
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(openButton);
        add(buttonPane, BorderLayout.PAGE_END);
        openButton.setVisible(true);
        buttonPane.setVisible(true);
    }

    //EFFECTS: displays outfit in option pane
    private void displayOutfit(Outfit outfit) {
        JOptionPane.showMessageDialog(WardrobeManagerProgram.this, outfitItems(outfit),
                outfit.getName(),JOptionPane.INFORMATION_MESSAGE,OUTFIT);
    }

    //EFFECTS: returns a list of items in an outfit with attributes
    private String outfitItems(Outfit outfit) {
        return  "Outfit: " + outfit.getName() + itemAttributes(outfit.getHat(),"Hat")
                + itemAttributes(outfit.getShirt(), "Shirt") + itemAttributes(outfit.getSweater(), "Sweater")
                + itemAttributes(outfit.getJacket(), "Jacket") + itemAttributes(outfit.getBottoms(), "Bottoms")
                + itemAttributes(outfit.getSocks(), "Socks") + itemAttributes(outfit.getShoes(), "Shoes");
    }

    //EFFECTS: returns the type and item attributes of the item for use to be in outfits if not null
    private String itemAttributes(Clothing item, String type) {
        if (null != item) {
            return "\n" + type + ":\n" + clothingAttributes(item);
        } else {
            return "";
        }
    }

    //EFFECTS: finds and opens clothing item if it exists
    private void doFind() {
        String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the item you want to find?", null);
        name = name.toLowerCase();
        if (null != myWardrobe.findCurrentItem(name)) {
            Clothing item = myWardrobe.findCurrentItem(name);
            displayItem(item);
        } else {
            errorMessage("item of name " + name + " was not found.");
        }
    }

    //EFFECTS: Displays a clothing item in pop up with attributes and picture
    private void displayItem(Clothing item) {
        ImageIcon icon = getTypeImage(item);
        JOptionPane.showMessageDialog(WardrobeManagerProgram.this, clothingAttributes(item),
                item.getName(),JOptionPane.INFORMATION_MESSAGE,icon);
    }

    //http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //EFFECTS: plays sound from file given
    private void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            errorMessage("Sound not playable or found");
        }
    }

    //EFFECTS: gets the correct image icon for type
    private ImageIcon getTypeImage(Clothing item) {
        String type = item.getType();
        if (type.equals("bottoms")) {
            return BOTTOMS;
        } else if (type.equals("hat")) {
            return HAT;
        } else if (type.equals("jacket")) {
            return JACKET;
        } else if (type.equals("shirt")) {
            return SHIRT;
        } else if (type.equals("shoe")) {
            return SHOE;
        } else if (type.equals("sock")) {
            return SOCK;
        } else {
            return SWEATER;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an outfit with the specified clothing types
    private void addOutfit() {
        String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What do you want to name this outfit?", null);
        Clothing hat = collectItem("hat");
        Clothing shirt = collectItem("shirt");
        Clothing sweater = collectItem("sweater");
        Clothing jacket = collectItem("jacket");
        Clothing bottoms = collectItem("bottoms");
        Clothing sock = collectItem("sock");
        Clothing shoe = collectItem("shoe");
        myWardrobe.makeOutfit(name, hat,shirt,sweater,jacket,bottoms,sock,shoe);
        playSound(OUTFIT_AUDIO_FILE);
        name = name.toLowerCase();
        displayOutfit(myWardrobe.findOutfit(name));
    }

    //http://www.java2s.com/Tutorial/Java/0240__Swing/UsingJOptionPanetopromptuserconfirmationademo.htm
    //EFFECTS: gets the clothing item of a specified type if it is to be added to an outfit, otherwise returns null
    // and displays error message
    private Clothing collectItem(String type) {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to add a " + type
                        + "to the outfit?", "Add " + type, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                    "What is the name of the " + type + " you want to add?", null);
            if (null != myWardrobe.findCurrentItem(name)) {
                return myWardrobe.findCurrentItem(name);
            } else {
                errorMessage("item of name " + name + " was not found.");
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: finds an outfit with name selected, and displays it if found, otherwise displays error message
    private void findOutfit() {
        String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the outfit you want to find?", null);
        if (null != myWardrobe.findOutfit(name)) {
            Outfit found = myWardrobe.findOutfit(name);
            displayOutfit(found);
        } else {
            errorMessage("outfit of name " + name + " was not found.");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the clothing item selected
    private void doRemove() {
        String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the item you want to remove?", null);
        name = name.toLowerCase();
        if (myWardrobe.removeClothing(name)) {
            System.out.println(name + " was successfully removed from your wardrobe, and is now in your old wardrobe");
        } else {
            System.out.println("There was no item of name " + name + " in your wardrobe");
        }
        updateClothingList();
    }

    //MODIFIES: this
    //EFFECTS: does the edit on the clothing item if found
    private void doEdit() {
        String name = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of the item you want to edit?", null);
        name = name.toLowerCase();
        if (null != myWardrobe.findCurrentItem(name)) {
            Clothing item = myWardrobe.findCurrentItem(name);
            editMenu(item);
        } else {
            System.out.println("item of name " + name + " was not found.");
        }
    }

    //https://www.roseindia.net/tutorial/java/swing/comboinjoptionpane.html
    //MODIFIES: this
    //EFFECTS: completes the editing and selects field & new name for field
    private void editMenu(Clothing item) {
        String [] editOptions = new String[] {"Name", "Size","Color","Brand","Fabric","Collaboration"};
        String whatToEdit = (String) JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "This Item currently has the following attributes:\n" + clothingAttributes(item)
                + "\n\nPlease select the attribute you would like to edit.", "Attributes",
                JOptionPane.INFORMATION_MESSAGE, null, editOptions,"Name");
        String newAtt = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What do you want to change the " + whatToEdit + " to?", null);
        if (whatToEdit.equals("Name")) {
            item.setName(newAtt);
        } else if (whatToEdit.equals("Size")) {
            item.setSize(newAtt);
        } else if (whatToEdit.equals("Color")) {
            item.setColour(newAtt);
        } else if (whatToEdit.equals("Brand")) {
            item.setBrand(newAtt);
        } else if (whatToEdit.equals("Fabric")) {
            item.setFabric(newAtt);
        } else {
            item.setCollaboration(newAtt);
        }
        System.out.println(whatToEdit + " was changed to " + newAtt);
        updateClothingList();
    }

    //EFFECTS: prints out the list of attributes for a clothing item
    private String clothingAttributes(Clothing item) {
        return "Name: " + item.getName() + "\nSize: " + item.getSize() + "\nColor: " + item.getColour()
                + "\nBrand: " + item.getBrand() + "\nFabric: " + item.getFabric()
                + "\nCollaboration: " + item.getCollaboration();
    }

    //MODIFIES: this
    //EFFECTS: adds a bottom to the wardrobe and refreshes main page with updated wardrobe
    private void addBottoms() {
        String name = collectName("bottoms");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        String length = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the length?", null);
        myWardrobe.makeBottoms(name,size,color,brand,fabric,length);
        updateClothingList();
        playSound(BOTTOMS_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a hat to the wardrobe and refreshes main page with updated wardrobe
    private void addHat() {
        String name = collectName("hat");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        myWardrobe.makeHat(name,size,color,brand,fabric);
        updateClothingList();
        playSound(HAT_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a jacket to the wardrobe and refreshes main page with updated wardrobe
    private void addJacket() {
        String name = collectName("jacket");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        myWardrobe.makeJacket(name,size,color,brand,fabric);
        updateClothingList();
        playSound(JACKET_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a shirt to the wardrobe and refreshes main page with updated wardrobe
    private void addShirt() {
        String name = collectName("shirt");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        String sleeveLength = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the sleeve length?", null);
        myWardrobe.makeShirt(name,size,color,brand,fabric,sleeveLength);
        updateClothingList();
        playSound(SHIRT_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a shoe to the wardrobe and refreshes main page with updated wardrobe
    private void addShoe() {
        String name = collectName("shoe");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        String model = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the model?", null);
        myWardrobe.makeShoe(name,size,color,brand,fabric,model);
        updateClothingList();
        playSound(SHOE_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a sock to the wardrobe and refreshes main page with updated wardrobe
    private void addSock() {
        String name = collectName("sock");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        String height = JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the height?", null);
        myWardrobe.makeSock(name,size,color,brand,fabric,height);
        updateClothingList();
        playSound(SOCK_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //MODIFIES: this
    //EFFECTS: adds a sweater to the wardrobe and refreshes main page with updated wardrobe
    private void addSweater() {
        String name = collectName("sweater");
        String size = collectSize();
        String color = collectColor();
        String brand = collectBrand();
        String fabric = collectFabric();
        myWardrobe.makeSweater(name,size,color,brand,fabric);
        updateClothingList();
        playSound(SWEATER_AUDIO_FILE);
        name = name.toLowerCase();
        displayItem(myWardrobe.findCurrentItem(name));
    }

    //EFFECTS: returns the name for a new clothing item
    private String collectName(String type) {
        return JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the name of this " + type + "?", null);
    }

    //EFFECTS: returns the size for a new clothing item
    private String collectSize() {
        return JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the size?", null);
    }

    //EFFECTS: returns the color for a new clothing item
    private String collectColor() {
        return JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the color?", null);
    }

    //EFFECTS: returns the brand for a new clothing item
    private String collectBrand() {
        return JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the brand?", null);
    }

    //EFFECTS: returns the size for a new clothing item
    private String collectFabric() {
        return JOptionPane.showInputDialog(WardrobeManagerProgram.this,
                "What is the fabric?", null);
    }

    //https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    //MODIFIES: this
    //EFFECTS: scales the image at the file location to be of desired size
    private static ImageIcon scaleImage(String fileLocation) {
        ImageIcon imageIcon = new ImageIcon(fileLocation);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(120,120,Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    //Runs the program
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            WardrobeManagerProgram wm = new WardrobeManagerProgram();
            wm.setVisible(true);
        });
    }

}
