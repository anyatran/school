import maya.cmds as cmds
cmds.file( f=True, new=True)
cmds.setAttr('perspShape.farClipPlane', 1000000)
scale=4400.0

#List of Planets.
planets=[['Sun',763000.0,0,0,0,0,0,60],['Mercury',4400.0,217.5,0,0,0,0,0.1],['Venus',651.0,245.33,0,0,0,0,177.3],
['Earth',7000.0,275.3,0,0,0,0,23.45],['Mars',4620.0,311,0,0,0,0,25.19],['Jupiter',77000.0,385,0,0,0,0,3.12],
['Saturn',63000.0,474,0,0,0,0,26.73],['Uranus',28000.4,555,0,0,0,0,97.86],['Neptune',28000.7,620,0,0,0,0,29.56],
['Pluto',6000.0,677,0,0,0,0,122.46]]

#This Script creates and places them in a list accessible by the script.
names=[p[0]]

#This Script creates the planets.
for planet in planets:
    cmds.sphere(n=planet[0],r=(planet[1]/scale),p=[planet[2],planet[3],planet[4]],ax=[planet[5],planet[6],planet[7]])

#These scripts rotates and sets the spears (planets).
#cmds.rotate(0,0,60,'Sun')
#cmds.rotate(0,0,0.1,'Mercury')
#cmds.rotate(0,0,177.3,'Venus')
#cmds.rotate(0,0,23.45,'Earth')
#cmds.rotate(0,0,25.19,'Mars')
#cmds.rotate(0,0,3.12,'Jupiter')
#cmds.rotate(0,0,26.73,'Saturn')
#cmds.rotate(0,0,97.86,'Uranus')
#cmds.rotate(0,0,29.56,'Neptune')
#cmds.rotate(0,0,122.46,'Pluto')

for s in range(0,24):
    for p in planets:
	  if p[0]=='Mercury':
	      print "Do something for Mercury"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Venus':
		  print "Do something for Venus"
		  z=cmds.getAttr(p[0]+'.rotateZ')
		  z+=1
		  cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Earth':
	      print "Do something for Earth"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Mars':
	      print "Do something for Merc"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Jupiter':
		  print "Do something for Jupiter"
		  z=cmds.getAttr(p[0]+'.rotateZ')
		  z+=1
		  cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Saturn':
	      print "Do something for Saturn"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Uranus':
	      print "Do something for Uranus"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Neptune':
		  print "Do something for Neptune"
		  z=cmds.getAttr(p[0]+'.rotateZ')
		  z+=1
		  cmds.setAttr(p[0]+'.rotateZ',z)
	  if p[0]=='Pluto':
	      print "Do something for Pluto"
	      z=cmds.getAttr(p[0]+'.rotateZ')
	      z+=1
	      cmds.setAttr(p[0]+'.rotateZ',z)