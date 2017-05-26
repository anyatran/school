'''

This script is converted from a mel script that builds a flower. This will have customized shaders to color the object.
'''

import maya.cmds as mc

def flowerMaker():

	numPetals = 10
	createShader = 1

	shads = mc.ls(mat = 1)
	for node in shads:
		if (node == "petalColor"):
			createShader = 0

	if (createShader == 0):
		print "Shaders already exists\n"
	else:
		''' Set up petal shader '''
		mc.shadingNode("lambert", asShader=1, name="petalColor")
		mc.shadingNode("ramp", asTexture=1, name="petalRamp")
		mc.sets(renderable=1, noSurfaceShader=1, empty=1, n="petalColorSG")

		mc.connectAttr("petalColor.outColor", "petalColorSG.surfaceShader", f=1)
		mc.connectAttr("petalRamp.outColor", "petalColor.color")

		''' Set attributes for petal shader, we will make the petals pink '''
		mc.setAttr("petalRamp.colorEntryList[3].color", 1, 0.75, 0.8, type="double3")
		mc.setAttr("petalRamp.colorEntryList[3].position", 1)

		mc.setAttr("petalRamp.colorEntryList[2].color", 1, 0.75, 0.8, type="double3")
		mc.setAttr("petalRamp.colorEntryList[2].position", 0.5)

		mc.setAttr("petalRamp.colorEntryList[1].color", 1, 0.75, 0.8, type="double3")
		mc.setAttr("petalRamp.colorEntryList[1].position", 0)

		mc.setAttr("petalRamp.type", 8)

		''' Setup Shader for the core '''
		mc.shadingNode("lambert", asShader=1, name="coreColor")
		mc.shadingNode("ramp", asTexture=1, name="coreRamp")
		mc.sets(renderable=1, noSurfaceShader=1, empty=1, n="coreColorSG")

		mc.connectAttr("coreColor.outColor", "coreColorSG.surfaceShader", f=1)
		mc.connectAttr("coreRamp.outColor", "coreColor.color")

		''' Set attributes for core shader, we will make the core yellow '''
		mc.setAttr("coreRamp.colorEntryList[3].color", 1, 1, 0, type="double3")
		mc.setAttr("coreRamp.colorEntryList[3].position", 1)

		mc.setAttr("coreRamp.colorEntryList[2].color", 1, 1, 0, type="double3")
		mc.setAttr("coreRamp.colorEntryList[2].position", 0.5)

		mc.setAttr("coreRamp.colorEntryList[1].color", 1, 1, 0, type="double3")
		mc.setAttr("coreRamp.colorEntryList[1].position", 0)

		mc.setAttr("coreRamp.type", 8)



	''' Build the flower core '''
	mc.sphere(ax=[0, 1, 0], n="core")
	myCore = mc.ls(sl=1)
	mc.scale(1, 0.5, 1)
	mc.move(0, 0.2, 0)

	mc.sets(n="core", edit=1, forceElement="coreColorSG")

	''' Build the flower petal '''
	mc.sphere(ax=[0, 1, 0])
	mc.move(0, 0, -1.6)
	mc.scale(0.7, 0.3, 1.7)
	mc.FreezeTransformations;
	mc.ResetTransformations;

	myPetal = mc.ls(sl=1)
	mc.parent(myPetal, myCore)
	mc.select(myPetal[0])
	mc.pickWalk(d="down")
	myPetalShape = mc.ls(sl=1)
	mc.sets(myPetalShape[0], edit=1, forceElement="petalColorSG")

	''' Move the tip of the petals '''
	mc.select(myPetal[0] + ".cv[3] [7]")
	mc.move(0, 2, 0, r=1)

	''' Select the inner part of the petal '''
	for uCV in range (5, 7):
		for vCV in range (0, 8):
			mc.select(myPetal[0]+".cv["+str(uCV)+"]["+str(vCV)+"]")
			mc.move(0, -0.4, 0, r=1)

	mc.select(myPetal[0])
	degreeApart = (360 / numPetals)

	for i in range (2, numPetals + 1):
		mc.duplicate()
		mc.rotate(0, degreeApart, 0, r=1, p=[0, 0.2, 0])

	mc.select(myCore)
	mc.rename("Flower")

flowerMaker()

