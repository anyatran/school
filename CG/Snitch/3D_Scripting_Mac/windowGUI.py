import maya.cmds as mc
if mc.window("WinOne", q=True, ex=True):
  mc.deleteUI("WinOne")
  
# In Python: selectedObjects = mc.ls(sl=True)
'''
if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" MyWindow;
 
columnLayout;
text -l "Text element";
intFieldGrp -l "intField";
button -l "Button";
text -l "etc";
 
showWindow MyWindow;
'''
import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=598,height=555)
mc.columnLayout(rowSpacing=10)
mc.text(l="Text element")
mc.intFieldGrp(l="intField")
mc.button(l="Button")
mc.text(l="etc.")
mc.showWindow("MyWindow")

'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" MyWindow;
 
rowLayout -nc 3;
intField;
intField;
button -l "Button";
 
showWindow MyWindow
'''

import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=598,height=555)
mc.rowLayout(nc=3)
mc.intField()
mc.intField()
mc.button(l="Button")
mc.showWindow("MyWindow")


import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowLayout(nc=3,cw3=[60, 100, 60])
mc.intField()
mc.intField()
mc.button(l="Button")
mc.showWindow("MyWindow")


'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" MyWindow;
 columnLayout myMainCol; // (child of the window)
  rowLayout -nc 2 myFirstRow; //this is a child of the myMainCol layout
   text -l "first element in the row layout"; // child of myFirstRow
   text -l "second element in the row layout";
   setParent ..; // go up one in the hierarchy. 
   // Note: You may also use setParent myMainCol in this case
  text -l "first element of the myMainCol layout";
  text -l "second element of the myMainCol layout";
 showWindow MyWindow

'''

import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.columnLayout("myMainCol")
mc.rowLayout("myFirstRow",numberOfColumns=2)
mc.text(l="first element in the row layout")
mc.text(l="second element in the row layout")
mc.setParent( '..' )
mc.text(l="first element of the myMainCol layout")
mc.text(l="second element of the myMainCol layout")
mc.intField()
mc.intField()
mc.button(l="Button")
mc.showWindow("MyWindow")



'''

window;
 rowColumnLayout -numberOfColumns 1;
  button -l "button1";
  button -l "button2";
  button -l "button3";
showWindow

'''
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowColumnLayout("myMainRowCol",numberOfColumns=1)
mc.button(l="Button 1")
mc.button(l="Button 2")
mc.button(l="Button 3")
mc.showWindow("MyWindow")

'''
window;
 rowColumnLayout -numberOfColumns 2;
  button -l "button1";
  button -l "button2";
  button -l "button3";
  button -l "button4";
showWindow

'''
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowColumnLayout("myMainRowCol",numberOfColumns=2)
mc.button(l="Button 1")
mc.button(l="Button 2")
mc.button(l="Button 3")
mc.button(l="Button 4")
mc.showWindow("MyWindow")


'''
window;
 rowColumnLayout -numberOfRows 1;
  button -l "button1";
  button -l "button2";
  button -l "button3";
showWindow

'''


if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowColumnLayout("myMainRowCol",numberOfRows=1)
mc.button(l="Button 1")
mc.button(l="Button 2")
mc.button(l="Button 3")
mc.showWindow("MyWindow")

'''
window;
 rowColumnLayout -numberOfRows 2;
  button -l "button1";
  button -l "button2";
  button -l "button3";
  button -l "button4";
showWindow

'''

if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowColumnLayout("myMainRowCol",numberOfRows=2)
mc.button(l="Button 1")
mc.button(l="Button 2")
mc.button(l="Button 3")
mc.button(l="Button 4")
mc.showWindow("MyWindow")


'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
window -t "Example Window" MyWindow;
 
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
