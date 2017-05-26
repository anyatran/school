class Explosion {
  int mass;
  ArrayList particles;
  //int LIFESPAN; //= 120;
  //global gravity
  // PVector acceleration; // =  new PVector(0f, 0.025);
  Explosion(int mass) {
    this.mass = mass;
    //this.LIFESPAN = 120;
    //this.acceleration = new PVector(0f, 0.025);
  }

  //  void setup() {
  //    size(400, 400);
  //    stroke(0);
  //    strokeWeight(3);
  //    fill(150);
  //    smooth();
  //    particles = new ArrayList();
  //    for (int i = 0; i < mass; i++) {
  //      Particle p = new Particle();
  //      particles.add(p);
  //    }
  //  }
  //
  //  void draw() {
  //    background(255);
  //    //only create when mouse moves
  //    if (abs(mouseX-pmouseX) > 0.0001) {
  //      particles.add(new Particle());
  //    }
  //    //for (int i = particles.size ()-1; i >= 0; i--) {
  //    for (int i = mass - 1; i >= 0; i--) {
  //      Particle p = (Particle)particles.get(i);
  //      if (!p.exist()) {
  //        particles.remove(i);
  //      }
  //    }
  //  }
}


public class Particle {
  PVector location;
  PVector velocity;
  int age;
  PVector acceleration;
  int LIFESPAN;
  public Particle() {
    location = new PVector(mouseX, mouseY);
    //get velocity from direction and speed of mouse movement
    velocity = new PVector(mouseX-pmouseX, mouseY-pmouseY);
    age = 0;
    acceleration = new PVector(0f, 0.025);
    this.LIFESPAN = 120;
  }

  public boolean exist() {
    velocity.add(acceleration);
    location.add(velocity);
    ellipse(location.x, location.y, 10, 10);
    if (age > LIFESPAN) {
      return false;
    }
    age++;
    return true;
  }
}
// SETTING UP THE GAME
Explosion e;// = new Explosion(10);
void setup() {
  size(400, 400);
  stroke(0);
  strokeWeight(3);
  fill(150);
  smooth();
  e = new Explosion(10);
  e.particles = new ArrayList();
  for (int i = 0; i < e.mass; i++) {
    Particle p = new Particle();
    e.particles.add(p);
  }
}

void draw() {
  background(255);
  //only create when mouse moves
  if (abs(mouseX-pmouseX) > 0.0001) {
    e.particles.add(new Particle());
  }
  //for (int i = particles.size ()-1; i >= 0; i--) {
  for (int i = e.mass - 1; i >= 0; i--) {
    Particle p = (Particle)e.particles.get(i);
    if (!p.exist()) {
      e.particles.remove(i);
    }
  }
}

