#Billy's Balloon
import maya.cmds as cmds

#erases anything on stage creates a new file
newfile = cmds.file( f=True, new=True)

#sets playback time
cmds.playbackOptions( minTime='0', maxTime='1200', ast=0, ps=1.8)

#sets the angle of the camera
cmds.setAttr('persp.translateX', 50)
cmds.setAttr('persp.translateY', 7)
cmds.setAttr('persp.translateZ', 0)

#sets the rotation of the camera
cmds.setAttr('persp.rotateX', 0)
cmds.setAttr('persp.rotateY', 90)
cmds.setAttr('persp.rotateZ', 0)

#a function to apply color to objects 
def applyColor(objectName, colorR, colorG, colorB):
    myShader = cmds.shadingNode('blinn', n='myShader', asShader=True)
    cmds.select(objectName)
    cmds.setAttr( myShader +'.color',colorR, colorG, colorB, type='double3')
    shaderSet = cmds.sets(renderable=True, noSurfaceShader=True, empty=True)
    cmds.connectAttr(myShader +".outColor", shaderSet+".surfaceShader")
    cmds.sets(forceElement=shaderSet, e=True)
    
#a function to animate the objects with given attribute
def keyAnimation(pObjectName, pStartTime, pEndTime, pTargetAttribute, pStartValue, pEndValue):
    cmds.cutKey(pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute)
    cmds.setKeyframe(pObjectName, time=pStartTime, attribute=pTargetAttribute, value=pStartValue)
    cmds.setKeyframe(pObjectName, time=pEndTime, attribute=pTargetAttribute, value=pEndValue)
    cmds.selectKey(pObjectName, time=(pStartTime, pEndTime), attribute=pTargetAttribute, keyframe=True)
    cmds.keyTangent(inTangentType='linear', outTangentType='linear')

# create the models 
bhead = cmds.polySphere(name='bhead', r=1.8)
bstring = cmds.polyCylinder(r=0.1, h=7, name='bstring')
head = cmds.polySphere(name='head', r=1.3)
eye_l = cmds.polySphere(name='eye1', r=0.4)
eye_r = cmds.polySphere(name='eye2', r=0.4)
body = cmds.polySphere(name='body', r=1.5)
arm_l = cmds.polyCylinder(r=0.2, h=3, name='arm1')
arm_r = cmds.polyCylinder(r=0.2, h=3, name='arm2')
leg_l = cmds.polyCylinder(r=0.2, h=3.5, name='leg1')
leg_r = cmds.polyCylinder(r=0.2, h=3.5, name='leg2')
rattle = cmds.polySphere(r=0.5, n='rattle')
stick = cmds.polyCylinder(r=0.1, h=2.5, n='stick')

def createModel():
    #the balloon
    cmds.move(0,10,3.2,bhead)
    cmds.scale(1,1.15,1,bhead)
    applyColor('bhead',0.8,0,0)
    
    cmds.move(0,5.5,3.2,bstring)
    cmds.group('bhead','bstring',n='balloon')
    applyColor('bstring',0.1,0.1,0.1)
    
    #the head
    cmds.move(0, 5, 0, head)
    applyColor('head',1,0.8,0.1)
    
    cmds.move(1.1,5.2,0.5,eye_l)
    cmds.move(1.1,5.2,-0.5,eye_r)
    cmds.group('head','eye1','eye2', n='headGroup')
    applyColor('eye1',0,0,0)
    applyColor('eye2',0,0,0)
    
    #the body
    cmds.scale(1, 1.5, 1, body)
    cmds.move(0,2,0,body)
    applyColor('body',0.9,0.9,0.9)
    
    cmds.rotate(65, 0, 0, arm_l)
    cmds.rotate(80, 0, 0, arm_r)
    cmds.move(0,3,2, arm_l)
    cmds.move(0,2.5,-2,arm_r)
    applyColor('arm1',1,0.8,0.1)
    applyColor('arm2',1,0.8,0.1)
    
    cmds.rotate(100, 30, 0, leg_l)
    cmds.rotate(80, -30, 0, leg_r)
    cmds.move(1,0.4,2, leg_l)
    cmds.move(1,0.4,-2,leg_r)
    applyColor('leg1',1,0.7,0)
    applyColor('leg2',1,0.7,0)
    cmds.group('body','arm1','leg1','leg2', n='bodyGroup')
    
    #the rattle
    cmds.move(0, 3.8, -3.7, rattle)
    cmds.move(0, 3, -3.7, stick)
    applyColor('rattle',0.5,0.5,1)
    applyColor('stick',0.2,0.2,0)
    cmds.group('rattle', 'stick', n='rattleGroup')
    cmds.rotate(-20, 0, 0, 'rattleGroup')
    cmds.group('arm2','rattleGroup', n='shake')
    cmds.group('headGroup','bodyGroup','shake', n='kid')

