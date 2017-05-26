## Animated flowers 

import maya.cmds as cmds
import random

## Creates a new file
cmds.file(f = True, new = True)

## Sets the playback time
cmds.playbackOptions(minTime = '0', maxTime = '600')

## Sets the position of the camera
cmds.setAttr('persp.translateX', 50)
cmds.setAttr('persp.translateY', 15)
cmds.setAttr('persp.translateZ', -10)

def makeFlower():
    ## Number of petals on the flower
    numPetals = 10
    
    ## Shaders for the flower
    myShader = cmds.shadingNode('lambert', n = "core", asShader = True)
    myShader1 = cmds.shadingNode('lambert', n = "petal", asShader = True)
    
    ## Build the flower core
    cmds.sphere(axis = [0.0, 1.0, 0.0], name = "core")
    myCore = cmds.ls(selection = True)
    cmds.scale(1.0, 0.5, 1.0)
    cmds.move(0.0, 0.2, 0.0)
    
    ## Colors the core of the flower
    cmds.select(myCore)
    shaderSet = cmds.sets(renderable = True, noSurfaceShader = True, empty = True, name = myShader + 'SG')
    cmds.setAttr(myShader + '.color', 0.18, 0.40, 0.698, type = 'double3')
    ##cmds.setAttr(myShader + '.incandescence', 0.0, 0.0, 0.0, type = 'double3')
    cmds.connectAttr(myShader + ".outColor", shaderSet + ".surfaceShader")
    cmds.sets(forceElement = shaderSet, e = True)
    
    ## Build the petal
    cmds.sphere(axis = [0.0, 1.0, 0.0])
    cmds.move(0.0, 0.0, -1.6)
    cmds.scale(0.7, 0.3, 1.7)
    cmds.FreezeTransformations()
    cmds.ResetTransformations()
    myPetal = cmds.ls(selection = True)
    
    # Curve the tip of the petal
    cmds.select(myPetal[0] + '.cv[3][7]')
    cmds.move(0.0, 2.0, 0.0, relative = True)
    
    # Select the inner part of the petal pull down
    # One loop for the U direction
    for uCV in xrange(5,7):
        for vCV in xrange(0,8):
            cmds.select(myPetal[0] + '.cv[' + str(uCV) + '][' + str(vCV) + ']')
            cmds.move(0.0, -0.4, 0.0, relative = True)
    
    ## Color the petal of the flower
    cmds.select(myPetal)
    shaderSet1 = cmds.sets(renderable = True, noSurfaceShader = True, empty = True, name = myShader1 + 'SG')
    cmds.setAttr(myShader1 + '.color', 1.0, 0.361, 0.169, type = 'double3')
    ##cmds.setAttr(myShader1 + '.incandescence', 0.0, 0.0, 0.0, type = 'double3')
    cmds.connectAttr(myShader1 + ".outColor", shaderSet1 + ".surfaceShader")
    cmds.sets(forceElement = shaderSet1, e = True)
    
    ## Parent the petal to the core of the flower
    cmds.parent(myPetal, myCore)
    cmds.select(myPetal[0])
    cmds.pickWalk(direction="down")
    
    ## Duplicates the petals and rotates them around the core
    cmds.select(myPetal[0])
    degreeApart = (360.0/numPetals)
    for x in xrange(1,numPetals):
        cmds.duplicate()
        cmds.rotate(0.0, degreeApart, 0.0, relative = True)
    cmds.select(myCore)


numFlowers = random.randint(75,100)
for flowerNum in xrange(0, numFlowers):
    makeFlower()
    ## X, Y, and Z positions
    xpos = random.randint(30,70)
    ypos = random.randint(15,30)
    zpos = random.randint(50,100) * -1
    
    ## Rotates the flower to "face" camera
    cmds.rotate(90,0,0)
    ## Moves flower to xpos, ypos, zpos (in view of the camera)
    cmds.move(xpos, ypos, zpos)
    
    ## Determining whether to spin clockwise or counterclockwise
    clock = False
    if (random.randint(0,100) < 50):
        clock = True
    
    ## Animates the flower by setting keyframes every tick (tx)
    for tx in xrange(0,600):
        if (clock):
            rotation = tx * -1
        else:
            rotation = tx
        fallRate = ypos
        ypos -= 0.05
        cmds.setKeyframe(attribute = "rotateZ", value = rotation, t = tx)
        cmds.setKeyframe(attribute = "translateY", value = fallRate, t = tx)

cmds.play()