import java.util.ArrayList;

import processing.core.PApplet;

public class Sketch extends PApplet {

  boolean isGameLoading = true;

  // Flappy Bird variables
  float birdY, birdVelocity;
  float gravity = 0.6f;
  int score = 0;
  ArrayList<Pipe> pipes;

/**
 * The Pipe class represents the obstaces in the game. 
 * Each pipe has a top and bottom that moves across the screen
 */
  public class Pipe {
    float top, bottom;
    float x = width;
    float pipeWidth = 30; // Width of the pipe
    float gap = 150; // Gap between the top and bottom pipes
    boolean scored = false;


    /**
     * Initializes pipes with random spacing
     */
    Pipe() {
      float space = random(100, height - 100 - gap); // Adjust the range for random space start
      top = space;
      bottom = height - (space + gap);
      x = width;
    }

    /**
     * Draws pipes on the screen
     */
    public void show() {
      fill(0, 255, 0);  // Green color for pipes
      rect(x, 0, pipeWidth, top);
      rect(x, height - bottom, pipeWidth, bottom);
    }

    /**
     * Moves pipes to the left.
     */
    public void update() {
      x -= 2;
      if (!scored && x < 50) {
        score++;
        scored = true;
      }
    }
    /**
     * Checks if the pipe is off the screen
     * @return boolean Returns true if the pipe is off the screen
     */
    boolean offScreen() {
      return x < -pipeWidth;
    }

    /**
     * Checks if the bird hits this pipe
     * @param birdY position of bird
     * @return boolean Returns true if the bird hits the pipe
     */
    boolean hits(float birdY) {
      if (birdY < top || birdY > height - bottom) {
        if (x < 50 && x + pipeWidth > 50) {
          return true;
        }
      }
      return false;
    }
  }

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    birdY = height / 2;
    pipes = new ArrayList<Pipe>();
    pipes.add(new Pipe());
  }

  public void draw() {
    if (isGameLoading) {
      // Display loading screen
      background(51, 153, 255);
      fill(255);
      textAlign(CENTER, CENTER);
      text("Press any key to begin the game", width / 2, height / 2);
    } else {
      // Flappy Bird game logic
      background(51, 153, 255);

      // Bird
      fill(255, 200, 0);
      ellipse(50, birdY, 20, 20);
      birdVelocity += gravity;
      birdY += birdVelocity;

      // Pipes
      for (int i = pipes.size() - 1; i >= 0; i--) {
        Pipe pipe = pipes.get(i);
        pipe.show();
        pipe.update();

        if (pipe.hits(birdY)) {
          // Reset game if bird hits a pipe
          resetGame();
        }

        if (pipe.offScreen()) {
          pipes.remove(i);
          pipes.add(new Pipe());
        }
      }

      // Score
      fill(255);
      textSize(16);
      text("Score: " + score, width - 100, 30);
    }
  }

  /**
   * Resets game to its initial state
   * This happens when the bird hits a pipe
   */
  public void resetGame() {
    birdY = height / 2;
    birdVelocity = 0;
    score = 0;
    pipes.clear();
    pipes.add(new Pipe());
  }

  /**
   * Handles key press events
   * Used to start the game and make the bird flap
   */
  // Detect key presses to start game or flap bird
  public void keyPressed() {
    if (isGameLoading) {
      isGameLoading = false;
      background(51, 153, 255);
    } else {
      birdVelocity = -10;  // Flap the bird
    }
  }
}
