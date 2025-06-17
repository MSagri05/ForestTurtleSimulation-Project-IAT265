package e102_Manmeet_Sagri_Assignment1_301545311;

import javax.swing.JFrame;

/* FOR BONUS: ADDED ANIMATIONS HEAD BOB ANIMATION FOR TURTLE FACE..
 * LEGS MOVEMENT FOR TURTLE
 */

public class ForestApp extends JFrame {

	
	
    public ForestApp(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        // Instantiate the panel
        ForestPanel panel = new ForestPanel();

        // Add it to the frame
        this.add(panel);
        this.pack(); // adjust frame size to preferred panel size

        this.setLocationRelativeTo(null); // center on screen
        this.setVisible(true); // show the window
    }

    public static void main(String[] args) {
        new ForestApp("Turtle Forest");
    }
}