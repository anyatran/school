import maya.cmds as cmds

def generateCollision(mode=None):

    selected = cmds.ls(sl=True)
    
    xmin, ymin, zmin, xmax, ymax, zmax = cmds.xform(selected ,q=True,ws=True, bb=True)
    
    width = abs(xmax - xmin)
    height = abs(ymax - ymin)
    depth = abs(zmax  - zmin)

    name = (selected[0].split(".")[0] + "_COL")
    
    if mode == "box":
        mesh = cmds.polyCube(w=width, h=height, d=depth, n=name)[0]

    if mode == "sphere":
        radius = max([width, height, depth])/2
        mesh = cmds.polySphere(r=radius,sx=10,sy=10, n=(selected[0].split(".")[0] + "_COL"))[0]
    
    if mode == "cylinder":
        radius = max([width, depth])/2
        mesh = cmds.polyCylinder(r=radius,height=height, sc=1 ,cuv=3, sx=12, sy=1, sz=1, n=name)[0]
    
    xPos = xmax - width/2
    yPos = ymax - height/2
    zPos = zmax - depth/2

    cmds.xform(mesh, ws=True, t=[xPos,yPos,zPos])

