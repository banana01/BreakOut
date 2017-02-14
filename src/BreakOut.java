//Name -
//Date -
//Class -
//Lab  - 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class BreakOut extends Canvas implements KeyListener, Runnable
{
	private int leftScore, rightScore;
	private Ball ball;
	private Paddle paddle;
	private boolean[] keys;
	private BufferedImage back;
	private Dimension size;
	private Goal leftGoal, rightGoal;
	private BreakOutBlock[] BOBS;


	public BreakOut(int w, int h)
	{
		//set up all variables related to the game
		size = new Dimension(w-50, h-50);
		ball = new Ball((int)size.getWidth()/2,(int) size.getHeight()/2, 5,5,Color.red,1 , 1);
		int blocks = 0;
		
		paddle = new Paddle((int)size.getWidth()/2,(int) size.getHeight()-15, 50, 10, Color.black, 2);
		for(int i = 60; i < (int)size.getWidth()-60; i += 30)
		{
			for(int j = 50; j < 200; j += 25)
			{
				blocks++;
			}
		}
		BOBS = new BreakOutBlock[blocks];
		for(int i = 60; i < (int)size.getWidth()-60; i += 30)
		{
			for(int j = 50; j < 200; j += 25)
			{
				BOBS[(((i-60)/30)*6)+((j-50)/25)] = new BreakOutBlock(i,j, 29, 24 , new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
			}
			
		}
	
		
		ball.setXSpeed(1);
		ball.setYSpeed(3);

		keys = new boolean[8];

    
    	setBackground(Color.WHITE);
		setVisible(true);
		
		new Thread(this).start();
		
		
		addKeyListener(this);		//starts the key thread to log key strokes
	}
	
   public void update(Graphics window){
	   paint(window);
   }
   private void drawScore(Graphics window)
   {
	   Color c = window.getColor();
	   window.setColor(Color.white);
	   
	   
	   window.fillRect((int)size.getWidth()/2, (int)size.getHeight()-60, window.getFontMetrics().stringWidth("Score::"+getScore()), 25);
	   window.setColor(c);
	   window.drawString("Score::"+getScore(), (int)size.getWidth()/2, (int)size.getHeight()-50);
	  

   }
   private void reset(Graphics graphToBack)
   {
	   ball.setXSpeed(0);
		ball.setYSpeed(0);
		Color c = graphToBack.getColor();
		graphToBack.setColor(Color.white);
		graphToBack.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		graphToBack.setColor(c);
		ball.setPos((int)size.getWidth()/2, (int) size.getHeight()/2+50);
		setScore(getScore()-5);
		double p = (Math.random()-0.5)*4;
		if(p > 0)
			ball.setXSpeed((int)Math.ceil(p));
		else
			ball.setXSpeed((int)Math.floor(p));
		ball.setYSpeed((int)Math.ceil(Math.random()*4));
   }
   
   public void paint(Graphics window)
   {
	   	size = this.getSize();
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		for(int i = 0; i < BOBS.length; i++)
		{
			if(BOBS[i] != null)
			{
				BOBS[i].draw(graphToBack);
			}
			
		}
		ball.moveAndDraw(graphToBack);
		paddle.draw(graphToBack);
		drawScore(graphToBack);
	
		
		for(int i = 0; i < BOBS.length; i++)
		{
			if(BOBS[i] != null)
			{
				if
				(
						(ball.getX() >= BOBS[i].getX() && ball.getX() <= BOBS[i].getX()+BOBS[i].getWidth()) &&
						(ball.getY() >= BOBS[i].getY() && ball.getY() <= BOBS[i].getY()+BOBS[i].getHeight())/* ||
						
						(ball.getX()+ball.getWidth() >= BOBS[i].getX() && ball.getX()+ball.getWidth() <= BOBS[i].getX()+BOBS[i].getWidth()) &&
						(ball.getY() >= BOBS[i].getY() && ball.getY() <= BOBS[i].getY()+BOBS[i].getHeight()) ||
						
						(ball.getX() >= BOBS[i].getX() && ball.getX() <= BOBS[i].getX()+BOBS[i].getWidth()) &&
						(ball.getY()+ball.getHeight() >= BOBS[i].getY() && ball.getY()+ball.getHeight() <= BOBS[i].getY()+BOBS[i].getHeight()) ||
						
						(ball.getX()+ball.getWidth() >= BOBS[i].getX() && ball.getX()+ball.getWidth() <= BOBS[i].getX()+BOBS[i].getWidth()) &&
						(ball.getY()+ball.getHeight() >= BOBS[i].getY() && ball.getY()+ball.getHeight() <= BOBS[i].getY()+BOBS[i].getHeight())*/
				)
				{
					BOBS[i].Collide(graphToBack, ball);
					BOBS[i] = null;
					if(Math.random() > 0.1)
						ball.setYSpeed(-ball.getYSpeed());	
					else 
						ball.setXSpeed(-ball.getXSpeed());	
					
					setScore(getScore()+1);
					
				}
			}
			
		}
		if(ball.getX() <= 5 || (ball.getX() >= (int)size.getWidth() -5))
		{
			ball.setXSpeed(-ball.getXSpeed());
		}
		if(ball.getY() <= 5)
		{
			ball.setYSpeed(-ball.getYSpeed());
		}
		if(ball.getY() >= (int)size.getHeight())
		{
			reset(graphToBack);
		}

		if
		(
				(ball.getX() >= paddle.getX() && ball.getX() <= paddle.getX()+paddle.getWidth()) &&
				(ball.getY() >= paddle.getY() && ball.getY() <= paddle.getY()+paddle.getHeight())
		)
		{
			if(ball.getY() <= paddle.getY()+paddle.getHeight()-Math.abs(ball.getYSpeed()))
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
			else 
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
			
		}

		if(keys[1] == true)
		{
			//move left paddle down and draw it on the window
			paddle.moveLeftAndDraw(graphToBack);

		}
		if(keys[3] == true)
		{
			paddle.moveRightAndDraw(graphToBack);
		}
		
		











		
		twoDGraph.drawImage(back, null, 0, 0);
	}

	public void keyPressed(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=true; break;
			case 'A' : keys[1]=true; break;
			case 'S' : keys[2]=true; break;
			case 'D' : keys[3]=true; break;
			
			case 'I' : keys[4]=true; break;
			case 'J' : keys[5]=true; break;
			case 'K' : keys[6]=true; break;
			case 'L' : keys[7]=true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
		case 'W' : keys[0]=false; break;
		case 'A' : keys[1]=false; break;
		case 'S' : keys[2]=false; break;
		case 'D' : keys[3]=false; break;
		
		case 'I' : keys[4]=false; break;
		case 'J' : keys[5]=false; break;
		case 'K' : keys[6]=false; break;
		case 'L' : keys[7]=false; break;
		}
	}

	public void keyTyped(KeyEvent e){}

	public int getScore()
	{
		return rightScore;
	}

	
	public void setScore(int rightScore)
	{
		this.rightScore = rightScore;
	}


public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}	
}