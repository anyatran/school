import maya.cmds as mc
# Check for shader
shades = mc.ls(mat=True)
# scale 1 .5 1;
mc.scale(1,0.5,1)
# sphere -ax 0 1 0 -n "core";
myCore = mc.polySphere(axis=[0,1,0], name="core")
# move 0 0.2 0;
mc.move(0,0.2,0)
mc.select(myPetal[0])
mc.duplicate() 
'''
	string $sel[] = `ls -sl`;
	string $polyNameArray[];
	string $polyName;
	string $compList;
	string $faceNum[];
	float $dropout;
	float $lengthValue;
		setAttr ($extrude[0] + ".localScaleX") ($localScale[0] * 0.99);
		$extrude = `polyExtrudeFacet -ch 1 -kft 0 $polyName`;

'''