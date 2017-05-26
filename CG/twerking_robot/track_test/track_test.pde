
void setup( )
{
  size(333, 333);
  background(205,0,0);
  
}

void draw( )
{
  float angle = atan2(mouseY - 100, mouseX - 100);
  
  background(205,0,0);
  pushMatrix();
  translate(100, 100);
  rotate(angle);
  rect(0, 0, 50, 10);
  popMatrix();
}
