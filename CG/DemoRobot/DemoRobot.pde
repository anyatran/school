int animationStep = 1; // animation change rate 
int animationBG = 1;  // animation background
float roboX = 450;
float roboY = 505;
int move = 1;

void setup()
{
  
  size(1000, 620);
  background(205,0,0);
  smooth();
}
void draw()
{ 
  
  background(205,0,0);
  move += 1;
  float mx = mouseX;
  float my = mouseY;
  animationBG += 1;
  
  
  drawingBackground(animationBG);
  
  
  
  //move the robot (preparing for translation)
    if (mx > roboX)
  {
    roboX += 2;
  }else{
    if(mx<roboX)
    {
      roboX -= 2;
    }
  }
      if (my > roboY)
  {
   roboY +=2;
  }else{
    if(my<roboY)
    {
    roboY -= 2;
    }
  }
  
  //translating the robot
  translate(roboX,roboY);
  
  //animate the robot
  if (sqrt((mx-roboX)*(mx-roboX)+(my-roboY)*(my-roboY)) > 100){
  animationStep+=1;
  }
  scale(0.5);
  drawingAll(0,0,animationStep);
  
}
//////////////////////////////////////////////////////////////
// reaching to 0,0
void drawingAll(float x,float y,int animate)
{
  int a1 = animate % 40;
  int a2 = animate % 120;
  int a = a1;
  if(a>20)
  {
    a = 40 - a;
  }
  noStroke();
  
  //aerial
  
  fill( 10, 10, 10 );
  ellipse( x+a/20, y-175, 10+a/2, 10+a/2);
  fill( 205, 0, 0 );
  ellipse( x-a/20, y-175, 5, 5 );

  fill(0,0,0);
  rect(x-2,y-170,4,30);
  fill( 205, 0, 0 );
  rect(x-1,y-170,2,30);
  
  triangle(x-20,y-140,x+20,y-140,x,y-145);
  fill(0,0,0);
  
  //body
  fill(65+a,105-a,255-a*2);
  
  //body1
  rect(x-65, y-140, 125, 80, 16, 16, 16, 16);

  //body2
  rect(x-90,y-55,180,120,16+(-10+a),16+(-10+a),16,16);
  
  //body3
  rect(x-40,y+70,80,60,16,16,16,16);
  
  fill(205,0,0);
  //body1.5
  rect(x-10+a/2,y-61,12,6);
  //body2.5
  rect(x-4-a/2,y+64,12,6);
  
  
  //draw face
  
  //white contours
  fill(255,255,255);
  rect(x-55,y-130,35,35,12,12,12,12);
  rect(x+15,y-130,35,35,12,12,12,12);
  
  triangle(x-15,y-70,x,y-95,x+15,y-70);
  
  //red fills
  fill(205,0,0);
  rect(x-50-a/10,y-125-a/10,25+a/5,25+a/5,12,12,12,12);
  rect(x+20-a/10,y-125-a/10,25+a/5,25+a/5,12,12,12,12);
  
  triangle(x-11,y-72,x-3+a/5,y-86,x-3+a/5,y-72);
  triangle(x+3-a/5,y-72,x+3-a/5,y-86,x+11,y-72);
  
  //left arm
  smooth();

  noFill();
  stroke(205,0,0);
  strokeWeight(4);
  beginShape();
  curveVertex(x-90, y-40); 
  curveVertex(x-90, y-40);
  curveVertex(x-120-(a/10), y+5-a); //midpoint of circle
  curveVertex(x-130-(a/2), y+45-a/2); 
  curveVertex(x-130-(a/2), y+45-a/2); 
  endShape();
 

//the mid point  
  fill(203, 96, 21); // UT burnt orange R: 203 G: 96 B: 21
  noStroke();
  ellipse(x-120-(a/10),y+5-a,20,20);
  
    //left hand
 fill(205,0,0);
 translate(x-145-(a/5),y+40-a);
 //rotating
 rotate(radians(a*1.5));
 rect(0,0,40,20,100,100,0,0);
 rect(0+a/2,20,10,20,0,0,0,0);
 rect(30-a/2,20,10,20,0,0,0,0);
 
 //translation cancled
 rotate(radians(-a*1.5));
 translate(145-x+(a/5),-y-40+a);
 
  
 //right arm
 
  smooth();

  noFill();
  stroke(205,0,0);
  strokeWeight(4);
  beginShape();
  curveVertex(x+90, y-40); 
  curveVertex(x+90, y-40);
  curveVertex(x+120+(a/10), y+5-a); //midpoint of circle
  curveVertex(x+130+(a/2), y+45-a/2); 
  curveVertex(x+130+(a/2), y+45-a/2); 
  endShape();
  
//the mid point  
  fill(203, 96, 21);
  noStroke();
  ellipse(x+120+(a/10),y+5-a,20,20);
  
   //right hand
 fill(205,0,0);
 
 translate(x+110+(a/5),y+45-a);
 rotate(radians(-a*1.5));
 rect(0,0,40,20,100,100,0,0);
 rect(0+a/2,20,10,20,0,0,0,0);
 rect(30-a/2,20,10,20,0,0,0,0);
 
 //translation cancled
 rotate(radians(a*1.5));
 translate(-x-110-(a/5),-y-45+a);
  
  //left leg and feet
  //rect(210,275,80,60,16,16,16,16);
  
    smooth();

  noFill();
  stroke(205,0,0);
  strokeWeight(4);
  beginShape();
  curveVertex(x-35, y+125); 
  curveVertex(x-35, y+125);
  curveVertex(x-45-a, y+160-a); //midpoint of circle
  curveVertex(x-35-a, y+200-a); 
  curveVertex(x-35-a, y+200-a); 
  endShape();
  
  fill(205,0,0);
  rect(x-80-a,y+200-a,50,20);
  
  
 //mid point
 noStroke();
 fill(203, 96, 21); // UT burnt orange R: 203 G: 96 B: 21
 ellipse(x-45-a,y+160-a,20,20);


  //right leg and feet
  //rect(210,275,80,60,16,16,16,16);
  
    smooth();

  noFill();
  stroke(205,0,0);
  strokeWeight(4);
  beginShape();
  curveVertex(x+35, y+125); 
  curveVertex(x+35, y+125);
  curveVertex(x+70-a, y+140+a); //midpoint of circle
  curveVertex(x+60-a, y+180+a); 
  curveVertex(x+60-a, y+180+a); 
  endShape();
  
  fill(205,0,0);
  rect(x+56-a,y+180+a,50,20);
  
 //mid point
 noStroke();
 fill(203, 96, 21);
 ellipse(x+70-a,y+140+a,20,20);
   
 
   //the lightning sign
   
  translate(x,y);
  
  rotate(radians(a2*3));
  fill(205,0,0);
  ellipse(0,0,55,55);
  fill(255,255,255);
  ellipse(0,0,46,46);
  //250,205
  fill(255-a*7,a*7,a-200);
  beginShape();
  vertex(-5,-25);
  vertex(-20,0);
  vertex(-5,0);
  vertex(-13,22);
  vertex(19,-10);
  vertex(3,-10);
  vertex(13,-25);
  endShape();
  rotate(radians(-a2*3));
  translate(-x,-y);
  
}

