'''

	Toggle Backface Culling
'''



import maya.cmds as cmds 

objects_culled = []
selection = cmds.ls(sl=True)
for selected in selection:
    selected = selected.split(".")[0]

    # if we have a shape on our hands, go up one 
    if cmds.objectType(selected, isType="mesh"):
        selected = cmds.listRelatives(selected, parent=True)[0]

    if selected not in objects_culled:
        val = cmds.polyOptions(selected, q=True, bc=True)
        if val[0] == 1:
            cmds.polyOptions(selected, fb=True)
        else:
            cmds.polyOptions(selected, bc=True)
        objects_culled.append(selected)
