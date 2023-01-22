package objects;

import java.awt.Color;
import java.util.Vector;

import core.GameWindow;

public class CollisionShape extends BoxShape
{	
	public CollisionShape()
	{
		GameWindow.addCollisionShape(this);
		color = Color.BLUE;
	}
	
	public Vector<CollisionShape> getColliders()
	{
		return GameWindow.collisionDetector.getCollidingObjects(this);
	}
	
	public boolean isColliding() 
	{
		return GameWindow.collisionDetector.isShapeColliding(this);
	}
}
