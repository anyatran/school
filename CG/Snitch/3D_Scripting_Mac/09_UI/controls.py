"""

    controls.py
    
    Super basic example that just opens up a window without anything in it


"""

import maya.cmds as cmds

# declare the name of our window object
window_name = "controlsWindow"

# check if that object already exists, and delete it if it does 
if cmds.window(window_name, q=True, exists=True):
    cmds.deleteUI(window_name)
    
# create a new window object
my_window = cmds.window(window_name, title="WIDGETS!")

# the adj flag makes the buttons stretch to the width of the column
my_layout = cmds.columnLayout(parent=my_window, adj=True)

# make some buttons 
cmds.button(parent = my_layout, l = "button")
cmds.text(parent = my_layout, l = "text")

cmds.checkBox( label='checkBox' )
cmds.checkBoxGrp( numberOfCheckBoxes=3, label='checkBoxGrp', labelArray3=['One', 'Two', 'Three'] )

cmds.floatFieldGrp( numberOfFields=2, label='floatFieldGrp')
cmds.floatSliderGrp( label='floatSliderGrp', field=True )

cmds.intFieldGrp( numberOfFields=2, label='intFieldGrp')
cmds.intSliderGrp( label='intSliderGrp', field=True )

cmds.textFieldGrp( label='textFieldGrp' , text='Text')
cmds.textFieldButtonGrp( label='textFieldButtonGrp', text='Text', buttonLabel='Button' )

cmds.optionMenuGrp( label='optionMenuGrp')
cmds.menuItem( label='Option1' )
cmds.menuItem( label='Option2' )
cmds.menuItem( label='Option3' )

collection = cmds.radioCollection()
collectionLayout = cmds.rowLayout(numberOfColumns=4)
cmds.text(parent = my_layout, l = "radioButton", p=collectionLayout)
rb1 = cmds.radioButton( label='One', p=collectionLayout)
rb2 = cmds.radioButton( label='Two', p=collectionLayout)
rb3 = cmds.radioButton( label='Three', p=collectionLayout )

cmds.colorSliderGrp( label='colorSliderGrp' , p=my_layout)

# show that window
cmds.showWindow(my_window)
