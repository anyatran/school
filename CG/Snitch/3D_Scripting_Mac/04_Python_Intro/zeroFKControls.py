"""

    zeroFKControls.py
    
    Python Version of the mel script that zeroes out selected
    FK Controls


"""

import maya.cmds as cmds 

selection = cmds.ls(sl=True)

if len(selection) == 0 :
    cmds.select("*FKControl", r=True)
    
fk_controls = cmds.ls(sl=True)
attributes = ["translate", "rotate"]
axes = ["X", "Y", "Z"]

for control in fk_controls:
    
    for attribute in attributes:
        
        for axis in axes:
        
            attribute_name = control + "." + attribute + axis
            if not cmds.getAttr(attribute_name, l=True):
                cmds.setAttr(attribute_name, 0)