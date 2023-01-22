package modules;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class InputManager 
{
	
	private static Vector<Action> actions = new Vector<Action>();
	
	public InputManager() 
	{
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent k) 
            {
            	switch(k.getID())
            	{
            	case KeyEvent.KEY_PRESSED:
            	{
            		keyPressed(k.getKeyCode());
            		break;
            	}
            	case KeyEvent.KEY_RELEASED:
            	{
            		keyReleased(k.getKeyCode());
            		break;
            	}
            	}
            	
            	return false;
            }
        });
	}

	
	private void keyPressed(int k) {
		for (int i = 0; i < actions.size(); i++)
		{
			Action a = actions.get(i);
			if (a.keyCode == k)
			{
				a.pressed = true;
			}
		}
	}

	private void keyReleased(int k) {
		for (int i = 0; i < actions.size(); i++)
		{
			Action a = actions.get(i);
			if (a.keyCode == k)
			{
				a.pressed = false;
			}
		}
		
	}
	
	public boolean isActionPressed(String name)
	{
		for (int i = 0; i < actions.size(); i++)
		{
			Action a = actions.get(i);
			if (a.name == name && a.pressed)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void addAction(String name, int keyCode) 
	{
		actions.add(new Action(name, keyCode));
	}
	
}

class Action
{
	public int keyCode;
	public String name;
	public boolean pressed = false;
	
	Action(String name, int keyCode)
	{
		this.name = name;
		this.keyCode = keyCode;
	}
}
