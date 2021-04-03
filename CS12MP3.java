import java.util.Random;
import java.util.Scanner;
public class CS12MP3
{
	public static void main (String args[])throws InterruptedException
	{
		OpponentVisualizer ov = new OpponentVisualizer();
		double Noivernmv;
		int Noivernbrry=0;
		Random Pr = new Random();
		Random Nm = new Random();
		Random Nr = new Random();
		Random Nb = new Random();
		int dotindex;
		String pokestr;
		Pokemon p = new Pokemon();
		Background bckgrnd = new Background();
		boolean precharge = false;
		boolean nrecharge = false;
		
		final MarioWindow w1 = new MarioWindow();
		
		w1.add(bckgrnd);
		w1.add(ov);
		w1.add(p);
		(new Thread()
		{
			public void run()
			{
				w1.startGame();
			}
		}).start();
		bckgrnd.music();
		pokestr = p.name;
		while(p.hp>0&&ov.pq.isEmpty()!=true)
		{
			ov.front.a_type = p.atktype;
			ov.front = ov.pq.peek();
			ov.front.playeratk = p.atk;
			ov.front.playerspatk = p.spatk;
			p.enemyatk = ov.front.atk;
			p.enemyspatk = ov.front.spatk;
			p.a_type = ov.front.atktype;
			int click = p.status;
			ov.click = click;

			if(click!=0||precharge)
			{
				if(precharge)
				{
					p.recharge = true;
					Thread.sleep(2000);
					p.recharge = false;
					p.stillrecharge = true;
					System.out.println(pokestr+" must rechage...");
					precharge = false;
					click=6;
				}
				else
				{
					//credit to Lance for the one-time gif code
					if(click==1)
					{
						System.out.println(pokestr+" used Tackle");
						p.mv1 = true;
						ov.attackFront();
						p.atkfactor = ov.front.dmgfactor;
						Thread.sleep(2250);
						p.mv1 = false;
						//p.mv1gif.getImage().flush();
					}
					else if(click==2)
					{
						System.out.println(pokestr+" used Headbutt");
						if(p.pp>0)
						{
							int Playerrnd = Pr.nextInt(100)+1;
							if(Playerrnd<=20)
							{
								p.mv2missed = true;
								Thread.sleep(2000);
								p.mv2missed = false;
								System.out.println(pokestr+" missed...");
								p.pp--;
							}
							else
							{
								p.mv2 = true;
								ov.attackFront();
								p.atkfactor = ov.front.dmgfactor;
								Thread.sleep(2250);
								p.mv2 = false;
								p.pp--;
							}
						}
						else
						{
							p.mv2nopp = true;
							Thread.sleep(2000);
							p.mv2nopp = false;
							System.out.println(pokestr+"has no more PP left for Headbutt...");
						}
					}
					else if(click==3)
					{
						System.out.println(pokestr+" used "+p.rcklssatk);
						int Playerrnd = Pr.nextInt(100)+1;
						if(Playerrnd<=50)
						{
							p.mv3missed = true;
							Thread.sleep(2000);
							p.mv3missed = false;
							System.out.println(pokestr+" missed...");
						}
						else
						{
							p.mv3 = true;
							ov.attackFront();
							p.atkfactor = ov.front.dmgfactor;
							Thread.sleep(2250);
							p.mv3 = false;
							precharge = true;
						}
					}
					else if(click==4)
					{
						System.out.println(pokestr+" used Recover");
						p.mv4 = true;
						Thread.sleep(2000);
						p.mv4 = false;
						p.Recover();
					}
					else if(click==5)
					{
						p.item = true;
						Thread.sleep(2000);
						p.item = false;
						p.Item();
					}
				}
				MarioWindow.delay(500);
				if(ov.front.hp>0)
				{
					if(nrecharge)
					{
						ov.front.recharge = true;
						Thread.sleep(2000);
						ov.front.recharge = false;
						System.out.println("Noivern must recharge...");
						nrecharge = false;
					}
					else
					{
						if(ov.front.hp<=((50.0/100)*ov.front.maxhp))
						{
							Noivernmv = Nm.nextInt(100)+1;
						}
						else
						{
							Noivernmv = Nm.nextInt(75)+1;
						}
						if(ov.front.pp==0)
						{
							Noivernbrry = Nb.nextInt(100)+1;
						}
						if(Noivernmv>=1&&Noivernmv<=25)
						{
							System.out.println("Noivern used Tackle");
							ov.front.mv1 = true;
							ov.front.atkfactor = p.dmgfactor;
							p.getTackle();
							Thread.sleep(1500);
							ov.front.mv1 = false;
							if(p.hp<=0)
							{
								p.hp=0;
								break;
							}
						}
						else if(Noivernmv>=26&&Noivernmv<=50)
						{
							System.out.println("Noivern used Headbutt");
							if(ov.front.pp>0)
							{
								int Noivernrnd = Nr.nextInt(100)+1;
								if(Noivernrnd<=20)
								{
									ov.front.mv2missed = true;
									Thread.sleep(1500);
									ov.front.mv2missed = false;
									System.out.println("Noivern missed...");
									ov.front.pp--;
								}
								else
								{
									ov.front.mv2 = true;
									ov.front.atkfactor = p.dmgfactor;
									p.getHeadbutt();
									Thread.sleep(1500);
									ov.front.mv2 = false;
									ov.front.pp--;
									if(p.hp<=0)
									{
										p.hp=0;
										break;
									}
								}
							}
							else
							{
								ov.front.mv2nopp = true;
								Thread.sleep(2000);
								ov.front.mv2nopp = false;
								System.out.println("Noivern has no PP left for Headbutt...");
							}
						}
						else if(Noivernmv>=51&&Noivernmv<=75)
						{
							System.out.println("Noivern used Hyper Beam");
							int Noivernrnd = Nr.nextInt(100)+1;
							if(Noivernrnd<=50)
							{
								ov.front.mv3missed = true;
								Thread.sleep(1500);
								ov.front.mv3missed = false;
								System.out.println("Noivern missed...");
							}
							else
							{
								ov.front.mv3 = true;
								ov.front.atkfactor = p.dmgfactor;
								p.RecklessAttack();
								Thread.sleep(1500);
								ov.front.mv3 = false; 
								nrecharge = true;
								if(p.hp<=0)
								{
									p.hp=0;
									break;
								}
							}
						}
						else if(Noivernmv>=76&&Noivernmv<=100)
						{
							System.out.println("Noivern used Recover");
							ov.front.mv4 = true;
							Thread.sleep(1500);
							ov.front.mv4 = false;
							ov.front.Recover();
						}
						if(Noivernbrry>=50)
						{
							ov.front.berry = true;
							Thread.sleep(2500);
							ov.front.berry = false;
							ov.front.Berry();
							Noivernbrry = 49;
						}
					}
				}
				if(ov.exit)
				{
					MarioWindow.delay(1000);
					System.exit(0);
				}
				if(ov.pq.head.next!=null)
				{
					ov.front = ov.pq.head.next.data;
				}
			}
			p.status=0;
			p.dmgfactor = 1.0;
			ov.front.dmgfactor = 1.0;
			MarioWindow.delay(500);
			p.stillrecharge = false;
		}
		if(p.hp==0)
		{
			System.out.println(pokestr+" has been defeated...");
			p.fainted=true;
			MarioWindow.delay(1000);//2500
			System.exit(0);
		}
	}
}