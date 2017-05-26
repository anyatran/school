import maya.cmds as cmds
import random
#7 balls that jiggle
#erases anything on stage creates a new file
cmds.file(f=True, new=True)

#sets plaback time
# random.random()-0.5  range -0.5 to 0.5
cmds.playbackOptions( minTime='0', maxTime='12sec' )
balls=[]
for i in range(1,8):
  n=cmds.polySphere(name='ball_', radius=(2*random.random()))
  print n  
  balls.append(n[0])  
  cmds.move((5*(random.random()-0.5)),(11+(5*random.random())),(5*(random.random()-0.5)),n[0],absolute=True)
  
for s in range(0,12):
  for b in balls:
    ys=cmds.getAttr(b+'.rotateY')
    print ys	
    ys+=(360/12)
    cmds.setAttr(b+'.rotateY',ys)  
    y=cmds.getAttr(b+'.translateY')
    print y	
    y-=1
    cmds.setAttr(b+'.translateY',y)
    z=cmds.getAttr(b+'.translateZ')	
    z+=(random.random()-0.5)	
    print z	
    cmds.setAttr(b+'.translateZ',z)	
    x=cmds.getAttr(b+'.translateX')	
    x+=(random.random()-0.5)	
    print x	
    cmds.setAttr(b+'.translateX',x)		
  cmds.setKeyframe( balls, t=(str(s)+'sec'))

# myShader = cmds.shadingNode('anisotropic', asShader=True)
myShader=cmds.shadingNode('anisotropic',asShader=True)
shadingGroup=cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name=myShader + 'SG' )
cmds.setAttr( myShader+'.color',0.99, 0.543,0.02, type='double3')
cmds.setAttr(myShader+'.translucence',0.55)
cmds.connectAttr(myShader+".outColor", shadingGroup+".surfaceShader")
cmds.select(balls)
cmds.sets(forceElement=shadingGroup,e=True)
  
cmds.play()

