# operations.py

import maya.cmds as cmds

def operations( pObjectName, pStartTime, pEndTime, pTargetAttribute, pVal ):
    
    cmds.cutKey( pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute )
    
    cmds.setKeyframe( pObjectName, time=pStartTime, attribute=pTargetAttribute, value=0 )
    
    cmds.setKeyframe( pObjectName, time=pEndTime, attribute=pTargetAttribute, value=pVal )
    
    cmds.selectKey( pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute, keyframe=True )
    
    cmds.keyTangent( inTangentType='linear', outTangentType='linear' )
    
def setCamera():
    cmds.camera()
    #sets the angle of the camera
    cmds.setAttr('persp.translateX', 50)
    cmds.setAttr('persp.translateY', 5.372)
    cmds.setAttr('persp.translateZ', 1.949)
 
    #sets the rotation of the camera
    cmds.setAttr('persp.rotateX', 351.700)
    cmds.setAttr('persp.rotateY', 92.000)
    cmds.setAttr('persp.rotateZ', 360.000)
    
selectionList = cmds.ls( selection=True, type='transform' )

if len( selectionList ) >= 1:
    
    
    startTime = cmds.playbackOptions( query=True, minTime=True )
    endTime = cmds.playbackOptions( query=True, maxTime=True )
    
    for objectName in selectionList:
        
        operations( objectName, startTime, endTime, 'rotateX', 360 )
        operations( objectName, startTime, endTime, 'translateX', 15 )
        cmds.scale(0.5,0.5,0.5, objectName)
        setCamera()

    
else:
    
    print 'Please select at least one object'