#procedural animation for models creation
def animateCreation():
    keyAnimation(bhead,0,2,'visibility',False, True)
    keyAnimation(bhead,0,50,'scaleY',0,1.2)
    keyAnimation(bstring,50,52,'visibility',False, True)
    keyAnimation(bstring,50,100,'scaleY',0,1)
    keyAnimation(head,150,152,'visibility',False, True)
    keyAnimation(head,150,170,'scaleY',0,1)
    keyAnimation(eye_l,170,171,'visibility',False, True)
    keyAnimation(eye_l,170,185,'scaleY',0,1)
    keyAnimation(eye_r,185,186,'visibility',False, True)
    keyAnimation(eye_r,185,200,'scaleY',0,1)
    keyAnimation(body,200,201,'visibility',False, True)
    keyAnimation(body,200,250,'scaleY', 0,1.5)
    keyAnimation(arm_l,250,251,'visibility',False, True)
    keyAnimation(arm_l,250,260,'scaleY',0,1)
    keyAnimation(arm_r,260,261,'visibility',False, True)
    keyAnimation(arm_r,260,270,'scaleY',0,1)
    keyAnimation(leg_l,270,271,'visibility',False, True)
    keyAnimation(leg_l,270,280,'scaleY',0,1)
    keyAnimation(leg_r,280,281,'visibility',False, True)
    keyAnimation(leg_r,280,290,'scaleY',0,1)
    keyAnimation('rattleGroup',290,291,'visibility',False, True)
    keyAnimation('rattleGroup',290,320,'scaleY',0,1)

#Other Animation
def funAnimation():
    #change the pivot to shake the rattle
    cmds.xform('shake',piv=[0,2.8,-1.1])
    keyAnimation('shake',330,360,'rotateX',0,40)
    keyAnimation('shake',360,420,'rotateX',40,-40)
    keyAnimation('shake',420,480,'rotateX',-40,40)
    keyAnimation('shake',480,520,'rotateX',40,-10)
    
    #balloon hit the kid
    cmds.xform('kid',piv=[0,2,0])
    keyAnimation('balloon',550,580,'rotateX',0,-90)
    keyAnimation('kid',570,580,'translateY',0,-1)
    keyAnimation('balloon',580,620,'rotateX',-90,0)
    keyAnimation('balloon',620,680,'rotateX',0,-140)
    keyAnimation('balloon',620,680,'translateY',0,-1)
    keyAnimation('kid',657,680,'rotateZ',0,-90)
    keyAnimation('balloon',680,720,'rotateX',-140,0)
    keyAnimation('balloon',680,720,'translateY',-1,-2.5)
    keyAnimation('balloon',680,720,'translateX',0,1.5)
    
    #balloon pull up the kid
    keyAnimation('balloon',720,750,'translateX',1.5,0)
    keyAnimation('balloon',720,780,'translateY',-2.5, 1.5)
    keyAnimation('balloon',750,780,'translateZ', 0, -1.7)
    keyAnimation('kid',720,750,'rotateZ',-90,0)
    keyAnimation('kid',750,780,'translateY',-1,0)
    keyAnimation('kid',750,780,'rotateX',0,-40)
    keyAnimation('balloon',780,900,'translateY',1.5,20.5)
    keyAnimation('kid',780,900,'translateY',0,19)
    #change camera 
    keyAnimation('persp',720,900,'translateX',50,100)
    keyAnimation('persp',720,900,'translateY',7, 10)
    
    #kid falling
    cmds.setKeyframe('kid', time=1000, attribute='translateY', value=19)
    cmds.setKeyframe('kid', time=1060, attribute='translateY', value=0)
    cmds.selectKey('kid', time=(1000, 1060), attribute='translateY', keyframe=True)
    cmds.keyTangent(inTangentType='linear', outTangentType='auto')
    keyAnimation('kid',1050,1080,'rotateX',-40,0)
    keyAnimation('kid',1050,1080,'rotateZ',0,90)


#main function to run the script
def main():
    createModel()
    animateCreation()
    funAnimation()
    cmds.select(clear=True)
    cmds.play()
    
if __name__ == "__main__":
    main();
