# AnimateFox.py

import maya.cmds as cmds

selectionList = cmds.ls (selection=True, type='transform')

if len(selectionList) >= 1:
     
    startTime = cmds.playbackOptions (query=True, minTime=True)
    endTime = cmds.playbackOptions (query=True, maxTime=True)
    
    for objectName in selectionList:
           
           #Rotates, translates and scales through keyframes
           cmds.cutKey(objectName, time=(startTime, endTime), attribute='rotateY')
           cmds.cutKey(objectName, time=(startTime, endTime), attribute='scaleX')
           #Keyframe 1
           cmds.setKeyframe(objectName, time=startTime, attribute='rotateY', value=0)
           cmds.scale(1, 1, 1, objectName)
           cmds.setKeyframe(objectName, time=startTime, attribute='translateY', value=50)
           #Keyframe 2
           cmds.setKeyframe(objectName, time=endTime/4, attribute='scaleX', value=2)
           cmds.setKeyframe(objectName, time=endTime/4, attribute='translateY', value=100)
           #Keyframe 3
           cmds.setKeyframe(objectName, time=endTime/2, attribute='scaleX', value=4)
           cmds.setKeyframe(objectName, time=endTime/2, attribute='translateY', value=150)
           #Keyframe 4
           cmds.setKeyframe(objectName, time=endTime/1.3333, attribute='scaleX', value=2)
           cmds.setKeyframe(objectName, time=endTime/1.3333, attribute='translateY', value=100)
           #Keyframe 5
           cmds.setKeyframe(objectName, time=endTime, attribute='rotateY', value=360)
           cmds.scale(.25, 1, 1, objectName)
           cmds.setKeyframe(objectName, time=endTime, attribute='translateY', value=50)
           #Linearize Rotation Speed
           cmds.selectKey(objectName, time=(startTime, endTime), attribute='rotateY', keyframe=True)
           cmds.keyTangent(inTangentType='linear', outTangentType='linear')
           
           #sets the angle of the camera
           cmds.setAttr('persp.translateX', 300)
           cmds.setAttr('persp.translateY', 100)
           cmds.setAttr('persp.translateZ', 300)
           
           #sets the rotation of the camera
           cmds.setAttr('persp.rotateX', 0)
           cmds.setAttr('persp.rotateY', 45.000) 
           cmds.setAttr('persp.rotateZ', 0.000)