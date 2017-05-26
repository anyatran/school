'''

	Toggle Xray Mode
'''



import maya.cmds as cmds 

selection = cmds.ls(sl=True)
for sel in selection:

    xray = cmds.displaySurface(sel, q=True, xRay=True)
    cmds.displaySurface(sel, xRay=not xray[0])


