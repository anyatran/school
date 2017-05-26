void setup( )
{
  size(555,555);
}

void draw( )
{
  float angle = atan2(mouseY - 100, mouseX - 100);
  
  background(97, 84, 69); // #615445 Northeastern University Warm Gray 
  pushMatrix();
  translate(263,263);
  rotate(angle);
  rect(0, 0, 50, 10);
  popMatrix();
}
