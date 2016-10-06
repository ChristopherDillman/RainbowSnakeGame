//Snake.java by Christopher Dillman on 7/20/2014

package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public JFrame frame;	
	
	public Toolkit toolkit;
	
	public RenderPanel renderPanel;
	
	public Timer timer = new Timer(20, this);
	
	public static Snake snake;
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	public int ticks = 0, direction = DOWN, score, tailLength = 10, time, rainbowCount = 0;
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, 
			SCALE = 10;
	
	public Point head, apple;
	
	public Random rand;
	
	public Dimension dim;
	
	public boolean over = false, paused;
	
	/**
	 * Constructor
	 */
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake by Christopher Dillman");
		frame.setVisible(true);
		frame.setSize(805,700);
		frame.setResizable(false);
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, 
				dim.height / 2 - frame.getHeight() / 2);
		frame.add(renderPanel = new RenderPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		startGame();
		
	}
	
	public void startGame() {
		over = false;
		paused = false;
		tailLength = 1;
		direction = DOWN;
		head = new Point(0, -1);
		rand = new Random();
		snakeParts.clear();
		apple = new Point(rand.nextInt(79), rand.nextInt(66));
		
		//adds point right at the point of the tail
		for(int i = 0; i < tailLength; i++) {
			snakeParts.add(new Point(head.x, head.y));
		}
		
		timer.start();
	}
	
	public static void main(String[] args) {
		snake = new Snake();
	}

	/**
	 * Repaints the panel after the timer reaches 3
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		time++;
		ticks++;
		
		if(ticks % 3 == 0 && head != null && !over && !paused) {
			//Places the head of the snake onto the screen after each tick
			snakeParts.add(new Point(head.x, head.y));
			
			if(direction == UP) {
				if (head.y - 1 >= 0 && tailNotAt(head.x, head.y - 1))
					head = (new Point(head.x, head.y - 1));
				else 
					over = true; //the snake hit the edge of the screen
			}
			
			if(direction == DOWN) {
				if (head.y + 1 < 67 && tailNotAt(head.x, head.y + 1))
					head = (new Point(head.x, head.y + 1));
				else
					over = true; //the snake hit the edge of the screen
			}
			
			if(direction == LEFT) {
				if (head.x - 1 >= 0 && tailNotAt(head.x - 1, head.y))
					head = (new Point(head.x - 1, head.y));
				else
					over = true; //the snake hit the edge of the screen 
			}
			
			if(direction == RIGHT) {
				if (head.x + 1 < 80 && tailNotAt(head.x + 1, head.y))
					head = (new Point(head.x + 1, head.y));
				else
					over = true; //the snake hit the edge of the screen
			}

			//Removes the end of the snake after each tick
			if (snakeParts.size() > tailLength)
				snakeParts.remove(0);
			
			//if the head collides with the apple
			if(apple != null) {
				if (head.equals(apple)) {
					score+=10;
					tailLength++;
					rainbowCount++;
					//places a new apple on the screen
					apple.setLocation(rand.nextInt(79), rand.nextInt(66));
				}
			}
		}
	}

	/**
	 * Checks if the tail collides with the head
	 * @param x -- x location of head
	 * @param y -- y location of head
	 * @return
	 */
	public boolean tailNotAt(int x, int y) {
		for(Point point : snakeParts) {
			if (point.equals(new Point(x, y)))
				return false;
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_A && direction != RIGHT)
			direction = LEFT;
		if (i == KeyEvent.VK_D && direction != LEFT)
			direction = RIGHT;
		if (i == KeyEvent.VK_W && direction != DOWN)
			direction = UP;
		if (i == KeyEvent.VK_S && direction != UP)
			direction = DOWN;
		if (i == KeyEvent.VK_SPACE)
			if (over)
				startGame();
		else
			paused = !paused;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
