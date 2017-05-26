## Converting flowerPetal.mel to flowerPetal.py

import maya.cmds as cmds

def flowerMaker():
    numPetals = 10
    hasShader = False
    ## Check for shader
    shades = cmds.ls(mat = True)
    for myNode in shades:
        if (myNode == "petalColor"):
            hasShader = True
            
    if hasShader == True:
        print "There is already a shader"
    elif hasShader == False:
        ## Build our shader network
        cmds.shadingNode('lambert', asShader = True, name = "petalColor")
        cmds.shadingNode('ramp', asTexture = True, name = "petalRamp")
        cmds.sets(empty = True, noSurfaceShader = True, renderable = True, name = "petalColorSG")
        cmds.connectAttr('petalColor.outColor', 'petalColorSG.surfaceShader', force = True)
        cmds.connectAttr('petalRamp.outColor', 'petalColor.color')
        
        cmds.setAttr('petalRamp.colorEntryList[3].color', 1.0, 0.0, 0.0, type = "double3")
        cmds.setAttr('petalRamp.colorEntryList[3].positon', 1.0)
        
        cmds.setAttr('petalRamp.colorEntryList[2].color', 1.0, 1.0, 0.0, type = "double3")
        cmds.setAttr('petalRamp.colorEntryList[2].positon', 0.5)
        
        cmds.setAttr('petalRamp.colorEntryList[1].color', 1.0, 0.0, 0.0, type = "double")
        cmds.setAttr('petalRamp.colorEntryList[1].positon', 0.0)
        cmds.setAttr('petalRamp.type', 8.0)
    else:
        print "Catastrophic collapse in code!"
    
    ## Build the flower core
    cmds.sphere(axis = [0.0, 1.0, 0.0], name = "core")
    myCore = cmds.ls(selection = True)
    cmds.scale(1.0, 0.5, 1.0)
    cmds.move(0.0, 0.2, 0.0)
    
    ## Build the petal
    cmds.sphere(axis = [0.0, 1.0, 0.0])
    cmds.move(0.0, 0.0, -1.6)
    cmds.scale(0.7, 0.3, 1.7)
    cmds.FreezeTransformations()
    cmds.ResetTransformations()
    
    myPetal = cmds.ls(selection = True)
    cmds.parent(myPetal, myCore)
    cmds.select(myPetal[0])
    cmds.pickWalk(direction="down")
    myPetalShape = cmds.ls(selection = True)
    cmds.sets(myPetalShape[0], edit = True, forceElement = 'petalColorSG')
    
    # Move the tip of the petal
    cmds.select(myPetal[0] + '.cv[3][7]')
    cmds.move(0.0, 2.0, 0.0, relative = True)

    # Select the inner part of the petal pull down
    # One loop for the U direction
    for uCV in xrange(5,7):
        for vCV in xrange(0,8):
            cmds.select(myPetal[0] + '.cv[' + str(uCV) + '][' + str(vCV) + ']')
            cmds.move(0.0, -0.4, 0.0, relative = True)
    
    cmds.select(myPetal[0])
    degreeApart = (360.0/numPetals)
    for x in xrange(1,numPetals):
        cmds.duplicate()
        cmds.rotate(0.0, degreeApart, 0.0, relative = True)
    
    cmds.select(myCore)
    cmds.rename("Flower")
