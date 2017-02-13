//Name -
//Date -
//Class -
//Lab  - 

import java.awt.Color;
import java.awt.Graphics;

public class Block implements Locatable
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	private Color color;

	public Block()
	{


	}

	//add other Block constructors - x , y , width, height, color
	
	public Block(int x, int y, int w, int h, Color c)
	{
		setX(x); setHeight(h);setY(y); setWidth(w); setColor(c);
	}
	public Block(int x, int y, int w, int h)
	{
		setX(x); setHeight(h);setY(y); setWidth(w);
	}
	public Block(int w, int h)
	{
		setWidth(w);setHeight(h);
	}
	
	
	
	
	
	
	
	
	
	
	
   //add the other set methods
	public void setX(int x)
   	{
	   xPos = x;
   	}
   	public void setY(int y)
   	{
	   yPos = y;
   	}
   	public void setPos(int x, int y)
   	{
	   xPos = x; yPos = y;
   	}
   	public void setWidth(int w)
   	{
	   width = w;
   	}
   	public void setHeight(int h)
   	{
	   height = h;
   	}
   
   	public int getWidth()
   	{
	return width;
   	}
   	public int getHeight()
   	{
	   return height;
   	}
   	@Override
  	public int getX()
  	{
		// TODO Auto-generated method stub
		return xPos;
   	}

	@Override
	public int getY()
	{
		// TODO Auto-generated method stub
		return yPos;
	}   
	public Color getColor()
	{
		return color;
	}
   public void setColor(Color col)
   {

	   color = col;
   }

   public void draw(Graphics window)
   {
      window.setColor(color);
      window.fillRect(getX(), getY(), getWidth(), getHeight());
   }

   public void draw(Graphics window, Color col)
   {


   }
   
	public boolean equals(Object obj)
	{
		Block block = (Block) obj;
		if(block.getWidth() == this.getWidth() && block.getHeight() == this.getHeight() && block.getColor().equals(this.getColor()))
		{
			return true;
		}
		return false;
	}

	
   //add the other get methods
    public String toString()
    {
    	return getX()+" "+getY()+" "+getHeight()+" "+getWidth()+" "+getColor();
    }

   //add a toString() method  - x , y , width, height, color
}