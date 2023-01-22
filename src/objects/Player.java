package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import core.GameWindow;
import util.*;

public class Player extends GameObject 
{
	CollisionShape shape = null;
	Sprite sprite = null;
	AnimationPlayer animation = null;
	float speed = 600;
	
	@Override
	public void ready() 
	{
		shape = new CollisionShape();
		shape.size = new Vector2(16, 16);
		shape.color = Color.GREEN;
		sprite = new Sprite();
		animation = new AnimationPlayer(sprite);
		animation.addAnimation("walk", 2, "C:\\Users\\Vasko\\OneDrive\\Documents\\Godot Projects\\LBS-GameJam-3\\assets\\enemies\\Alf2.png", 0.1f, true);
		animation.setAnimation("walk", false);
		animation.setPlaying(true);
		sprite.setTexture("C:\\Users\\Vasko\\OneDrive\\Documents\\Godot Projects\\LBS-GameJam-3\\assets\\enemies\\Alf2.png");
		addChild(animation);
		addChild(sprite);
		addChild(shape);
	}
	
	@Override
	public void update(double delta) 
	{	
		Vector2 pos = getPosition();

		if (GameWindow.input.isActionPressed("right"))
		{
			pos.x += speed * delta;
		}
		else if (GameWindow.input.isActionPressed("left"))
		{
			pos.x -= speed * delta;
		}
		if (GameWindow.input.isActionPressed("up"))
		{
			pos.y -= speed * delta;
		}
		else if (GameWindow.input.isActionPressed("down"))
		{
			pos.y += speed * delta;
		}
		
		// System.out.println(shape.isColliding());
		setPosition(pos);
	}

	@Override
	public void draw(Graphics g, ImageObserver o)
	{
		// TODO Auto-generated method stub
		
	}
	
}
