#----------------------------------------------------------------------------------------------
#
#    QTown tool
#
#    SCRIPT:        icQTOwn.mel
#
#    AUTHOR:        Ingo Clemens
#                info@effect-house.de
#
#    DESCRIPTION:
#                This script creates a city like geometry based on the selected polygons.
#                Just simply select some faces of the object to build upon and execute.
#
#    VERSIONS:
#                1.3    - Oct. 02, 2009
#
#                - added the feature to not be restricted to a planar surface, in order to
#                    allow for features such as hills
#                - added a curved option, to be able to work with cylindrical and
#                    spherical shapes (disables the detail and well features - sorry :-)!)
#                - added a colorize option, which assigns some shaders to the surfaces
#                    NOTE: WITH THIS OPTION ENABLED IT IS COMMON THAT UNDOING THE TOOL WILL
#                            NOT WORK CORRECTLY, SO BE SURE TO SAFE PRIOR TO
#                            EXECUTING! UNDOING AND RERUNNING THE SCRIPT CAN EVENTUALLY CAUSE
#                            MAYA TO CRASH!
#                FIXES:
#                - fixed, what caused maya to crash on some machines running windows
#                - the well occurance parameter wasn't correctly implemented
#                - fixed a bug where some wells have received top rims
#
#                1.1 - Sep. 21, 2009
#                1.0 - Jul. 12, 2009
#
#----------------------------------------------------------------------------------------------

#----------------------------------------------------------------------------------------------
#
#    USE AND MODIFY AT YOUR OWN RISK!!
#
#----------------------------------------------------------------------------------------------

import maya.cmds as cmds

import random
import math


def icQTexecute():

#initializing the viarable in python with none
    sel = None
    sel = cmds.ls(selection=True)
    polyNameArray = None
    polyName = None
    compList = None
    faceNum = None
    dropout = None
    lengthValue = None

    if ( sel[0].nodeType != "mesh"):
        cmds.error("Please select some polygons!")
        
    del faceNum[0:len(faceNum)]
    #get the numbers of the selected polygons
    compList = icQTgetSelectionNumbers()
    
    faceNum = compList.split(":")
    polyNameArray = sel[0].split(".")

    #create the shaders if necessary
    if (cmds.checkBox('applyColor', query=True, value=True)):
        icTQcreateColors()

    cmds.progressWindow(title="Constructing",
                        progress=0,
                        max=(len(faceNum)),
                        isInterruptable=True)

    #go through each selected polygon and extrude it
    for i, w in enumerate(faceNum):
        cmds.progressWindow(endProgress=True,progress=i,status=("Face " + i + " of " + (len(faceNum)) + " in Progress"))
        
        dropout = random.randint(1,10)
        if (dropout > (cmds.checkBox(dropout, query=True, value=True)) / 10 + 1):
            #select the current polygon
            polyName = polyNameArray[0] + ".f[" + faceNum[i] + "]"
            cmds.select(polyName,replace=True)
            lengthValue = icQTinnerExtrude(polyName, polyNameArray[0], "first")
            icQTfirstLevelExtrude( polyName, lengthValue, polyNameArray[0] )

        # Check if the dialog has been cancelled
        if (cmds.progressWindow(query=True,isCancelled=True)):
             break

    #if any geometry for the top surfaces have been created combine them with the source
    #geometry
    if (cmds.objExists('topCubes')):
        cmds.select(polyNameArray[0],replace=True)
        topCubes = cmds.listRelatives('topCubes',children=True)
        for cubes in topCubes:
            cmds.select(polyNameArray[0],allDescendents=True)
        cmds.polyUnite(0,polyUnite=True)
        cmds.constructionHistory()

    cmds.progressWindow(endProgress=True)
    cmds.select(clear=True)


def icQTgetSelectionNumbers():

    sel = None
    sel = cmds.ls(selection=True)
    selArray=None
    selPart=None
    selStart=None
    selEnd=None
    faceNum=None
    compList=None

    #find all polygon numbers of the selection
    for s in sel:
    
        #if there are is a range of polygons selected
        if ":" in s:
        
            #separate the start and end numbers
            selArray = s.split(":")
            #find the number of digits of the polygon number
            selPart=selArray[0].split("[")
            #define the number for the start of the range
            selStart = min((len(selArray[0]) - len(selPart[1]) + 1), (len(selArray[0])))
            #define the number for the end of the range
            selPart=selArray[1].split("]")
            selEnd = max(1, (len(selPart[0])))
            #build a list of all numbers in between
            for i in range(selStart,selEnd):
                faceNum[len(faceNum)] = i
            
        
        #if there is a single polygon listed
        else:
        
            selArray = s.split("[")
            faceNum[len(faceNum)] = max(1, (len(selArray[1]) - 1))
        
    
    for f in faceNum:
    
        compList = (compList + f + ":")
    
    return compList


