import maya.cmds as cmds
import random

#initial camera
cmds.setKeyframe( 'persp', v=50.305,    at='translateX', itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'persp', v=43.089,    at='translateY', itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'persp', v=418.660,   at='translateZ', itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'persp', v=-25.538,   at='rotateX',    itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'persp', v=31.000,    at='rotateY',    itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'persp', v=0.000,     at='rotateZ',    itt='linear', ott='linear', t = 0)

#start camera change (wait 48 frames = 2 secs)
cmds.setKeyframe( 'persp', v=50.305,    at='translateX', itt='linear', ott='linear', t = 48)
cmds.setKeyframe( 'persp', v=43.089,    at='translateY', itt='linear', ott='linear', t = 48)
cmds.setKeyframe( 'persp', v=418.660,   at='translateZ', itt='linear', ott='linear', t = 48)
cmds.setKeyframe( 'persp', v=-25.538,   at='rotateX',    itt='linear', ott='linear', t = 48)
cmds.setKeyframe( 'persp', v=31.000,    at='rotateY',    itt='linear', ott='linear', t = 48)
cmds.setKeyframe( 'persp', v=0.000,     at='rotateZ',    itt='linear', ott='linear', t = 48)

#finish camera change (wait 48 frames = 2 secs)
cmds.setKeyframe( 'persp', v=1439.872,      at='translateX', itt='linear', ott='linear', t = 96)
cmds.setKeyframe( 'persp', v=1282.38,       at='translateY', itt='linear', ott='linear', t = 96)
cmds.setKeyframe( 'persp', v=-1214.148,     at='translateZ', itt='linear', ott='linear', t = 96)
cmds.setKeyframe( 'persp', v=-38.138,       at='rotateX',    itt='linear', ott='linear', t = 96)
cmds.setKeyframe( 'persp', v=128.600,       at='rotateY',    itt='linear', ott='linear', t = 96)
cmds.setKeyframe( 'persp', v=0.000,         at='rotateZ',    itt='linear', ott='linear', t = 96)


# SPIN PROP
cmds.setKeyframe( 'propel', v=0,     at='rotateZ', itt='linear', ott='linear', t = 0)
cmds.setKeyframe( 'propel', v=-2160, at='rotateZ', itt='linear', ott='linear', t = 240)


#move sub
cmds.setKeyframe( 'moveSub2', v=0.0,         at='translateZ', itt='spline', ott='spline', t = 0)
cmds.setKeyframe( 'moveSub2', v=0.0,         at='translateZ', itt='spline', ott='spline', t = 100)
cmds.setKeyframe( 'moveSub2', v=-800.000,    at='translateZ', itt='spline', ott='spline', t = 200)