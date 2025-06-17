package e102_Manmeet_Sagri_Assignment1_301545311;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import processing.core.PVector;


public class Turtle {
	
	//fields 
	private PVector pos;
	private PVector vel;
	

	
	private Color shellColor;
	private Color bodyColor;
	private Color shellCircles;
	private double scaleFactor;
	
	private boolean facingLeft = false;
	//private static final float HALF_WIDTH = 70;   //for boundary box.. edge handling
	//private static final float HALF_HEIGHT = 65;  
	
	//animations
	private float headBob;
	private int legStepCounter;
	private boolean legStepForward = true; // toggle leg position

	
	// constructor 
	public Turtle (int x, int y) {
		
		this.pos = new PVector(x,y);
		
		// set shell color randomly from light brown to dark brown (took help from online resources to random brown colors)
		this.shellColor = new Color(100 + (int)(Math.random() * 70), 40 + (int)(Math.random() * 60), (int)(Math.random() * 40));
		
		// set body color to green
		this.bodyColor = new Color(61, 99, 26);
		
		this.shellCircles = new Color(247, 213, 195);

	 

		this.scaleFactor = 0.5 + Math.random() * 0.5; // range: 0.5 to 1.0 RANDOMLY SCALE 
	    this.headBob = 0;
	    
	    this.vel = new PVector(1, 1); // move directly to the right
	    this.vel.setMag(1);           // set speed
	    this.facingLeft = false;      // make sure it faces right
	
		
	}
	
	public void draw(Graphics2D g2) {
	    // Save current transform so we can restore later
	    AffineTransform old = g2.getTransform();

	    // Move to turtle's position
	    g2.translate(pos.x, pos.y);
	    g2.scale(scaleFactor, scaleFactor);
	    

	    if (facingLeft) {
	        g2.scale(-1, 1);             // mirror horizontally
	        g2.translate(-80, 0);       // shift because drawing starts at x = 0 to x = 100+
	    }
	    
	

	    // (0,0) is the center of the turtle
	    drawTurtleBody(g2);
	    
	    
	 	//  bounding box 
	    //g2.setColor(Color.BLUE);
	    //int boxX = -5;   // farthest left part (face)
	    //int boxY = -30;   // top leg
	    //int boxW = 150;   // from -40 to +100 (face to far right)
	    //int boxH = 130;   // from -30 to +100 (top leg to bottom)

	    //g2.drawRect(boxX, boxY, boxW, boxH);
	    
	    
	    
	    

	    // Restore transform so nothing else is affected
	    g2.setTransform(old);
	    
	   
	}
	
	private void drawTurtleBody(Graphics2D g2) {
		
		AffineTransform original = g2.getTransform();

	    
		g2.translate(0, Math.sin(headBob) * 6);
		
		//face
		g2.setColor(bodyColor);
		g2.fillOval(95,0,50,40);
		
		//eyes
		g2.setColor(Color.WHITE);
	    g2.fillOval( 110, 0,  20, 20);
	    
	    g2.setColor(Color.BLACK);
	    g2.fillOval( 120, 7, 8, 10);
	    
	    //mouth 
	  	g2.setColor(Color.RED);
	  	g2.fillOval(120, 30, 20,5 );
	   
	    g2.setTransform(original);
		
		//neck
		g2.setColor(bodyColor);
		g2.fillOval(65,10,40,50);
		
		
		AffineTransform old = g2.getTransform();
		// === LEG 1 === Front leg (bottom right)
		old = g2.getTransform();
		g2.translate(15, 50);
		if (legStepForward) {
		    g2.rotate(Math.toRadians(15));
		} else {
		    g2.rotate(Math.toRadians(-15));
		}
		g2.setColor(bodyColor);
		g2.fillOval(-15, 0, 30, 50);
		g2.setTransform(old);

		// === LEG 2 === Back leg (upper right)
		old = g2.getTransform();
		g2.translate(25, -30);
		if (legStepForward) {
		    g2.rotate(Math.toRadians(-15));
		} else {
		    g2.rotate(Math.toRadians(15));
		}
		g2.setColor(bodyColor);
		g2.fillOval(-15, 0, 30, 50);
		g2.setTransform(old);

		// === LEG 3 === Back left leg (top)
		old = g2.getTransform();
		g2.translate(75, -20);
		if (legStepForward) {
		    g2.rotate(Math.toRadians(-10));
		} else {
		    g2.rotate(Math.toRadians(10));
		}
		g2.setColor(bodyColor);
		g2.fillOval(-15, -10, 30, 50);
		g2.setTransform(old);

		// === LEG 4 === Back left leg (bottom)
		old = g2.getTransform();
		g2.translate(65, 50);
		if (legStepForward) {
		    g2.rotate(Math.toRadians(10));
		} else {
		    g2.rotate(Math.toRadians(-10));
		}
		g2.setColor(bodyColor);
		g2.fillOval(-15, 0, 30, 50);
		g2.setTransform(old);



		
		//shell
		g2.setColor(shellColor);
		g2.fillOval(0, 0, 100, 70);
		
		//shell circles
		g2.setColor(shellCircles);
		g2.fillOval(10, 20, 5, 25);
		g2.fillOval(30, 10, 8, 45);
		g2.fillOval(50,8, 10, 55);
		g2.fillOval(70,15, 8, 40);
		g2.fillOval(90,20, 5, 25);
		
		
		

	   
	    
	   
	}
	
	public void move() {
	    pos.add(vel);
	    updateFacingDirection();
	    edgeHandling();
	}
	
	public void update() {
		move();
		headBob += 0.1;
		
		legStepCounter++;
		if (legStepCounter % 10 == 0) { 
	        legStepForward = !legStepForward;// switch leg state every few frames
	    }
	}
	
	private void edgeHandling() {
	    float minX = 20 + 65;   // left margin + leftmost offset = 25
	    float maxX = 20 + ForestEnvironment.WIDTH - 145;  // right margin - rightmost extent = 655

	    float minY = 220;  // top of the grass area
	    float maxY = 20 + ForestEnvironment.HEIGHT - 100; // bottom edge - bottom part of turtle = 480

	    if (pos.x < minX) {
	        pos.x = minX;
	        vel.x *= -1;
	        updateFacingDirection();
	    } else if (pos.x > maxX) {
	        pos.x = maxX;
	        vel.x *= -1;
	        updateFacingDirection();
	    }

	    if (pos.y < minY) {
	        pos.y = minY;
	        vel.y *= -1;
	    } else if (pos.y > maxY) {
	        pos.y = maxY;
	        vel.y *= -1;
	    }
	}

	
	private void updateFacingDirection() {
	    if (vel.x < 0) {
	        facingLeft = true;
	    } else {
	        facingLeft = false;
	    }
	}
	
	public boolean isNearFood(Food f) {
	    return PVector.dist(this.pos, f.getPosition()) < 40; 
	}
	
	public void moveToward(PVector target) {
	    PVector desired = PVector.sub(target, this.pos);
	    desired.setMag(4);// WHEN SEES FOOD.. spped increases.. kind like running towards food 
	    this.vel = desired;
	    updateFacingDirection();
	    move(); // re-use existing movement logic
	    headBob += 0.1;// reusing head bob
	    legStepCounter++;
		if (legStepCounter % 10 == 0) { 
	        legStepForward = !legStepForward;// switch leg state every few frames
	    }
	}
	


	
	
	

}