def icQTinnerExtrude( polyName, nodeName, stage ):

    compList=None
    edgeNum=None
    extrude=None
    bbox1=None
    bbox2=None
    freeX=None
    freeY=None
    localScale=None
    moveVal=None
    moveValList=None
    moveValMul=None
    edgePos=None
    edgeLengthArray=None
    lengthValue=None
    lengthValueList=None
    baseInset=None
    randSign=None
    moveValIndex=None
    #continue = 1
    edgeStart=None
    edgeEnd=None
    edgeLength=None

    #assign the random scale values
    scaleRangeLow = cmds.floatFieldGrp(query=True,value1='minScale')
    scaleRangeHi = 1.5
    randSX = random.uniform(scaleRangeLow,scaleRangeHi)
    randSY = random.uniform(scaleRangeLow,scaleRangeHi)

    #color the selection
    if (stage == "first"):
        icQTapplyColor(polyName, "street" )
    
    else:
    
        #colorize
        colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
        randColor = random.randint(0,5.9)
        icQTapplyColor(polyName, colorNames[randColor] )
    

    #get the initial bounding box of the selection
    bbox1 = cmds.xform(polyName,query=True,boundingBox=True)

    #extrude the face and scale it
    extrude = cmds.polyExtrudeFacet(polyName,constructionHistory=True,keepFacesTogether=False)
    cmds.setAttr((extrude[0] + ".localScale") , randSX, randSY, 1, type="double3")
    #decrease the local scale until the size is smaller than the inital bounding box
    
    for i in range(0,50):
        localScale = cmds.getAttr(extrude[0] + ".localScale")
        cmds.setAttr((extrude[0] + ".localScaleX"), (localScale[0] * 0.99))
        cmds.setAttr((extrude[0] + ".localScaleY"), (localScale[1] * 0.99))

        bbox2 = cmds.xform(polyName,query=True,boundingBox=True)
        #include some headroom space in the calculation
        freeX = bbox2[3] / 30
        freeY = bbox2[5] / 30
        #if the bounding box is in positive space go for the max values first
        if (bbox1[3] >= 0):
        
            #check for the max values of the bounding box
            if ((abs(bbox1[3]) > abs(bbox2[3] + freeX))and(abs(bbox1[5]) > abs(bbox2[5] + freeY))):
            
                #check for the min values of the bounding box
                if ((abs(bbox1[0]) > abs(bbox2[0] + freeX))and(abs(bbox1[2]) > abs(bbox2[2] + freeY))):
                    break
                
            
        
        #if the bounding box is in negative space go for the min values first
        else:
        
            #check for the min values of the bounding box
            if ((abs(bbox1[0]) > abs(bbox2[0] + freeX))and(abs(bbox1[2]) > abs(bbox2[2] + freeY))):
            
                #check for the max values of the bounding box
                if ((abs(bbox1[3]) > abs(bbox2[3] + freeX))and(abs(bbox1[5]) > abs(bbox2[5] + freeY))):
                    break
                
            
        
    

    #if the surface is non planar scale the height to zero
    if (not(cmds.checkBox(query=True,value='planarCheck'))):
    
        if (bbox2[1] != bbox2[4]):
            cmds.scale(1,0,1,polyName,pivot=(0,(bbox2[1] + ((bbox2[4] - bbox2[1]) / 2)),0),relative=True)
    

    #define a offset multiplier for each polygon
    moveValMult = random.uniform(0.1,1)
    moveValList[0] = (abs(bbox1[3]) - abs(bbox2[3])) * moveValMult
    moveValList[1] = (abs(bbox1[5]) - abs(bbox2[5])) * moveValMult
    #create a random index to choose if the translation is in X or Y
    moveValIndex = random.uniform(0,1.9)
    #create a random sign for the translation
    randSign = random.uniform(-1.9,1.9)
    if (moveValIndex == 0):
    
        cmds.setAttr((extrude[0] + ".localTranslateX"), (moveValList[0] * randSign))
    
    else:
    
        cmds.setAttr((extrude[0] + ".localTranslateY"), (moveValList[1] * randSign))
    

    #convert the face to an edge selection
    cmds.select(cmds.polyListComponentConversion(polyName,fromFace=True,toEdge=True ))
    #get the numbers of the selected edges
    compList = icQTgetSelectionNumbers()
    edgeNum = compList.split(":")
    #find the longest edge in the list
    for e in edgeNum:
    
        lengthValue = 0
        edgePos = cmds.xform(query=True,worldSpace=True,translation=(nodeName + ".e[" + e + "]"))
        #edgeStart = Vector(edgePos[0],edgePos[1],edgePos[2])
        #edgeEnd = Vector(edgePos[3],edgePos[4],edgePos[5])
        #calculate the length of the edge
        edgeLengthArray = [abs(edgePos[0] - edgePos[3]),abs(edgePos[1]-edgePos[4]),abs(edgePos[2]-edgePos[5])]
        for el in edgeLengthArray:
        
            if (el > lengthValue):
            
                lengthValue = el
            
        
        lengthValueList[len(lengthValueList)] = lengthValue
    
    #finally find the longest edge
    lengthValue = 0
    for v in lengthValueList:
    
        if (v > lengthValue):
        
            lengthValue = v
        
    
    #create a base extrusion if the polygon hasn't been offset much
#    if (moveValMult < 0.2):
#    
#       baseInset = rand(0.9,0.95)
#        extrude = `polyExtrudeFacet -ch 1 -kft 0 polyName`
#        setAttr (extrude[0] + ".localTranslateZ") (lengthValue / 4)
#        extrude = `polyExtrudeFacet -ch 1 -kft 0 polyName`
#        setAttr (extrude[0] + ".localScaleX") baseInset
#        setAttr (extrude[0] + ".localScaleY") baseInset
    

    return lengthValue


