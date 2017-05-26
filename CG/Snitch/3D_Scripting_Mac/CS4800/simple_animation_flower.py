import maya.cmds as cmds
import random

YOU_WANT_TO_SEE_A_PRETTY_FLOWER_AT_THE_START = True     # optional, of course.

class PetalWindow():
    def __init__(self):
        self.buildWindow()

    def buildWindow(self):
        self.win = cmds.window("PetalGen 1.0")
        self.layout = cmds.columnLayout(parent = self.win)

        self.button_genPetals = cmds.button(label = 'generate petals', command = self.genPetals)
        self.button_clearAll = cmds.button(label = 'clear all', command = self.clearPetals)

        self.checkBox_resetAtHit = cmds.checkBox("resetAtHit", label = 'Keep petals in bounding box?')
        self.slider_petals = cmds.intSliderGrp("frames", visible=True, field=True, label='Frame count', min=1, max=5000, value=200, step=1 )
        self.slider_petals = cmds.intSliderGrp("petals", visible=True, field=True, label='Petals to generate', min=1, max=10, value=1, step=1 )

        self.slider_xBounds = cmds.intSliderGrp("xBounds", visible=True, field=True, label="X Bounds", min=1, max=100, value = 8, step=1)
        self.slider_yCap = cmds.intSliderGrp("yCap", visible=True, field=True, label="Y Cap", min=1, max=100, value = 20, step=1)
        self.slider_zBounds = cmds.intSliderGrp("zBounds", visible=True, field=True, label="Z Bounds", min=1, max=100, value = 8, step=1)

        self.slider_xWind = cmds.intSliderGrp("xWind", visible=True, field=True, label="X Wind", min=-5, max=5, value=0, step=1)
        self.slider_zWind = cmds.intSliderGrp("zWind", visible=True, field=True, label="Z Wind", min=-5, max=5, value=0, step=1)

        cmds.showWindow(self.win)

    def genPetals(self, window=None, arg=None):

        petals = cmds.intSliderGrp("petals", query=True, value=True)
        resetAtHit = cmds.checkBox("resetAtHit", query=True, value=True)
        frames = cmds.intSliderGrp("frames", query=True, value=True)

        yCap = cmds.intSliderGrp("yCap", query=True, value=True)
        xBounds = cmds.intSliderGrp("xBounds", query=True, value=True)
        zBounds = cmds.intSliderGrp("zBounds", query=True, value=True)

        xWind = cmds.intSliderGrp("xWind", query=True, value=True)
        zWind = cmds.intSliderGrp("zWind", query=True, value=True)

        args = [petals, frames, resetAtHit, yCap, xBounds, zBounds, xWind, zWind]

        print "(added %i petals)" % petals
        fallingPetals(*args)

    def clearPetals(self, window=None, arg=None):
        try:
            cmds.select("petalObj*")
            cmds.delete()
            cmds.select("flowerObj*")
            cmds.delete()
        except:
            pass # none to delete

    def show(self):
        pass


def flowerMaker(numPetals=10):
        
    # build the flower core
    myCore = cmds.sphere(axis=[0,1,0], name="coreObj") # better?
    cmds.scale(1,0.5,1)
    cmds.move(0,0.2,0) # moves the core up

    p = petal(core=myCore)

    cmds.select(p)
    degreeApart = (360.0/numPetals)
    for i in xrange(2, numPetals + 1):
        cmds.duplicate()
        cmds.rotate(0, degreeApart, 0, relative=True)

    cmds.select(myCore)
    cmds.rename("flowerObj")


