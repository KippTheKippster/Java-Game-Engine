package modules;

import java.util.Vector;

import core.GameWindow;
import objects.CollisionShape;

public class CollisionDetector 
{
	public boolean areShapesColliding(CollisionShape rect1, CollisionShape rect2) 
	{
		if (rect1 == rect2)
		{
			return false;
		}
		
		if (
				rect1.getPosition().x < rect2.getPosition().x + rect2.size.x &&
				rect1.getPosition().x + rect1.size.x > rect2.getPosition().x &&
				rect1.getPosition().y < rect2.getPosition().y + rect2.size.y &&
				rect1.getPosition().y + rect1.size.y > rect2.getPosition().y)
		{
			return true;
		}
		return false;
	}
	
	public Vector<CollisionShape> getCollidingObjects(CollisionShape shape)
	{
		Vector<CollisionShape> shapes = GameWindow.collisionShapes;
		Vector<CollisionShape> colliders = new Vector<CollisionShape>();
		
		for (int i = 0; i < shapes.size(); i++)
		{
			if (areShapesColliding(shape, shapes.get(i)))
			{
				colliders.add(shapes.get(i));
			}
		}
		
		return colliders;
	}
	
	public boolean isShapeColliding(CollisionShape shape)
	{
		Vector<CollisionShape> shapes = GameWindow.collisionShapes;
		
		for (int i = 0; i < shapes.size(); i++)
		{
			if (areShapesColliding(shape, shapes.get(i)))
			{
				return true;
			}
		}
		
		return false;
	}
}
