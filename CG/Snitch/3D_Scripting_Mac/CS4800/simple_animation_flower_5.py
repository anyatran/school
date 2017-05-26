import maya.cmds as cmds
import random

#to define a flowerMaker function
def flowerMaker():
 numPetals = 10         #Number of petals
 createShader = 1       

#to check if the shader exists
 shads = cmds.ls(mat=True)
 for myNode in shads:
	if myNode == "petalColor":
		createShader = 0

 if createShader == 0:
	print "The Shader is already there \n"
 elif createShader == 1:
#to build our shader network
	cmds.shadingNode('lambert', n='petalColor', asShader=True)
	cmds.shadingNode('ramp', n="petalRamp", asTexture=True)
	cmds.sets(renderable=True, noSurfaceShader=True, empty=True, n='petalColorSG')
	cmds.connectAttr('petalColor.outColor','petalColorSG.surfaceShader', f=True)
	cmds.connectAttr('petalRamp.outColor','petalColor.color')
	cmds.setAttr('petalRamp.colorEntryList[3].color', 1, 0, 0, type = 'double3')
	cmds.setAttr('petalRamp.colorEntryList[3].position', 1)
	cmds.setAttr('petalRamp.colorEntryList[2].color', 0.9, 0, 1, type = 'double3')
	cmds.setAttr('petalRamp.colorEntryList[2].position', .5)
	cmds.setAttr('petalRamp.colorEntryList[1].color', 1, 0, 0, type = 'double3')
	cmds.setAttr('petalRamp.colorEntryList[1].position', 0)
	cmds.setAttr('petalRamp.type', 8)
	
#to build the flower core
 cmds.sphere(n= 'core', ax=(0,1,0))
 myCore = cmds.ls(sl=True)
 cmds.sets(myCore[0], edit=True, forceElement='initialShadingGroup')
 cmds.setAttr('lambert1.color', 1, 1, 0, type = 'double3')
 cmds.scale(1, .5, 1)
 cmds.move(0, 0.2, 0)
	
#to build the petal
 cmds.sphere(ax=(0,1,0))
 cmds.move(0,0,-1.6)
 cmds.scale(.7,.3,1.7)
 cmds.FreezeTransformations()
 cmds.ResetTransformations()

 myPetal = cmds.ls(sl=True)
 cmds.parent(myPetal, myCore)
 cmds.select(myPetal[0])
 cmds.pickWalk(d='down')
 myPetalShape = cmds.ls(sl=True)
 cmds.sets(myPetalShape[0],edit=True, forceElement='petalColorSG')

#to move the tip of the petal
 cmds.select(myPetal[0]+'.cv[3][7]')
 cmds.move(0,2,0, r=True)

#to select the inner part of the petal pull down
#One loop for the U direction
 for uCV in range(5,7):
	 for vCV in range(0,8):
		 cmds.select(myPetal[0]+'.cv['+str(uCV)+']['+str(vCV)+']')
		 cmds.move(0,-0.4,0, r=True) 

#to build the flower by 10 petals
 cmds.select(myPetal[0])
 degreeApart = 360/numPetals
 for i in range(2,numPetals + 1):
	 cmds.duplicate()
	 cmds.rotate(0, degreeApart, 0, r=True)
	
 cmds.select(myCore)
 cmds.rename('Flower')
flowerMaker()

#Animation
#my list of flowers
flowers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]

#to set playback time
cmds.playbackOptions( minTime='0', maxTime='600' )

xpos = -11		#the beginning edge of the flower storm 

for flower in flowers:
 scalenum = random.uniform(0.4, 0.8)	#the scale value for flowers
 cmds.select('Flower')
 cmds.duplicate()
 cmds.scale(scalenum,scalenum,scalenum, r=True)			#to scale the flower randomly
 cmds.move(xpos, 0.2, random.randrange(-10, 10, 1)) 	#to move the flower randomly
 xpos = xpos + 2.5			#to move to the next x-position
 #to rotate flowers to the left or to the right
 if random.randint(0,1) == 0:
   cmds.expression(s = 'Flower' + flower + '.rotateY = time * ' + str(random.randrange(10, 30, 1)), o = 'Flower' + flower, ae = 1, uc = all)
 else:
   cmds.expression(s = 'Flower' + flower + '.rotateY = time * ' + str(random.randrange(-30, -10, 1)), o = 'Flower' + flower, ae = 1, uc = all)
cmds.select('Flower')
cmds.delete()	#to delete the initial flower
