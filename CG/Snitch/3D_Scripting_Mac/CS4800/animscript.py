import maya.cmds as cmds

my_guy = 'polySurface21'

#Select my guy
cmds.select(my_guy, r=True)

#clear animation
start_time = cmds.playbackOptions(query=True, minTime=True)
end_time = cmds.playbackOptions(query=True, maxTime=True)

cmds.cutKey(time=(start_time, end_time))

#Rotate
cmds.rotate(0.0,90.0, 0.0,r=True, os=True)

#Scale
cmds.scale(1.1, 1.1, 1.1, r=True)

#Translate
cmds.move(0,3.0,0.0, r=True)

#Move camera
cmds.orbit('persp', ha=90)

inc = end_time/5

#Animated Part
for i in range(0,6):
    my_time = (end_time/5)*i
    my_rotation = (360/5)*i
    cmds.setKeyframe(my_guy, at='rotateY', v=my_rotation, t=my_time)
    
cmds.keyTangent(my_guy, at='rotateY', itt='linear', ott='linear', animation='objects')