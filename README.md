# Inventory Tracker User Guide
  This guide will take you through each specified requirement in the directions in order.
___
# 1. Interacting with the GUI
    There are many buttons and text fields available that allow for user interaction with my GUI. 
    I recommend starting off with the "Add New Item" button at the bottom of the GUI.
___
# 2. Storing 100 Items
    The Inventory Tracker can handle the tracking of over 100 Items.
    To show this you will have to press the "Add New Item" button and input 100 unique items.
___
# 3. Storing Items
    Items can be stored by using the "Add New Item" button at the bottom of the GUI
      and inputting valid data into the Add Item Window that will popup.
    ___
    # 1 Storing Values
      The Add Item Window offers a text field for any inputted value greater than or equal to 0.
      An error label will show if this condition is not met.
    ___
    # 2 Storing Serial Numbers
      The Add Item Window offers a text field for inputting serial Numbers that fit the format XXXXXXXXXX,
        where X is either a letter or a digit.
      An error label will show if the serial number is less than 10 characters.
      An error label will show if the serial number is not composed of letters and/or digits.
      An error label will show if the serial number is not unique.
    ___
    # 3 Storing Names
      The Add Item Window offers a text field for inputting a string between 2 and 256 characters in length(inclusive).
      An error label will show if the name is not between 2 and 256 characters(inclusive)
___
# 4. Removing Items
    Items can be removed by first selecting one in the TableView, then clicking
      the "Delete Selected Item" button at the bottom of the GUI.
___
# 5,6,7. Editing Items
    Items can be edited by double clicking on the desired cell in the TableView, and typing in
      a valid replacement string.
    ___
    For example: you can not replace a value cell with a letter string
        but you can replace a name cell with a double value
___
# 8,9,10. Sorting TableView
    The Tableview visible in the GUI is fully sortable by means of the column headers.
    Simply click where the up facing triangle is on the column headers to change the sort order.
___
# 11,12. Searching the List
    Searching the list is done by way of a live filter seen on the top right of the TableView.
    You can change the ChoiceBox to the side of the query field to change what TableView Property you want to search by.
    As you type into the query field, the Table should automatically update to contain only items that match the query.
___
# 13. Saving Your Inventory
    To Save your Inventory to an external file use the File menu option on the top left of the GUI and click "Save as..."
    ___
    1. Selecting File Format
        In the File Chooser the ChoiceBox for selecting the Flie Format is to the right of the main text field.
        ___
        1. TSV files are made correctly
          To see how TSV files are saved. Saving the Inventory and looking at the external file.
        ___
        2. HTML files are made correctly
          To see how HTML files are saved. Saving the Inventory and looking at the external file.
        ___
        3. JSON files are made correctly
          up to the point when this readME was made, JSON was not implemented into the GUI
    ___
    2. User Provided File Name and Location
        The user can provide the file name and location by using the FileChooser attached to the Save As... button
___
# 14. Loading an Inventory
    To load a previously saved inventory, click the "Load" button in the File menu at the top left of the screen.
    It is below the "Save As..." button in the Menu. 
    Next you would locate the saved Inventory and open it from the popup File Chooser.
      There is an extension filter dropdown menu at the bottom of the File Chooser to help in finding the file.
    The data is automatically loaded into the TableView.
      
