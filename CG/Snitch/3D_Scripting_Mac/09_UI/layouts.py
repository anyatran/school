"""

    layous.py
    
    A Few examples of the different layouts 


"""

import maya.cmds as cmds


def columnLayoutWindow():

    # create our window 
    my_window = createWindow("columnLayoutWindow", "Column Layout")

    # the adj flag makes the buttons stretch to the width of the column
    my_layout = cmds.columnLayout(parent=my_window, adj=True)
    
    # make some buttons 
    cmds.button(parent = my_layout)
    cmds.button(parent = my_layout)
    cmds.button(parent = my_layout)
    cmds.button(parent = my_layout)

    # show that window
    cmds.showWindow(my_window)

def rowLayoutWindow():

    # create our window 
    my_window = createWindow("rowLayoutWindow", "Row Layout")

    my_layout = cmds.rowLayout( numberOfColumns=3, columnWidth3=(80, 75, 150), adjustableColumn=3, columnAlign=(1, 'right'), columnAttach=[(1, 'both', 0), (2, 'both', 0), (3, 'both', 0)] )
    
    # make some buttons 
    cmds.text(parent = my_layout)
    cmds.intField(parent = my_layout)
    cmds.intSlider(parent = my_layout)

    # show that window
    cmds.showWindow(my_window)

def rowColumnWindow():

    # create our window 
    my_window = createWindow("rowColumnWindow", "Row Column Layout")

    # try changing the numberOfRows to numberOfColumns
    my_layout = cmds.rowColumnLayout( numberOfRows =2 )
    
    # make some buttons 
    cmds.button()
    cmds.button()
    cmds.button()    
    cmds.button()
    cmds.button()


    # show that window
    cmds.showWindow(my_window)

def nestedLayoutWindow():

    # create our window 
    my_window = createWindow("nestedLayoutWindow", "Nested Layout")

    # the adj flag makes the buttons stretch to the width of the column
    columns = cmds.columnLayout(parent=my_window, adj=True)


    cmds.button(parent = columns)
    cmds.button(parent = columns)
    
    my_layout = cmds.rowLayout( parent=columns, numberOfColumns=3, columnWidth3=(80, 75, 150), adjustableColumn=3, columnAlign=(1, 'right'), columnAttach=[(1, 'both', 0), (2, 'both', 0), (3, 'both', 0)] )
    
    # make some buttons 
    cmds.text(parent = my_layout)
    cmds.intField(parent = my_layout)
    cmds.intSlider(parent = my_layout)


    cmds.button(parent = columns)
    # show that window
    cmds.showWindow(my_window)




def createWindow(window_name, window_title):
    # check if that object already exists, and delete it if it does 
    if cmds.window(window_name, q=True, exists=True):
        cmds.deleteUI(window_name)
        
    # create a new window object
    my_window = cmds.window(window_name, title=window_title)
    
    return my_window


# Uncomment these following lines to see the different types of layouts

#columnLayoutWindow()
#rowLayoutWindow()
#nestedLayoutWindow()
rowColumnWindow()