package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;

public class AnimationPlayer extends GameObject implements ClockListener
{
	 class Animation
     {
         public Vector<BufferedImage> textures = new Vector<BufferedImage>();

         public String name;

         public float speed;

         public int frames;

         public boolean looping;

         
         public Animation(int frames, float animationSpeed, boolean looping, BufferedImage texture, String name)
         {
        	 this.speed = animationSpeed;
             this.frames = frames;
             this.looping = looping;
             this.name = name;

             setCutTextures(texture);
         }

         
         private void setCutTextures(BufferedImage texture)
         {
             textures.clear();

             for (int i = 0; i < frames; i++)
             {
                 int w = (texture.getWidth() / frames) * (i);
                 textures.add(texture.getSubimage(w, 0, texture.getWidth() / frames, texture.getHeight()));
                 //textures.add(texture);
             }
         }
     }

     private Map<String, Animation> animations = new HashMap<String, Animation>();

     public Sprite sprite;
     private Animation currentAnimation;

     private Clock _timer = new Clock();

     public int currentFrame;

     private boolean _playing;

     public void setPlaying(boolean value) 
     {
         _playing = value;

         if (value)
         {
             _timer.start();
         }
         else
         {
             _timer.stop();
         }
     }
     
     public boolean isPlaying() {
    	 return _playing;
     }
     
     public AnimationPlayer(Sprite sprite)
     {
         this.sprite = sprite;

         _timer.looping = true;
         _timer.waitTime = 0.1f;
         //_timer.TimeOut += OnNextFrame;
         _timer.addListener(this);
         _timer.start();

         addChild(_timer);
     }

     public void addAnimation(String name, int frames, BufferedImage texture, float speed, boolean looping)
     {
         Animation anim = new Animation(frames, speed, looping, texture, name);

         animations.put(name, anim);
     }

     public void addAnimation(String name, int frames, String texture, float speed, boolean looping)
     {
    	 Animation anim = null;
    	 try 
    	 {
    		 anim = new Animation(frames, speed, looping, ImageIO.read(new File(texture)), name);
    		 animations.put(name, anim);
    	 } 
    	 catch (IOException e) 
    	 {
    		 e.printStackTrace();
    	 }
     }

     public void setAnimation(String animation, boolean forceReset)
     {
         currentAnimation = animations.get(animation);

         if (forceReset || animation != currentAnimation.name)
         {
             nextFrame(); 
         }

         _timer.waitTime = currentAnimation.speed;
     }

     public String getCurrentAnimation()
     {
         return currentAnimation.name;
     }

     private void nextFrame()
     {
         if (currentAnimation == null)
             return;

         if (currentFrame >= currentAnimation.frames)
         {
             if (currentAnimation.looping)
             {
                 currentFrame = 0;
             }
             else
             {
                 return;
             }
         }

         currentFrame += 1;
         sprite.texture = currentAnimation.textures.get(currentFrame - 1);
     }

	@Override
	public void timeout() {
		// TODO Auto-generated method stub
		nextFrame();
	}
     
}