def icQTfirstLevelExtrude(polyName, lengthValue, nodeName):

    extrude=None
    bbCube=None
    cubeCenterX=None
    cubeCenterZ=None
    cubeSize=None
    randomHeight=None
    randomWellHeight=None
    special=None
    shrinkScale=None
    randSecondLevel=None
    wellsRandom=None
    shrinkRandom=None
    noWells=None
    
    #extrude the face with some random height
    heightMin = cmds.floatFieldGrp(query=True,value1='heightRange')
    heightMax = cmds.floatFieldGrp(query=True,value2='heightRange')
    randomHeight = random.uniform(heightMin,heightMax)
    randomWellHeight = random.uniform(1,1.1)

    #colorize
    colorNames = ["brick1","brick2","glass1","glass2",
                            "sand1","sand2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,9.9)

    special = random.uniform(1,10)
    stacksTrue = cmds.checkBox(query=True,value='stacksCheck')
    if (stacksTrue == 1 and special > 1 and special < 2):
    
        icQTstackExtrude ( polyName, (lengthValue * randomHeight) )
        noWells = 1
    
    else:
    
        icQTapplyColor(polyName, colorNames[randColor] )

        shrinkRandom = random.uniform(0,12)
        shrinkScale = random.uniform(0.8,0.95)
        extrude = cmds.polyExtrudeFacet(polyName,constructionHistory=True,keepFacesTogether=False)
        cmds.setAttr((extrude[0] + ".localTranslateZ"), (lengthValue * randomHeight))
        if (cmds.checkBox(query=True,value='shrinkCheck') and (shrinkRandom < 2)):
        
            cmds.setAttr((extrude[0] + ".localScaleX"), shrinkScale)
            cmds.setAttr((extrude[0] + ".localScaleY"), shrinkScale)
            noWells = 1
        
    

    #
    #for the vertical walls
    #

    if (cmds.checkBox(query=True,value='wellsCheck') and (noWells == 0)):
    
        wellsRandom = random.uniform(0,10)
        if (wellsRandom < cmds.floatFieldGrp(query=True,value1='wellsOccur')):
        
            #get the dimensions
            bbCube = cmds.xform(polyName,query=True,boundingBox=True)
            cubeSize = (abs(bbCube[0] - bbCube[3])) * (abs(bbCube[2] - bbCube[5]))
            cubeCenterX = (bbCube[0] + bbCube[3]) / 2
            cubeCenterZ = (bbCube[2] + bbCube[5]) / 2
        
            wells = icQTwells ( lengthValue, cubeSize )
            #get the dimension for the longest edge to decide if the vertical walls need to be rotated
            if (bbCube[5] - bbCube[2] > bbCube[3] - bbCube[0]):
            
                cmds.rotate(0, 90, 0, wells, relative=True)
            
            #place the wells at the center of the polygon
            cmds.xform('wells',ws=True,t=(cubeCenterX, (bbCube[1] - (lengthValue * randomHeight)), cubeCenterZ))
            cmds.makeIdentity('wells',t=True,s=True,a=True)
            #scale the wells to the height of the extrusion
            cmds.scale(1, (lengthValue * randomHeight * random.uniform(0.97,1.005)), 1, wells,absolute=True)
        
            #if not already present create a group and parent the new geometry under it
            if (not(cmds.objExists('topCubes'))):
            
                cmds.createNode('transform',n='topCubes')
            
            cmds.parent('wells', 'topCubes')
        
    

    #define the color for the top rim
    #colorize
    rimColor = ["rim1","rim2"]
    randColorRim = random.uniform(0,1.9)

    #build a rim at the top
    icQTbuildRim( polyName, rimColor[randColorRim] )

    #build another level based on random number
    randSecondLevel = random.uniform(0.2,4)

    if (randSecondLevel < 1 and randomHeight >= 1 and cmds.checkBox(query=True,v='roofCube')):
    
        icQTroofCube ( polyName, nodeName, rimColor[randColorRim] )
    
    if (randSecondLevel == 1 and randomHeight >= 2):
    
        if (cmds.checkBox(query=True,v='stairs')):
        
            icQTstairStepSecondLevel ( polyName, lengthValue, colorNames[randColor], rimColor[randColorRim] )
            if (randomHeight >= 2 and cmds.checkBox(query=True, v='antenna')):
            
                icQTbuildAntenna ( polyName, lengthValue )
            
        
        else:
        
            if (cmds.checkBox(query=True,value='topDetail')):
            
                for i in range(0,cmds.radioButtonGrp(query=True,s1='complexity')):
                
                    icQTtopDetail ( polyName, i )
                
            
        
    
    else:
    
        if (cmds.checkBox(query=True,value='topDetail')):
        
            for i in range(0,cmds.radioButtonGrp(query=True,s1='complexity')):
                icQTtopDetail ( polyName, i )
            
        
    
    if (randSecondLevel == 2):
    
        randomTop = random.uniform(-1,1.3)
        if (randomTop > 0):
        
            icQTsplitSecondLevel ( polyName, nodeName )
            if (randomHeight >= heightMax / 2 and cmds.checkBox(query=True,v='topDetail')):
            
                for i in range(0,cmds.radioButtonGrp(query=True,s1='complexity')):
                    icQTtopDetail ( polyName, i )
                
            
        
        elif (randomTop < 0 and cmds.checkBox(query=True, v='topDetail')):
        
            icQTtopDetail ( polyName, 0 )
            icQTdivideSecondLevel ( polyName, nodeName )
        
    

    cmds.select(nodeName,r=True)
    cmds.polySoftEdge(a=False)
    cmds.delete(nodeName,ch=True)


def icQTstackExtrude( polyName, lengthValue ):

    extrude=None
    baseHeight=None
    storyHeight=None
    ledgeHeight=None
    ledgeScale=None
    storyScale=None
    storyLedgeRatio=None
    floors=None

    #define the base height before the stacking occurs
    baseHeight = random.uniform(0.05,0.15)
    #the actual base height is a fragment of the overall length
    baseHeight = baseHeight * lengthValue
    #the remaining extrude length is without the base height
    lengthValue = lengthValue - baseHeight
    #the story height depends on the scale
    #define the ledge scale and story scale
    ledgeScale = random.uniform(0.9,0.98)
    ledgeUpScale = 1 + (1 - ledgeScale)
    storyScale = random.uniform(0.5,2)
    floors = math.ceil(lengthValue / storyScale)
    storyLedgeRatio = random.uniform(4,11)
    #the story height is 3/4, the ledge height is 1/4
    storyHeight = lengthValue / floors / storyLedgeRatio
    ledgeHeight = storyHeight
    storyHeight = storyHeight * (storyLedgeRatio - 1)

    #extrude the base height
    extrude = cmds.polyExtrudeFacet(polyName, ch=True,kft=False) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"),baseHeight)

    #colorize
    colorLedge = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    colorFloor = ["brick1","brick2","glass1","glass2","sand1","sand2","light1","light2"]
    randColorLedge = random.uniform(0,5.9)
    randColorFloor = random.uniform(0,7.9)
    icQTapplyColor ( polyName, colorLedge[randColorLedge] )
    icQTapplyColor ( polyName, colorFloor[randColorFloor] )

    #create the stories
    for i in range(0,floors):
    
        icQTapplyColor ( polyName, colorLedge[randColorLedge] )
        #extrude the face and scale it
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False)
        cmds.setAttr((extrude[0] + ".localScale"), ledgeUpScale, ledgeUpScale, 1,type='double3')
        #extrude for the ledge height
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False)
        cmds.setAttr((extrude[0] + ".localTranslateZ"), ledgeHeight)
        #extrude for the ledge inset
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False)
        cmds.setAttr((extrude[0] + ".localScale"), ledgeScale, ledgeScale, 1,type='double3')
        #extrude for the floor height
        icQTapplyColor ( polyName, colorFloor[randColorFloor] )
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False)
        cmds.setAttr ((extrude[0] + ".localTranslateZ"), storyHeight)
    

    #colorize the top polygon
    colorNames = ["rim1","rim2"]
    randColor = random.uniform(0,1.9)
    icQTapplyColor ( polyName, colorNames[randColor] )


