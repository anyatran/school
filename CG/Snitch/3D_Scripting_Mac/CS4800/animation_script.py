import maya.cmds as cmds

NEUTRAL_Y = 2.4
SCALE_FACTOR = 1.5

def neutral_scale(i):
    cmds.setKeyframe(v=NEUTRAL_Y, at='translateY', time=i)
    cmds.setKeyframe(v=1, at='s', time=i)

def scale_up(i):
    cmds.setKeyframe(v=NEUTRAL_Y * SCALE_FACTOR, at='translateY', time=i)
    cmds.setKeyframe(v=SCALE_FACTOR, at='s', time=i)

# select the model
cmds.select("body")

# move it forward/back (by setting keyframes)
cmds.setKeyframe(v=0, at='translateX', time=0)
cmds.setKeyframe(v=3, at='translateX', time=30)
cmds.setKeyframe(v=0, at='translateX', time=60)

# make it jump and spin
# (jump)
cmds.setKeyframe(v=NEUTRAL_Y, at='translateY', time=60)
cmds.setKeyframe(v=6, at='translateY', time=90)
cmds.setKeyframe(v=NEUTRAL_Y, at='translateY', time=120)
# (spin)
cmds.setKeyframe(v=0, at='rotateY', time=60)
cmds.setKeyframe(v=360, at='rotateY', time=120)
cmds.setKeyframe(v=0, at='rotateY', time=120)

# scale it up (and translate it up)
# (scale up and down progressively faster)

i = 120
add = 30
neut = True
while add > 0:
    if neut:
        neutral_scale(i)
    else:
        scale_up(i)

    i += add
    add = int(add * 0.9)
    neut = not neut

# reset
neutral_scale(i)

# pirouette 
cmds.setKeyframe(v=0, at='rotateY', time=i)
cmds.setKeyframe(v=0, at='rotateX', time=i)
cmds.setKeyframe(v=360, at='rotateY', time=i + 60)
cmds.setKeyframe(v=30, at='rotateX', time=i + 60)

# wait a bit, then fall over
# (wait)
cmds.setKeyframe(v=30, at='rotateX', time=i + 90)
cmds.setKeyframe(v=NEUTRAL_Y, at='translateY', time=i + 90)
# (fall)
cmds.setKeyframe(v=90, at='rotateX', time=i + 100)
cmds.setKeyframe(v=1, at='translateY', time=i + 100)

# move the CAMERA (during the scaling)
def set_cam(tx, ty, tz, i):
    cmds.setKeyframe('persp', at='translateX', v=tx, time=i)
    cmds.setKeyframe('persp', at='translateY', v=ty, time=i)
    cmds.setKeyframe('persp', at='translateZ', v=tz, time=i)

# camera dollying
cmds.setKeyframe('persp', at='rotateX', v=-14.7, time=0)
cmds.setKeyframe('persp', at='rotateY', v=70.6, time=0)
cmds.setKeyframe('persp', at='rotateZ', v=0, time=0)

set_cam(45, 14, 16, 0)
set_cam(7, 3.4, 2.5, 460)