import maya.cmds as cmds

def flowerMaker():
    numPetals=10
    createShader = 1

    # Check for Shader
    shads = cmds.ls(materials=True)
    for myNode in shads:
        if myNode == "petalColor":
            createShader = 0

    if createShader == 0:
        print "The Shader is already there \n"
    else:
        # Build our shader network
        cmds.shadingNode('lambert', asShader=True, n='petalColor')
        cmds.shadingNode('ramp', asTexture=True, n='petalRamp')
        cmds.sets(renderable=True, noSurfaceShader=True, empty=True, n='petalColorSG')
        cmds.connectAttr('petalColor.outColor', 'petalColorSG.surfaceShader', force=True)
        cmds.connectAttr('petalRamp.outColor', 'petalColor.color')

        cmds.setAttr('petalRamp.colorEntryList[3].color', 1, 0, 0, type="double3")
        cmds.setAttr('petalRamp.colorEntryList[3].position', 1)

        cmds.setAttr('petalRamp.colorEntryList[2].color', 1, 1, 0, type="double3")
        cmds.setAttr('petalRamp.colorEntryList[2].position', .5)

        cmds.setAttr('petalRamp.colorEntryList[1].color', 1, 0, 0, type="double3")
        cmds.setAttr('petalRamp.colorEntryList[1].position', 0)

        cmds.setAttr('petalRamp.type', 8)

    # Build the flower core
    cmds.sphere(axis=(0,1,0), n='core')
    myCore = cmds.ls(sl=True)
    cmds.scale(1,.5,1)
    cmds.move(0,0.2,0)

    # Build the petal
    cmds.sphere(axis=(0,1,0))
    cmds.move(0,0,-1.6)
    cmds.scale(.7,.3,1.7)

    cmds.makeIdentity(apply=True, t=1, r=1, s=1, n=0)
    cmds.makeIdentity(apply=False, t=1, r=1, s=1)

    myPetal = cmds.ls(sl=True)
    cmds.parent(myPetal, myCore)
    cmds.select(myPetal[0])
    cmds.pickWalk(d='down')
    myPetalShape = cmds.ls(sl=True)
    cmds.sets(myPetalShape[0], edit=True, fe='petalColorSG')

    # Move the tip of the petal
    cmds.select(myPetal[0]+".cv[3] [7]")
    cmds.move(0,2,0, r=True)

    # Select the inner part of the petal pull down
    # One loop for the U direction
    for uCV in range(5, 7):
        for vCV in range(0,8):
            cmds.select(myPetal[0]+".cv["+str(uCV)+"]["+str(vCV)+"]")
            cmds.move(0,-0.4,0, r=True)

    cmds.select(myPetal[0])

    degreeApart = (360/numPetals)

    for i in range(2, numPetals+1):
        cmds.duplicate()
        cmds.rotate(0, degreeApart, 0, r=True)

    cmds.select(myCore)
    cmds.rename('Flower')







flowerMaker()