def icQTstairStepSecondLevel( polyName, lengthValue, color1, color2 ):

    extrude=None
    maxLevelSteps = cmds.radioButtonGrp(query=True,sl='maxLevelSteps')
    levelNum = random.uniform(1,maxLevelSteps + 1)
    levelHeight = random.uniform(0.2,0.8)
    levelScale = random.uniform(0.6,0.8)

    for i in range(0,levelNum):
        #extrude the face and scale it
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False) 
        cmds.setAttr((extrude[0] + ".localScale"),levelScale, levelScale, 1,type='double3') 

        icQTapplyColor ( polyName, color1 )

        #extrude again and set a height
        extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False) 
        cmds.setAttr((extrude[0] + ".localTranslateZ"), (lengthValue * levelHeight))
        lengthValue = lengthValue * 0.8

        #build a rim at the top
        icQTbuildRim ( polyName, color2 )
    


def icQTbuildAntenna( polyName, lengthValue ):

    extrude=None
    antennaHeight = random.uniform(0.6,1.5)
    #extrude the face and scale it
    extrude = cmds.polyExtrudeFacet(polyName, ch=True, kft=False) 
    cmds.setAttr((extrude[0] + ".localScale"),0.1, 0.1, 1, type='double3') 

    #colorize
    colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,5.9)
    icQTapplyColor ( polyName, colorNames[randColor] )

    #extrude again and set a height
    extrude = cmds.polyExtrudeFacet(polyName,ch=True,kft=False) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"), (lengthValue * antennaHeight))
    cmds.setAttr((extrude[0] + ".localScale"), 0.1, 0.1, 1, type='double3')


def icQTsplitSecondLevel( polyName, nodeName ):

    extrude=None
    compList=None
    vertList=None
    edgeNum=None
    vertNum=None
    faceNum=None
    edgePos=None
    edgeLengthArray=None
    lengthValue=None
    lengthValueList=None
    splitValue=None
    edgeStart=None
    edgeEnd=None
    edgeLength=None
    randomEdge=None
    edgeList=None
    parallel=None
    divisions=None

    #convert the face to an edge selection
    cmds.select(cmds.polyListComponentConversion(polyName,ff=True,te=True))
    #get the numbers of the selected edges
    compList = icQTgetSelectionNumbers()
    edgeNum = compList.split(":")

    #find the edge lengths
    for e in edgeNum:
    
        lengthValue = 0
        edgePos = cmds.xform(query=True,ws=True,t=(nodeName + ".e[" + e + "]"))

        edgeLengthArray = [edgePos[0]-edgePos[3],edgePos[1]-edgePos[4],edgePos[2]-edgePos[5]]
        for el in edgeLengthArray:
        
            if (el > lengthValue):
            
                lengthValue = el
            
        
        lengthValueList[len(lengthValueList)] = lengthValue
    

    #pick an edge from the list that defines the length of the edge pair
    randomEdge = random.uniform(0,(len(lengthValueList) - 1))

    #find the edge pair that is the size of the random edge
    for i in range(0,len(lengthValueList)):
    
        if (lengthValueList[i] > lengthValueList[randomEdge] - 0.001 and lengthValueList[i] < lengthValueList[randomEdge] + 0.001):
            edgeList[len(edgeList)] = edgeNum[i]
        
    
    #if the polygon is nonsquare take the first two edges
    if (len(edgeList) != 2):
    
        edgeList[0] = edgeNum[0]
        for i in range(1,len(lengthValueList)):
            if (parallel == 0):
            
                edgeList[1] = edgeNum[i]
                cmds.select((nodeName + ".e[" + edgeList[i] + "]"),r=(nodeName + ".e[" + edgeList[0] + "]"))
                #convert the edge to a vertex selection
                cmds.select(cmds.polyListComponentConversion(fe=True,tv=True))
                #get the vertex selection
                vertList = icQTgetSelectionNumbers()
                vertNum = vertList.split(":") 
                #if there are only three verts selected the two edges connect
                #in this case get the next edge
                if (len(vertNum) == 4):
                
                    parallel = 1
                
            
        
    

    for i in range(0,len(edgeNum)):
    
        if (edgeNum[i] != edgeList[0] and edgeNum[i] != edgeList[1]):
        
            edgeList[len(edgeList)] = edgeNum[i]
        
    

    #generate a split value
    splitValue = random.uniform(0.25,0.75)
    #and split the polygon
    cmds.polySplit(ch=1,s=1,sma=90,ep=[(edgeList[0],splitValue),(edgeList[1],splitValue)])

    #colorize
    colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,5.9)
    icQTapplyColor ( polyName, colorNames[randColor] )

    #extrude and set a height
    extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"),(min((lengthValue * splitValue),(lengthValue / 10))))


