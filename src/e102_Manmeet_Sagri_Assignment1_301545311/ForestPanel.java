/* FOR BONUS: ADDED ANIMATIONS HEAD BOB ANIMATION FOR TURTLE FACE..
 * LEGS MOVEMENT FOR TURTLE
 * WHEN TURTLE EATS THE MUSROOM, THERE IS CRUMBS EFFECT
 */


package e102_Manmeet_Sagri_Assignment1_301545311;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;

import processing.core.PVector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ForestPanel extends JPanel implements ActionListener{
	
	
	//turtle, food, environment
	 private final ForestEnvironment environment;
	 private Turtle turtle;
	 private Food mushroom;
	 
	 // for animation
	 private Timer timer;
	 
	 // for mushroom spawing
	 private boolean foodVisible = true;
	 private long foodEatenTime = 0;
	 private final int RESPAWN_DELAY = 5000; // 5 seconds in milliseconds
	 
	 // crumb effect after food is eaten
	 private boolean showCrumbs = false;
	 private int crumbFrames = 0;
	 private PVector crumbPos; // where the food was eaten

	
	public ForestPanel() {
		this.setPreferredSize(new Dimension(800, 600)); // Set panel's size
		
		
		environment = new ForestEnvironment(); // create environment
		turtle = new Turtle(ForestEnvironment.WIDTH/2, ForestEnvironment.HEIGHT/2); // start at the center of environment
		mushroom = new Food(300, 300, new Color(200, 0, 0)); // red cap
		
		//timer to call actionPerformed every 30ms
		timer = new Timer(30, this); // ~33 frames per second
	    timer.start();
		
		
		
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(255,255,255)); //display window background is white
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		
		
		environment.draw(g2); // draw the background environment + decorations
		
		// draw food only if it's visible
		if (foodVisible) {
		    mushroom.draw(g2);
		}
		
		// draw creature turtle
		turtle.draw(g2);
		
		
		// red rectangle to show the boundary of the environment
		g2.setColor(Color.RED);
		g2.drawRect(20, 20, ForestEnvironment.WIDTH, ForestEnvironment.HEIGHT); // edge box BORDER
		
		
		// draw crumb splash animation (after eating)
		if (showCrumbs && crumbFrames > 0) {
		    drawCrumbs(g2, crumbPos.x, crumbPos.y);
		    crumbFrames--;
		    if (crumbFrames <= 0) showCrumbs = false;
		}
	

	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		// if food is visible, check if turtle has reached it
		if (foodVisible) {
	        if (turtle.isNearFood(mushroom)) {
	            foodVisible = false;
	            
	         // save where the mushroom was eaten
	            crumbPos = mushroom.getPosition().copy(); // remember where it was
	            showCrumbs = true;
	            crumbFrames = 10; // show for 10 frames (~1/3 second)
	            
	            foodEatenTime = System.currentTimeMillis();
	            
	            
	        } else {
	        	// move turtle toward the mushroom
	            turtle.moveToward(mushroom.getPosition());
	        }
	    } else {
	        // Wait 5 seconds then respawn
	        if (System.currentTimeMillis() - foodEatenTime > RESPAWN_DELAY) {
	            float newX = 40 + (float)Math.random() * (ForestEnvironment.WIDTH -200);// keep the mushroom within the environmnet width and height
	            
	            // Y range constrained to 220  to 580 .. grass ground area only.. not in they sky
	            float newY = 220 + (float)Math.random() * (ForestEnvironment.HEIGHT -300); 
	            
	            mushroom = new Food(newX, newY, new Color(200, 0, 0));
	            foodVisible = true;
	        } else {
	            turtle.update(); // wander .. looking hunting for food
	        }
	    }

	    
	    repaint();
	}
	
	
	// small crumbs animation after eating
	private void drawCrumbs(Graphics2D g2, float x, float y) {
	    g2.setColor(new Color(255, 200, 100)); // crumb color
	    g2.fillOval((int)x + 5, (int)y + 20, 5, 5);
	    g2.fillOval((int)x + 15, (int)y + 25, 4, 4);
	    g2.fillOval((int)x + 10, (int)y + 30, 3, 3);
	}

	
	
	

}
