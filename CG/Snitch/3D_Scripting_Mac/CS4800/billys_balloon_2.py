# Assignment 4 - Billy's Balloon
# Copyright Zane Whitney 2013

import maya.cmds as cmds
import random

#Constants
BILLY_TORSO_RADIUS = 4
BILLY_HEAD_RADIUS = 2
BILLY_EYE_RADIUS = .5
BILLY_EYE_BULGE = 1.5
BILLY_ARM_WIDTH = .25
BILLY_ARM_LENGTH = 7

BALLOON_RADIUS = 2
BALLOON_DISTANCE = 10

INITIAL_CAMERA_X = 0
INITIAL_CAMERA_Y = 0
INITIAL_CAMERA_Z = 50

#erases anything on stage creates a new file
cmds.file( f=True, new=True)

#sets plaback time
cmds.playbackOptions( minTime='0', maxTime='600' )

#sets the location of the camera
cmds.setAttr('persp.translateX', 0)
cmds.setAttr('persp.translateY', 0)
cmds.setAttr('persp.translateZ', 50)

#sets the rotation of the camera
cmds.setAttr('persp.rotateX', 0)
cmds.setAttr('persp.rotateY', 0)
cmds.setAttr('persp.rotateZ', 0)

cmds.ls()

#procedurally generate Billy
def makeBilly():
    #torso
    torso = cmds.polySphere(radius=BILLY_TORSO_RADIUS)
    cmds.nonLinear(torso, type='flare', endFlareX=.5, endFlareZ=.5)

    #head and eyes
    head = cmds.polySphere(radius=BILLY_HEAD_RADIUS)

    #move the head to the top
    cmds.move(0, (BILLY_TORSO_RADIUS + BILLY_HEAD_RADIUS), 0)
    core = cmds.polyUnite(head, torso)

    leftEye = cmds.polySphere(radius=BILLY_EYE_RADIUS)
    cmds.move((-(BILLY_HEAD_RADIUS/2)), (BILLY_TORSO_RADIUS + BILLY_HEAD_RADIUS), BILLY_EYE_BULGE)

    rightEye = cmds.polySphere(radius=BILLY_EYE_RADIUS)
    cmds.move(((BILLY_HEAD_RADIUS/2)), (BILLY_TORSO_RADIUS + BILLY_HEAD_RADIUS), BILLY_EYE_BULGE)

    print core, leftEye, rightEye
    core2 = cmds.polyUnite(core, leftEye, rightEye)

    #arms
    leftArm = cmds.polyCube(h=BILLY_ARM_WIDTH, w=BILLY_ARM_LENGTH, d=BILLY_ARM_WIDTH)
    cmds.move(-BILLY_TORSO_RADIUS, (BILLY_TORSO_RADIUS / 2), 0)

    rightArm = cmds.polyCube(h=BILLY_ARM_WIDTH, d=BILLY_ARM_WIDTH, w=BILLY_ARM_LENGTH)
    cmds.move(BILLY_TORSO_RADIUS, (BILLY_TORSO_RADIUS / 2), 0)

    coreArms = cmds.polyUnite(core2, leftArm, rightArm)

    #legs
    leftLeg = cmds.polyCube(h=BILLY_ARM_LENGTH, d=BILLY_ARM_WIDTH, w=BILLY_ARM_WIDTH)
    cmds.move(-(BILLY_TORSO_RADIUS / 2), -(BILLY_TORSO_RADIUS), 0)

    rightLeg = cmds.polyCube(h=BILLY_ARM_LENGTH, d=BILLY_ARM_WIDTH, w=BILLY_ARM_WIDTH)
    cmds.move((BILLY_TORSO_RADIUS / 2), -(BILLY_TORSO_RADIUS), 0)

    billy = cmds.polyUnite(coreArms, leftLeg, rightLeg)


#Procedurally generate balloon
def makeBalloon():
    bubble = cmds.polySphere(r=BALLOON_RADIUS)
    cmds.nonLinear(bubble, typ='flare', endFlareX=1.25, endFlareZ=1.25)

    tie = cmds.polyCone(radius=.1, height=.1)
    cmds.move(0, -BALLOON_RADIUS, )

    balloon = cmds.polyUnite(bubble, tie)
    cmds.move(BALLOON_DISTANCE, BALLOON_DISTANCE, 0)


makeBilly()
makeBalloon()


balloonHitCurve = cmds.curve(p=[(2, 6, 0), (3, 5, 0), (5, 6, 0), (9, 9, 0), (12, 10, 0)], k=[0,0,0,1,2,2,2] )


