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

public class AirHockey extends Canvas implements KeyListener, Runnable
{
	private int leftScore, rightScore;
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private boolean[] keys;
	private BufferedImage back;
	private Dimension size;
	private Goal leftGoal, rightGoal;


	public AirHockey(int w, int h)
	{
		//set up all variables related to the game
		size = new Dimension(w-50, h-50);
		ball = new Ball((int)size.getWidth()/2,(int) size.getHeight()/2, 10,10,Color.red,1 , 1);
		
		rightPaddle = new Paddle((int)size.getWidth()-10, (int)size.getHeight()/2, 10, 50, Color.black, 2);
		leftPaddle = new Paddle((int)size.getWidth()-((int)size.getWidth()-10),(int)size.getHeight()/2, 10, 50 , Color.black, 2);
		
		leftGoal = new Goal((int)size.getWidth()-((int)size.getWidth()-4),(int)size.getHeight()/2-50, 2, 150, Color.red);
		rightGoal = new Goal((int)size.getWidth()+25,(int)size.getHeight()/2-50, 2, 150, Color.red);
		
		ball.setXSpeed(2);
		ball.setYSpeed(1);

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
	   
	   
	   window.fillRect((int)size.getWidth()/2, (int)size.getHeight()-40, window.getFontMetrics().stringWidth("LeftScore::"+getLeftScore()+"\n"+"RightScore::"+getRightScore()), 25);
	   window.setColor(Color.black);
	   window.fillRect(500, 0, 5, (int)size.getHeight());
	   window.setColor(c);
	   window.drawString("LeftScore::"+getLeftScore()+"\n"+"RightScore::"+getRightScore(), (int)size.getWidth()/2, (int)size.getHeight()-20);
	  

   }
   private void reset(Graphics graphToBack)
   {
	   ball.setXSpeed(0);
		ball.setYSpeed(0);
		Color c = graphToBack.getColor();
		graphToBack.setColor(Color.white);
		graphToBack.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		graphToBack.setColor(c);
		ball.setPos((int)size.getWidth()/2, (int) size.getHeight()/2);
		double p = (Math.random()-0.5)*4;
		if(p > 0)
			ball.setYSpeed((int)Math.ceil(p));
		else
			ball.setYSpeed((int)Math.floor(p));
		ball.setXSpeed((int)Math.ceil(Math.random()*4));
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

		ball.moveAndDraw(graphToBack);
		leftPaddle.draw(graphToBack);
		rightPaddle.draw(graphToBack);
		leftGoal.draw(graphToBack);
		rightGoal.draw(graphToBack);
		drawScore(graphToBack);
		if(leftGoal.Collision(ball))
		{
			
			setRightScore(getRightScore()+1);
			reset(graphToBack);
			
			
		}
		if(rightGoal.Collision(ball))
		{
			setLeftScore(getLeftScore()+1);
			reset(graphToBack);
		}
		if(!(ball.getX()<=(int)size.getWidth()-15 && ball.getX()>=(int)size.getWidth()-((int)size.getWidth()-5)))
		{
		
				//ball.setYSpeed(-ball.getYSpeed());
				ball.setXSpeed(-ball.getXSpeed());
		}
		
		/*	if(ball.getX()<=(int)size.getWidth()-11)
			{
				setRightScore(getRightScore()+1);
			}
			else
			{
				setLeftScore(getLeftScore()+1);
			}
			ball.setXSpeed(0);
			ball.setYSpeed(0);
			Color c = graphToBack.getColor();
			graphToBack.setColor(Color.white);
			graphToBack.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
			graphToBack.setColor(c);
			ball.setPos((int)size.getWidth()/2, (int) size.getHeight()/2);
			
			ball.setXSpeed((int)Math.ceil(Math.random()*2));
			double p = Math.random()-0.5;
			if(p > 0)
				ball.setYSpeed((int)Math.ceil(p));
			else
				ball.setYSpeed((int)Math.floor(p));
			
		}*/

		
		//see if the ball hits the top or bottom wall 
		/*if(!(ball.getX()>=10 && ball.getX()<=550))
		{
			ball.setXSpeed(-ball.getXSpeed());
		}
		*/

		if(!(ball.getY() >= size.getHeight()-(size.getHeight()-15) && ball.getY() <= size.getHeight()-15))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}