def icQTdivideSecondLevel( polyName, nodeName ):

    compList=None
    faceNum=None
    extrude=None
    divisions=None

    #subdivide the top
    cmds.select(polyName,r=True)
    divisions = random.uniform(1,2)
    cmds.polySmooth(polyName,mth=0,dv=divisions,c=0,kb=1,sl=1,ch=1)
    #get the numbers of the selected faces
    compList = icQTgetSelectionNumbers()
    faceNum=compList.split(":")

    #go through each face and extrude it by random
    for f in faceNum:
    
        skip = random.uniform(-2,4)
        if (skip < 0):
        
            polyName = nodeName + ".f[" + f + "]"
            lengthValue = icQTinnerExtrude ( polyName, nodeName, "" )
            extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
            cmds.setAttr((extrude[0] + ".localTranslateZ"), (lengthValue / 2))
        
    


def icQTroofCube(polyName, nodeName, color ):

    extrude=None
    moveValMult=None
    bbox1=None
    bbox2=None
    moveValList=None
    moveValIndex=None
    randSign=None

    #assign the random scale values
    sizeMin = cmds.floatFieldGrp(query=True,v1='sizeRange')
    sizeMax = cmds.floatFieldGrp(query=True,v2='sizeRange')
    randSX = random.uniform(sizeMin,sizeMax)
    randSY = random.uniform(sizeMin,sizeMax)

    #get the initial bounding box of the selection
    bbox1 = cmds.xform(polyName,query=True,bb=True) 

    #extrude the face and scale it
    extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localScale"),randSX, randSY, 1,type='double3') 
    #get the new bounding box size
    bbox2 = cmds.xform(polyName,query=True,bb=True) 

    icQTapplyColor ( polyName, color )

    #define a offset multiplier for each polygon
    moveValMult = random.uniform(0.6,0.9)
    moveValList[0] = (abs(bbox1[3]) - abs(bbox2[3])) * moveValMult
    moveValList[1] = (abs(bbox1[5]) - abs(bbox2[5])) * moveValMult
    #create a random index to choose if the translation is in X or Y
    moveValIndex = random.uniform(0,1.9)
    #create a random sign for the translation
    randSign = random.uniform(-1.9,1.9)
    if (moveValIndex == 0):
    
        cmds.setAttr((extrude[0] + ".localTranslateX"), (moveValList[0] * randSign))
    
    else:
    
        cmds.setAttr((extrude[0] + ".localTranslateY"), (moveValList[1] * randSign))
    
    #extrude and set a height
    extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"), (abs(moveValList[moveValIndex]) * 0.5))
    #build a rim at the top
    icQTbuildRim ( polyName, color )


def icQTtopDetail( topPoly, scaleHeight ):

    roofCube=None
    extrude=None
    bbFloor=None
    bbCube=None
    floorSize=None
    floorCenterX=None
    floorCenterZ=None
    cubeSize=None
    scaleMax=None
    height=None
    randSizeX=None
    randSizeZ=None
    moveRangeX=None
    moveRangeZ=None
    randMoveX=None
    randMoveZ=None
    randType=None


    bbFloor = cmds.xform(topPoly,query=True,bb=True) 
    floorSize = (abs(bbFloor[0] - bbFloor[3])) * (abs(bbFloor[2] - bbFloor[5]))

    floorCenterX = (bbFloor[0] + bbFloor[3]) / 2
    floorCenterZ = (bbFloor[2] + bbFloor[5]) / 2

    randType = random.uniform(0,3.5)
    if (randType == 1):
    
        roofCube = icQTsingleCube(0)
        #colorize
        colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
        randColor = random.uniform(0,5.9)
        icQTapplyColor ( roofCube, colorNames[randColor] )
    
    elif (randType == 2):
    
        roofCube = icQTlShape()
    
    elif (randType == 3):
    
        roofCube = icQTtriplet()
    
    else:
    
        return
    

    #place the cube at the center of the selected polygon
    cmds.xform(roofCube,ws=True,t=(floorCenterX, bbFloor[4], floorCenterZ))
    #and get its dimensions
    bbCube = cmds.xform(roofCube,query=True, bb=True) 
    cubeSize = (abs(bbCube[0] - bbCube[3])) * (abs(bbCube[2] - bbCube[5]))

    userHeight = cmds.floatFieldGrp(query=True,v1='detailHeight')
    userHeight = random.uniform(userHeight * 0.8, userHeight * 1.2)
    if (scaleHeight > 0):
    
        height = userHeight + (userHeight / 10 * scaleHeight)
    
    else:
    
        height = userHeight
    

    #scale and position the cube
    scaleMax = math.sqrt(floorSize / cubeSize)
    randSizeX = random.uniform(scaleMax/2,scaleMax)
    randSizeZ = random.uniform(randSizeX/2,randSizeX)
    cmds.scale(roofCube,a=[randSizeX,height,randSizeZ])

    #freeze the transforms and get the new size
    cmds.makeIdentity(roofCube,t=1,s=1,a=1) 
    bbCube = cmds.xform(roofCube,query=True,bb=True) 

    #calculate the moving range in X and Z
    moveRangeX = bbFloor[3] - bbCube[3]
    moveRangeZ = bbFloor[5] - bbCube[5]
    randMoveX = random.uniform(moveRangeX * -1,moveRangeX)
    randMoveZ = random.uniform(moveRangeZ * -1,moveRangeZ)
    cmds.move(randMoveX, 0, randMoveZ,roofCube,r=True)

    #if the size is too big reduce its scale
    bbCube = cmds.xform(roofCube,query=True,bb=True) 
    for i in range(0,10):
    
        if ((bbCube[0] < bbFloor[0])or(bbCube[3] > bbFloor[3])or(bbCube[2] < bbFloor[2]) or (bbCube[5] > bbFloor[5])):
        
            #move it back and scale
            cmds.move(((randMoveX/10) * -1), 0, ((randMoveZ/10) * -1),roofCube,r=True)
            cmds.scale(roofCube,a=[0.9, 1, 0.9]) 
            cmds.makeIdentity(roofCube, s=1, a=1) 
            #get the new bounding box
            bbCube = cmds.xform(roofCube, query=True, bb=True) 
        
    
    if ((bbCube[0] < bbFloor[0])or(bbCube[3] > bbFloor[3])or(bbCube[2] < bbFloor[2]) or (bbCube[5] > bbFloor[5])):
    
        #if its still too big delete it
        cmds.delete(roofCube)
    

    #if not already present create a group and parent the new geometry under it
    if (not(cmds.objExists('topCubes'))):
    
        cmds.createNode(transform=True,n='topCubes')
    
    if (cmds.objExists(roofCube and cmds.objExists('topCubes'))):
    
        cmds.parent(roofCube,'topCubes')
    


