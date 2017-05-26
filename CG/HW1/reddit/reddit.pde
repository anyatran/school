int animationStep = 1;
int animationBG = 1;
float roboX = 100;
float roboY = 100;
int move = 1;

void setup() {
  size(500, 500);
  smooth();
  frameRate(22);
}

void draw() {
  background(#4D2B78);
  animationBG+=1;

  move += 1;
  float mx = mouseX;
  float my = mouseY;
  drawBG(animationBG);

  //move the robot (preparing for translation)
  if (mx > roboX) {
    roboX += 2;
  } else {
    if (mx<roboX) {
      roboX -= 2;
    }
  }
  if (my > roboY) {
    roboY +=2;
  } else {
    if (my<roboY) {
      roboY -= 2;
    }
  }

  //translating the robot
  translate(roboX, roboY);

  //animate the robot
  if (sqrt((mx-roboX)*(mx-roboX)+(my-roboY)*(my-roboY)) > 18) {
    animationStep+=1;
  }
  scale(0.7);
  drawBody(10, 10, animationStep);
}
void drawBody(float x, float y, int animate) {
  int a1 = animate % 40;
  int a2 = animate % 120;
  int a = a1;
  if (a>20) {
    a = 40 - a;
  }
  pushMatrix();
  fill(188, 157, 202);
  // BODY
  beginShape();
  curveVertex(140+a+x, 175+a);
  curveVertex(140+a+x, 175+a);
  curveVertex(150+a+x, 147+a);
  curveVertex(170+a+x, 137+a);
  curveVertex(190+a+x, 147+a);
  curveVertex(200+a+x, 175+a);

  curveVertex(200+a+x, 175+a);
  curveVertex(220+a+x, 178+a);
  curveVertex(233+a+x, 197+a);
  curveVertex(227+a+x, 217+a);
  curveVertex(220+a+x, 227+a);
  curveVertex(218+a+x, 229+a);

  curveVertex(218+a+x, 229+a);
  curveVertex(228+a+x, 239+a);
  curveVertex(238+a+x, 263+a);
  curveVertex(230+a+x, 282+a);
  curveVertex(218+a+x, 293+a);
  curveVertex(210+a+x, 291+a);

  curveVertex(210+a+x, 291+a);
  curveVertex(208+a+x, 300+a);
  curveVertex(200+a+x, 310+a);
  curveVertex(190+a+x, 306+a);
  curveVertex(186+a+x, 303+a);
  curveVertex(184+a+x, 301+a);

  curveVertex(184+a+x, 301+a);
  curveVertex(183+a+x, 300+a);
  curveVertex(178+a+x, 309+a);
  curveVertex(173+a+x, 312+a);
  curveVertex(163+a+x, 306+a);
  curveVertex(160+a+x, 302+a);
  curveVertex(158+a+x, 299+a);

  curveVertex(158+a+x, 299+a);
  curveVertex(157+a+x, 300+a);
  curveVertex(150+a+x, 309+a);
  curveVertex(145+a+x, 310+a);
  curveVertex(140+a+x, 306+a);
  curveVertex(138+a+x, 302+a);
  curveVertex(137+a+x, 297+a);
  curveVertex(136+a+x, 296+a);

  curveVertex(136+a+x, 296+a);
  curveVertex(133+a+x, 298+a);
  curveVertex(128+a+x, 301+a);
  curveVertex(123+a+x, 302+a);
  curveVertex(122+a+x, 301+a);
  curveVertex(120+a+x, 298+a);
  curveVertex(118+a+x, 294+a);
  curveVertex(116+a+x, 288+a);
  curveVertex(117+a+x, 282+a);


  curveVertex(117+a+x, 282+a);
  curveVertex(109+a+x, 276+a);
  curveVertex(104+a+x, 270+a);
  curveVertex(101+a+x, 267+a);
  curveVertex(96+a+x, 258+a);
  curveVertex(98+a+x, 246+a);
  curveVertex(103+a+x, 238+a);
  curveVertex(110+a+x, 225+a);

  curveVertex(110+a+x, 225+a);
  curveVertex(104+a+x, 220+a);
  curveVertex(96+a+x, 208+a);
  curveVertex(95+a+x, 195+a);
  curveVertex(106+a+x, 182+a);
  curveVertex(140+a+x, 175+a);
  curveVertex(140+a+x, 175+a);

  endShape();


  //STAR
  fill(#FFDE75);
  stroke(#D58190);
  beginShape();
  vertex(163+a+x, 169+a);
  vertex(152+a+x, 165+a);
  vertex(165+a+x, 160+a);
  vertex(170+a+x, 147+a);
  vertex(175+a+x, 160+a);
  vertex(188+a+x, 165+a);
  vertex(178+a+x, 169+a);
  vertex(182+a+x, 179+a);
  vertex(171+a+x, 170+a);
  vertex(162+a+x, 179+a);
  endShape();

  //EYES
  fill(0);
  stroke(0);
  ellipse(150+a+x, 200+a, 6, 8); //left eye
  ellipse(190+a+x, 200+a, 6, 8); //right eye
  ellipse(170+a+x, 220+a, 22, 14); //mouth
  noFill();
  strokeWeight(1.5);
  beginShape(); //left eye brow
  curveVertex(145+a+x, 188+a);
  curveVertex(145+a+x, 188+a);
  curveVertex(150+a+x, 185+a);
  curveVertex(154+a+x, 186+a);
  curveVertex(154+a+x, 186+a);
  endShape();

  beginShape(); // right eye brow
  curveVertex(190+a+x, 186+a);
  curveVertex(190+a+x, 186+a);
  curveVertex(195+a+x, 185+a);
  curveVertex(199+a+x, 188+a);
  curveVertex(199+a+x, 188+a);
  endShape();

  //RIGHTARM

  fill(188, 157, 202);
  stroke(0);
  translate((a/10), a/3-1);
  rotate(radians(a*0.05));
  beginShape();
  curveVertex(212+(a/10)+a+x, 218+a/3+a);
  curveVertex(212+(a/10)+a+x, 218+a/3+a);
  curveVertex(242-(a/10)+a+x, 219+a/3+a);
  curveVertex(262-(a/10)+a+x, 225+a/1.5+a);
  curveVertex(263-(a/2)+a+x, 230+a/2+a);
  curveVertex(242-(a/2)+a+x, 227+a/3+a);
  curveVertex(212+(a/3)+a+x, 227+a/3+a);
  curveVertex(212+(a/3)+a+x, 227+a/3+a);
  endShape();
  rotate(radians(a*0.05));
  translate((a/10), a/3-1);
  

  // LEFT ARM
  beginShape();
  curveVertex(120-(a/10)+a+x, 218+a/3+a);
  curveVertex(120-(a/10)+a+x, 218+a/3+a);
  curveVertex(90+(a/10)+a+x, 219+a/3+a);
  curveVertex(70+(a/10)+a+x, 225+a/1.5+a);
  curveVertex(69+(a/2)+a+x, 230+a/2+a);
  curveVertex(90+(a/2)+a+x, 227+a/3+a);
  curveVertex(120-(a/3)+a+x, 227+a/3+a);
  curveVertex(120-(a/3)+a+x, 227+a/3+a);
  endShape();
  popMatrix();
}

void drawBG(int animate) {
pushMatrix();
//  float[][] randomStars = new float[20][2];
  int fallingstar = animate % height;
//  
//  
//  for (
//  
//  if (fallingstar>height)
  {
    fallingstar = 0;
    fallingstar+=6;
  }
  fill(255);
  
  ellipse(100, fallingstar, 10, 10);
  popMatrix();
}
