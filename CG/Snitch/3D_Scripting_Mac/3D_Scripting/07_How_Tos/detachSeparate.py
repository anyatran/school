'''
	
	detachSeparate.py
	
	cleanly separates faces out of meshes 

	The code will have to store the selected faces
	
'''

# import your maya 
import maya.cmds as cmds
import maya.OpenMaya as OpenMaya

# create 2 dictionaries that you will use the future to store data
face_objects = {}
dupe_objects = {}


# list all of the selected faces 
# note: look up in the documentation what 34 means here, they list out all of the values 
selected_faces = cmds.filterExpand(selectionMask=34) 

# bail if we don't have selected faces 
if not selected_faces or not len(selected_faces):
    OpenMaya.MGlobal.displayError("Please select some faces!")

# now we need to figure out what objects we have selected
# we can use a dictionary to store a list of objects
# that have a corresponding list of faces 
for face in selected_faces:

	# the split command will split the string into an array
	# it will use the "." we give it, as the breaking point
	
	# so if we have pSphere1.f[12] as the input
	# it will put pSphere1 in the object variable
	# and f[12] in the face_index
	
    object, face_index = face.split(".")

    # if the object is not in our dictionary
    # make a new entry for it
	# the idea is to end up with a dictionary like this
	# {'pSphere1':['f[15]', 'f[12]']}
    if object not in face_objects:
        face_objects[object] = []

    face_objects[object].append(face_index)
    
    
# then we should duplicate the objects
# the idea is to end up with 2 objects 
# one with the faces and one with the hole
for object in face_objects:
    dupe_objects[object] = cmds.duplicate(object)[0]

# delete the current selected faces, so we make a hole 
# in the original object 
cmds.delete(selected_faces)

new_objects = []


# now we select all the faces on the duped object
# deselect the original faces, and nuke it from space

for object in dupe_objects:
    # select all the faces on the new object 
    cmds.select(dupe_objects[object] + ".f[*]")
    
    for face in face_objects[object]:
        cmds.select(dupe_objects[object] +"." +  face, d=True)

    cmds.delete(cmds.ls(sl=True))
    new_obj = cmds.rename(dupe_objects[object], object + "_1")
    cmds.xform(new_obj, cp=True)
    new_objects.append(new_obj)
	
cmds.selectMode(object=True)
cmds.select(new_objects)
