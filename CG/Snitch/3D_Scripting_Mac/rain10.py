import maya.cmds as cmds, random
raindrops=[]
altitude=9
for x in range(0,5):
  for z in range(0,6):
    rnd=(0.1+random.random()*0.1)  
    n=cmds.polySphere( name = 'rain_', radius =rnd)
    name=n[0]+'.scaleY' 
    print n,' ', name	
    y=cmds.getAttr(name)
    rnd=(0.9+0.9*random.random()) 	
    y=y*rnd  	
    cmds.setAttr(name,y)
    name=n[0]+'.scaleX' 
    xp=cmds.getAttr(name)
    rnd=(0.8+random.random()) 	
    xp=xp*rnd  	
    cmds.setAttr(name,xp)
    name=n[0]+'.scaleZ' 
    zp=cmds.getAttr(name)
    rnd=(0.8+random.random()) 	
    zp=zp*rnd  	
    cmds.setAttr(name,zp)	
    offset=random.randint(0,altitude)	
    raindrops.append(n[0])
    cmds.move(x,altitude+offset,z,n[0])
print raindrops
for drop in raindrops:
  rnd=-1*(0.3+random.random()*0.2)
  rb='rb_'+drop 
  print drop,' ',rnd,' ',rb
  cmds.select(drop)
  cmds.rigidBody(n=rb,active=True, iv=(0,rnd, 0)) 

#  polyPlane -ch on -o on -w 32.897207 -h 32.897207 -sw 1 -sh 1 -cuv 2 ;
cmds.polyPlane(n='ground',ch=True,o=True,w=32.29,h=32.29,sw=1,sh=1,cuv=2) 
cmds.select('ground') 
cmds.rigidBody(n='rb_ground',passive=True) 