def petal(core=None, xyz=[0,0,0], rot=[0,0,0]):

    myname = "petalObj"
    if core:
        myname = "flowerPetalObj"

    # build the petal, position it
    UNUSED = cmds.sphere(name=myname, axis=[0,1,0])
    if core:
        cmds.move(0,0,-1.6)
    else:
        cmds.move(*xyz) # unpack xyz

    cmds.scale(0.7,0.3,1.7)

    if core:
        cmds.FreezeTransformations()
        cmds.ResetTransformations()

    p = cmds.ls(sl=True)[0]

    if core:
        cmds.parent(p, core)

    cmds.select(p)
    cmds.pickWalk(direction="down")
    myPetalShape = cmds.ls(sl=True)

    # move the tip of the petal
    cmds.select(p+".cv[3][7]")
    cmds.move(0,2,0, relative=True)

    # Select the inner part of the petal pull down
    # One loop for the U direction
    for uCV in xrange(5,7):     # These numbers are
        for vCV in xrange(0,8): # not arbitrary.
            suffix = ".cv[%i][%i]" % (uCV, vCV)
            cmds.select(p + suffix);
            cmds.move(0,-0.4,0, relative=True)

    if rot:
        cmds.select(p)
        cmds.rotate(*rot, relative=False)

    return p


def fallingPetals(petals, frames, resetAtHit, yCap, xBounds, zBounds, xWind, zWind):

    # CONSTANTS
    FRAMES =            frames
    RESET_AT_HIT =      resetAtHit

    Y_CAP =             yCap
    X_BOUNDS =          xBounds
    Z_BOUNDS =          zBounds

    X_WIND =            xWind
    Z_WIND =            zWind

    Y_SPEED_CAP =       0.1

    ACCEL_FACTOR =      0.002
    ACCEL_CAP =         0.006

    cmds.playbackOptions( minTime='0', maxTime=str(FRAMES) )

    # for every petal...
    for i in xrange(petals):

        # ======= initialization of values ========
        # position
        px = random.randint(-X_BOUNDS/2, X_BOUNDS/2)
        py = random.randint(0, Y_CAP)
        pz = random.randint(-Z_BOUNDS/2, Z_BOUNDS/2)

        # velocity
        dx = smoothNum(0,0.01) + xWind / 10.0
        dy = -random.random() * Y_SPEED_CAP
        dz = smoothNum(0,0.01) + zWind / 10.0

        # acceleration
        ax = smoothNum(0, ACCEL_FACTOR)
        az = smoothNum(0, ACCEL_FACTOR)

        # the petal itself
        p = petal(xyz=[px,py,pz])

        rotateY = random.randint(0,360)

        # create all keyframes
        for f in xrange(FRAMES):
            cmds.setKeyframe(p, attribute='translateX', value=px, t=f )
            cmds.setKeyframe(p, attribute='translateY', value=py, t=f )
            cmds.setKeyframe(p, attribute='translateZ', value=pz, t=f )
            cmds.setKeyframe(p, attribute='rotateX', value=dx * 300, t=f )
            cmds.setKeyframe(p, attribute='rotateY', value=rotateY,  t=f )
            cmds.setKeyframe(p, attribute='rotateZ', value=dz * 300, t=f )

            # update accelerations
            ax = smoothNum(0, ACCEL_FACTOR)
            az = smoothNum(0, ACCEL_FACTOR)

            # enfore the acceleration cap
            if abs(ax) > ACCEL_CAP:
                ax = -ax/2
            if abs(az) > ACCEL_CAP:
                az = -az/2
            

            # update velocities
            dx = dx + ax
            dy = -random.random() * Y_SPEED_CAP
            dz = dz + az

            
            # update positions
            px += dx
            py += dy
            pz += dz

            if RESET_AT_HIT:
                # reset positions, if out of bounding box
                if py < 0:
                    py = Y_CAP

                if (abs(px) > X_BOUNDS) or (abs(pz) > Z_BOUNDS):
                    px = random.randint(-X_BOUNDS/2, X_BOUNDS/2)
                    pz = random.randint(-Z_BOUNDS/2, Z_BOUNDS/2)


def smoothNum(tendency, deviation):
    return random.gauss(tendency, deviation)

if YOU_WANT_TO_SEE_A_PRETTY_FLOWER_AT_THE_START:
    flowerMaker()

a = PetalWindow()


