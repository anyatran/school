import maya.cmds as cmds

#sets plaback time
cmds.playbackOptions( minTime='0', maxTime='400' )

# Variables
numPetals=10
createPetalShader = 1
createCoreShader = 1

rotation=120
counter=0

# Check for Shader
shads = cmds.ls(materials=True)
for myNode in shads:
    if myNode == 'petalColorer':
        createPetalShader = 0
    if myNode == 'coreColor':
        createCoreShader = 0

if createPetalShader == 0:
    print 'The Petal Shader is already there \n'
else:
    # Shader for petals
    petal = cmds.shadingNode( 'blinn', asShader=True, n='petalColorer' )
    cmds.setAttr( petal+'.color', .91, .87, .16,  type='double3' )
    petalSG = cmds.sets( renderable=True, noSurfaceShader=True, empty=True, name='petalSG' );
    cmds.connectAttr( petal+'.outColor', petalSG+'.surfaceShader', force=True)

if createCoreShader == 0:
    print 'The Core Shader is already there \n'
else:
    # Shader for core
    core = cmds.shadingNode( 'blinn', asShader=True, n='coreColor' )
    cmds.setAttr( core+'.color', 0.94, 0.38, 0.05,  type='double3' )
    coreSG = cmds.sets( renderable=True, noSurfaceShader=True, empty=True, name='coreSG' );
    cmds.connectAttr( core+'.outColor', coreSG+'.surfaceShader', force=True)

# Build the flower core
cmds.sphere(axis=(0,1,0), n='core')
myCore = cmds.ls(sl=True)
cmds.scale(1,.25,1)
cmds.move(0,0.2,0)
cmds.sets(myCore, edit=True, fe='coreSG')

# Build the petal
cmds.sphere(axis=(0,1,0))
cmds.move(0,0,1.5)
cmds.scale(.7,.2,1.7)

cmds.makeIdentity(apply=True, t=1, r=1, s=1, n=0)
cmds.makeIdentity(apply=False, t=1, r=1, s=1)

myPetal = cmds.ls(sl=True)
cmds.parent(myPetal, myCore)
cmds.select(myPetal[0])
cmds.pickWalk(d='down')
myPetalShape = cmds.ls(sl=True)
cmds.sets(myPetalShape[0], edit=True, fe='petalSG')

# Move the tip of the petal
cmds.select(myPetal[0]+'.cv[3] [3]')
cmds.move(0,1,0, r=True)

# Select the inner part of the petal pull down
# One loop for the U direction
for uCV in range(5, 7):
    for vCV in range(0,8):
        cmds.select(myPetal[0]+'.cv['+str(uCV)+']['+str(vCV)+']')
        cmds.move(0,-0.4,0, r=True)

cmds.select(myPetal[0])

degreeApart = (360/numPetals)

for i in range(2, numPetals+1):
    cmds.duplicate()
    cmds.rotate(0, degreeApart, 0, r=True)

myPetal = cmds.ls('nurbsSphere*')
del myPetal[numPetals:]

for x in xrange(0,400):
    counter+=1
    if x<320 and x>200:
        rotation-=1
        for petal in myPetal:
            cmds.setKeyframe(petal,attribute='rotateX', value=-rotation/2, t=counter)

cmds.select(myCore)
cmds.rename('Flower')

cmds.play()