package core;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modules.*;
import objects.BoxShape;
import objects.CollisionShape;
import objects.GameObject;
import objects.Player;
import util.*;

public class GameWindow extends JPanel implements GraphicsListener 
{
	public enum StretchMode
	{
		DISABLED,
		KEEP_ASPECT,
		EXPAND
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 416659649397718181L;
	
	private static Vector<GameObject> objects = new Vector<GameObject>();
	public static Vector<CollisionShape> collisionShapes = new Vector<CollisionShape>();
	
	public static JFrame frame;

	public static InputManager input = new InputManager(); 
	public static CollisionDetector collisionDetector = new CollisionDetector();
	
	public static Vector2 scale = new Vector2(1, 1);
	public static Vector2 resolution = new Vector2(640, 360);
	public static StretchMode stretchMode = StretchMode.DISABLED;
	
    JButton button = null;
	
	public void ready() 
	{
        BoxShape s = new BoxShape();
        s.setPosition(new Vector2(16, 16+3));
        s.size = new Vector2(128, 128);
        s.color = Color.RED;
        addGameObject(s);
        
        button = new JButton("Test button");
        button.setVisible(true);
        add(button);
        
        Button b1 = new Button("ass");
        add(b1);
        
        for (int i = 0; i < 20; i++)
        {
            CollisionShape a = new CollisionShape();
            a.setPosition(new Vector2((float)Math.random() * 640f, (float)Math.random() * 640f));
            a.size = new Vector2(128, 128);
            a.color = Color.BLUE;
            addGameObject(a);
        }
        
        
        BoxShape b = new BoxShape();
        b.setPosition(new Vector2(196, 32));
        b.size = new Vector2(16, 16);
        addGameObject(b);
        
        Player p = new Player();
        p.setPosition(new Vector2(88, 88));
        addGameObject(p);
        
        Player p2 = new Player();
        p2.setPosition(new Vector2(88 + 16, 88+24));
        addGameObject(p2);
        
        GameThread a = new GameThread();
        a.addListener(this);
        a.start();
        
        input.addAction("right", KeyEvent.VK_D);
        input.addAction("left", KeyEvent.VK_A);
        input.addAction("up", KeyEvent.VK_W);
        input.addAction("down", KeyEvent.VK_S);
	}
	
	public static void addGameObject(GameObject obj) 
	{
		objects.add(obj);
		obj.ready();
	}
	
	public static void addCollisionShape(CollisionShape shape)
	{
		collisionShapes.add(shape);
	}
	
	public void paint(Graphics g)
	{			
		Dimension screenSize = getSize();
		g.clearRect(0, 0, screenSize.width, screenSize.height);
		
		for (int i = 0; i < objects.size(); i++)
		{
			GameObject obj = objects.get(i);
			obj.draw(g, this);
		}
		
		//button.paintAll(g);
	}

	@Override
	public void update(double delta) 
	{	
		scaleWindow();
		
		for (int i = 0; i < objects.size(); i++)
		{
			GameObject obj = objects.get(i);
			obj.update(delta);
		}
		
		
		repaint();
	}
	
	private void scaleWindow()
	{
		Vector2 windowSize = new Vector2(getRootPane().getSize().width, getRootPane().getSize().height);
		
		if (stretchMode != StretchMode.DISABLED)
			scale = new Vector2(windowSize.x / resolution.x, windowSize.y / resolution.y);
		
		if (stretchMode == StretchMode.KEEP_ASPECT)
			if (scale.x > scale.y)
				scale.x = scale.y;
			else
				scale.y = scale.x;
		
		Vector2 scaledResolution;
		
		if (stretchMode == StretchMode.DISABLED)
			scaledResolution = windowSize;
		else
			scaledResolution = new Vector2((int)(resolution.x * scale.x), (int)(resolution.y * scale.y));
		
		Vector2 offset;
		
		if (stretchMode == StretchMode.KEEP_ASPECT)
			offset = new Vector2((windowSize.x - scaledResolution.x) / 2, (windowSize.y - scaledResolution.y) / 2);
		else
			offset = new Vector2();
		
		setBounds((int)offset.x, (int)offset.y, (int)scaledResolution.x, (int)scaledResolution.y);
		
		//System.out.println("sx: " + scale.x + " sy: " + scale.y);
	}
}
