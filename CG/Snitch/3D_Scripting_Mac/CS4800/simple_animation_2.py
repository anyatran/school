import maya.cmds as cmds

cmds.setAttr('persp1.translateX', 9.453)
cmds.setAttr('persp1.translateY', 1.983)
cmds.setAttr('persp1.translateZ', 18.285)

cmds.setAttr('persp1.rotateX', 5.062)
cmds.setAttr('persp1.rotateY', 1113)
cmds.setAttr('persp1.rotateZ', 0)

for s in range(0, 10):
    cmds.setAttr('pSphere2.scaleY', 1-((s%10)*0.1))
    cmds.setAttr('pSphere1.scaleY', 1-((s%10)*0.1)) 
    cmds.setKeyframe('pSphere2', 'pSphere1', attribute='scaleY', t=s)

for s in range(10, 20):
    cmds.setAttr('pSphere2.scaleY', 0+((s%10)*0.1))
    cmds.setAttr('pSphere1.scaleY', 0+((s%10)*0.1))
    cmds.setKeyframe('pSphere2', 'pSphere1', attribute='scaleY', t=s)

for s in range(20, 40):
    cmds.setAttr('pCylinder1.rotateZ', -20+((s%20)*4))
    cmds.setAttr('pSphere4.rotateZ', -14.894-((s%20)*50))
    cmds.setAttr('pSphere3.rotateZ', -14.894-((s%20)*50))    
    cmds.setKeyframe('pCylinder1', attribute='rotateZ', t=s)
    cmds.setKeyframe('pSphere4','pSphere3', attribute='rotateZ', t=s)


for s in range(40, 70):
    cmds.setAttr('pCylinder1.translateY', 4.618+((s%40)))
    cmds.setAttr('pSphere1.translateY', 4.537+((s%40)))
    cmds.setAttr('pSphere2.translateY', 4.537+((s%40)))
    cmds.setAttr('pSphere4.rotateZ', -14.894-((s%40)*50))
    cmds.setAttr('pSphere3.rotateZ', -14.894-((s%40)*50)) 
    cmds.setKeyframe('pCylinder1', 'pSphere1', 'pSphere2', attribute='translateY', t=s)
    cmds.setKeyframe('pSphere4','pSphere3', attribute='rotateZ', t=s)


cmds.play()