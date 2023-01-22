package core;

import javax.swing.JButton;
import javax.swing.JFrame;

import core.GameWindow.StretchMode;

public class Main
{
    private static JFrame win = null;
    private static GameWindow canvas = null;
	
	public static void main(String[] args) 
	{        
        canvas = new GameWindow();
        canvas.setDoubleBuffered(true);
        JButton button = new JButton("Test button");
        button.setVisible(true);
        canvas.add(button);
        
        win = new JFrame("Java Game Engine");
        win.setSize(800,600);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
        win.add(canvas);
        //GameWindow.frame = win;
        //win.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        GameWindow.stretchMode = StretchMode.KEEP_ASPECT;
        canvas.ready();
        //win.setBounds(0, 0, 640, 360);
	}
}
