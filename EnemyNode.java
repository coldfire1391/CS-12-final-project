import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class EnemyNode extends GameObject
{
	Noivern data;
	EnemyNode next;
	public EnemyNode(Noivern pasok)
	{
		data = pasok;
	}
	public void visualizeEnemyNode(Graphics2D g)
	{
		g.setFont(new Font("Power Clear", Font.PLAIN, 22));
		g.setColor(Color.BLACK);
		if(data.fainted)
		{
			g.drawString(data.opponents[data.current]+" fainted",10,200);
		}
		else//if(current==0&&!fainted)
		{
			data.pokemongif.paintIcon(data,g,data.x,data.y);//this
		}
		if(data.recharge)
		{
			g.drawString(data.opponents[data.current]+" must recharge",10,200);
		}
		else if(data.mv1)
		{
			//data.mv1gif.paintIcon(data,g,150,100);
			g.drawString(data.opponents[data.current]+" used Tackle",10,200);
			if(data.atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,200);
			}
			else if(data.atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(data.atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(data.mv2)
		{
			//data.mv2gif.paintIcon(data,g,200,100);
			g.drawString(data.opponents[data.current]+" used Headbutt",10,200);
			if(data.atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,200);
			}
			else if(data.atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(data.atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(data.mv2missed)
		{
			g.drawString(data.opponents[data.current]+" used Headbutt",10,200);
			g.drawString(data.opponents[data.current]+" missed",10,220);
		}
		else if(data.mv2nopp)
		{
			g.drawString(data.opponents[data.current]+" has no PP left for Headbutt",10,200);
		}
		else if(data.mv3)
		{
			//data.mv3gif.paintIcon(data,g,250,100);
			g.drawString(data.opponents[data.current]+" used Hyper Beam",10,200);
			if(data.atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,200);
			}
			else if(data.atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(data.atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(data.mv3missed)
		{
			g.drawString(data.opponents[data.current]+" used Hyper Beam",10,200);
			g.drawString(data.opponents[data.current]+" missed",10,220);
		}
		else if(data.mv4)
		{
			//data.mv4gif.paintIcon(data,g,300,100);
			g.drawString(data.opponents[data.current]+" used Recover",10,200);
		}
		else if(data.berry)
		{
			g.setFont(new Font("Power Clear", Font.PLAIN, 16));
			g.drawString(data.opponents[data.current]+" found and ate a Leppa Berry",10,200);
			g.drawString("The PP of "+data.opponents[data.current]+"'s Headbutt was restored",10,220);
		}
		g.setFont(new Font("Power Clear", Font.PLAIN, 22));
		

		//double factor = 160/data.maxhp;
		//g.drawString(data.a_type+"",300,300);
		for(int i=0;i<data.factor*data.hp;i++)
		{
			if(data.hp>(data.maxhp*(50.0/100)))
			{
				g.drawImage(data.healthbargreen,173+i,111,null);
			}
			else if((data.hp>(data.maxhp*(25.0/100))))
			{
				g.drawImage(data.healthbaryellow,173+i,111,null);
			}
			else
			{
				g.drawImage(data.healthbarred,173+i,111,null);
			}
		}
		g.setFont(new Font("Power Clear", Font.PLAIN, 26));
		g.drawString(data.opponents[data.current],60,90);
	}
}