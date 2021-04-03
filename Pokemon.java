import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.Scanner;
public class Pokemon extends GameObject
{
	boolean end;
	boolean fainted;
	String name;
	String[] pokemon;
	String[] mv3array;
	String[] mv3gifarray;

	int[] types1;
	int type1;
	int[] types2;
	int type2;
	double[][] dmgtable;
	
	int id;
	int dotindex;
	int maxhp;
	
	BufferedImage healthbargreen;
	BufferedImage healthbaryellow;
	BufferedImage healthbarred;

	double factor;
	int hp;
	int atk;
	int def;
	int spatk;
	int spdef;
	String rcklssatk;
	//int spd;
	int pp;

	
	int a_type;//atktype of enemy
	double dmgfactor;
	double atkfactor;//enemy dmgfactor

	boolean mv1;
	boolean mv2;
	boolean mv2nopp;
	boolean mv2missed;
	boolean mv3;
	boolean mv3missed;
	boolean mv4;
	boolean recharge;
	boolean stillrecharge;
	boolean item;
	int status;


	int[] pokemongify;
	int[] pokemongifx;
	int ypos;
	int xpos;
	ImageIcon pokemongif;

	double enemyatk;
	double enemyspatk;
	int atktype;

	public Pokemon()//constructor
	{
		end = false;
		healthbargreen = MarioWindow.getImage("Health/green.png");
		healthbaryellow = MarioWindow.getImage("Health/yellow.png");
		healthbarred = MarioWindow.getImage("Health/red.png");
		pp=15;
		System.out.print("003. Venusaur\n006. Charizard\n009. Blastoise\nChoose Pokemon id: ");
		Scanner chc = new Scanner(System.in); //credit to Joshua dela Sierra for working input gif calling
		id = chc.nextInt();
		pokemon = new String[]{"","","","Venusaur.gif","","","Charizard.gif","","","Blastoise.gif"};
		pokemongify = new int[]{0,0,0,261,0,0,222,0,0,255};
		pokemongifx = new int[]{0,0,0,135,0,0,115,0,0,150};

		types1 = new int[]{0,0,0,4,0,0,1,0,0,2};
		type1 = types1[id];
		types2 = new int[]{0,0,0,7,0,0,9,0,0,2};
		type2 = types2[id];

		mv3array = new String[]{"","","","FRENZY PLANT","","","BLAST BURN","","","HYDRO CANNON"};

		pokemongif = MarioWindow.getGif("Player/"+pokemon[id]);
		ypos = pokemongify[id];
		xpos = pokemongifx[id];
		rcklssatk = mv3array[id];

		dotindex = pokemon[id].indexOf(".");
		name = pokemon[id].substring(0,dotindex);
		status=0;
		if(id==3)
		{
			factor = 2.0;
			maxhp = 80;
			hp = 80;
			atk = 82;
			def = 83;
			spatk = 100;
			spdef = 100;
			//spd = 80;
			atktype = 4;
		}
		else if(id==6)
		{
			factor = 2.05;
			maxhp = 78;
			hp = 78;
			atk = 84;
			def = 78;
			spatk = 109;
			spdef = 85;
			//spd = 100;
			atktype = 1;
		}
		else if(id==9)
		{
			maxhp = 79;
			factor = 2.025;
			hp = 79;
			atk = 83;
			def = 100;
			spatk = 85;
			spdef = 105;
			//spd = 78;
			atktype = 2;
		}

		mv1 = false;
		mv2 = false;
		mv2missed = false;
		mv2nopp = false;
		mv3 = false;
		mv3missed = false;
		mv4 = false;
		item = false;
		fainted=false;

		dmgtable = new double[17][17];
		for(int i=0;i<17;i++)
		{
			for(int j=0;j<17;j++)
			{
				if((i==0&&j==13)||(i==3&&j==8)||(i==6&&j==13)||(i==8&&j==9)||(i==10&&j==15)||(i==13&&j==0))
				{
					dmgtable[i][j]=0.0;
				}
				else if((i==1&&((j==1)||(j==2)||(j==12)||(j==14)))||(i==2&&((j==2)||(j==4)||(j==14)))||(i==3&&((j==3)||(j==4)||(j==14)))||(i==4&&((j==1)||(j==4)||(j==7)||(j==9)||(j==11)||(j==14)||(j==16)))||(i==5&&((j==1)||(j==2)||(j==5)||(j==16)))||(i==6&&((j==7)||(j==9)||(j==10)||(j==11)))||(i==7&&((j==7)||(j==8)||(j==12)||(j==13)))||(i==8&&((j==4)||(j==11)))||(i==9&&((j==3)||(j==12)||(j==16)))||(i==10&&((j==10)||(j==16)))||(i==11&&((j==1)||(j==6)||(j==7)||(j==9)||(j==13)||(j==16)))||(i==12&&((j==6)||(j==8)||(j==16)))||(i==13&&j==15)||(i==14&&j==16)||(i==15&&((j==6)||(j==15)))||(i==16&&((j==1)||(j==2)||(j==3)||(j==16))))
				{
					dmgtable[i][j]=0.5;
				}
				else if((i==1&&((j==4)||(j==5)||(j==11)||(j==16)))||(i==2&&((j==1)||(j==8)||(j==12)))||(i==3&&((j==2)||(j==9)))||(i==4&&((j==2)||(j==8)||(j==12)))||(i==5&&((j==4)||(j==8)||(j==9)||(j==14)))||(i==6&&((j==0)||(j==5)||(j==12)||(j==15)))||(i==7&&j==4)||(i==8&&((j==1)||(j==3)||(j==7)||(j==12)||(j==16)))||(i==9&&((j==4)||(j==6)||(j==11)))||(i==10&&((j==6)||(j==7)))||(i==11&&((j==4)||(j==10)||(j==15)))||(i==12&&((j==1)||(j==5)||(j==9)||(j==11)))||(i==13&&((j==10)||(j==13)))||(i==14&&j==14)||(i==15&&((j==10)||(j==13)))||(i==16&&((j==5)||(j==12))))
				{
					dmgtable[i][j]=2.0;
				}
				else
				{
					dmgtable[i][j]=1.0;
				}
			}
		}
	}
	public void paint(Graphics2D g)
	{
		g.setFont(new Font("Power Clear", Font.PLAIN, 22));

		if(status==0&&!fainted&&!end&&!recharge&&!stillrecharge)
		{
			g.drawString("What will "+name+" do?",10,200);
		}
		if(recharge)
		{
			g.drawString(name+" must recharge",10,200);
		}
		else if(mv1)
		{
			//mv1gif.paintIcon(this,g,300,100);
			g.drawString(name+" used Tackle",10,200);
			if(atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,220);
			}
			else if(atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(mv2)
		{
			//mv2gif.paintIcon(this,g,300,100);
			g.drawString(name+" used Headbutt",10,200);
			if(atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,220);
			}
			else if(atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(mv2missed)
		{
			g.drawString(name+" used Headbutt",10,200);
			g.drawString(name+" missed",10,220);
		}
		else if(mv2nopp)
		{
			g.drawString("Headbutt has no PP left",10,200);
		}
		else if(mv3)
		{
			//mv3gif.paintIcon(this,g,300,100);
			g.drawString(name+" used "+rcklssatk,10,200);
			if(atkfactor==0.0)
			{
				g.drawString("But it had no effect...",10,220);
			}
			else if(atkfactor<1.0)
			{
				g.drawString("It's not very effective...",10,220);
			}
			else if(atkfactor>1.0)
			{
				g.drawString("It's super effective!",10,220);
			}
		}
		else if(mv3missed)
		{
			g.drawString(name+" used "+rcklssatk,10,200);
			g.drawString(name+" missed",10,220);
		}
		else if(mv4)
		{
			//mv4gif.paintIcon(this,g,300,100);
			g.drawString(name+" used Recover",10,200);
		}
		else if(item)
		{
			g.drawString("PKMN trainer used PP Restore",10,200);
			if(pp==15)
			{
				g.drawString("But it had no effect",10,220);
			}
			else
			{
				g.drawString("Headbutt's PP was restored",10,220);
			}
		}
		if(!fainted)
		{
			pokemongif.paintIcon(this,g,xpos,ypos);
		}
		else
		{
			g.drawString(name+" fainted...",10,200);
		}
		g.drawString(maxhp+"",685,340);
		g.drawString(hp+"",650,340);
		for(int i=0;i<factor*hp;i++)
		{
			if(hp>(maxhp*(50.0/100)))
			{
				g.drawImage(healthbargreen,579+i,304,null);
			}
			else if((hp>(maxhp*(25.0/100))))
			{
				g.drawImage(healthbaryellow,579+i,304,null);
			}
			else
			{
				g.drawImage(healthbarred,579+i,304,null);
			}
		}
		g.setFont(new Font("Power Clear", Font.PLAIN, 26));
		g.drawString(name,470,285);
		g.setFont(new Font("Power Clear", Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString("TACKLE",90,430);
		g.drawString("HEADBUTT",360,430);
		g.drawString(rcklssatk,65,500);
		g.drawString("RECOVER",365,500);
		g.drawString(pp+"",675,435);
		g.drawString("15",730,435);
		g.drawString("ITEM",660,500);
		//g.drawString(status+"",400,300);
	}
	public void getTackle()
	{
		//a_type = 0;
		double dmg = ((((((2*20)/5)+2)*50*(enemyatk/def))/50)+2);
		dmgfactor = factor(a_type,type1,type2,dmgtable);
		hp-=dmg*dmgfactor;
	}
	public void getHeadbutt()
	{
		//a_type = 0;
		double dmg = ((((((2*20)/5)+2)*70*(enemyatk/def))/50)+2);
		dmgfactor = factor(a_type,type1,type2,dmgtable);
		hp-=dmg*dmgfactor;
	}
	public void RecklessAttack()
	{
		//a_type = 0;
		double dmg = ((((((2*20)/5)+2)*150*(enemyspatk/spdef))/50)+2);
		dmgfactor = factor(a_type,type1,type2,dmgtable);
		hp-=dmg*dmgfactor;
	}
	public void Recover()
	{
		if((hp+(maxhp/2))>=maxhp)
		{
			hp=maxhp;
		}
		else
		{
			hp+=(maxhp/2);
		}
	}
	public void Item()
	{
		pp=15;
	}
	public double factor(int a_type, int type1, int type2, double[][] dmgtable)//, double[][] tdarray)
	{
		if(type1==type2)
		{
			return dmgtable[a_type][type1];
		}
		else
		{
			return dmgtable[a_type][type1]*dmgtable[a_type][type2];
		}
	}
	public void mouseClicked(int xmouse, int ymouse, int button)
	{
		if((xmouse>=17)&&(xmouse<=17+260)&&(ymouse>=390)&&(ymouse<=390+58))
		{
			status=1;//tackle
		}
		else if((xmouse>=285)&&(xmouse<=285+285)&&(ymouse>=390)&&(ymouse<=390+58))
		{
			status=2;//headbutt
		}
		else if((xmouse>=17)&&(xmouse<=17+260)&&(ymouse>=457)&&(ymouse<=457+59))
		{
			status=3;//recklessattack
		}
		else if((xmouse>=285)&&(xmouse<=285+285)&&(ymouse>=457)&&(ymouse<=457+59))
		{
			status=4;//recover
		}
		else if((xmouse>=603)&&(xmouse<=603+180)&&(ymouse>=457)&&(ymouse<=457+59))
		{
			status=5;//item
		}
	}
}