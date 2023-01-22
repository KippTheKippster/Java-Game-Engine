package objects;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;

import core.GameWindow;
import util.Vector2;

public abstract class GameObject 
{
	private Vector2 position = new Vector2();
	
	public Vector2 getPosition() { return position; }
	public void setPosition(Vector2 position) 
	{ 
		this.position = position; 
		
		for (int i = 0; i < children.size(); i++)
		{
			GameObject child = children.get(i);
			child.setPosition(position);
		}
	}
	
	public void addPosition(Vector2 position) 
	{
		setPosition(new Vector2(this.position.x + position.x, this.position.y + position.y));
	}
	
	public Vector<GameObject> children = new Vector<GameObject>();
	
	public void addChild(GameObject child) 
	{
		children.add(child);
		GameWindow.addGameObject(child);
	}
	
	public void update(double delta) {}
	
	public void draw(Graphics g, ImageObserver o) {}
	
	public void ready() {}
}
