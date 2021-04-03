import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
public class Noivern extends GameObject
{
	String name;

	int current;
	String[] opponents;
	
	int[] types1;
	int type1;
	int[] types2;
	int type2;
	double[][] dmgtable;
	int[] ycoord;
	int[] xcoord;
	int x;
	int y;

	BufferedImage healthbargreen;
	BufferedImage healthbaryellow;
	BufferedImage healthbarred;
	boolean fainted;

	int[] maxhps;
	int[] atks;
	int[] defs;
	int[] spatks;
	int[] spdefs;

	int maxhp;
	int hp;
	double factor;
	int atk;
	int def;
	int spatk;
	int spdef;
	//int spd;

	double dmgfactor;
	double atkfactor;//player dmgfactor

	int pp;
	boolean mv1;
	boolean mv2;
	boolean mv3;
	boolean mv4;
	boolean recharge;
	boolean berry;
	boolean mv3missed;
	boolean mv2missed;
	boolean mv2nopp;

	ImageIcon pokemongif;

	double playeratk;
	double playerspatk;
	int a_type;
	int atktype;
	public Noivern(int c)//constructor
	{
		mv2nopp=false;
		fainted=false;
		//end = false;
		current = c;
		opponents = new String[]{"Noivern","Bouffalant","Eelektross","Flygon","Samurott","Heracross","Aerodactyl"};
		name = opponents[current];
		types1 = new int[]{9,0,3,8,2,11,12};
		type1 = types1[current];
		types2 = new int[]{14,0,3,14,2,6,9};
		type2 = types2[current];
		
		maxhps = new int[]{85,95,85,80,95,80,80};
		maxhp = maxhps[current];
		hp = maxhps[current];
		factor = 160.0/maxhp;
		atks = new int[]{70,110,115,100,100,125,105};
		atk = atks[current];
		defs = new int[]{80,95,80,80,85,75,65};
		def = defs[current];
		spatks = new int[]{97,40,105,80,108,40,60};
		spatk = spatks[current];
		spdefs = new int[]{80,95,80,80,70,95,75};
		spdef = spdefs[current];
		atktype = 0;

		pokemongif = MarioWindow.getGif("Enemies/"+name+".gif");
		xcoord = new int[]{450,495,495,500,485,525,475};
		x = xcoord[current];
		ycoord = new int[]{0,95,65,25,55,75,15};
		y = ycoord[current];

		healthbargreen = MarioWindow.getImage("Health/green.png");
		healthbaryellow = MarioWindow.getImage("Health/yellow.png");
		healthbarred = MarioWindow.getImage("Health/red.png");
		
		pp=15;//15
		mv1 = false;
		mv2 = false;
		mv3 = false;
		mv4 = false;
		berry = false;
		recharge = false;
		mv2missed = false;
		mv3missed = false;
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
		// types1map = new HashMap<>();
		// types2map = new HashMap<>();
		// a_typemap = new HashMap<>();
	}
	public void getTackle()
	{
		a_type = 0;
		double dmg = ((((((2*15)/5)+2)*50*(playeratk/def))/50)+2);
		dmgfactor = factor(a_type,type1,type2,dmgtable);
		hp-=dmg*dmgfactor;
	}
	public void getHeadbutt()
	{
		a_type = 0;
		double dmg = ((((((2*15)/5)+2)*70*(playeratk/def))/50)+2);
		dmgfactor = factor(a_type,type1,type2,dmgtable);
		hp-=dmg*dmgfactor;
	}
	public void getHyperBeam()
	{
		double dmg = ((((((2*15)/5)+2)*150*(playerspatk/spdef))/50)+2);
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
	public void Berry()
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
}