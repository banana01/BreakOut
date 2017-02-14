import java.awt.Color;
import java.awt.Graphics;
import java.security.PrivilegedActionException;

public class BreakOutBlock extends Block
{
	public BreakOutBlock()
	{
		
	}
	public BreakOutBlock(int x, int y, int w, int h, Color c)
	{
		setX(x); setHeight(h);setY(y); setWidth(w); setColor(c);
	}
	private void Destroy(Graphics window)
	{
		Color c = window.getColor();
		window.setColor(Color.white);
		window.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		window.setColor(c);
	}
	public boolean Collide(Graphics window, Ball ball)
	{
		if
		(
				(ball.getX() >= this.getX() && ball.getX() <= this.getX()+this.getWidth()) &&
				(ball.getY() >= this.getY() && ball.getY() <= this.getY()+this.getHeight())
		)
		{
			Destroy(window);
			return true;
		}
		return false;
	}
	
	
}
