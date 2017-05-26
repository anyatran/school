import maya.cmds as cmds
import math
import random

def flowerMaker(nf):
	#erases anything on stage creates a new file
	#cmds.file( f=True, new=True)
	#sets plaback time
	numPetals=10
	createShader=1
	#Check for Shader
	shads=cmds.ls()
	for myNode in shads:
		if myNode == "petalColor":
			createShader=0
			
	if createShader == 0:
		print "The Shader is already there \n"
		
	elif createShader == 1:
		cmds.shadingNode('lambert', n="petalColor", asShader=True)
		cmds.shadingNode('ramp', asTexture=True, n="petalRamp")
		cmds.sets(renderable=True,empty=True,noSurfaceShader=True,n='petalColorSG')
		cmds.connectAttr('petalColor.outColor', 'petalColorSG.surfaceShader', f=True)
		cmds.connectAttr('petalRamp.outColor','petalColor.color')
		cmds.setAttr("petalRamp.colorEntryList[3].color",1,1,0.5,type='double3')
		cmds.setAttr("petalRamp.colorEntryList[3].position",1)
		cmds.setAttr("petalRamp.colorEntryList[2].color",1,1,1,type='double3')
		cmds.setAttr("petalRamp.colorEntryList[2].position",.5)
		cmds.setAttr("petalRamp.colorEntryList[1].color",1,0.5,0.5,type='double3')
		cmds.setAttr("petalRamp.colorEntryList[1].position",0)
		cmds.setAttr('petalRamp.type',8)
		
		cmds.shadingNode('lambert', n="coreColor", asShader=True)
		cmds.shadingNode('ramp', asTexture=True, n="coreRamp")
		cmds.sets(renderable=True,empty=True,noSurfaceShader=True,n='coreColorSG')
		cmds.connectAttr('coreColor.outColor', 'coreColorSG.surfaceShader', f=True)
		cmds.connectAttr('coreRamp.outColor','coreColor.color')
		cmds.setAttr("coreRamp.colorEntryList[3].color",1,1,0.5,type='double3')
		cmds.setAttr("coreRamp.colorEntryList[3].position",1)
		cmds.setAttr("coreRamp.colorEntryList[1].color",1,0.5,0.5,type='double3')
		cmds.setAttr("coreRamp.colorEntryList[1].position",0)
		cmds.setAttr('coreRamp.type',8)
		
	cmds.sphere(ax=(0, 1, 0),n="core")
	#build the flower core
	myCore=cmds.ls(sl=1)
	cmds.scale(1,.5,1)
	cmds.move(0,0.2,0)
	cmds.sets(myCore[0], edit=True, forceElement='coreColorSG')
	#build the petal
	cmds.sphere(ax=(0, 1, 0))
	cmds.move(0,0,-1.6)
	cmds.scale(.7,.3,1.7)
	cmds.FreezeTransformations()
	cmds.ResetTransformations()
	myPetal=cmds.ls(sl=1)
	cmds.parent(myPetal, myCore, shape=True)
	cmds.select(myPetal[0])
	cmds.pickWalk(d='down')
	myPetalShape=cmds.ls(sl=1)
	cmds.sets(myPetalShape[0], edit=True, forceElement='petalColorSG')
	#move the tip of the petal
	cmds.select(myPetal[0] + ".cv[3][7]")
	cmds.move(0,2,0,r=1)
	#Select the inner part of the petal pull down
	#One loop for the U direction
	for uCV in range(5,7):
		for vCV in range(0,8):
			cmds.select(myPetal[0] + ".cv[" + str(uCV) + "][" + str(vCV) + "]")
			cmds.move(0,-0.4,0,r=1)
	
	cmds.select(myPetal[0])
	degreeApart=float((360 / numPetals))
	
	for k in range (3):
		if k is not 0:
			cmds.duplicate()
			cmds.scale((k % 2 + 1) * 0.5)
			cmds.rotate(k * 20, k * 20)
		for i in range(2,numPetals+1):
			cmds.duplicate()
			cmds.rotate(0,degreeApart,0,r=1)
			
	cmds.select(myCore)
	cmds.rename('Flower' + str(nf))
	
def flowerAnimation(nflowers):
	iniposx = []
	iniposz = []
	rotation = []
	translation = []
	floating = []
	offset = 750
	
	for i in range (nflowers):
		flower = flowerMaker(i)
		iniposx.append(random.randint(20,100) / 10.0 - 5)
		iniposz.append(random.randint(20,100) / 10.0 - 5)
		rotation.append(random.randint(20,120) / 10.0)
		translation.append(random.randint(20,50) / 10.0)
		floating.append(0)
		cmds.setKeyframe( 'Flower' + str(i), attribute="visibility", value= 0, t= 0)

		
	for tx in xrange(0, 1000 * nflowers):
		for nf in range(nflowers):
			if tx == 0:
				cmds.setKeyframe( 'Flower' + str(nf), attribute="visibility", value= 1, t= tx + (nf * offset))
			elif tx == 1000:
				cmds.setKeyframe( 'Flower' + str(nf), attribute="visibility", value= 0, t= tx + (nf * offset))
			if not floating[nf]:
				cmds.setKeyframe( 'Flower' + str(nf), attribute="translateY", value= 15 - tx/50.0, t= tx + (nf * offset))
				cmds.setKeyframe( 'Flower' + str(nf), attribute="rotateX", value= rotation[nf] * math.sin(tx/100.0), t= tx + (nf * offset))
				cmds.setKeyframe( 'Flower' + str(nf), attribute="rotateZ", value= rotation[nf] * math.cos(tx/100.0), t= tx + (nf * offset))
			cmds.setKeyframe( 'Flower' + str(nf), attribute="rotateY", value= (tx/(2 * floating[nf]+1)), t= tx + (nf * offset))
			cmds.setKeyframe( 'Flower' + str(nf), attribute="translateX", value= iniposx[nf] - translation[nf]  * math.sin(tx/100.0), t= tx + (nf * offset))
			cmds.setKeyframe( 'Flower' + str(nf), attribute="translateZ", value= iniposz[nf] - translation[nf] * math.cos(tx/100.0), t= tx + (nf * offset))
			if (tx == 660):
				floating[nf] = 1
				cmds.setKeyframe( 'Flower' + str(nf), attribute="rotateX", value= 0, t= tx + (nf * offset))
				cmds.setKeyframe( 'Flower' + str(nf), attribute="rotateZ", value= 0, t= tx + (nf * offset))

FLOWERS = 5
cmds.playbackOptions( minTime='0', maxTime=str(750*FLOWERS))
flowerAnimation(FLOWERS)
cmds.play
