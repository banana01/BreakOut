//Name -
//Date -
//Class -
//Lab  - 

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block
{
   //instance variables
   private int speed;
   private boolean isLeftSide = true;

   public Paddle()
   {
		super(10,10);
      speed =5;
      if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }


   //add the other Paddle constructors
   public Paddle(int x, int y)
   {
	   setX(x);setY(y);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }
   public Paddle(int x, int y, int sp)
   {
	   setX(x);setY(y);setSpeed(sp);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }
   public Paddle(int x, int y, int w, int h)
   {
	   setX(x);setY(y);setWidth(w);setHeight(h);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }
   public Paddle(int x, int y, int w, int h, int s)
   {
	   setX(x);setY(y);setWidth(w);setHeight(h);setSpeed(s);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }
   public Paddle(int x, int y, int w, int h, Color c)
   {
	   setX(x);setY(y);setWidth(w);setHeight(h);setColor(c);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }
   public Paddle(int x, int y, int w, int h, Color c, int s)
   {
	   setX(x);setY(y);setWidth(w);setHeight(h);setColor(c);setSpeed(s);
	   if(this.getX() > 500)
	   {
		   isLeftSide = false;
	   }
   }








   public void moveRightAndDraw(Graphics window)
   {
	   window.setColor(Color.white);
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	   if(isLeftSide && this.getX() < 490)
	   {
		   setX(getX()+(2*speed));
	   }
	   if(!isLeftSide && this.getX() < 970)
	   {
		   setX(getX()+(2*speed));
	   }
	   window.setColor(this.getColor());
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	  
   }
   public void moveLeftAndDraw(Graphics window)
   {
	   window.setColor(Color.white);
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	   window.setColor(Color.white);
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	   if(isLeftSide && this.getX() >= 0)
	   {
		   setX(getX()-(2*speed));
	   }
	   if(!isLeftSide && this.getX() > 505)
	   {
		   setX(getX()-(2*speed));
	   }
	   window.setColor(this.getColor());
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
   }
   public void moveUpAndDraw(Graphics window)
   {
	   window.setColor(Color.white);
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	   setY(getY()-(2*speed));
	   window.setColor(this.getColor());
	   window.fillRect(getX(), getY(), getWidth(), getHeight());

   }

   public void moveDownAndDraw(Graphics window)
   {
	   window.setColor(Color.white);
	   window.fillRect(getX(), getY(), getWidth(), getHeight());
	   setY(getY()+(2*speed));
	   window.setColor(this.getColor());
	   window.fillRect(getX(), getY(), getWidth(), getHeight());

   }
   public void setSpeed(int sp)
   {
	   speed = sp;
   }
   
public boolean isLeftSide()
{
	return isLeftSide;
}



public void setLeftSide(boolean isLeftSide)
{
	this.isLeftSide = isLeftSide;
}


//add get methods
   public int getSpeed()
   {
	   return speed;
   }
   public String toString()
   {
	   return "X:"+getX()+" Y:"+getY()+" W:"+getWidth()+" H:"+getHeight()+" C:"+getColor()+" Sp:"+getSpeed();
   }
   //add a toString() method
}