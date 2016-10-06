//RenderPanel.java by Christopher Dillman on 7/20/2014
//Draws graphics for Snake.java

package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
	
	public static Color background = new Color(1666073);
	public static Color purple = new Color(1515001515);
	public static Color orange = new Color(16160000);
	public static Color pink = new Color(16163300);
	public static Color snakeRed = new Color(200, 0, 0);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(background);
		g.fillRect(0, 0, 800, 700);
		Snake snake = Snake.snake;
		g.setColor(Color.BLUE);
		for(Point point : snake.snakeParts) {
			if(snake.rainbowCount % 8 == 0)
				g.setColor(Color.BLUE);
			else if (snake.rainbowCount % 8 == 1)
				g.setColor(purple);
			else if (snake.rainbowCount % 8 == 2)
				g.setColor(pink);
			else if (snake.rainbowCount % 8 == 3)
				g.setColor(snakeRed);
			else if (snake.rainbowCount % 8 == 4)
				g.setColor(orange);
			else if (snake.rainbowCount % 8 == 5)
				g.setColor(Color.YELLOW);
			else if (snake.rainbowCount % 8 == 6)
				g.setColor(Color.GREEN);
			else if (snake.rainbowCount % 8 == 7)
				g.setColor(Color.CYAN);
			
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, 
					Snake.SCALE, Snake.SCALE);
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, 
				Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		g.fillRect(snake.apple.x * Snake.SCALE, snake.apple.y * Snake.SCALE, 
				Snake.SCALE, Snake.SCALE);
		String str = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 3 / 20;
		g.setColor(Color.WHITE);
		g.drawString(str, (int) (getWidth() / 2 - str.length() * 1.5f), 10);
		
	}
}
