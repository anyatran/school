"""

    helloWorldWindow.py
    
    Super basic example that just opens up a window without anything in it


"""

import maya.cmds as cmds

# declare the name of our window object
window_name = "helloWorldWindow"

# check if that object already exists, and delete it if it does 
if cmds.window(window_name, q=True, exists=True):
    cmds.deleteUI(window_name)
    
# create a new window object
my_window = cmds.window(window_name, title="Hello World!")

# show that window
cmds.showWindow(my_window)
