package e102_Manmeet_Sagri_Assignment1_301545311;

import java.awt.*;
import java.awt.geom.AffineTransform;

import processing.core.PVector;


public class Food {
	
	private PVector pos;
	private Color capColor
	
	// Constructor
    public Food(float x, float y, Color capColor) {
        this.pos = new PVector(x, y);
        this.capColor = capColor;
    }
    
 // Draw method (with Graphics2D passed in)
    public void draw(Graphics2D g2) {
        AffineTransform old = g2.getTransform();
        
        g2.translate(pos.x, pos.y); // translate to position
        // Draw mushroom at position
        drawMushroom(g2);

        g2.setTransform(old); // restore
    }

    private void drawMushroom(Graphics2D g2) {


        // Cap as an arc (semi-circle)
        g2.setColor(capColor);
        g2.fillArc(0,0 , 40, 50, 0, 180); // x, y, width, height, startAngle, arcAngle

        // Simple dot on the cap
        g2.setColor(Color.WHITE);
        g2.fillOval(8, 5, 6, 6);
        g2.fillOval(20, 10, 6, 6);

        //stem
        g2.setColor(new Color(255, 230, 180)); 
        g2.fillRect(15, 25, 10, 22);
    }


    // Getter for position if needed later
    public PVector getPosition() {
        return pos;
    }
}
   
