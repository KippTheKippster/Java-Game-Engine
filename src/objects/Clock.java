package objects;

import java.util.ArrayList;
import java.util.List;

public class Clock extends GameObject
{
	//public event EventHandler? TimeOut;
	private List<ClockListener> listeners = new ArrayList<ClockListener>();
    public float waitTime;
    public float timeLeft;

    public boolean looping;

    public Clock()
    {
        waitTime = 1;
        timeLeft = 0;
    }

    @Override
    public void update(double deltaTime)
    {
        if (timeLeft > 0)
        {
            timeLeft -= deltaTime;

            if (timeLeft <= 0)
            {
                //TimeOut?.Invoke(this, new EventArgs());
            	timeout();
            	
                if (looping)
                {
                    start();
                }
            }
        }
    }
    
    public void addListener(ClockListener c)
    {
    	listeners.add(c);
    }

    private void timeout() 
    {
    	for (int i = 0; i < listeners.size(); i++)
    	{
    		listeners.get(i).timeout();
    	}
    }
    
    public void start()
    {
        timeLeft = waitTime;
    }

    public void stop()
    {
        timeLeft = 0;
    }
}

interface ClockListener 
{
	void timeout();
}
