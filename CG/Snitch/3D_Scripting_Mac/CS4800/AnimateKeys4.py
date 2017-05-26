#AnimateKeys.py

import maya.cmds as cmds

def keyClearAll( keyTimeMax ):
    cmds.cutKey( 'Hand_Left', time=(0,800) )
    cmds.cutKey( 'Hand_Right', time=(0,800) )
    cmds.cutKey( 'Hips', time=(0,800) )
    cmds.cutKey( 'Iris_Left', time=(0,800) )
    cmds.cutKey( 'Iris_Right', time=(0,800) )
    cmds.cutKey( 'Animation_Camera', time=(0,800) )
keyClearAll( 800 )

def keyRobot( ):
    cmds.setKeyframe( 'Hand_Left',  time=0, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hand_Right', time=0, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=0, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=0, attribute='tz', value=0 )
    cmds.setKeyframe( 'Iris_Left',  time=0, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Left',  time=0, attribute='scaleZ', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=0, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=0, attribute='scaleZ', value=1 )
    
    cmds.setKeyframe( 'Hand_Left',  time=100, attribute='rz', value=20 )
    cmds.setKeyframe( 'Hand_Right', time=100, attribute='rz', value=20 )
    cmds.setKeyframe( 'Iris_Left',  time=100, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Left',  time=100, attribute='scaleZ', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=100, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=100, attribute='scaleZ', value=0.5 )
    
    cmds.setKeyframe( 'Hand_Left',  time=200, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hand_Right', time=200, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=200, attribute='rz', value=10 )
    cmds.setKeyframe( 'Hips',       time=200, attribute='tz', value=2 )
    cmds.setKeyframe( 'Iris_Left',  time=200, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Left',  time=200, attribute='scaleZ', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=200, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=200, attribute='scaleZ', value=1 )
    
    cmds.setKeyframe( 'Hand_Left',  time=300, attribute='rz', value=-20 )
    cmds.setKeyframe( 'Hand_Right', time=300, attribute='rz', value=-20 )
    cmds.setKeyframe( 'Iris_Left',  time=300, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Left',  time=300, attribute='scaleZ', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=300, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=300, attribute='scaleZ', value=0.5 )
    
    cmds.setKeyframe( 'Hand_Left',  time=400, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hand_Right', time=400, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=400, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=400, attribute='tz', value=0 )
    cmds.setKeyframe( 'Iris_Left',  time=400, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Left',  time=400, attribute='scaleZ', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=400, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=400, attribute='scaleZ', value=1 )
    
    cmds.setKeyframe( 'Hand_Left',  time=500, attribute='rz', value=20 )
    cmds.setKeyframe( 'Hand_Right', time=500, attribute='rz', value=20 )
    cmds.setKeyframe( 'Iris_Left',  time=500, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Left',  time=500, attribute='scaleZ', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=500, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=500, attribute='scaleZ', value=0.5 )
    
    cmds.setKeyframe( 'Hand_Left',  time=600, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hand_Right', time=600, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=600, attribute='rz', value=-10 )
    cmds.setKeyframe( 'Hips',       time=600, attribute='tz', value=-2 )
    cmds.setKeyframe( 'Iris_Left',  time=600, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Left',  time=600, attribute='scaleZ', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=600, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=600, attribute='scaleZ', value=1 )
    
    cmds.setKeyframe( 'Hand_Left',  time=700, attribute='rz', value=-20 )
    cmds.setKeyframe( 'Hand_Right', time=700, attribute='rz', value=-20 )
    cmds.setKeyframe( 'Iris_Left',  time=700, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Left',  time=700, attribute='scaleZ', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=700, attribute='scaleX', value=0.5 )
    cmds.setKeyframe( 'Iris_Right', time=700, attribute='scaleZ', value=0.5 )
    
    cmds.setKeyframe( 'Hand_Left',  time=800, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hand_Right', time=800, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=800, attribute='rz', value=0 )
    cmds.setKeyframe( 'Hips',       time=800, attribute='tz', value=0 )
    cmds.setKeyframe( 'Iris_Left',  time=800, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Left',  time=800, attribute='scaleZ', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=800, attribute='scaleX', value=1 )
    cmds.setKeyframe( 'Iris_Right', time=800, attribute='scaleZ', value=1 )
#keyRobot( )

def keyHome_Camera( keyTime ):
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tx', value=44 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='ty', value=10 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tz', value=35 )

def keyHome2_Camera( keyTime ):
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tx', value=0 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='ty', value=14 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tz', value=55 )

def keyHome3_Camera( keyTime ):
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tx', value=-44 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='ty', value=10 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tz', value=35 )

def keyLow_Camera( keyTime ):
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tx', value=37 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='ty', value=-20 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tz', value=37 )

def keyHead_Camera( keyTime ):
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tx', value=-10 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='ty', value=11 )
    cmds.setKeyframe( 'Animation_Camera', time=keyTime, attribute='tz', value=15 )

def keyTangentAll( ):
    cmds.keyTangent( 'Hand_Right', inTangentType='clamped', outTangentType='clamped' )
    cmds.keyTangent( 'Hand_Left', inTangentType='clamped', outTangentType='clamped' )
    cmds.keyTangent( 'Hips', inTangentType='clamped', outTangentType='clamped' )
    cmds.keyTangent( 'Animation_Camera', inTangentType='clamped', outTangentType='clamped' )
#keyTangentAll( )


keyClearAll( 800 )
keyRobot( )
keyHome_Camera( 1 )
keyHome2_Camera( 200 )
keyHome3_Camera( 400 )
keyHome2_Camera( 600 )
keyHome_Camera( 800 )
#keyLow_Camera( 400 )
#keyHead_Camera( 400 )

keyTangentAll( )

