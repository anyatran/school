import maya.cmds as c

def makeFlower():

	numPetals = 10
	createShader = True

	# check for shader
	shads = c.ls(mat=True)
	for myNode in shads:
		if myNode == "petalColor":
			createShader = False;

	# switch statement
	if createShader == False:
		print "The Shader is already there \n"
	elif createShader == True:
		# build our shader network
		c.shadingNode('lambert', n="petalColor", asShader=True)
		c.shadingNode('ramp', n="petalRamp", asTexture=True)
		c.sets(renderable=True, noSurfaceShader=True, empty=True, n="petalColorSG")
		c.connectAttr("petalColor.outColor", "petalColorSG.surfaceShader", force=True)
		c.connectAttr("petalRamp.outColor", "petalColor.color")

		c.shadingNode('lambert', n="coreColor", asShader=True)
		c.sets(renderable=True, noSurfaceShader=True, empty=True, n="coreColorSG")
		c.connectAttr("coreColor.outColor", "coreColorSG.surfaceShader", force=True)

		c.setAttr("coreColor.color", 1, 1, 0.126, type="double3")

		c.setAttr("petalRamp.colorEntryList[3].color", 1, 1, 1, type="double3")
		c.setAttr("petalRamp.colorEntryList[3].position", 1)

		c.setAttr("petalRamp.colorEntryList[2].color", 1, 0.593, 0.551, type="double3")
		c.setAttr("petalRamp.colorEntryList[2].position", .5)

		c.setAttr("petalRamp.colorEntryList[1].color", 1, 0.198, 0.419, type="double3")
		c.setAttr("petalRamp.colorEntryList[1].position", 0)
		c.setAttr("petalRamp.type", 8)

	# build the flower core
	c.sphere(ax=(0, 1, 0), n="core")
	myCore = c.ls(sl=True)
	c.scale(1, .5, 1)
	c.move(0, 0.2, 0)

	# build the petal
	c.sphere(ax=(0, 1, 0))
	c.move(0, 0, -1.6)
	c.scale(.7, .3, 1.7)

	c.makeIdentity(apply=True, t=1, r=1, s=1, n=0)
	c.makeIdentity(apply=False, t=1, r=1, s=1)

	myPetal = c.ls(sl=True)
	c.sets(myCore, edit=True, forceElement="coreColorSG")
	c.parent(myPetal, myCore)
	c.select(myPetal[0])
	c.pickWalk(d="down")
	myPetalShape = c.ls(sl=True)
	c.sets(myPetalShape[0], edit=True, forceElement="petalColorSG")

	# move the tip of the petal
	c.select(myPetal[0]+".cv[3] [7]")
	c.move(0, 2, 0, r=True)

	# select the inner part of the petal pull down
	# one loop for the U direction
	for uCV in range(5, 7):
		for vCV in range(0, 8):
			c.select(myPetal[0] + ".cv[" + str(uCV) + "][" + str(vCV) + "]")
			c.move(0, -0.4, 0, r=True)

	c.select(myPetal[0])

	degreeApart = (360/numPetals)

	for i in range(2, numPetals+1):
		c.duplicate()
		c.rotate(0, degreeApart, 0, r=True)

	c.select(myCore)
	c.rename("Flower")

makeFlower();

# twirl flower
for tx in xrange(0,900):
	c.setKeyframe('Flower', attribute="rotateY", value=tx, t= tx)

c.play()