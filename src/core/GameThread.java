package core;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread 
{
	 private List<GraphicsListener> listeners = new ArrayList<GraphicsListener>();
	 private long last_time = System.nanoTime();
	 private double ns = 1000000000;
	 private double delta = 0;

	 public void addListener(GraphicsListener toAdd) 
	 {
	     listeners.add(toAdd);
	 }

	 public void emitUpdate(double delta) 
	 {
	     for (GraphicsListener hl : listeners)
	         hl.update(delta);
	 }
	
	public void run()
	{		
		while (true)
		{
			long time  = System.nanoTime();
	        delta = (time - last_time) / ns;
	        last_time = time;
	        //System.out.println(delta);
	        //if(delta>=01) {
			emitUpdate(delta);
	            //delta--;
			
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //}
		}
	}
}

//An interface to be implemented by everyone interested in "Hello" events
interface GraphicsListener 
{
 void update(double delta);
}

