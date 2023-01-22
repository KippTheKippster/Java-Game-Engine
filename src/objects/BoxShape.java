package objects;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import core.GameWindow;
import util.Vector2;

public class BoxShape extends Shape
{
	public Vector2 size;

	@Override
	public void draw(Graphics g, ImageObserver o) 
	{
		// TODO Auto-generated method stub
		g.setColor(color);
		/*g.drawRect((int)getPosition().x, (int)getPosition().y,
				(int)size.x, (int)size.y);*/
		g.drawRect((int)(getPosition().x * GameWindow.scale.x), (int)(getPosition().y * GameWindow.scale.y),
				(int)(size.x * GameWindow.scale.x), (int)(size.y * GameWindow.scale.y));
	}
}
