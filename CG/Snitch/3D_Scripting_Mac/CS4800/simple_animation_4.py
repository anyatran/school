import maya.cmds as cmds
import random

'''
Simple animation script that moves persp camera
scales, rotates and translates and shades.
'''

#erases anything on stage creates a new file
newfile = cmds.file(f=True, new=True)

sec=33
#sets playback time
cmds.playbackOptions( minTime='0sec', maxTime='33sec', ast=0, ps=1.8)

#sets the angle of the camera
cmds.setAttr('persp.translateX', 33.331)
cmds.setAttr('persp.translateY', 99.372)
cmds.setAttr('persp.translateZ', 0)

#sets the rotation of the camera
cmds.setAttr('persp.rotateX', 351.700)
cmds.setAttr('persp.rotateY', 92.000)
cmds.setAttr('persp.rotateZ', 60.000)

#a function to apply color to objects 
def applyColor(objectName, colorR, colorG, colorB):
    myShader = cmds.shadingNode('blinn', n='myShader', asShader=True)
    cmds.select(objectName)
    cmds.setAttr( myShader +'.color',colorR, colorG, colorB, type='double3')
    shaderSet = cmds.sets(renderable=True, noSurfaceShader=True, empty=True)
    cmds.connectAttr(myShader +".outColor", shaderSet+".surfaceShader")
    cmds.sets(forceElement=shaderSet, e=True)
	
#create center Sphere
cmds.polySphere(n='mySphere', sx=20, sy=20)	
applyColor('mySphere',random.random(),random.random(),random.random())
#my list of 3 rings
rings = ["Ring1", "Ring2", "Ring3"]
dbz=[]
for ring in rings:
   #This will be used for  the rings radius
   r = 3
   #creates the ring
   x = 0
   y = 0
   z = 0 

   n=cmds.polyTorus(r=(3*random.random()*r), sr=(r*random.random()))
   cmds.move(x,y,z,n[0], absolute=True )
   dbz.append(n[0])   
   applyColor(n[0],random.random(),random.random(),random.random())
rotation={}
for ring in dbz:
  rotation[ring]=(33)
for ti in range(0,sec):
  for ring in dbz:
    ty=cmds.getAttr(ring+'.translateY')
    print ty  
    ty=ty
    cmds.setAttr(ring+'.translateY', ty)
    tx=cmds.getAttr(ring+'.translateX')
    tx=tx
    cmds.setAttr(ring+'.translateX', tx)
    tz=cmds.getAttr(ring+'.translateZ')
    tz=tz
    cmds.setAttr(ring+'.translateZ', tz)	
    print tx,' ', ty,' ', tz
    sz=1.33-(0.6*random.random())
    cmds.scale(sz,sz,sz,ring)
    cmds.rotate(rotation[ring],5,5,ring,pivot=(tx,ty,tz),relative=True)
  s=str(ti)+'sec'
  cmds.setKeyframe(dbz,t=s)

cmds.play()