void drawingBackground(int animate)
{
  int a1 = animate % 8;
  int a2 = animate % 360;
  int a3 = animate % 360;
  int amoon = animate % 160;
  a2 += 65;
  int a = a1;
  if(a>4)
  {
    a = 8 - a;
  }
  if(a3>180)
  {
    a3 = 360 - a3;
  }
  if(amoon>80)
  {
    amoon = 160 - amoon;
  }
  fill(175-a3/1.38,238-a3/(0.8*1.38),238-a3/1.38);
  rect(0,0,1000,620);
  //500,280
  fill(0,0,0);
  //ellipse(500,230,30,30); -250,-100
  //the sun 
  
  translate(500,280);
  rotate(radians(a2));
  fill(253, 184, 19);  // UT burnt orange R: 203 G: 96 B: 21  
  // #FDB813 Hex Color | RGB: 253, 184, 19 | SUN, YELLOW ORANGE  
  beginShape();
  vertex(112-a-400,60+a-200);
  vertex(119+a-400,65+a-200);
  vertex(127-a-400,50+a-200);
  vertex(135+a-400,55-a-200);
  vertex(143-a-400,44+a-200);
  vertex(153-a-400,45+a-200);
  vertex(160-a-400,42+a-200);
  vertex(167+a-400,60-a-200);
  vertex(170+a-400,50-a-200);
  vertex(173-a-400,62+a-200);
  vertex(180-a-400,51+a-200);
  vertex(183+a-400,65+a-200);
  vertex(188-a-400,63-a-200);
  vertex(185+a-400,67-a-200);
  
  vertex(195-a-400,70+a-200);
  vertex(199-a-400,77+a-200);
  vertex(192+a-400,85+a-200);
  vertex(196+a-400,92-a-200);
  vertex(193+a-400,100-a-200);
  vertex(199-a-400,105+a-200);
  vertex(193-a-400,111+a-200);
  vertex(196-a-400,117+a-200);
  vertex(191+a-400,122-a-200);
  vertex(197-a-400,129+a-200);
  vertex(193-a-400,134+a-200);
  vertex(198-a-400,137-a-200);
  vertex(194-a-400,141+a-200);
  vertex(191+a-400,137-a-200);
  
  vertex(187-a-400,140-a-200);
  vertex(190+a-400,137+a-200);
  vertex(185-a-400,142+a-200);
  vertex(179+a-400,138-a-200);
  vertex(174+a-400,141-a-200);
  vertex(169-a-400,145+a-200);
  vertex(161-a-400,150+a-200);
  vertex(155+a-400,144-a-200);
  vertex(149+a-400,149-a-200);
  vertex(143-a-400,145+a-200);
  vertex(139-a-400,151+a-200);
  vertex(135-a-400,143-a-200);
  vertex(128-a-400,151-a-200);
  vertex(120+a-400,142+a-200);
  vertex(118+a-400,136+a-200);
  vertex(110-a-400,133+a-200);
  vertex(107-a-400,136+a-200);
  
  vertex(115+a-400,131-a-200);
  vertex(105-a-400,135+a-200);
  vertex(113-a-400,134+a-200);
  vertex(108-a-400,131+a-200);
  vertex(104+a-400,128-a-200);
  vertex(100+a-400,121-a-200);
  vertex(107-a-400,119-a-200);
  vertex(102+a-400,109+a-200);
  vertex(106+a-400,103-a-200);
  vertex(101-a-400,99+a-200);
  vertex(107-a-400,93+a-200);
  vertex(103-a-400,85+a-200);
  vertex(109+a-400,80-a-200);
  vertex(103+a-400,74-a-200);
  vertex(106-a-400,67+a-200);
  vertex(112-a-400,62+a-200);
  endShape();
  
  //110-190,60-140
  
  fill(255,255,32);
  ellipse(-250,-100,80,80);
  
  //the moon
  //ellipse(500,230,30,30); -250,-100
  fill(238,221,130);
  ellipse(250,130,80,80);
  
  //the eclipse?
  fill(175-a3/1.38,238-a3/(0.8*1.38),238-a3/1.38);
  ellipse(160+amoon,130,80,80);
  
  rotate(radians(-a2));
  translate(-500,-280);
  
  //the grass
  
  fill(5,166,17);
  beginShape();
  curveVertex(0,  620);
  curveVertex(0,  620);
  curveVertex(0,  285);
  curveVertex(500,  325);
  curveVertex(1000,  285);
  curveVertex(1000, 620);
  curveVertex(1000, 620);
  endShape();
  
  fill(204, 255, 0);
 // ellipse()
}
