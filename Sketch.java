import java.util.Scanner;

import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
	public class SnakeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[10][10];
        int snakeLength = 1;
        int[] snakeX = new int[100];
        int[] snakeY = new int[100];
        int appleX, appleY;
        char direction = 'R'; // R: Right, L: Left, U: Up, D: Down

        initializeGrid(grid);
        initializeSnake(snakeX, snakeY, snakeLength);
        placeApple(grid, snakeX, snakeY, snakeLength);

        while (true) {
            displayGrid(grid);
            System.out.println("Enter a direction (R/L/U/D or Q to quit): ");
            char input = scanner.next().charAt(0);

            if (input == 'Q' || input == 'q') {
                System.out.println("Game Over. Thanks for playing!");
                break;
            }

            direction = updateDirection(direction, input);

            boolean isAppleEaten = moveSnake(grid, snakeX, snakeY, snakeLength, direction);
            if (isAppleEaten) {
                snakeLength++;
                placeApple(grid, snakeX, snakeY, snakeLength);
            }

            if (checkCollision(snakeX, snakeY, snakeLength) || snakeLength == 100) {
                System.out.println("Game Over. Snake crashed!");
                break;
            }
        }
    }

    private static void initializeGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    private static void initializeSnake(int[] snakeX, int[] snakeY, int length) {
        snakeX[0] = 0;
        snakeY[0] = 0;
    }

    private static void placeApple(char[][] grid, int[] snakeX, int[] snakeY, int snakeLength) {
        int x, y;

        do {
            x = (int) (Math.random() * grid.length);
            y = (int) (Math.random() * grid[0].length);
        } while (isSnakeBody(snakeX, snakeY, snakeLength, x, y));

        grid[x][y] = 'A';
    }

    private static void displayGrid(char[][] grid) {
        System.out.println("-------- Snake Eating Apple Game --------");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("| " + grid[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("----------------------------------------");
    }

    private static char updateDirection(char currentDirection, char input) {
        switch (input) {
            case 'R':
            case 'r':
                return 'R';
            case 'L':
            case 'l':
                return 'L';
            case 'U':
            case 'u':
                return 'U';
            case 'D':
            case 'd':
                return 'D';
            default:
                return currentDirection;
        }
    }

    private static boolean moveSnake(char[][] grid, int[] snakeX, int[] snakeY, int snakeLength, char direction) {
        int headX = snakeX[0];
        int headY = snakeY[0];
        int newHeadX = headX;
        int newHeadY = headY;

        switch (direction) {
            case 'R':
                newHeadY++;
                break;
            case 'L':
                newHeadY--;
                break;
            case 'U':
                newHeadX--;
                break;
            case 'D':
                newHeadX++;
                break;
        }

        if (newHeadX < 0 || newHeadX >= grid.length || newHeadY < 0 || newHeadY >= grid[0].length) {
            return false; // Snake hit the wall
        }

        if (grid[newHeadX][newHeadY] == 'A') {
            grid[newHeadX][newHeadY] = ' ';
            moveBody(snakeX, snakeY, snakeLength);
            return true; // Snake ate the apple
        }

        if (isSnakeBody(snakeX, snakeY, snakeLength, newHeadX, newHeadY)) {
            return false; // Snake collided with its own body
        }

        moveBody(snakeX, snakeY, snakeLength);
        snakeX[0] = newHeadX;
        snakeY[0] = newHeadY;

        return false;
    }

    private static void moveBody(int[] snakeX, int[] snakeY, int snakeLength) {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
    }

    private static boolean isSnakeBody(int[] snakeX, int[] snakeY, int snakeLength, int x, int y) {
        for (int i = 0; i < snakeLength; i++) {
            if (snakeX[i] == x && snakeY[i] == y) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkCollision(int[] snakeX, int[] snakeY, int snakeLength) {
        int headX = snakeX[0];
        int headY = snakeY[0];

        for (int i = 1; i < snakeLength; i++) {
            if (headX == snakeX[i] && headY == snakeY[i]) {
                return true; // Snake collided with its own body
            }
        }
        return false;
    }
}
  }
  
  // define other methods down here.
}