package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GameWindow;

public class Sprite extends GameObject 
{
	public BufferedImage texture;  

	@Override
	public void draw(Graphics g, ImageObserver o)
	{
		// TODO Auto-generated method stub
		if (texture == null)
			return;
		
		g.drawImage(texture, (int)(getPosition().x * GameWindow.scale.x), (int)(getPosition().y *  GameWindow.scale.y), 
				(int)(texture.getWidth() *  GameWindow.scale.x), (int)(texture.getHeight() * GameWindow.scale.y), o);	
	}

	
	public void setTexture(String path) 
	{
		try {
			texture = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