def icQTsingleCube(wells):

    roofCube=None

    #create a cube with the pivot at the bottom
    roofCube = cmds.polyCube(ch=0)
    #delete the bottom face
    cmds.delete(roofCube[0] + ".f[3]")
    cmds.move(0, 0.5, 0,roofCube[0],a=True) 
    cmds.makeIdentity(roofCube[0],t=1,a=1)
    cmds.xform(roofCube[0],ws=True,piv=(0, 0, 0)) 

    rim = random.uniform(0,5)
    if (rim < 2 and wells == 0):
    
        #build a rim at the top
        icQTbuildRim( roofCube[0] + ".f[1]", "" )
    

    return roofCube[0]


def icQTlShape():

    roofCube=None
    extrude=None
    zero=None
    worldPos=None
    randRot=None

    #create a cube with the pivot at the bottom
    roofCube = cmds.polyCube(sx=2,sz=2,ch=0)
    #delete the bottom faces
    cmds.delete(roofCube[0] + ".f[8:11]")
    #move the first two faces along their normals
    cmds.polyMoveFacet(ltz=(random.randint(0,2)) (roofCube[0] + ".f[0:1]"))
    #extrude one face
    extrude = cmds.polyExtrudeFacet((roofCube[0] + ".f[13]"),ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"), (random.uniform(0.5,2)))
    cmds.DeleteHistory(roofCube[0])

    #colorize
    colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,5.9)
    icQTapplyColor ( roofCube[0], colorNames[randColor] )

    #move the cube so that its pivot is centered at the bottom
    cmds.select(cl=True)
    cmds.move(0, 0.5, 0,roofCube[0],a=True)
    cmds.makeIdentity(roofCube[0],t=1,a=1) 
    cmds.select(roofCube[0])
    cmds.CenterPivot()
    cmds.xform(roofCube[0], r=True, piv=(0, -0.5, 0)) 

    # save the position of the object
    worldPos = cmds.xform(roofCube[0],query=True,ws=True,rp=True) 
    # create a new locator and move it to the position of the object
    zero = cmds.spaceLocator()
    cmds.move(worldPos[0], worldPos[1], worldPos[2],zero[0],a=True) 
    # parent the object to the locator
    cmds.parent(roofCube[0],zero[0])
    # move the locator back to the world origin
    cmds.move(0, 0, 0,zero[0],a=True) 
    # unparent the object and delete the locator
    cmds.parent(roofCube[0],w=True) 
    cmds.delete(zero[0])
    # freeze the position transformation of the object
    cmds.makeIdentity(a=1,t=1,r=0,s=0)
    # move the object back to the old position
    cmds.move(worldPos[0], worldPos[1], worldPos[2],roofCube[0],a=True) 

    randRot = random.randint(0,10)
    for i in range(0,randRot):
        cmds.rotate(roofCube[0],os=[0, 90, 0]) 
    

    return roofCube[0]


def icQTtriplet():

    duplicate=None
    combineList=None
    roofCube = icQTsingleCube(0)
    moveVal = random.uniform(1.5,3)

    #colorize
    colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,5.9)
    icQTapplyColor( roofCube, colorNames[randColor] )

    #scale the first cube and store it
    cmds.scale(roofCube, 1, 1, (random.randint(2,4)))
    combineList[len(combineList)] = roofCube

    rim = random.randint(0,5)
    if (rim < 2):
        #build a rim at the top
        icQTbuildRim ( roofCube + ".f[1]", "" )
    

    #duplicate the first cube and offset to x
    duplicate = cmds.duplicate(rr=True,rc=roofCube)
    combineList[len(combineList)] = duplicate[0]
    cmds.move(moveVal,0, 0, duplicate[0],a=True)
    #duplicate a second time and offset to -x
    duplicate = cmds.duplicate(roofCube,rr=True,rc=True) 
    combineList[len(combineList)] = duplicate[0]
    cmds.move((moveVal * -1), 0, 0, duplicate[0], a=True) 

    #combine the three cubes
    triplet = cmds.polyUnite(combineList[0], combineList[1], combineList[2], ch=0) 
    #delete the transform nodes of the other cubes
    for i in range(1,3):
        if (cmds.objExists(combineList[i])):
        
            cmds.delete(combineList[i])
        
    

    randRot = random.randint(0,10)
    for i in range(0,randRot):
        cmds.rotate(0, 90, 0, triplet[0],os=True)
    

    return triplet[0]


