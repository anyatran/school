'''
	cleanCombine

	
	This is your first complete script, it is a really handy script 
	to combine objecs in Maya. 
	
	It will combine the objects together, and also do a few cleanup steps 
	afterwards, like properly naming the objecs, maintaining the original layer, 
	moving the pivot back to where it was before. 

	You should place this script in your scripts directory
	
	C:\Users\YOUR_USERNAME\Documents\maya\2012\scripts
	
	and then make a shelf button with the following command 
	
	import cleanCombine      # this imports your module, just like we import maya.cmds 
	cleanCombine.combine()   # this calls your function to run 
	
'''

import maya.cmds as cmds

def combine():
	'''
		Combines the selected objects cleanly
	'''
	
	# grab your selection using the ls command 
	selection = cmds.ls(sl=True)
	
	# the object that is your "source" is going to be the first one you selected 
	target_object = selection[0]

	# if you want your source object to be the last one selected, comment the line above
	# and uncomment the following line
	
	# target_object = selection[-1] # the -1 array lookup means the last item of the array 

	# let's give the user some feedback onto what's happening
	print "The Target Object is ", target_object 	
	
	# store the pivot information, so we can restore it in the future 
	pivot = cmds.xform(target_object, query=True, worldSpace=True, rotatePivot=True)
	
	# store the displayLayer for the same reason
	displayLayers = cmds.listConnections(target_object, type="displayLayer")

	# combine the meshes, with disabled construction history
	# this will get rid of the empty nodes that get left behind
	# and store the resulting new mesh name as a variable, so we can mess with it 
	new_mesh = cmds.polyUnite(ch=False)

	# move the pivot back into place
	cmds.xform(new_mesh, rotatePivot=pivot)

	# if the object was a part of any display layers
	#  add the new object back to that layer
	if displayLayers:
	
		# the listConnections command will give us an array back of all of the different 
		# connections, but since we can only be in one layer at a time, we know 
		# that our layer is going to be the first one. 
		# that's why we put the [0] after displayLayers
		cmds.editDisplayLayerMembers(displayLayers[0], new_mesh)


	# rename the new mesh to it's original name 
	cmds.rename(new_mesh, target_object)
	
	print "Done!"