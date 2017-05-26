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
		
		cm.setAttr("petalRamp.colorEntryList[3].color", 0, 1, 1, type="double3")
		cm.setAttr("petalRamp.colorEntryList[3].position", 1)
				
		cm.setAttr("petalRamp.colorEntryList[2].color", 0.07, 0.18, 0.33, type="double3")
		cm.setAttr("petalRamp.colorEntryList[2].position", 0.5)
		
		cm.setAttr("petalRamp.colorEntryList[1].color", 0, 1, 1, type="double3")
		cm.setAttr("petalRamp.colorEntryList[1].position", 0)
		cm.setAttr("petalRamp.type", 8)
		
		
		cm.shadingNode("lambert", asShader=True, name="coreColor") 
		cm.shadingNode("ramp", asTexture=True, name="coreRamp") 
		cm.sets(renderable=True, noSurfaceShader=True, empty=True, n="coreColorSG")
		cm.connectAttr("coreColor.outColor", "coreColorSG.surfaceShader", force=True)
		cm.connectAttr("coreRamp.outColor", "coreColor.color")
		
		cm.setAttr("coreRamp.colorEntryList[3].color", 0.78, 0.78, 0.04, type="double3")
		cm.setAttr("coreRamp.colorEntryList[3].position", 1)
				
		cm.setAttr("coreRamp.colorEntryList[2].color", 0.78, 0.78, 0.04, type="double3")
		cm.setAttr("coreRamp.colorEntryList[2].position", 0.5)
		
		cm.setAttr("coreRamp.colorEntryList[1].color", 0.78, 0.78, 0.04, type="double3")
		cm.setAttr("coreRamp.colorEntryList[1].position", 0)
		cm.setAttr("coreRamp.type", 8)
		
		
		
		
		
		
	#build the flower core
	cm.sphere(ax=[0,1,0], name="core")
	myCore = cm.ls(sl=True)
	cm.sets(myCore, edit=True, forceElement="coreColorSG")
	cm.scale(1,0.5,1)
	cm.move(0,0.5,0)
	
	#build the petal
	cm.sphere(ax=[0,2,0])
	cm.move(0,0.3,-1.6)
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
	keyFullRotation("Flower", 0, 180, 360, "rotateY")
	generatePetals(myPetal[0])
	
	
	
	# keyRotation.py

import maya.cmds as cmds

def keyFullRotation( pObjectName, pStartTime, pEndTime, pTargetValue, pTargetAttribute ):    
    cmds.cutKey( pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute )    
    cmds.setKeyframe( pObjectName, time=pStartTime, attribute=pTargetAttribute, value=0 )    
    cmds.setKeyframe( pObjectName, time=pEndTime, attribute=pTargetAttribute, value=pTargetValue )    
    cmds.selectKey( pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute, keyframe=True )    
    cmds.keyTangent( inTangentType='linear', outTangentType='linear' )
    
selectionList = cmds.ls( selection=True, type='transform' )

if len( selectionList ) >= 1:    
    # print 'Selected items: %s' % ( selectionList )    
    startTime = cmds.playbackOptions( query=True, minTime=True )
    endTime = cmds.playbackOptions( query=True, maxTime=True )
    for objectName in selectionList:
        
        # objectTypeResult = cmds.objectType( objectName )        
        # print '%s type: %s' % ( objectName, objectTypeResult )        
        keyFullRotation( objectName, startTime, endTime,360, 'rotateY' )    
else:    
    print 'Please select at least one object'
	
	
	
# randomInstances.py

import maya.cmds as cmds
import random
import time


def generatePetals(obj):
	random.seed( time.time() )

	cmds.select(obj)
	result = cmds.ls( orderedSelection=True )

	print 'result: %s' % ( result )

	transformName = result[0]

	instanceGroupName = cmds.group( empty=True, name=transformName + '_instance_grp#' )
	keyFullRotation(instanceGroupName, 0, 180, 1080, "rotateY")

	for i in range( 0, 150 ):

		instanceResult = cmds.instance( transformName, name=transformName + '_instance#' )	
		cmds.select(instanceResult)
		cmds.parent( instanceResult, instanceGroupName)
		keyFullRotation(instanceResult, 0, 180, 1080, "rotateZ")	
		
		#print 'instanceResult: ' + str( instanceResult )
		
		x = random.uniform( -19, 19 )
		if x > 0:
		    x += 1
		else:
		    x -= 1
			
		y = random.uniform( 1, 40 ) 
		
		z = random.uniform( -17, 17 )
		if y > 0:
		    y += 3
		else:
		    y -= 3
		
		cmds.move( x, y, z, instanceResult )
		
		xRot = random.uniform( 0, 360 )
		yRot = random.uniform( 0, 360 )
		zRot = random.uniform( 0, 360 )
		
		cmds.rotate( xRot, yRot, zRot, instanceResult )
		
		scalingFactor = random.uniform( 0.3, 1.5 )
		
		cmds.scale( scalingFactor, scalingFactor, scalingFactor, instanceResult )

	#cmds.hide( transformName )

	cmds.xform( instanceGroupName, centerPivots=True )