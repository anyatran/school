class Robot {
  int animationStep;
  float rx;
  float ry;
  int move;
  float easing;
  Robot() {
    animationStep = 1;
    move = 1;
    easing = 0.05;
  }
  void drawBody() {
    int a1 = animationStep % 40;
    int a = a1;
    if (a>20) {
      a = 40 - a;
    }
    pushMatrix();
    fill(188, 157, 202);
    stroke(0);
    // BODY
    beginShape();
    curveVertex(140+a, 175+a);
    curveVertex(140+a, 175+a);
    curveVertex(150+a, 147+a);
    curveVertex(170+a, 137+a);
    curveVertex(190+a, 147+a);
    curveVertex(200+a, 175+a);

    curveVertex(200+a, 175+a);
    curveVertex(220+a, 178+a);
    curveVertex(233+a, 197+a);
    curveVertex(227+a, 217+a);
    curveVertex(220+a, 227+a);
    curveVertex(218+a, 229+a);

    curveVertex(218+a, 229+a);
    curveVertex(228+a, 239+a);
    curveVertex(238+a, 263+a);
    curveVertex(230+a, 282+a);
    curveVertex(218+a, 293+a);
    curveVertex(210+a, 291+a);

    curveVertex(210+a, 291+a);
    curveVertex(208+a, 300+a);
    curveVertex(200+a, 310+a);
    curveVertex(190+a, 306+a);
    curveVertex(186+a, 303+a);
    curveVertex(184+a, 301+a);

    curveVertex(184+a, 301+a);
    curveVertex(183+a, 300+a);
    curveVertex(178+a, 309+a);
    curveVertex(173+a, 312+a);
    curveVertex(163+a, 306+a);
    curveVertex(160+a, 302+a);
    curveVertex(158+a, 299+a);

    curveVertex(158+a, 299+a);
    curveVertex(157+a, 300+a);
    curveVertex(150+a, 309+a);
    curveVertex(145+a, 310+a);
    curveVertex(140+a, 306+a);
    curveVertex(138+a, 302+a);
    curveVertex(137+a, 297+a);
    curveVertex(136+a, 296+a);

    curveVertex(136+a, 296+a);
    curveVertex(133+a, 298+a);
    curveVertex(128+a, 301+a);
    curveVertex(123+a, 302+a);
    curveVertex(122+a, 301+a);
    curveVertex(120+a, 298+a);
    curveVertex(118+a, 294+a);
    curveVertex(116+a, 288+a);
    curveVertex(117+a, 282+a);


    curveVertex(117+a, 282+a);
    curveVertex(109+a, 276+a);
    curveVertex(104+a, 270+a);
    curveVertex(101+a, 267+a);
    curveVertex(96+a, 258+a);
    curveVertex(98+a, 246+a);
    curveVertex(103+a, 238+a);
    curveVertex(110+a, 225+a);

    curveVertex(110+a, 225+a);
    curveVertex(104+a, 220+a);
    curveVertex(96+a, 208+a);
    curveVertex(95+a, 195+a);
    curveVertex(106+a, 182+a);
    curveVertex(140+a, 175+a);
    curveVertex(140+a, 175+a);

    endShape();


    //STAR
    fill(#FFDE75);
    stroke(#D58190);
    beginShape();
    vertex(163+a, 169+a);
    vertex(152+a, 165+a);
    vertex(165+a, 160+a);
    vertex(170+a, 147+a);
    vertex(175+a, 160+a);
    vertex(188+a, 165+a);
    vertex(178+a, 169+a);
    vertex(182+a, 179+a);
    vertex(171+a, 170+a);
    vertex(162+a, 179+a);
    endShape();

    //EYES
    fill(0);
    stroke(0);
    ellipse(150+a, 200+a, 6, 8); //left eye
    ellipse(190+a, 200+a, 6, 8); //right eye
    ellipse(170+a, 220+a, 22, 14); //mouth
    noFill();
    strokeWeight(1.5);
    beginShape(); //left eye brow
    curveVertex(145+a, 188+a);
    curveVertex(145+a, 188+a);
    curveVertex(150+a, 185+a);
    curveVertex(154+a, 186+a);
    curveVertex(154+a, 186+a);
    endShape();

    beginShape(); // right eye brow
    curveVertex(190+a, 186+a);
    curveVertex(190+a, 186+a);
    curveVertex(195+a, 185+a);
    curveVertex(199+a, 188+a);
    curveVertex(199+a, 188+a);
    endShape();

    //RIGHTARM

    fill(188, 157, 202);
    stroke(0);
    translate((a/10), a/3-1);
    rotate(radians(a*0.05));
    beginShape();
    curveVertex(212+(a/10)+a, 218+a/3+a);
    curveVertex(212+(a/10)+a, 218+a/3+a);
    curveVertex(242-(a/10)+a, 219+a/3+a);
    curveVertex(262-(a/10)+a, 225+a/1.5+a);
    curveVertex(263-(a/2)+a, 230+a/2+a);
    curveVertex(242-(a/2)+a, 227+a/3+a);
    curveVertex(212+(a/3)+a, 227+a/3+a);
    curveVertex(212+(a/3)+a, 227+a/3+a);
    endShape();
    rotate(radians(a*0.05));
    translate((a/10), a/3-1);


    // LEFT ARM
    beginShape();
    curveVertex(120-(a/10)+a, 218+a/3+a);
    curveVertex(120-(a/10)+a, 218+a/3+a);
    curveVertex(90+(a/10)+a, 219+a/3+a);
    curveVertex(70+(a/10)+a, 225+a/1.5+a);
    curveVertex(69+(a/2)+a, 230+a/2+a);
    curveVertex(90+(a/2)+a, 227+a/3+a);
    curveVertex(120-(a/3)+a, 227+a/3+a);
    curveVertex(120-(a/3)+a, 227+a/3+a);
    endShape();
    popMatrix();
  }
}
class Star {
  float x;
  float y;
  float radius1;
  float radius2;
  int npoints;
  Star(int x, int y) {
    x = x;
    y =  y;
    radius1 = 30;
    radius2 = 70;
    npoints = 5;
  }

  void star() {
    float angle = TWO_PI / npoints;
    float halfAngle = angle/2.0;
    beginShape();
    for (float a = 0; a < TWO_PI; a += angle) {
      float sx = x + cos(a) * radius2;
      float sy = y + sin(a) * radius2;
      vertex(sx, sy);
      sx = x + cos(a+halfAngle) * radius1;
      sy = y + sin(a+halfAngle) * radius1;
      vertex(sx, sy);
    }
    endShape(CLOSE);
  }
}
//class BG {
//  int animationBG;
//  float sx;
//  float sy;
//  BG() {
//    animationBG = 1;
//  }
//
//
//  void drawBG() {
//  Star st = new Star
//    sx = random(0, width*2);
//    sy = 0;
//    animationBG+=1;
//    pushMatrix();
//    int fallingstar = animationBG % height;
//    if (fallingstar>height)
//    {
//      fallingstar = 0;
//      fallingstar+=6;
//    }
//    Star st = new Star(sx, fallingstar);
//    fill(#FFDE75);
//    stroke(#D58190);
//    //    beginShape();
//    //    scale(0.3);
//    //    vertex(sx+23, 29+sy+fallingstar*10);
//    //    vertex(sx+12, 25+sy+fallingstar*10);
//    //    vertex(sx+25, 20+sy+fallingstar*10);
//    //    vertex(sx+30, 7+sy+fallingstar*10);
//    //    vertex(sx+35, 20+sy+fallingstar*10);
//    //    vertex(sx+48, 25+sy+fallingstar*10);
//    //    vertex(sx+38, 29+sy+fallingstar*10);
//    //    vertex(sx+42, 39+sy+fallingstar*10);
//    //    vertex(sx+31, 30+sy+fallingstar*10);
//    //    vertex(sx+22, 39+sy+fallingstar*10);
//    //    endShape();
//    //st.star(animationBG);
//    popMatrix();
//  }
//}

// SETING UP THE GAME
Star st1;
Star st2;
Star st3;
Robot r;
//BG b;
void setup() {
  r = new Robot();
  //b = new BG();
  st1 = new Star(250, 250);
  st2 = new Star(60, 20);
  st3 = new Star(430, 100);
  size(500, 500);
  smooth();
  frameRate(22);
}

void draw() {
  background(#4D2B78);

  r.move += 1;
  float targetX = mouseX-90;
  float dx = targetX - r.rx;
  if (abs(dx) > 1) {
    r.rx += dx * r.easing;
  }
  float targetY = mouseY-90;
  float dy = targetY - r.ry;
  if (abs(dy) > 1) {
    r.ry += dy * r.easing;
  }

  //b.drawBG();
  pushMatrix();
  translate(250, 250);
  rotate(frameCount / -100.0);
  fill(#FFDE75);
  st1.star();  
  rotate(frameCount / -100.0);
  translate(250, 250);
  popMatrix();
  pushMatrix();
  translate(60, 20);
  rotate(frameCount / 100.0);
  st2.star();
  rotate(frameCount / 100.0);
  translate(60, 20);
  popMatrix();

  pushMatrix();
  translate(430, 100);
  rotate(frameCount / 20.0);
  st3.star();
  rotate(frameCount / 20.0);
  translate(430, 100);
  popMatrix();

  //translating the robot
  translate(r.rx, r.ry);

  //animate the robot
  if (abs(dx) > 1) {
    r.animationStep+=1;
  }
  scale(0.7);
  r.drawBody();
}

