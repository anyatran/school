''' 

	Chapter 6 - Maya bread and butter 

	This is a cheat cheet for some of the basic mel commands that are most used 

	ls - list objects in your scene
	get and setAttr - get and set attributes on your objects 
	xform - gets and sets transform information on your objects, heavier duty
	
'''

import maya.cmds as cmds

###################################################################################
# THE LS COMMAND 
###################################################################################

# the ls command is your bread and butter 
all_objects = cmds.ls() # this will list everything in the scene

selected_objects = cmds.ls(sl=True) # this will return your selection

all_pointlights = cmds.ls(type="pointLight") # this will return all objects of a specific type
'''
	some common types are:

	transform
	mesh
	joint 
'''

# you can also use the ls command to search for something by name 
properly_named_meshes = cmds.ls("*MSH") 

# if you don't know the type of the asset you want to find
# you can find it out with the objectType command
object_type = cmds.objectType("objectName")

###################################################################################
# THE GETATTR AND SETATTR COMMANDS
###################################################################################

# GETATTR lets you get any attribute from an object by name 
cmds.getAttr("pSphere1.translateY")

# you can store the getAttr values for later 
translate_x = cmds.getAttr("pShere1.translateX") 

# the setAttr command sets values for an attribute 
cmds.setAttr("pSphere1.translateY", 1)

###################################################################################
# THE XFORM COMMAND
###################################################################################

# the xform command lets you get and set some great information on the objects 

# set rotation of sphere
cmds.xform('pSphere1', relative=True, rotation=(0, 90, 0) )

# you can use it to center the pivot of an object too 

cmds.xform('pSphere1', centerPivots=True)

# the best way to grab an object in world space (it's true position in space) is 
# to grab it's rotate pivot
world_translation = cmds.xform("pCube1", query=True, worldSpace=True, rotatePivot=True)
cmds.xform("pCube2", ws=True, t=world_translation)