'''

	bottomPivot.py

	For each object selected:
		center the pivot
		move the pivot to the lowest Y point in the object 

'''

import maya.cmds as cmds 

selection = cmds.ls(sl=True)
for selected in selection:

    # center the pivot
    cmds.xform(selected, cp=True)
    
    # determine the bounding box so we know where to put our pivot
    # bounding_box = xmin ymin zmin xmax ymax zmax.
    bounding_box = cmds.xform(selected, q=True, boundingBox=True, ws=True)
    xmin, ymin, zmin, xmax, ymax, zmax = bounding_box
    
    # move the pivot points in the Y direction to the bottom Y point
    cmds.move(ymin, [selected + ".scalePivot",selected + ".rotatePivot"], y=True, absolute=True)

# notify the user that we're done
print ("Pivot Move Complete!")