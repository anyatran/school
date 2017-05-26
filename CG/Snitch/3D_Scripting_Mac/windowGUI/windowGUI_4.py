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