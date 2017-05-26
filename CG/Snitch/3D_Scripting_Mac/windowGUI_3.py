import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=598,height=555)
mc.rowLayout(nc=3)
mc.intField()
mc.intField()
mc.button(l="Button")
mc.showWindow("MyWindow")

'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}

window -t "Example Window"  -w 598 -h 555 MyWindow;
 
rowLayout -nc 3;
intField;
intField;
button -l "Button";
 
showWindow MyWindow
'''