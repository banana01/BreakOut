//Name -
//Date -
//Class -
//Lab  - 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowStateListener;

public class Ball extends Block
{
	private int xSpeed;
	private int ySpeed;

	public Ball()
	{
		super(200,200);
		xSpeed = 3;
		ySpeed = 1;
	}

	//add the other Ball constructors
	public Ball(int x, int y)
	{
		setX(x);setY(y);
	}
	public Ball(int x, int y, int w, int h)
	{
		setX(x);setY(y);setWidth(w);setHeight(h);
	}
	public Ball(int x, int y, int w, int h, Color c)
	{
		setX(x);setY(y);setWidth(w);setHeight(h);setColor(c);
	}
	public Ball(int x, int y, int w, int h, Color c, int xS, int yS)
	{
		setX(x);setY(y);setWidth(w);setHeight(h);setColor(c);
	}
	
	
	
	
	
	
	
	
	
	
	
	   
   //add the set methods
   public void setXSpeed(int xS)
   {
	   xSpeed = xS;
   }
   public void setYSpeed(int yS)
   {
	   ySpeed = yS;
   }
   public int getXSpeed()
   {
	   return xSpeed;
   }
   public int getYSpeed()
   {
	   return ySpeed;
   }
   
   public void moveAndDraw(Graphics window)
   {
   	//draw a white ball at old ball location
	  window.setColor(Color.white);
	  window.fillRect(getX(), getY(), getWidth(), getHeight());
	  window.setColor(this.getColor());
      setX(getX()+xSpeed);
      setY(getY()+ySpeed);
      window.fillRect(getX(), getY(), getWidth(), getHeight());

		//draw the ball at its new location
   }
   
	public boolean equals(Object obj)
	{
		Ball ball = (Ball) obj;
		if(ball.getWidth() == this.getWidth() && ball.getHeight() == this.getHeight() && ball.getColor().equals(this.getColor()) && ball.getXSpeed() == this.getXSpeed() && ball.getYSpeed() == this.getYSpeed())
		{
			return true;
		}
		return false;
	}   

   //add the get methods
	public String toString()
	{
		return "X:"+getX()+" Y:"+getY()+" W:"+getWidth()+" H:"+getHeight()+" C:"+getColor();
	}
   //add a toString() method
}