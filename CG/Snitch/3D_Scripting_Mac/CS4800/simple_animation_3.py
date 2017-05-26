import maya.cmds as cmds
import random

#set initial camera attributes
cmds.setAttr('persp.translateX', -161.000)
cmds.setAttr('persp.translateY', 75.000)
cmds.setAttr('persp.translateZ', 225.000)
cmds.setAttr('persp.rotateX', -15.000)
cmds.setAttr('persp.rotateY', -35.000)
cmds.setAttr('persp.rotateZ', 0.000)
cmds.playbackOptions( minTime='0sec', maxTime='15sec', ast=0, ps=1.8)

sec = 15
rotation = (40-(60*random.random()))
for ti in range(0, sec):
    ty = cmds.getAttr('dishbot.translateY')
    ty -= 1
    cmds.setAttr('dishbot.translateY', ty)    
    tz = cmds.getAttr('dishbot.translateZ')
    tz += 10*(random.random()-0.5)
    cmds.setAttr('dishbot.translateZ', tz)    
    tx = cmds.getAttr('dishbot.translateX')
    tx += 10*(random.random()-0.5)
    cmds.setAttr('dishbot.translateX', tx)
    print(ti, ":", tx, ty, tz)
    sc=1-(random.random())/2.0
    cmds.scale(sc, sc, sc, 'dishbot')
    cmds.rotate(rotation, (5-(10*random.random())), (5-(2.5*random.random())), 'dishbot', pivot=(tx,ty,tz), relative=True)
	#set frame
    s=str(ti)+'sec'
    cmds.setKeyframe('dishbot', t=s)
    
cmds.play()