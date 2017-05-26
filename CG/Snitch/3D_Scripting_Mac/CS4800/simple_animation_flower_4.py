import maya.cmds as cmds

def flowerMaker():
    numPetals = 10
    createShader = 1;

    shads = cmds.ls(selection=True, mat=True)
    for node in shads:
        if node == "petalColor":
            createShader = 0


    if createShader == 0:
        print "The Shader is already there"
    elif createShader == 1:
        cmds.shadingNode("lambert", asShader=True, n="petalColor")
        cmds.shadingNode("ramp", asTexture=True, n="petalRamp")
        cmds.sets(renderable=True, noSurfaceShader=True, empty=True, n="petalColorSG")
        if cmds.isConnected("petalRamp.outColor", "petalColor.color") != True:
            cmds.connectAttr("petalRamp.outColor", "petalColor.color")
        
        cmds.setAttr("petalRamp.colorEntryList[3].color", 1, 1, 0, type="double3")
        cmds.setAttr("petalRamp.colorEntryList[3].position",  1)
        cmds.setAttr("petalRamp.colorEntryList[2].color", 1, 1, 0, type="double3")
        cmds.setAttr("petalRamp.colorEntryList[2].position", .5)
        cmds.setAttr("petalRamp.colorEntryList[1].color", 1, 1, 1, type="double3")
        cmds.setAttr("petalRamp.colorEntryList[1].position",  1)
        cmds.setAttr("petalRamp.type", 8)
        
    cmds.sphere(ax=[0, 1, 0], n="core")
    cmds.scale(.8, .5, .8)
    cmds.move(0, .2, 0)

    #build petal
    cmds.sphere(ax=[0, 1, 0], n="petal")
    cmds.move(0, 0, -1.6)
    cmds.scale(.7, .3, 1.7)

    myPetal = ["petal"]
    cmds.parent(myPetal, "core")
    cmds.select(myPetal[0])

    cmds.pickWalk(d="down")
    cmds.sets("petal", forceElement="petalColorSG", edit=True)
    cmds.sets("core", forceElement="petalColorSG", edit=True)

    cmds.select(myPetal[0]+".cv[3] [7]")
    cmds.move(0, 2, 0, r=True)

    for i in range(5, 7):
        for j in range(0, 8):
            cmds.select(myPetal[0]+".cv["+str(i)+"]["+str(j)+"]")
            cmds.move(0, -.4, 0, r=True)

    cmds.select(myPetal[0])
    incrementVal = (360/numPetals)
    degreesApart = incrementVal
    for k in range(2, numPetals+1):
        cmds.duplicate()
        cmds.rotate(0, degreesApart, 0, pivot=(0, 0, 0))
        degreesApart = degreesApart + incrementVal

    cmds.select("core")
    cmds.rename("Flower")
    cmds.select("Flower")

def animateFlower():

    cmds.playbackOptions(min=1, max=260)

    cmds.setKeyframe("petal", time=10, attribute="rotateX", value=0)
    cmds.setKeyframe("petal", time=1, attribute="translateY", value=0)
    cmds.setKeyframe("petal", time=24, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal", time=48, attribute="translateY", value=-8)

    cmds.setKeyframe("petal5", time=10, attribute="rotateX", value=0)
    cmds.setKeyframe("petal5", time=1, attribute="translateY", value=0)
    cmds.setKeyframe("petal5", time=24, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal5", time=48, attribute="translateY", value=-12)

    cmds.setKeyframe("petal4", time=34, attribute="rotateX", value=0)
    cmds.setKeyframe("petal4", time=25, attribute="translateY", value=0)
    cmds.setKeyframe("petal4", time=48, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal4", time=96, attribute="translateY", value=-18)

    cmds.setKeyframe("petal9", time=34, attribute="rotateX", value=0)
    cmds.setKeyframe("petal9", time=25, attribute="translateY", value=0)
    cmds.setKeyframe("petal9", time=48, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal9", time=96, attribute="translateY", value=-22)

    cmds.setKeyframe("petal8", time=58, attribute="rotateX", value=0)
    cmds.setKeyframe("petal8", time=49, attribute="translateY", value=0)
    cmds.setKeyframe("petal8", time=96, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal8", time=120, attribute="translateY", value=-15)

    cmds.setKeyframe("petal7", time=106, attribute="rotateX", value=0)
    cmds.setKeyframe("petal7", time=97, attribute="translateY", value=0)
    cmds.setKeyframe("petal7", time=120, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal7", time=168, attribute="translateY", value=-6)

    cmds.setKeyframe("petal3", time=130, attribute="rotateX", value=0)
    cmds.setKeyframe("petal3", time=121, attribute="translateY", value=0)
    cmds.setKeyframe("petal3", time=144, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal3", time=216, attribute="translateY", value=-10)

    cmds.setKeyframe("petal1", time=130, attribute="rotateX", value=0)
    cmds.setKeyframe("petal1", time=121, attribute="translateY", value=0)
    cmds.setKeyframe("petal1", time=144, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal1", time=216, attribute="translateY", value=-8)

    cmds.setKeyframe("petal6", time=154, attribute="rotateX", value=0)
    cmds.setKeyframe("petal6", time=145, attribute="translateY", value=0)
    cmds.setKeyframe("petal6", time=168, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal6", time=264, attribute="translateY", value=-9)

    cmds.setKeyframe("petal2", time=178, attribute="rotateX", value=0)
    cmds.setKeyframe("petal2", time=169, attribute="translateY", value=0)
    cmds.setKeyframe("petal2", time=192, attribute="rotateX", value=-55)
    cmds.setKeyframe("petal2", time=312, attribute="translateY", value=-14)

    cmds.keyTangent(inTangentType='linear', outTangentType='linear')
    cmds.play(forward=True)