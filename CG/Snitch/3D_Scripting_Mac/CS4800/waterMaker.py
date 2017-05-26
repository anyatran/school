import maya.cmds as cmds

def waterMaker():
	cmds.file( f=True, new=True)
	createShader=1
	#Check for Shader
	shads=cmds.ls()
	for myNode in shads:
		if myNode == "waterShader":
			createShader=0
			
	if createShader == 0:
		print "The Shader is already there \n"
		
	elif createShader == 1:
		print "Place the code to create the shader here\n"
		cmds.shadingNode('blinn',asShader=1, n='waterShader')
		# Result: waterShader // 
		cmds.sets(renderable=True,empty=1,noSurfaceShader=True,name='waterShaderSG')
		# Result: waterShaderSG // 
		#cmds.connectAttr('waterShaderSG.surfaceShader','waterShader.outColor', f=True)
		# Result: Connected waterShader.outColor to waterShaderSG.surfaceShader. // 
		#cmds.popupMenu(deleteAllItems='graph1HyperShadeEdPopupMenu',e=1)
		# Result: graph1HyperShadeEdPopupMenu //
		cmds.shadingNode('file', asTexture=1)
		# Result: file1 // 
		cmds.shadingNode('place2dTexture', asUtility=1)
		# Result: place2dTexture1 // 
		#cmds.connectAttr('file1.coverage','place2dTexture1.coverage', f=1)
		# Result: Connected place2dTexture1.coverage to file1.coverage. // 
		cmds.connectAttr('file1.translateFrame','place2dTexture1.translateFrame', f=1)
		# Result: Connected place2dTexture1.translateFrame to file1.translateFrame. // 
		cmds.connectAttr('file1.rotateFrame','place2dTexture1.rotateFrame', f=1)
		# Result: Connected place2dTexture1.rotateFrame to file1.rotateFrame. // 
		cmds.connectAttr('file1.mirrorU','place2dTexture1.mirrorU', f=1)
		# Result: Connected place2dTexture1.mirrorU to file1.mirrorU. // 
		cmds.connectAttr('file1.mirrorV','place2dTexture1.mirrorV', f=1)
		# Result: Connected place2dTexture1.mirrorV to file1.mirrorV. // 
		cmds.connectAttr('file1.stagger','place2dTexture1.stagger', f=1)
		# Result: Connected place2dTexture1.stagger to file1.stagger. // 
		cmds.connectAttr('file1.wrapU','place2dTexture1.wrapU', f=1)
		# Result: Connected place2dTexture1.wrapU to file1.wrapU. // 
		cmds.connectAttr('file1.wrapV','place2dTexture1.wrapV', f=1)
		# Result: Connected place2dTexture1.wrapV to file1.wrapV. // 
		cmds.connectAttr('file1.repeatUV','place2dTexture1.repeatUV', f=1)
		# Result: Connected place2dTexture1.repeatUV to file1.repeatUV. // 
		cmds.connectAttr('file1.offset','place2dTexture1.offset', f=1)
		# Result: Connected place2dTexture1.offset to file1.offset. // 
		cmds.connectAttr('file1.rotateUV','place2dTexture1.rotateUV', f=1)
		# Result: Connected place2dTexture1.rotateUV to file1.rotateUV. // 
		cmds.connectAttr('file1.noiseUV','place2dTexture1.noiseUV', f=1)
		# Result: Connected place2dTexture1.noiseUV to file1.noiseUV. // 
		cmds.connectAttr('file1.vertexUvOne','place2dTexture1.vertexUvOne', f=1)
		# Result: Connected place2dTexture1.vertexUvOne to file1.vertexUvOne. // 
		cmds.connectAttr('file1.vertexUvTwo','place2dTexture1.vertexUvTwo', f=1)
		# Result: Connected place2dTexture1.vertexUvTwo to file1.vertexUvTwo. // 
		cmds.connectAttr('file1.vertexUvThree','place2dTexture1.vertexUvThree', f=1)
		# Result: Connected place2dTexture1.vertexUvThree to file1.vertexUvThree. // 
		cmds.connectAttr('file1.vertexCameraOne','place2dTexture1.vertexCameraOne', f=1)
		# Result: Connected place2dTexture1.vertexCameraOne to file1.vertexCameraOne. // 
		cmds.connectAttr('place2dTexture1.outUV','file1.uv')
		# Result: Connected place2dTexture1.outUV to file1.uvCoord. // 
		cmds.connectAttr('place2dTexture1.outUvFilterSize','file1.uvFilterSize')
		# Result: Connected place2dTexture1.outUvFilterSize to file1.uvFilterSize. // 
		#cmds.popupMenu(deleteAllItems='graph1HyperShadeEdPopupMenu',e=1)
		# Result: graph1HyperShadeEdPopupMenu // 
		cmds.shadingNode('fractal', asTexture=1)
		# Result: fractal1 // 
		cmds.shadingNode('place2dTexture', asUtility=1)
		# Result: place2dTexture2 // 
		cmds.connectAttr('place2dTexture2.outUV','fractal1.uv')
		# Result: Connected place2dTexture2.outUV to fractal1.uvCoord. // 
		cmds.connectAttr('place2dTexture2.outUvFilterSize','fractal1.uvFilterSize')
		# Result: Connected place2dTexture2.outUvFilterSize to fractal1.uvFilterSize. // 
		cmds.shadingNode('plusMinusAverage', asUtility=1)
		# Result: plusMinusAverage1 // 
		cmds.setAttr("fractal1.amplitude",0.094)
		cmds.setAttr("fractal1.colorGain",0.944,1,1,type='double3')
		cmds.connectAttr('file1.outColor', 'plusMinusAverage1.input3D[0]', f=1)
		cmds.connectAttr('fractal1.outColor', 'plusMinusAverage1.input3D[1]', f=1)
		# cmds.defaultNavigation(destination="waterShader.color",createNew=1)
		# cmds.createRenderNode(-allWithTexturesUp, "defaultNavigation -force true -connectToExisting -source %node -destination waterShader.color", "")
		# cmds.defaultNavigation(destination="waterShader.color",defaultTraversal=1)
		cmds.shadingNode('envBall', asTexture=1)
		# Result: envBall1 // 
		cmds.shadingNode('place3dTexture', asUtility=1)
		# Result: place3dTexture1 // 
		cmds.connectAttr('place3dTexture1.wim[0]','envBall1.pm')
		# Result: Connected place3dTexture1.worldInverseMatrix to envBall1.placementMatrix. // 
		#cmds.defaultNavigation(source='envBall1',destination='waterShader.color',connectToExisting=1,force=True)
		#cmds.window('createRenderNodeWindow',vis=False,e=1)
		#cmds.connectAttr('envBall1.outColor', 'waterShader.color', f=1)
		#cmds.defaultNavigation(source='plusMinusAverage1',destination='envBall1.image',ce=1)
		cmds.connectAttr('plusMinusAverage1.output3D', 'envBall1.image', f=1)
		# Result: Connected plusMinusAverage1.output3D to envBall1.image. //
		cmds.shadingNode('fractal', asTexture=1)
		# Result: fractal2 // 
		cmds.shadingNode('place2dTexture', asUtility=1)
		# Result: place2dTexture3 // 
		cmds.connectAttr('place2dTexture3.outUV','fractal2.uv')
		# Result: Connected place2dTexture3.outUV to fractal2.uvCoord. // 
		cmds.connectAttr('place2dTexture3.outUvFilterSize','fractal2.uvFilterSize')
		# Result: Connected place2dTexture3.outUvFilterSize to fractal2.uvFilterSize. // 
		cmds.setAttr("fractal2.amplitude",0.03)
		#cmds.popupMenu(deleteAllItems='graph1HyperShadeEdPopupMenu',e=1)
		# Result: graph1HyperShadeEdPopupMenu // 
		cmds.shadingNode('displacementShader', asUtility=1)
		cmds.connectAttr('fractal2.outAlpha', 'displacementShader1.displacement',f=1)
		# Result: Connected fractal2.outAlpha to displacementShader1.displacement. // 
		cmds.connectAttr('displacementShader1.displacement','waterShaderSG.displacementShader',f=1)
		# Result: Connected displacementShader1.displacement to waterShaderSG.displacementShader. // 
		cmds.setAttr('fractal2.alphaIsLuminance',True)
		
		#
		# MEL CODE (SHADE BUILDING RECORDING, NOT WORKING PROPERLY)
		#
		# shadingNode -asShader blinn;
		# // Result: blinn1 // 
		# sets -renderable true -noSurfaceShader true -empty -name blinn1SG;
		# // Result: blinn1SG // 
		# connectAttr -f blinn1.outColor blinn1SG.surfaceShader;
		# // Result: Connected blinn1.outColor to blinn1SG.surfaceShader. // 
		# popupMenu -e -deleteAllItems graph1HyperShadeEdPopupMenu;
		# // Result: graph1HyperShadeEdPopupMenu //
		# shadingNode -asTexture file;
		# // Result: file1 // 
		# shadingNode -asUtility place2dTexture;
		# // Result: place2dTexture1 // 
		# connectAttr -f place2dTexture1.coverage file1.coverage;
		# // Result: Connected place2dTexture1.coverage to file1.coverage. // 
		# connectAttr -f place2dTexture1.translateFrame file1.translateFrame;
		# // Result: Connected place2dTexture1.translateFrame to file1.translateFrame. // 
		# connectAttr -f place2dTexture1.rotateFrame file1.rotateFrame;
		# // Result: Connected place2dTexture1.rotateFrame to file1.rotateFrame. // 
		# connectAttr -f place2dTexture1.mirrorU file1.mirrorU;
		# // Result: Connected place2dTexture1.mirrorU to file1.mirrorU. // 
		# connectAttr -f place2dTexture1.mirrorV file1.mirrorV;
		# // Result: Connected place2dTexture1.mirrorV to file1.mirrorV. // 
		# connectAttr -f place2dTexture1.stagger file1.stagger;
		# // Result: Connected place2dTexture1.stagger to file1.stagger. // 
		# connectAttr -f place2dTexture1.wrapU file1.wrapU;
		# // Result: Connected place2dTexture1.wrapU to file1.wrapU. // 
		# connectAttr -f place2dTexture1.wrapV file1.wrapV;
		# // Result: Connected place2dTexture1.wrapV to file1.wrapV. // 
		# connectAttr -f place2dTexture1.repeatUV file1.repeatUV;
		# // Result: Connected place2dTexture1.repeatUV to file1.repeatUV. // 
		# connectAttr -f place2dTexture1.offset file1.offset;
		# // Result: Connected place2dTexture1.offset to file1.offset. // 
		# connectAttr -f place2dTexture1.rotateUV file1.rotateUV;
		# // Result: Connected place2dTexture1.rotateUV to file1.rotateUV. // 
		# connectAttr -f place2dTexture1.noiseUV file1.noiseUV;
		# // Result: Connected place2dTexture1.noiseUV to file1.noiseUV. // 
		# connectAttr -f place2dTexture1.vertexUvOne file1.vertexUvOne;
		# // Result: Connected place2dTexture1.vertexUvOne to file1.vertexUvOne. // 
		# connectAttr -f place2dTexture1.vertexUvTwo file1.vertexUvTwo;
		# // Result: Connected place2dTexture1.vertexUvTwo to file1.vertexUvTwo. // 
		# connectAttr -f place2dTexture1.vertexUvThree file1.vertexUvThree;
		# // Result: Connected place2dTexture1.vertexUvThree to file1.vertexUvThree. // 
		# connectAttr -f place2dTexture1.vertexCameraOne file1.vertexCameraOne;
		# // Result: Connected place2dTexture1.vertexCameraOne to file1.vertexCameraOne. // 
		# connectAttr place2dTexture1.outUV file1.uv;
		# // Result: Connected place2dTexture1.outUV to file1.uvCoord. // 
		# connectAttr place2dTexture1.outUvFilterSize file1.uvFilterSize;
		# // Result: Connected place2dTexture1.outUvFilterSize to file1.uvFilterSize. // 
		# popupMenu -e -deleteAllItems graph1HyperShadeEdPopupMenu;
		# // Result: graph1HyperShadeEdPopupMenu // 
		# shadingNode -asTexture fractal;
		# // Result: fractal1 // 
		# shadingNode -asUtility place2dTexture;
		# // Result: place2dTexture2 // 
		# connectAttr place2dTexture2.outUV fractal1.uv;
		# // Result: Connected place2dTexture2.outUV to fractal1.uvCoord. // 
		# connectAttr place2dTexture2.outUvFilterSize fractal1.uvFilterSize;
		# // Result: Connected place2dTexture2.outUvFilterSize to fractal1.uvFilterSize. // 
		# shadingNode -asUtility plusMinusAverage;
		# // Result: plusMinusAverage1 // 
		# setAttr "fractal1.amplitude" 0.094;
		# setAttr "fractal1.colorGain" -type double3 0.944 1 1 ;
		# connectAttr -force file1.outColor plusMinusAverage1.input3D[0];
		# connectAttr -force fractal1.outColor plusMinusAverage1.input3D[1];
		# select -r blinn1 ;
		# defaultNavigation -createNew -destination "blinn1.color";
		# createRenderNode -allWithTexturesUp "defaultNavigation -force true -connectToExisting -source %node -destination blinn1.color" "";
		# defaultNavigation -defaultTraversal -destination "blinn1.color";
		# shadingNode -asTexture envBall;
		# // Result: envBall1 // 
		# shadingNode -asUtility place3dTexture;
		# // Result: place3dTexture1 // 
		# connectAttr place3dTexture1.wim[0] envBall1.pm;
		# // Result: Connected place3dTexture1.worldInverseMatrix to envBall1.placementMatrix. // 
		# defaultNavigation -force true -connectToExisting -source envBall1 -destination blinn1.color; window -e -vis false createRenderNodeWindow;
		# connectAttr -force envBall1.outColor blinn1.color;
		# defaultNavigation -ce -source plusMinusAverage1 -destination envBall1.image;
		# connectAttr -force plusMinusAverage1.output3D envBall1.image;
		# // Result: Connected plusMinusAverage1.output3D to envBall1.image. //
		# shadingNode -asTexture fractal;
		# // Result: fractal2 // 
		# shadingNode -asUtility place2dTexture;
		# // Result: place2dTexture3 // 
		# connectAttr place2dTexture3.outUV fractal2.uv;
		# // Result: Connected place2dTexture3.outUV to fractal2.uvCoord. // 
		# connectAttr place2dTexture3.outUvFilterSize fractal2.uvFilterSize;
		# // Result: Connected place2dTexture3.outUvFilterSize to fractal2.uvFilterSize. // 
		# setAttr "fractal2.amplitude" 0.03;
		# popupMenu -e -deleteAllItems graph1HyperShadeEdPopupMenu;
		# // Result: graph1HyperShadeEdPopupMenu // 
		# connectAttr -f fractal2.outAlpha displacementShader1.displacement;
		# // Result: Connected fractal2.outAlpha to displacementShader1.displacement. // 
		# connectAttr -f displacementShader1.displacement blinn1SG.displacementShader;
		# // Result: Connected displacementShader1.displacement to blinn1SG.displacementShader. // 
		# setAttr fractal2.alphaIsLuminance true;
		# setAttr "polyPlane1.subdivisionsHeight" 40;
		# setAttr "polyPlane1.subdivisionsWidth" 40;
		# defaultNavigation -source blinn1 -destination |pPlane1|pPlaneShape1.instObjGroups[0] -connectToExisting;
		
	cmds.polyPlane(cuv=2,ch=True,h=25,sw=1,o=True,sh=1,w=25)
	myWater = cmds.ls(sl=1)
	cmds.setAttr("polyPlane1.subdivisionsHeight",40)
	cmds.setAttr("polyPlane1.subdivisionsWidth",40)
	cmds.sets(myWater[0], edit=True, forceElement='waterShaderSG')
	cmds.move(0,1,0,r=1)
	cmds.select(myWater)
	cmds.rename("MyWater")
	
waterMaker()