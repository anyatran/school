import maya.cmds as cm

def flowerMaker():
	
	numPetals = 10
	createShader = 1
	
	#Check for Shader
	shads = cm.ls(materials=True)
	for myNode in shads:
		if myNode == "petalColor":
			createShader = 0
	
	if createShader == 0:
		print "The Shader is already there \n"
	elif createShader == 1:
		#build our shader network
		cm.shadingNode("lambert", asShader=True, name="petalColor") 
		cm.shadingNode("ramp", asTexture=True, name="petalRamp") 
		cm.sets(renderable=True, noSurfaceShader=True, empty=True, n="petalColorSG")
		cm.connectAttr("petalColor.outColor", "petalColorSG.surfaceShader", force=True)
		cm.connectAttr("petalRamp.outColor", "petalColor.color")
		
		cm.setAttr("petalRamp.colorEntryList[3].color", 1, 0, 0, type="double3")
		cm.setAttr("petalRamp.colorEntryList[3].position", 1)
				
		cm.setAttr("petalRamp.colorEntryList[2].color", 1, 1, 0, type="double3")
		cm.setAttr("petalRamp.colorEntryList[2].position", 0.5)
		
		cm.setAttr("petalRamp.colorEntryList[1].color", 1, 0, 0, type="double3")
		cm.setAttr("petalRamp.colorEntryList[1].position", 0)
		cm.setAttr("petalRamp.type", 8)
		
		
		
		
		
		
	#build the flower core
	cm.sphere(ax=[0,1,0], name="core")
	myCore = cm.ls(sl=True)
	cm.scale(1,0.5,1)
	cm.move(0,0.2,0)
	
	#build the petal
	cm.sphere(ax=[0,1,0])
	cm.move(0,0,-1.6)
	cm.scale(0.7,0.3,1.7)
	cm.FreezeTransformations()
	cm.ResetTransformations()
	
	myPetal = cm.ls(sl=True)
	cm.parent(myPetal, myCore)
	cm.select(myPetal[0])
	cm.pickWalk(d="down")
	myPetalShape = cm.ls(sl=True)
	cm.sets(myPetalShape[0], edit=True, forceElement="petalColorSG")
	
	#move the tip of the petal
	cm.select(myPetal[0]+".cv[3] [7]")
	cm.move(0,2,0, r=True)
	
	#Select the inner part of the petal pull down
	#One loop for the U direction
	for uCV in range(5,7):
		for vCV in range(0,8):
			cm.select(myPetal[0]+".cv[{0}][{1}]".format(uCV,vCV))
			cm.move(0,-0.4,0, r=True)
			
	cm.select(myPetal[0])
	degreeApart = 360/numPetals
	for i in range(2,numPetals+1):
		cm.duplicate()
		cm.rotate(0,degreeApart,0, r=True)
	
	
	cm.select(myCore)
	cm.rename("Flower")
	
	