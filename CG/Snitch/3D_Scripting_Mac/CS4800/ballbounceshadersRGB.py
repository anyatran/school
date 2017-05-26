import maya.cmds as cmds
import random

#erases anything on stage creates a new file
cmds.file( f=True, new=True)

myShader = cmds.shadingNode('blinn', n='blue_balls', asShader=True)
myShader1 = cmds.shadingNode('blinn', n='red_balls', asShader=True)
myShader2 = cmds.shadingNode('blinn', n='yellow_balls', asShader=True)


#sets plaback time
cmds.playbackOptions( minTime='0', maxTime='600' )

#sets the angle of the camera
cmds.setAttr('persp.translateX', 734.471)
cmds.setAttr('persp.translateY', 91.372)
cmds.setAttr('persp.translateZ', 44.949)

#sets the rotation of the camera
cmds.setAttr('persp.rotateX', 351.700)
cmds.setAttr('persp.rotateY', 92.000)
cmds.setAttr('persp.rotateZ', 360.000)

#my list of balls
balls = ["a", "b", "c", "d", "e","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a1", "b1", "c1", "d1", "e1","g1","h1","i1","j1","k1","l1","m1","n1","o1","p1","q1","r1","s1","t1","u1","v1","w1","x1","y1","z1"]

g = -0.07

#initial height and travel
v0y = 2
v0x = 1


for ball in balls:
        #This will be used for the balls radius as well as the balls initial starting y
        num = random.randrange(1,4)
        
	#randomly chooses the z for the ball
        space = random.randint(0, 100)
       
	 # sets the x for the ball
        move = random.randrange(0,100,15)

        x=move
        z=space
        y=num/2
        dx=1
         
        
	#creates the ball
        cmds.sphere( name = ball, p=(x,y,z), radius = num )
      
        
      
         
        for itr in xrange(0,1):
               
        
                for tx in xrange(0,900):
                        #creates the movement throught the y
                        posy = y + v0y*(tx-1) + g*(tx-1)*(tx-1)/2
                        #Makes sure the ball bounces when it hits its initial height
                        if posy <= y + v0y*(1-1) + g*(1-1)*(1-1)/2:
                                posy = y + v0y*(dx-1) + g*(dx-1)*(dx-1)/2
                                dx+=1
                                #This sets the ground limit for each ball so it continues to bounce to infinity
                                if y + v0y*(dx-1) + g*(dx-1)*(dx-1)/2<= y + v0y*(1-1) + g*(1-1)*(1-1)/2:
                                        dx=1
                        #Creates the movement across the x
                        posx = x + v0x*((itr*0) + tx-1)
                        #this sets the keyframes based on tx
                        cmds.setKeyframe( ball, attribute="translateY", value=posy, t= tx )
                        cmds.setKeyframe( ball, attribute="translateX", value=posx, t= tx )
        #these parameters slightly change how high, far and fast the next ball bounces
        v0y+= .01
        v0x+=.001
        g+=-.001
        
cmds.select(balls[0:10])
shaderSet = cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name= myShader + 'SG')
cmds.setAttr( myShader +'.color',0,0.5 ,1, type='double3')
cmds.setAttr( myShader +'.incandescence', 0, 0, 1, type = 'double3')
cmds.connectAttr(myShader +".outColor", shaderSet+".surfaceShader")
cmds.sets(forceElement=shaderSet, e=True)

cmds.select(balls[11:31])
shaderSet1 = cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name= myShader + 'SG')
cmds.setAttr( myShader1 +'.color', 1, 0 ,0, type='double3')
cmds.setAttr( myShader1 +'.incandescence', 1, 0, 0, type = 'double3')
cmds.connectAttr(myShader1 +".outColor", shaderSet1+".surfaceShader")
cmds.sets(forceElement=shaderSet1, e=True)

cmds.select(balls[32:])
shaderSet2 = cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name= myShader + 'SG')
cmds.setAttr( myShader2 +'.color', 0.5, 0 , 0.5, type='double3')
cmds.setAttr( myShader2 +'.incandescence', 0.5, 0, 0.5 , type = 'double3')
cmds.connectAttr(myShader2 +".outColor", shaderSet2+".surfaceShader")
cmds.sets(forceElement=shaderSet2, e=True)


cmds.play()