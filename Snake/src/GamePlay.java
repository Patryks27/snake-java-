import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	private static final long serialVersionUID = 1L;

	private ImageIcon titleImage;
	
	private int[] snakeXLenght = new int [750];
	private int[] snakeYLenght = new int [750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightside;
	private ImageIcon leftside;
	private ImageIcon upside;
	private ImageIcon downside;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snake;
	private boolean gameOver = false;
	
	private int lenghtSnake = 3;
	
	private int moves =0;
	private int score = 0;
	
	private int [] enemyXPosition = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
									450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyYPosition = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
									450,475,500,525,550,575,600,625};
	private ImageIcon enemy;
	private Random random = new Random();
	private int xpos= random.nextInt(34);
	private int ypos= random.nextInt(23);
	
	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		if(moves==0)
		{
			snakeXLenght[2]=50;
			snakeXLenght[1]=75;
			snakeXLenght[0]=100;
			
			snakeYLenght[2]=100;
			snakeYLenght[1]=100;
			snakeYLenght[0]=100;
		}
		
		//draw title space
		g.setColor(Color.green);
		g.drawRect(24, 10, 851, 55);
		
		//draw title image
		titleImage = new ImageIcon("snake.png");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw game space
		g.setColor(Color.green);
		g.drawRect(24, 75, 851, 577);
		
		//background game space
		g.setColor(Color.gray);
		g.fillRect(25, 76, 850, 576);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,23));
		g.drawString("Scores: "+score, 760, 30);
		
		
		rightside = new ImageIcon("snakeimage.png");
		rightside.paintIcon( this, g, snakeXLenght[0], snakeYLenght[0]);
		
		for(int i=0;i<lenghtSnake;i++)
		{
			if(i==0 && left)
			{
				leftside = new ImageIcon("snakeleft.png");
				leftside.paintIcon( this, g, snakeXLenght[i], snakeYLenght[i]);
			}
			if(i==0 && right)
			{
				rightside = new ImageIcon("snakeright.png");
				rightside.paintIcon( this, g, snakeXLenght[i], snakeYLenght[i]);
			}
			if(i==0 && down)
			{
				downside = new ImageIcon("snakedown.png");
				downside.paintIcon( this, g, snakeXLenght[i], snakeYLenght[i]);
			}
			if(i==0 && up)
			{
				upside = new ImageIcon("snakeup.png");
				upside.paintIcon( this, g, snakeXLenght[i], snakeYLenght[i]);
			}
			
			if(i!=0)
			{
				snake = new ImageIcon("snakeimage.png");
				snake.paintIcon( this, g, snakeXLenght[i], snakeYLenght[i]);
			}
		}
		
		enemy= new ImageIcon("enemy.png");
		if(enemyYPosition[ypos]==snakeYLenght[0] && enemyXPosition[xpos]==snakeXLenght[0] )
		{
			lenghtSnake++;
			score++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		enemy.paintIcon(this, g, enemyXPosition[xpos],enemyYPosition[ypos]);
		
		for(int i=1;i<lenghtSnake;i++)
		{
			if(snakeYLenght[0]==snakeYLenght[i] && snakeXLenght[0] == snakeXLenght[i] ) {
				right=false;
				left=false;
				up=false;
				down=false;
				
				gameOver= true;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over ", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,25));
				g.drawString("Click Space to RESTART", 290, 340);
				
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int i=lenghtSnake-1;i>=0;i--)
			{
				snakeYLenght[i+1]=snakeYLenght[i];
			}
			for(int i=lenghtSnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeXLenght[0]=snakeXLenght[i]+25;
				}
				else {
					snakeXLenght[i]=snakeXLenght[i-1];
				}

				if(snakeXLenght[i]>850)
				{
					snakeXLenght[i]=25;
				}
			}
			repaint();
		}
		if(left) {
			for(int i=lenghtSnake-1;i>=0;i--)
			{
				snakeYLenght[i+1]=snakeYLenght[i];
			}
			for(int i=lenghtSnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeXLenght[0]=snakeXLenght[i]-25;
				}
				else {
					snakeXLenght[i]=snakeXLenght[i-1];
				}

				if(snakeXLenght[i]<25)
				{
					snakeXLenght[i]=850;
				}
			}
			repaint();
		}
		if(up) {
			for(int i=lenghtSnake-1;i>=0;i--)
			{
				snakeXLenght[i+1]=snakeXLenght[i];
			}
			for(int i=lenghtSnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeYLenght[0]=snakeYLenght[i]-25;
				}
				else {
					snakeYLenght[i]=snakeYLenght[i-1];
				}

				if(snakeYLenght[i]<75)
				{
					snakeYLenght[i]=625;
				}
			}
			repaint();
		}
		if(down) {
			for(int i=lenghtSnake-1;i>=0;i--)
			{
				snakeXLenght[i+1]=snakeXLenght[i];
			}
			for(int i=lenghtSnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeYLenght[0]=snakeYLenght[i]+25;
				}
				else {
					snakeYLenght[i]=snakeYLenght[i-1];
				}

				if(snakeYLenght[i]>625)
				{
					snakeYLenght[i]=75;
				}
			}
			repaint();
			
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			gameOver = false;
			lenghtSnake =3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !gameOver)
		{
			moves++;
			right=true;
			if(!left)
			{
				
			}
			else {
				right=false;
			}
			up = false;
			down = false;
			//snakeXLenght[0]=150;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && !gameOver)
		{
			moves++;
			left=true;
			if(!right)
			{
				right = false;
			}
			else {
				left=false;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && !gameOver)
		{
			moves++;
			up=true;
			if(!down)
			{
				down = false;
			}
			else {
				up=false;
			}
			right = false;
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !gameOver)
		{
			moves++;
			down=true;
			if(!up)
			{
				up = false;
			}
			else {
				down=false;
			}
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
