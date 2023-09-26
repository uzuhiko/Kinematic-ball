package init;

import static tools.NirajTools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallGravity extends JPanel implements ActionListener {
	private double ballX = 190;         // Initial x-coordinate of the ball
    private double ballY = 100;         // Initial y-coordinate of the ball
    private double ballRadius = 15.0;  // Radius of the ball
    private double initialY = ballY;    // Store the initial y-coordinate
    private double xVelocity = 0.0;     // Horizontal velocity of the ball
    private double yVelocity = 0.0;    // Vertical velocity of the ball
    private double gravity = 0.5;      // Acceleration due to gravity
    private double energyLoss = 0.75;  // Energy loss (damping factor)
    
    public BouncingBallGravity() {
        // Create a Timer that triggers the actionPerformed method every 10 milliseconds
        Timer timer = new Timer(10, this);
        timer.start(); // Start the timer
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to clear the panel
        g.setColor(Color.RED); // Set the drawing color to red
        int ballDiameter = (int) (2 * ballRadius); // Calculate the diameter of the ball
        // Draw a filled red circle (the ball) centered at (ballX, ballY)
        g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius), ballDiameter, ballDiameter);
    }

    public void actionPerformed(ActionEvent e) {
        // Update the position of the ball based on its velocity and gravity
        yVelocity += gravity; // Apply gravity to the vertical velocity
        ballX += xVelocity;   // Update the horizontal position of the ball
        ballY += yVelocity;   // Update the vertical position of the ball

        // Check for collisions with the boundaries of the panel
        if (ballX + ballRadius > getWidth()) {
            ballX = getWidth() - ballRadius;       // Adjust the horizontal position
            xVelocity = -xVelocity * energyLoss;   // Reverse the horizontal velocity with energy loss
        } else if (ballX - ballRadius < 0) {
            ballX = ballRadius;                    // Adjust the horizontal position
            xVelocity = -xVelocity * energyLoss;   // Reverse the horizontal velocity with energy loss
        }

        if (ballY + ballRadius > getHeight()) {
            ballY = getHeight() - ballRadius;      // Adjust the vertical position
            yVelocity = -yVelocity * energyLoss;   // Reverse the vertical velocity with energy loss
        } else if (ballY < initialY) {
            ballY = initialY;                      // Adjust the vertical position
            yVelocity = -yVelocity * energyLoss;   // Reverse the vertical velocity with energy loss
        }

        repaint(); // Request a repaint to update the panel
    }

    public static void main(String[] args) {
        // Create a JFrame (window) for the bouncing ball animation
        JFrame frame = new JFrame("Bouncing ball with gravity");
        BouncingBallGravity bouncingBall = new BouncingBallGravity(); // Create an instance of the BouncingBall class
        frame.add(bouncingBall); // Add the bouncingBall JPanel to the frame
        frame.setSize(400, 400); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the frame is closed
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible
    }
}
