package e102_Manmeet_Sagri_Assignment1_301545311;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class ForestEnvironment {
	
	
	// environment window smaller than panel by 20pixels
	public static final int WIDTH = 760;  // 800 - 2*20
    public static final int HEIGHT = 560; // 600 - 2*20
    
    //offsets to center the environment inside the panel
    private final int xOffset = 20;
    private final int yOffset = 20;

    public ForestEnvironment() {
    	
    	
    }
    
    // drAw the environment
    public void draw(Graphics2D g2) {
    	
    	
 
        // Draw background area
        g2.setColor(new Color(144, 238, 144)); // light green
        g2.fillRect(xOffset, yOffset, WIDTH, HEIGHT);
        g2.setColor(new Color(165, 223, 242));//skyblue color for sky
        g2.fillRect(xOffset, yOffset, WIDTH, 200);
        
        // calling all the methods here
        
        // trees
        drawTree(g2, 65, 200);
        drawTree(g2, 165, 250);
        drawTree(g2, 265, 200);
        
        drawTree(g2, 365, 250);
        
        drawTree(g2, 465, 200);
        drawTree(g2, 565, 250);
        drawTree(g2, 665, 200);
        
        // sun
        drawSun(g2,540,40);
        
        // clouds
        drawCloud(g2,60,40);
        drawCloud(g2,400,60);
        drawCloud(g2,600,30);

        
       
        
        
    }
    
    // method to draw trees
    private void drawTree(Graphics2D g2, int x, int y) {
        g2.setColor(new Color(101, 67, 33)); // trunk
        g2.fillRect(x + 20, y, 20, 70);
        g2.setColor(new Color(34, 139, 34)); // leaves
        g2.fillOval(x, y - 40, 60, 60);
        g2.fillOval(x-30,y-50,50,50);
        g2.fillOval(x+40,y-50,50,50);
        g2.fillOval(x+5, y-80, 50, 50);
    }
    
    // method to draw the sun
    private void drawSun(Graphics2D g2, int x, int y) {
    	g2.setColor(new Color(240, 213, 62));
    	g2.fillOval(x, y, 100, 100);
    	
    }
    
    // method to draw the clouds
    private void drawCloud(Graphics2D g2, int x, int y) {
    	g2.setColor(new Color(252, 252, 240));
    	g2.fillOval(x, y, 100, 40);
    	g2.fillOval(x+25, y-12, 100, 40);
    	g2.fillOval(x+55, y+12, 60, 40);
    	g2.fillOval(x+20, y+12, 60, 40);
    	g2.fillOval(x+50, y, 100, 40);
    	
    	
    	
    	
    }
    
    

    


}