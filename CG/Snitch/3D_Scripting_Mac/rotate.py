'''
Assingment Week 5
Drop and rotate a ball

Part 2 rotate your planets.

'''
import maya.cmds as cmds
#erases anything on stage creates a new file
cmds.file( f=True, new=True)
#sets plaback time
cmds.playbackOptions( minTime='0sec', maxTime='10sec')
cmds.polySphere(name='ball',radius=2)
cmds.move(0,9,0,'ball')
for t in range(0,10):
  y=cmds.getAttr('ball'+'.translateY')
  print y
  y=y-1
  cmds.rotate('6deg', '36deg', 0, 'ball',r=True)  
#  cmds.rotate( 0, '180deg', 0, 'circle1', pivot=(1, 0, 0) )
  cmds.setAttr('ball'+'.translateY',y)
#   cmds.setAttr('ball'+'.translateY',(y-1)) 
  cmds.setKeyframe(t=str(t)+'sec')
  
cmds.play()