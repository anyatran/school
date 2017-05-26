# keyRotation.py

import maya.cmds as cmds

def keyFullRotation( pObjectName, pStartTime, pEndTime, pTargetAttribute ):
    
    cmds.cutKey( pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute )
    
    cmds.setKeyframe( pObjectName, time=pStartTime, attribute=pTargetAttribute, value=0 )
    
    cmds.setKeyframe( pObjectName, time=pEndTime, attribute=pTargetAttribute, value=360 )
    
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
        
        keyFullRotation( objectName, startTime, endTime, 'rotateY' )

    
else:
    
    print 'Please select at least one object'