import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.columnLayout("myMainCol")
mc.frameLayout("frameCol1",label="First frame")
mc.text(l="Text element")
mc.intFieldGrp(l="intField")
mc.button(l="Button")
mc.text(l="etc.")
mc.setParent( '..' )
mc.setParent( '..' )
mc.frameLayout("frameCol2",label="Second frame",collapsable=True)
mc.text(l="Text element")
mc.intFieldGrp(l="intField")
mc.button(l="Button")
mc.text(l="etc.")
mc.setParent( '..' )
mc.setParent( '..' )
mc.showWindow("MyWindow")


'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" -w 298 -h 255 MyWindow;
 
columnLayout;
 frameLayout -label "First frame";
  columnLayout;
   text -l "Text element";
   intFieldGrp -l "intField";
   button -l "Button";
   text -l "etc";
   setParent ..;
  setParent ..;
 frameLayout -label "Second frame" -collapsable 1;
  columnLayout;
   text -l "Text element";
   intFieldGrp -l "intField";
   button -l "Button";
   text -l "etc";
   setParent ..;
  setParent ..;
 
showWindow MyWindow


'''