		if
		(
				(ball.getX() >= leftPaddle.getX()+leftPaddle.getWidth()-10 && ball.getX() <= leftPaddle.getX()+leftPaddle.getWidth()) &&
				(ball.getY() >= leftPaddle.getY() && ball.getY() <= leftPaddle.getY()+leftPaddle.getHeight())
		)
		{
			if(ball.getX() <= leftPaddle.getX()+leftPaddle.getWidth()-Math.abs(ball.getXSpeed()))
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
			else 
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
			
		}

		if
		(
				((ball.getX() >= rightPaddle.getX()-2 && ball.getX() <= rightPaddle.getX()+5)) &&
				(ball.getY() >= rightPaddle.getY() && ball.getY() <= rightPaddle.getY()+rightPaddle.getHeight())
		)
		{
			if(ball.getX() >= rightPaddle.getX()+Math.abs(ball.getXSpeed()))
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
			else 
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
			
		}
		/*if
		(
				ball.getX() == leftPaddle.getX()+leftPaddle.getWidth()+Math.abs(ball.getXSpeed()) && 
				(
						ball.getY() >= leftPaddle.getY() && 
						ball.getY() <= leftPaddle.getY()+leftPaddle.getHeight() || 
						ball.getY() + ball.getHeight() >= leftPaddle.getY() && 
						ball.getY() + ball.getHeight() < leftPaddle.getY() + leftPaddle.getHeight()
				)
		)
		{
			if(ball.getX() <= leftPaddle.getX()+leftPaddle.getWidth()-Math.abs(ball.getXSpeed()))
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
			else 
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
		}
		if
		(
				ball.getX() == rightPaddle.getX()-rightPaddle.getWidth()-Math.abs(ball.getXSpeed()) && 
				(
						ball.getY() >= rightPaddle.getY() && 
						ball.getY() <= rightPaddle.getY()+rightPaddle.getHeight() || 
						ball.getY() + ball.getHeight() >= rightPaddle.getY() && 
						ball.getY() + ball.getHeight() < rightPaddle.getY() + rightPaddle.getHeight()
				)
		)
		{
			if(ball.getX() <= rightPaddle.getX()-rightPaddle.getWidth()+Math.abs(ball.getXSpeed()))
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
			else 
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
		}*/
		
		
		


		//see if the paddles need to be moved
		if(keys[0] == true)
		{
			//move left paddle up and draw it on the window
			leftPaddle.moveUpAndDraw(graphToBack);
		}
		if(keys[1] == true)
		{
			//move left paddle down and draw it on the window
			leftPaddle.moveLeftAndDraw(graphToBack);

		}
		if(keys[2] == true)
		{
			leftPaddle.moveDownAndDraw(graphToBack);
		}
		if(keys[3] == true)
		{
			leftPaddle.moveRightAndDraw(graphToBack);
		}
		
		
		
		
		if(keys[4] == true)
		{
			//move left paddle up and draw it on the window
			rightPaddle.moveUpAndDraw(graphToBack);
		}
		if(keys[5] == true)
		{
			//move left paddle down and draw it on the window
			rightPaddle.moveLeftAndDraw(graphToBack);

		}
		if(keys[6] == true)
		{
			rightPaddle.moveDownAndDraw(graphToBack);
		}
		if(keys[7] == true)
		{
			rightPaddle.moveRightAndDraw(graphToBack);
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
	public int getLeftScore()
	{
		return leftScore;
	}
	
   
	public int getRightScore()
	{
		return rightScore;
	}

	
	public void setRightScore(int rightScore)
	{
		this.rightScore = rightScore;
	}

	
	public void setLeftScore(int leftScore)
	{
		this.leftScore = leftScore;
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