def icQTwells( length, size ):

    duplicate=None
    combineList=None
    command=None
    roofCube = icQTsingleCube(1)
    moveVal = random.uniform(1.5,3)
    wellSpace = random.randint(2,6)
    width=None
    wellCount = random.uniform(3,9.9)

    #colorize
    colorNames = ["concrete1","concrete2","dark1","dark2","light1","light2"]
    randColor = random.uniform(0,5.9)
    icQTapplyColor( roofCube, colorNames[randColor] )

    #calculate the width from the given length and size
    width = size / length

    #scale the first cube
    cmds.scale((length / wellCount / wellSpace), 1, 1, roofCube)
    #store the first cube
    combineList[size(combineList)] = roofCube
    #position it at the -x end of the length
    cmds.move((length / -2), 0, 0, roofCube, a=True)

    #duplicate the first cube and offset to x
    for i in range(1,wellCount):
        duplicate = cmds.duplicate(rr=True,rc=roofCube)
        combineList[len(combineList)] = duplicate[0]
        cmds.move((length / wellCount * i), 0, 0, duplicate[0],r=True)
    

    #combine the cubes
    command = "polyUnite -ch 0 "
    for item in combineList:
    
        command = command + item + " "
    
    triplet = cmds.eval(command)

    #delete the transform nodes of the other cubes
    for i in range(1,len(combineList)):
        if (cmds.objExists(combineList[i])):
        
            cmds.delete(combineList[i])
        
    

    #scale the wells for the width
    cmds.scale( 1, 1, (width * 1.05), triplet[0],a=True)

    return triplet[0]


def icQTbuildRim( polyName, color ):

    if (color == ""):
    
        #colorize
        colorNames = ["rim1","rim2"]
        randColor = random.uniform(0,1.9)
        icQTapplyColor ( polyName, colorNames[randColor] )
    
    else:
    
        icQTapplyColor ( polyName, color )
    

    #build a rim at the top
    extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localScale"), 0.95, 0.95, 1,type='double3')
    extrude = cmds.polyExtrudeFacet(polyName,ch=1,kft=0) 
    cmds.setAttr((extrude[0] + ".localTranslateZ"), -0.1)


def icTQcreateColors():

    cityShader=None
    citySG=None
    add=None

    baseColors = [0.6,0.6,0.6,0.7,0.7,0.7,
                            0.4,0.4,0.4,0.6,0.38,0.32,
                            0.47,0.23,0.17,0.36,0.48,0.55,
                            0.2,0.24,0.32,0.73,0.66,0.38,
                            0.6,0.51,0.34,
                            0.25,0.25,0.25,0.12,0.12,0.12,
                            0.85,0.85,0.85,0.96,0.96,0.96,
                            0.3,0.3,0.3,0.5,0.5,0.5]
    colorNames = ["street","concrete1","concrete2",
                            "brick1","brick2","glass1","glass2",
                            "sand1","sand2","dark1","dark2","light1","light2",
                            "rim1","rim2"]
    #create the shader
    for i in range(0,len(colorNames)):
        if (not(cmds.objExists(colorNames[i] + "_SH"))):
        
            cityShader = cmds.shadingNode(asShader='lambert',n=(colorNames[i] + "_SH"))
            citySG = cmds.sets(r=1,nss=1,em=True,n=(colorNames[i] + "_SG"))
            cmds.connectAttr((cityShader + ".outColor"), (citySG + ".surfaceShader"),f=True)
            cmds.setAttr((colorNames[i] + "_SH.color"), baseColors[i + add], baseColors[i + 1 + add], baseColors[i + 2 + add])
            add = add + 2
        
    


def icQTapplyColor( polyName, color ):

    if (cmds.checkBox(query=True,v='applyColor' and cmds.objExists(color + "_SG"))):
        cmds.sets(polyName,e=True,forceElement=(color + "_SG"))
    


def icQTaddToSet(polyName,type):

    #check if the set exists
    if (not(cmds.objExists (type + "_set"))):
    
        sel = cmds.ls(sl=True)
        #clear any selection so that an empty set is created
        cmds.select(cl=True)
        cmds.sets(n=(type + "_set"))
        cmds.select(cl=True)
        for j in sel:
            cmds.select(j,add=True)
    #add the polygons to the set
    cmds.sets(polyName,e=True,fe=(type + "_set"))    


def icQTcurvedStateChange():

    if (cmds.checkBox('curvedCheck',query=True,v=True)):
    
        cmds.checkBox('topDetail',e=True,v=0) 
        cmds.checkBox('topDetail',e=True,en=0) 
        cmds.radioButtonGrp('complexity',e=True,en=0) 
        cmds.floatFieldGrp('detailHeight',e=True,en=0) 
        cmds.checkBox('wellsCheck',e=True,v=0) 
        cmds.checkBox('planarCheck',e=True,v=1) 
        cmds.checkBox('wellsCheck',e=True,en=0) 
        cmds.checkBox('planarCheck',e=True,en=0) 
    
    else:
    
        cmds.checkBox('topDetail',e=True,v=1) 
        cmds.checkBox('topDetail',e=True,en=1) 
        cmds.radioButtonGrp('complexity',e=True,en=1) 
        cmds.floatFieldGrp('detailHeight',e=True,en=1) 
        cmds.checkBox('wellsCheck',e=True,en=1) 
        cmds.checkBox('planarCheck',e=True,en=1) 
    


