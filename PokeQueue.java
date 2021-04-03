import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class PokeQueue extends GameObject
{
	EnemyNode head;
	EnemyNode tail;
	EnemyNode rover;
	BufferedImage pokeball;
	public PokeQueue()
	{
		rover = head;
		pokeball = MarioWindow.getImage("Pokeball.png");
	}
	public void enqueue(Noivern s)
	{
		EnemyNode bago = new EnemyNode(s);
		if(head==null)
		{
			head = bago;
			tail = bago;
		}
		else
		{
			tail.next = bago;
			tail = bago;
		}
	}
	public void dequeue()
	{
		if(head!=null)
		{
			head=head.next;
		}
	}
	public Noivern peek()
	{
		if(head!=null)
		{
			Noivern result = head.data;
			return result;
		}
		else
		{
			return null; //or some other value that indicates na walang nakuha
					   //return null when dealing with Objects
		}
	}
	public void visualizeQueue(Graphics2D g)
	{
		rover = head;
		int count = 0;
		rover.visualizeEnemyNode(g);
		while(rover!=null)
		{
			count++;
			rover = rover.next; //go to the next Enemynode
		}
		int k=0;
		for(int i = 0; i<count;i++)
		{
			
			g.drawImage(pokeball,60+k,35,null);
			k+=20;
		}
	}
	public boolean isEmpty()
	{
		return head==null;
	}
}