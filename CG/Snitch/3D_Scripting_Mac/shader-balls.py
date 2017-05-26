import maya.cmds as cmds
import random

#my list of balls
balls = ["a", "b", "c", "d", "e","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","a1", "b1", "c1", "d1", "e1","g1","h1","i1","j1","k1","l1","m1","n1","o1","p1","q1","r1","s1","t1","u1","v1","w1","x1","y1","z1"]
g = -0.04

shaders = [] 
shaderTypes = ['blinn','lambert','phong','phongE','anisotropic']
'''
Lambert
Lambert is a flat material type that yields a smooth look without highlights. It calculates without taking into account surface reflectivity, which gives a matte, chalk-like appearance. Lambert material is ideal for surfaces that don't have highlights: pottery, chalk, matte paint, and so forth. By default, any newly created object gets the Lambert shader assigned to it. If the object should have highlights, though, it's a good idea to assign another shader. You'll want to see highlights even during the modeling stage, to see whether they are breaking across the model (indicating a seam in the surface).

Phong
The Phong material type takes into account the surface curvature, amount of light, and camera angle to get accurate shading and highlights. The algorithm results in tight highlights that are excellent for polished shiny surfaces, such as plastic, porcelain, and glazed ceramic.

TIP

If you notice that the highlights of a surface with a phong shader applied are exhibiting flickering in your animation, or you see a "ropy" appearance from line to line, switch to a Blinn material type, which has smoother highlights. This problem can also be made worse by bump mapping.

PhongE
PhongE is a faster rendering version of Phong that yields somewhat softer highlights than Phong. Most artists use regular Phong for objects with intense highlights and Blinn for everything else.

Blinn
The Blinn material type calculates surfaces similarly to Phong, but the shape of the specular highlights in Blinn materials reflects light more accurately. Blinn is good for metallic surfaces with soft highlights, such as brass or aluminum. Because Blinn is a versatile material type and generally renders without problems, it's the primary material type we've used in these tutorials.

Anisotropic
The Anisotropic material type stretches highlights and rotates them based on the viewer's relative position. Objects with many parallel micro-grooves, such as brushed metal, reflect light differently depending on how the grooves are aligned in relation to the viewer. Anisotropic materials are ideal for materials such as hair, feathers, brushed metal, and satin.

'''
st=len(shaderTypes)
s=len(balls)/3
for i in range(0,s);
  oz =  random.randint(0,s)
  ms =cmds.shadingNode(shaderTypes[oz], asShader=True)
  shaders.append(ms)
  
for shader in shaders:
  cmds.setAttr( shader +'.color',random.random(),random.random(),random.random(), type='double3')
  i = 0.5 + (random.random()/2.0) 
  cmds.setAttr( shader +'.incandescence', 0, 0, i, type = 'double3')

shaderSets = []
for shader in shaders:
  n=cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name= shader + 'SG')
  shaderSets.append(n)
# shaderSet = cmds.sets(renderable=True, noSurfaceShader=True, empty=True, name= myShader + 'SG')
# cmds.sets(forceElement=shaderSet, e=True)
