if mc.window("MyWindow", q=True, ex=True):
  mc.deleteUI("MyWindow")  
mc.window("MyWindow",t="Example Window",width=298,height=255)
mc.rowColumnLayout("myMainRowCol",numberOfColumns=1)
mc.button(l="Button 1")
mc.button(l="Button 2")
mc.button(l="Button 3")
mc.showWindow("MyWindow")

'''

if ( `window -exists MyWindow` ) {
 deleteUI MyWindow;
}

window -t "Example Window" -w 298 -h 255 MyWindow;
 rowColumnLayout -numberOfColumns 1;
  button -l "button1";
  button -l "button2";
  button -l "button3";
showWindow

'''