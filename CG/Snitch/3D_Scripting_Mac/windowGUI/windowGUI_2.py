import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=598,height=555)
mc.columnLayout()
mc.text(l="Text element")
mc.intFieldGrp(l="intField")
mc.button(l="Button")
mc.text(l="etc.")
mc.showWindow("MyWindow")


'''
if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" -w 598 -h 555 MyWindow;
 
columnLayout;
text -l "Text element";
intFieldGrp -l "intField";
button -l "Button";
text -l "etc";
 
showWindow MyWindow;
'''