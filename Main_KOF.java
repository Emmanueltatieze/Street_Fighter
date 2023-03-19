import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Main_KOF extends BasicGame {

	public Main_KOF(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	Joueur J;
	Input inp;
	int time;
	int time2;
	int time_bg;
	Image[] BG5=new Image[49];
	int bg_frame=0;
	
	ArrayList<boules>B=new ArrayList<>();
	private int time3=0;
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

		//g.setColor(Color.white);
		//g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

		Image bg=new Image("BG5/frame-0"+1+".gif");
		g.drawImage(BG5[bg_frame], 0, 0, null);
		if(time_bg>150)
		{
			time_bg=0;
			bg_frame++;
			if(bg_frame>=BG5.length)
				bg_frame=0;
		}
		
		
				J.dessiner_joueur(g);
				for(int i=0;i<B.size();i++)
				{
					B.get(i).dessiner_boule(g);
				}
				
				
				if((J.isNorm() && time>300)||(J.isFat()&&time>100)||(J.isEn_saut()&&time>100)||(J.isPied()&&time>100))
				{
					J.choixImg(gc,inp);
					time=0;
				}
				
//			
//			if(J.isNorm() && time>300)
//			{
//				J.choixImg(gc,inp);
//				time=0;
//			}
//			if(J.isFat()&&time>100)
//			{
//				J.choixImg(gc,inp);
//				time=0;
//			}
//			if(J.isEn_saut()&&time>100)
//			{
//				J.choixImg(gc,inp);
//				time=0;
//			}
			if(inp.isKeyDown(Input.KEY_DOWN))
			{
				
				J.choixImg(gc,inp);
					
			}
				
			
			if(J.isPoing()&&time>1)
			{
				J.choixImg(gc,inp);
				time=0;
				
			}
				
				
			if(J.isPoing_sol())
			{
				J.choixImg(gc,inp);
				
				
			}
//			if(J.isPied()&&time>100)
//			{
//				J.choixImg(gc,inp);
//				time=0;
//				
//			}
			if(J.isPied_sol())
			{
				
				J.choixImg(gc,inp);
				
				
			}
			

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		J=new Joueur(gc);
		inp=gc.getInput();
		time=0;
		time2=0;
		time_bg=0;
		for(int i=0;i<BG5.length;i++)
		{
			int c=i+1;
			if(c<10)
			BG5[i]=new Image("BG5/frame-0"+c+".gif");
			else
			BG5[i]=new Image("BG5/frame-"+c+".gif");

				
		}
		
		gc.setFullscreen(true);

	}

	@Override
	public void update(GameContainer gc, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		inp=gc.getInput();
		time2=alpha;
		time+=alpha;
		time3+=alpha;
		time_bg+=alpha;
		
		//if(time>10)
		//{
			if(inp.isKeyDown(Input.KEY_LEFT))
			{
				J.Gauche(alpha, gc);
			}
			if(inp.isKeyDown(Input.KEY_RIGHT))
			{
				J.Droite(alpha, gc);
			}

//			if(time3>5000)
//			{
//				Music hadoken = new Music("Sounds/hadoken.ogg");
//				hadoken.play();
//				
//				//hadoken.loop();
//				time3=0;
//				J.setFat(true);
//			}
		
			if(inp.isKeyPressed(Input.KEY_D)&&!J.isBas())
			{
				J.setFat(true);
				Music hadoken = new Music("Sounds/hadoken.ogg");
				hadoken.play();
			}
			if(inp.isKeyDown(Input.KEY_UP)&&!J.isEn_saut())
			{
				J.setEn_saut(true);
				J.Haut(alpha, gc);
					
			}
			if(inp.isKeyPressed(Input.KEY_W))
			{
				J.setPoing(true);
				//inp=gc.getInput();
				if(inp.isKeyDown(Input.KEY_DOWN))
				{
					//J.setPoing(false);
					J.setPoing_sol(true);
					J.setPoing(false);
				}
				else
					J.setPoing(true);
					
				
				//J.choixImg(gc,inp);
				//time=0;
					
			}
//			if(inp.isKeyPressed(Input.KEY_W)&&inp.isKeyDown(Input.KEY_DOWN))
//			{
//				J.setPoing(false);
//				J.setPoing_sol(true);
//			}
			if(inp.isKeyPressed(Input.KEY_A))
			{
				//inp=gc.getInput();
				if(inp.isKeyDown(Input.KEY_DOWN))
				{
					J.setNorm(false);
					J.setPied_sol(true);
				}
				else
					J.setPied(true);
				
			}
//			if(inp.isKeyPressed(Input.KEY_A)&&inp.isKeyDown(Input.KEY_DOWN))
//			{
//				J.setNorm(false);
//				J.setPied_sol(true);
//			}
//			if(inp.isKeyDown(Input.KEY_DOWN))
//			{
//				//J.setEn_saut(true);
//				//J.Haut(alpha, gc);
//					
//			}
			
			
			if(J.isEn_saut())
			{
				//J.setFat(false);
				J.setNorm(false);
			   J.Haut(alpha, gc);
			}
			
			
			
			if(J.isSend_boule())
			{
				if(J.getTemps_attente_apres_tir()==0)
				{
				 B.add(new boules(gc, J));
				}
				
				J.setTemps_attente_apres_tir(J.getTemps_attente_apres_tir()+alpha);
				//J.setSend_boule(false);
				
			}
			if(J.isPoing()||J.isPoing_sol())
			{
				J.setTemps_attente_apres_poing(J.getTemps_attente_apres_poing()+alpha);
				
			}
			if(J.isPied()||J.isPied_sol())
			{
				J.setTemps_attente_apres_pied(J.getTemps_attente_apres_pied()+alpha);
				
			}

			
			///////avancer boules//////
			for(int i=0;i<B.size();i++)
			{
				if(B.get(i)!=null)
				{
					B.get(i).Droite(alpha, gc);
				}
			}
			for(int i=0;i<B.size();i++)
			{
				if(B.get(i).getX()>gc.getWidth()+200)
				{
					B.remove(i);
					i--;
				}
			}
			
			
			
		//}

	}

}
