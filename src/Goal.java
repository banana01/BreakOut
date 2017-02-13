import java.awt.Color;

public class Goal extends Block
{
	private Paddle myPaddle;
	public Goal()
	{
		
	}
	public Goal(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}
	public Goal(int x, int y, int w, int h, Color c)
	{
		super(x, y, w, h, c);
	}
	
	public Paddle getMyPaddle()
	{
		return myPaddle;
	}
	public boolean Collision(Ball ball)
	{
		int xDis = Math.abs(ball.getX()-this.getX());
		if(xDis < 5 && (ball.getY() >= this.getY() && ball.getY() <= this.getY()+this.getHeight()))
		{
			return true;
		}
		return false;
	}
	public void setMyPaddle(Paddle myPaddle)
	{
		this.myPaddle = myPaddle;
	}
	
}
