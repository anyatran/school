import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Particle {
	int x;
	int y;
	int dx;
	int dy;
	int size;
	Color color;
	int life;
	int mass;
	int transparency;
	Particle(int x, int y, int dx, int dy, int size, 
			Color color, int life, int mass, int transparency) {
		this.x = x;
		this.y = y; 
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.color = color;
		this.life = life;
		this.mass = mass;
		this.transparency = transparency;
	}
	/**
	 * returns true if the lifetime is less that 0
	 * @return a boolean
	 */
	 boolean update() {
		x += dx;
		y += dy;
		life--;
		return (life <= 0);
	}
	// set the initial state
	 static Particle initState() {
		return new Particle(100, 100, 10, 10, 10, Color.blue, 
				10, 10, 255);
	}
	
	 void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(color);
        g2d.fillOval(x-(size/2), y-(size/2), size, size);
 
        g2d.dispose();
	}
}
