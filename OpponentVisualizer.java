import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class OpponentVisualizer extends GameObject
{
	PokeQueue pq;
	Noivern front;
	Random r;
	int click;
	boolean exit;
	boolean enqueued;
	int atktype;
	public OpponentVisualizer()
	{
		r = new Random();
		pq = new PokeQueue();
		Noivern zero = new Noivern(4);
		pq.enqueue(zero);
		Noivern one = new Noivern(1);
		pq.enqueue(one);
		Noivern two = new Noivern(2);
		pq.enqueue(two);

 		front = pq.peek();
 		exit = false;
 		enqueued = false;

	}
	public void paint(Graphics2D g)
	{
		pq.visualizeQueue(g);
		if(enqueued)
		{
			g.drawString("A Pokemon has entered the queue!",50,25);
		}

	}
	public void run()
	{
		while(true)
		{
			MarioWindow.delay(60000);
			int random = r.nextInt(100);
			int rint = r.nextInt(7);
			if(random <= 25)
			{
				Noivern enemy = new Noivern(rint);
				pq.enqueue(enemy);
				enqueued = true;
				MarioWindow.delay(1500);
				enqueued = false;
				System.out.println("a new Pokemon has entered the queue!");
				while(pq.rover!=null)
				{
					System.out.println(pq.rover.data.name);
					pq.rover = pq.rover.next;
				}
				pq.rover = pq.head;
			}
		}
	}
	public void attackFront()
	{
		//double dmgfactor = front.factor(front.a_type,front.type1,front.type2,front)
			if(click==1)
			{
				// front.a_type = 0;
				// if(front.factor(front.a_type,front.type1,front.type2,front.dmgtable))
				// {
					
				// }
				front.getTackle();
			}
			else if(click==2)
			{
				// front.a_type = 0;
				// if(front.factor())
				front.getHeadbutt();
			}
			else if(click==3)
			{
				//front.a_type = atktype;
				// if(front.factor())
				front.getHyperBeam();
			}
		if(front.hp<=0)
		{
			if(pq.head.next==null)
			{
				exit = true;
			}
			else
			{
				MarioWindow.delay(1000);
				pq.dequeue();
			}
		}
	}
}