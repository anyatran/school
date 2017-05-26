import maya.cmds as mc
if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")
'''
if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}
'''