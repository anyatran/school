import maya.cmds as cmds

def flowerMaker():
	numPetals = 10
	createShader = 1
	  
	# Check for Shader
	shads=cmds.ls(materials=True)
	for myNode in shads:
		if myNode=='petalColor':
			createShader==0
			
	if createShader==0:
		print 'The Shader is already there \n'
		
	else:
		# build our shader network
		cmds.shadingNode('lambert', asShader=True, n="petalColor")
		cmds.shadingNode('ramp', asTexture=True, n="petalRamp")
		
		cmds.sets(renderable=True,empty=True,noSurfaceShader=True,n='petalColorSG')
		if 	cmds.isConnected('petalColor.outColor', 'petalColorSG.surfaceShader'):
			print 'Already connected to a shader'
		else:
			cmds.connectAttr('petalColor.outColor', 'petalColorSG.surfaceShader', f=True)
			cmds.connectAttr('petalRamp.outColor','petalColor.color')			
		
		cmds.setAttr("petalRamp.colorEntryList[3].color",1,0,1,type='double3')	
		cmds.setAttr("petalRamp.colorEntryList[3].position",1)
		
		cmds.setAttr("petalRamp.colorEntryList[2].color",0,0,1,type='double3')
		cmds.setAttr("petalRamp.colorEntryList[2].position",0.5)
		
		cmds.setAttr("petalRamp.colorEntryList[1].color",0,0,1,type='double3')
		cmds.setAttr("petalRamp.colorEntryList[1].position",0)
				
	#build the flower core
	cmds.sphere(axis = [0, 1, 0])
	myCore=cmds.ls(selection=True)
	cmds.scale(1, .5, 1)
	cmds.move(0, 0.2, 0)
	cmds.sets(fe='petalColorSG')
	
	#build the petal
	cmds.sphere(axis = [0, 1, 0])
	cmds.move(0, 0, -1.6)
	cmds.scale(.7, .3, 1.7)
	cmds.makeIdentity(apply=True)
	cmds.makeIdentity()
	
	myPetal=cmds.ls(selection=True)
	cmds.parent(myPetal, myCore)
	cmds.select(myPetal[0])
	cmds.pickWalk(direction='down')
	myPetalShape=cmds.ls(selection=True)
	cmds.sets(fe='petalColorSG')
	
	#move the tip of the petal
	cmds.select(myPetal[0]+".cv[3] [7]")
	cmds.move(0, 2, 0, r=True)
	
	#Select the inner part of the petal pull down
	#One loop for the U direction
	for uCV in range (5, 7):
		for vCV in range (0, 8):
			cmds.select(myPetal[0]+".cv["+str(uCV)+"] ["+str(vCV)+"]")
			cmds.move(0, -0.4, 0, r=True)
			
	cmds.select(myPetal[0])
	degreeApart=(360/numPetals)
	for i in range (2, numPetals+1):
		cmds.duplicate()
		cmds.rotate(0, degreeApart, 0, r=True)

	cmds.select(myCore)
	cmds.rename('Flower')

	for tx in range (0, 18):
		cmds.setKeyframe('Flower', attribute="translateX", value=tx*1.2, t=tx)
		cmds.setKeyframe('Flower', attribute="translateY", value=tx*.9, t=tx)
		cmds.setKeyframe('Flower', attribute="translateZ", value=tx*1.2, t=tx)

	return
	
flowerMaker();

cmds.play()