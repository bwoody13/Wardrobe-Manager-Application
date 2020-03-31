# Clothing and Shoe Manager Application

## What will the application do?
Users will be able to login to this application, **upload their clothing and shoe items, find items based on criteria, 
view the estimated value of items in their wardrobe, and make outfits**.

*For Example:*
- A User can upload their wardrobe of clothes and shoes.
- With a wardrobe available they can find all their white t-shirts, all items from a specific brand, or jackets of 
  certain materials.
- Additionally, the user can add clothing pieces to an outfit, by adding different items from their wardrobe together.

## Who will use it?
This application is targeted towards streetwear enthusiasts and sneaker heads, who want to view their collection, make
outfits of items that fit well together, and sort their wardrobe.

## Why is this project of interest to me?
I personally am a big fan of streetwear and sneakers, and would love to have an application that allows me to see my
entire collection, make outfits, and see what clothing pieces I have that are similar to each other.

#User Stories:
***As a user, I want to be able to:***
- add a new piece of clothing to my wardrobe.
- associate an item of clothing to a brand(ie. Nike), type (ie. Shirt), and other important attributes associated with 
  that type (ie. fabric, collaboration, size, colour, etc.).
- add a piece of clothing to an outfit.
- remove a clothing item from my current wardrobe and store it in a wardrobe for items I've gotten rid of.
- find a clothing item or outfit by name.
- view all clothing items, all discarded clothing items, and outfits.
- filter my wardrobe to find specific items that meet my criteria.
- update information on an item in my wardrobe or add a detail about it.
- quit the application and have it save my wardrobe to a file.
- reload my application from a save file upon opening the application.

#Instructions for Grader:
Run WardrobeManagerProgram to open the Application
- You can generate the first required event by opening the Clothing Menu, then selecting "Add Clothing", then any type 
  of clothing you wish. From there you will enter the fields for that clothing item. after that is done the clothing 
  item will be added onto the wardrobe and displayed in its own pane, with audio playing signifying it was added. 
  You can also now see it in the list of clothing items in the main screen.
- You can generate the second required event by navigating to the "Add Outfit" button in the Outfit menu. Here you will 
  decide which clothing items (that are already in your wardrobe) you want to add to an outfit. From here you will also 
  be presented with the outfit in its own pane and audio notifying you it was added will play. To view you outfits, 
  simply select the "Display Outfits" button in the Wardrobe Menu.
- You can locate my visual and audio component by completing one of the tasks above (Add a clothing item or add a 
  outfit). Once created the pane will have a photo and audio saying what has been added to the wardrobe.
- You can save the state of my application by navigating to the File Menu and pressing the "Save" button, or to use the
  auto save, you can close the application and it will save for you.
- You can reload the state of my application by navigating to the File Menu and pressing the "Load" button. However,
  this is unnecessary as the application will load from the previous state when the application is opened.
  
##*Additional Things to Do*
   If you want to continue exploring this application you can test out the functionality of each different button in the
   menu. They are pretty explicit in terms of operation. Eg. the "Edit Clothing" button will look up a clothing item 
   then allow you to edit an attribute of your choosing. You may also wish to filter the wardrobe to only display
   certain clothing items. To do this, navigate to the Filter By Submenu under the Wardrobe Menu, then select the style
   in which you wish to filter. Enjoy using this application! :) 

#Coupling and Cohesion Analysis:
**Cohesion** among classes seems good. Clothing class doesn't do much besides create the object, Outfit contains clothing, 
Wardrobe has a list of clothing and outfits, but methods pertain to adding to lists and dealing with specific wardrobe 
tasks. GUI uses the wardrobe to modify and display it.

**Coupling** within and between classes, is a bit poor. There is duplication among methods within my GUI and in Wardrobe.
- In Wardrobe: the makeBottoms/Shirt etc. methods are all almost the exact same. I pulled out the parts that act the same
  and put it into the makeItem method, which is called by one of the makeBottoms/Shirt etc. methods with correct type 
  and fields.
- In WardrobeManagerProgram: the filtering methods all have the same lines ran towards the end of the method. I pulled
  out all those method calls and was able to refine my code.
- In WardrobeManagerProgram: the collect methods, are all the same except what is collected, so I changed the attribute 
  being collected to a parameter that is used within the method, allowing me to be more cohesive and remove additional 
  duplicate methods.

