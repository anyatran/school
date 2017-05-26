class Particle {
  PShape part;
  float velx;
  float vely;
  PVector vel;
  int size;
  int r;
  int g;
  int b;
  int transparency;
  int lifetime;
  final int oldLT;
  PVector gravity = new PVector(0, 0.1);
  Particle(float velx, float vely, int size, int r, int g, int b, 
  int transparency, int lifetime) {
    part = createShape(ELLIPSE, 0, 0, size, size);
    part.setFill(color(r,g,b,transparency));
    velx = velx;
    vely = vely;
    size = size;
    r = r;
    g = g;
    b = b;
    transparency = transparency;
    
    lifetime = lifetime;
    oldLT = lifetime;
    rebirth(width/2, height/2);
  }

  PShape getShape() {
    return part;
  }
  void rebirth(float x, float y) {
    float a = random(TWO_PI);
    float speed = random(0.5, 4);
    vel = new PVector(cos(a), sin(a));
    vel.mult(speed);
    lifetime = oldLT;
    transparency = 100;
    part.resetMatrix();
    part.translate(x, y);
  }

  boolean isDead() {
    if (lifetime < 0) {
      return true;
    } else {
      return false;
    }
  }

  public void update() {
    lifetime = lifetime - 1;
    vel.add(gravity);

    part.translate(vel.x, vel.y);
  }
}

class Particles {
  ArrayList<Particle> particles;
  PShape particleShape;
  float velx;
  float vely;
  int size;
  int r;
  int g;
  int b;
  int transparency;
  int lifetime;
  int mass;
  Particles(float velx, float vely, int size, int r, int g, int b,
  int transparency, int lifetime, int mass) {
    particleShape = createShape(PShape.GROUP);
    velx = velx;
    vely = vely;
    particles = new ArrayList<Particle>();
    size = size;
    r = r;
    g = g;
    b = b;
    transparency = transparency;
    lifetime = lifetime;
    mass = mass;

    for (int i = 0; i < mass; i++) {
      Particle p = new Particle(velx, vely, size, r, g, b, 
      transparency, lifetime);
      particles.add(p);
      particleShape.addChild(p.getShape());
    }
  }
  void update() {
    for (Particle p : particles) {
      p.update();
    }
  }
  void setEmitter(float x, float y) {
    for (Particle p : particles) {
      if (p.isDead()) {
        p.rebirth(x, y);
      }
    }
  }

  void display() {
    shape(particleShape);
  }
}

Particles ps;
void setup() {
  size(500, 500, P2D);
  orientation(LANDSCAPE);
  
  ps = new Particles(3, 5, 10, 255, 255, 255, 
  100, 10, 10);
  hint(DISABLE_DEPTH_MASK);
} 

void draw () {
  background(0);
  ps.update();
  ps.display();

  ps.setEmitter(mouseX, mouseY);

  //fill();
}

