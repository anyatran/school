'''

	mayaCornerstones.py
	
	This script is a cheat sheet for some of the more 
	useful commands in maya. 
	
	You might not use them in every script, but they always come in handy 

	listRelatives
	addAttr
	listConnections
	objExists	

	BONUS : A little API error reporting for you ! 
	
	import maya.OpenMaya as OpenMaya
	OpenMaya.MGlobal.displayWarning("Please select something!")
	OpenMaya.MGlobal.displayError("Please select something!")
'''


import maya.cmds as cmds

###############################################################################
# listRelatives 
###############################################################################

# listRelatives is awesome when you're doing rigging tools lets you crawl the 
# hierarchy of objects pretty easily 
# you can also use the fullPath flag if you want to get the long name for the 
# return objects

# get the direct children of an object
direct_children = cmds.listRelatives()

# get the parent of an object
parent_objects = cmds.listRelatives(parent=True)

# get all children of an object 
all_children = cmds.listRelatives(allDescendents=True)

# get all children of an object 
all_parents = cmds.listRelatives(allParents=True)

# get the children of a certain type 
joints = cmds.listRelatives(allDescendents=True, type="joint")

###############################################################################
# listConnections 
###############################################################################

# listConnections is great for getting and setting shader assignments, 
# displayLayers  and other slightly more obscure attributes (animCurves) 

# list all of the connected objects 
connections = cmds.listConnections()

# find which displayLayer an object is in
cmds.listConnections(type="displayLayer")

# get materials assigned to a mesh

def getMaterialsFromMesh():
    """
        materials are a bit tricky, the transform is the parent of the shape
        which is connected to different shading groups (or engines) which 
        are connected to the materials themselves. 
    """
    # make a new array 
    materials = []

    # get the mesh shape from the transform node
    shape = cmds.listRelatives(type="mesh")

    # get the shading groups, which are of type shadingengine
    shading_groups = cmds.listConnections(shape, type="shadingEngine")

    # iterate over the groups to get the materials
    for shading_group in shading_groups:

        # list all of the connections to the shading group
        connections = cmds.listConnections(shading_group)

        # filter that connection using the ls command 
        materials.extend(cmds.ls(connections, materials=True))

    # this is a trick to remove duplicate items
    materials = list(set(materials))

    print materials

###############################################################################
# addAttr
###############################################################################

# Adding custom attributes is super useful to add extra metadata on your 
# objects, this is particularly useful for exporters

cmds.addAttr( shortName='mya', longName='myattr')

###############################################################################
# getAttr
###############################################################################

# getAttr will read back your new attributes 
print cmds.getAttr("pSphere1.tx")

###############################################################################
# listAttr
###############################################################################

# listAttr will list all of the attributes on your object

print cmds.listAttr("pSphere1")

# but there is a special flag that you can find only attributes that you have 
# added 
print cmds.listAttr("pSphere1", ud=True)

###############################################################################
# connectAttr
###############################################################################

# connectAttr links two attributes together, this can be useful when you want # certain attributes to be driven by other attributes 

cmds.connectAttr("pointLightShape1.color", "pointLightShape2.color")

###############################################################################
# objExists
###############################################################################

# this is a simple little command that can identify if an object exist, 
# it's useful to check for naming conventions, things like, you have an object 
# that ends in _MESH but you also need an object that ends in _COL you can 
# check if that other exists with this command

print cmds.objExists("pSphere1")