def icQTown ():

    #declare a new window name
    win = "icQtown"

    #is the window already open?
    if (cmds.window(win,exists=True)):
    
        cmds.deleteUI(win)
    

    cmds.window(win,t="QTown v1.3",w=380,h=530) 
    if (cmds.windowPref(win,exists=True)):
    
        cmds.windowPref(win,e=True,wh=(380, 530)) 
    

    #create the form
    form = cmds.formLayout("parentForm")

    #create the frames in the form
    generalFrame = cmds.frameLayout('generalFrame',l="General",w=380,h=190,li=3,mw=3,mh=3,bs="etchedIn")
    cmds.setParent(form)
    #create the frames in the form
    additionalFrame = cmds.frameLayout('additionalFrame',l="Additional",w=380,h=190,li=3,mw=3,mh=3,bs="etchedIn")
    cmds.setParent(form)

    #create the buttons
    bExecute = cmds.button('executeButton',l="Build QTown",h=30,en=1,c="icQTexecute")

    #position the elements
    cmds.formLayout(form,e=True,
                    af=[(generalFrame, "top", 3),(generalFrame, "left", 3),(generalFrame, "right", 3),
                        (additionalFrame, "left", 3),(additionalFrame, "right", 3),(bExecute, "bottom", 3),
                        (bExecute, "left", 3),(bExecute, "right", 3)],
                    ac=[(additionalFrame, "top", 3, generalFrame),(additionalFrame, "bottom", 3, bExecute)],
                    an=[(generalFrame, "bottom"),(bExecute, "top")])


        

    #create the elements for the uiFrame section
    cmds.setParent(generalFrame)

    cmds.columnLayout()
    cmds.floatFieldGrp('dropout', l="Dropout %",w=180,cw2=(120, 60),
                       ct2=("left", "left"),co2=( 0, 0),cl2=("left", "left"),
                       pre=0,v1=10)
    cmds.floatFieldGrp('heightRange',l="Height Range",w=280,nf=2,cw3=( 120, 80, 80),
                       ct3=("left", "left", "left"), co3=(0, 0, 0), cl3=("left", "left", "left"),
                       pre=2,v1=0.2,v2=3)
    cmds.floatFieldGrp('minScale',l="Min Scale",w=180,cw2=(120, 60),
                       ct2=("left", "left"), co2=(0, 0),cl2=("left", "left"),
                       v1=0.5)
    cmds.checkBox('planarCheck',l="Planar Surface",v=1)
    cmds.text(l="",h=10)
    cmds.checkBox('curvedCheck',l="Curved Surface",v=0,cc=icQTcurvedStateChange())
    cmds.text(l="",h=10)
    cmds.checkBox('applyColor',l="Colorize",v=0)
    cmds.text(l="",h=10)
        
    cmds.setParent(additionalFrame)

    cmds.columnLayout()
    cmds.rowColumnLayout(nc=2,cw=[(1, 100),(2, 280)])
    cmds.checkBox('stairs',l="Stairs",v=1)
    cmds.radioButtonGrp(nrb=4,l="Max Level Steps",h=20,cl5=["left", "left", "left", "left", "left"],
                        cw5=[120, 35, 35, 35, 35],ct5=["left", "both", "both", "both", "both"],
                        la4=["2", "3", "4", "5"],sl=2)

    cmds.text(l="", h=10)
    cmds.text(l="", h=10)

    cmds.checkBox('stacksCheck', l="Stacks", v=0)
    cmds.text( l="", h=20)
    
    cmds.text(l="", h=10)
    cmds.text(l="", h=10)

    cmds.checkBox('wellsCheck', l="Wells", v=1) 
    cmds.floatFieldGrp('wellsOccur', l="Well Occurrence", w=180, cw2=[120, 60],
                       ct2=["left", "left"], co2=[0, 0], cl2=["left", "left"],
                       pre=0, v1=1) 
    cmds.text(l="", h=10)
    cmds.text(l="", h=10)

    cmds.checkBox('shrinkCheck', l="Shrink", v=0) 
    cmds.text(l="", h=10)

    cmds.text(l="", h=14)
    cmds.text(l="", h=14)

    cmds.checkBox('roofCube',l="Roof Cube",v=1) 
    cmds.floatFieldGrp('sizeRange', l="Size Range", w=280, nf=2, cw3=[120, 80, 80],
                       ct3=["left", "left", "left"], co3=[0, 0, 0], cl3=["left", "left", "left"],
                       pre=2, v1=0.25, v2=0.4) 

    cmds.text(l="", h=10)
    cmds.text(l="", h=10)

    cmds.checkBox('topDetail', l="Top Detail", v=1) 
    cmds.radioButtonGrp('complexity', nrb=3, l="Detail Complexity", h=20, cl4=["left", "left", "left", "left"],
                        cw4=[120, 35, 35, 35], ct4=["left", "both", "both", "both"],
                        la3=["1", "2", "3"], sl=1) 
    
    cmds.text(l="", h=5)
    cmds.text(l="", h=5)
    cmds.text(l="", h=20)
    cmds.floatFieldGrp('detailHeight', l="Detail Height", w=180, cw2=[120, 60],
                       ct2=["left", "left"], co2=[0, 0], cl2=["left", "left"],
                       v1=0.5) 

    cmds.text(l="", h=10)
    cmds.text(l="", h=10)

    cmds.checkBox('antenna', l="Antenna", v=1) 
    cmds.setParent(form)

    #make the window visible
    cmds.showWindow(win)


cmds.polyPlane(n='myPlane', sx=25, sy=25,  w=29.056873, h=29.827317)
# use F11 to get face mode  ... hold shift to get more than one face
icQTown()