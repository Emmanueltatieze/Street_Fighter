import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Joueur {
	private float x;
	private float y;
	private float v=300;
	Image img_j;
	
	Image[] imj=new Image[3];
	Image[] imj_fat=new Image[3];
	Image[] imj_saut=new Image[5];
	Image[] imj_pied=new Image[3];
	
	Image imj_bas=new Image("images/ryu_bas.png");
	Image imj_poing=new Image("images/ryu_poing.png");
	Image imj_poing_sol=new Image("images/ryu_poing_sol.png");


	
	public boolean isPoing_sol() {
		return poing_sol;
	}

	public void setPoing_sol(boolean poing_sol) {
		this.poing_sol = poing_sol;
	}

	private int pos=0;
	private int pos_fat=0;
	private int pos_saut=2;
	private int pos_pied=0;
	private int sens=1;
	private int compt=0;
	
	private boolean bas=false;
	private boolean poing=false;
	private boolean poing_sol=false;
	private boolean pied=false;
	private boolean pied_sol=false;
	
	private boolean finSaut=false;
	private boolean fat=false;
	private boolean norm=true;
	private boolean saut=false;
	private boolean send_boule=false;
	private int temps_attente_apres_poing=0;
	private int temps_attente_apres_pied=0;
	public boolean isBas() {
		return bas;
	}

	public void setBas(boolean bas) {
		this.bas = bas;
	}

	public boolean isPoing() {
		return poing;
	}

	public void setPoing(boolean poing) {
		this.poing = poing;
	}

	public int getTemps_attente_apres_poing() {
		return temps_attente_apres_poing;
	}

	public void setTemps_attente_apres_poing(int temps_attente_apres_poing) {
		this.temps_attente_apres_poing = temps_attente_apres_poing;
	}

	int temps_attente_apres_tir=0;
	int limite_fond=300;
	private boolean en_saut=false;
	

	

	

	public Joueur(GameContainer gc) throws SlickException {
		
		this.x=20;
		this.img_j=new Image("images/ryu_0.png");
		//img_j=img_j.getFlippedCopy(true, false);
		this.y=gc.getHeight()-limite_fond;//this.img_j.getHeight();
		imj[0]=new Image("images/ryu_0.png");
		imj[1]=new Image("images/ryu_1.png");
		
		//imj[0]=imj[0].getFlippedCopy(true, false);
		//imj[1]=imj[1].getFlippedCopy(true, false);
		
		
		
		imj_fat[0]=new Image("images/ryu_fat_0.png");
		imj_fat[1]=new Image("images/ryu_fat_1.png");
		imj_fat[2]=new Image("images/ryu_fat_2.png");
		
		imj_saut[0]=new Image("images/ryu_saut_0.png");
		imj_saut[1]=new Image("images/ryu_saut_1.png");
		imj_saut[2]=new Image("images/ryu_saut_2.png");
		imj_saut[3]=new Image("images/ryu_saut_3.png");
		imj_saut[4]=new Image("images/ryu_saut_4.png");
		
		imj_pied[0]=new Image("images/ryu_pied_0.png");
		imj_pied[1]=new Image("images/ryu_pied_1.png");
		imj_pied[2]=new Image("images/ryu_pied_sol.png");
 //imj[2]=new Image("images/ryu_1.png");
		pos=0;
	}
	
	public boolean isPied() {
		return pied;
	}

	public void setPied(boolean pied) {
		this.pied = pied;
	}

	public void choixImg(GameContainer gc,Input inp)
	{
		int c=pos;
		int c_fat=pos_fat;
		int c_saut=pos_saut;
		int c_pied=pos_pied;
		
		
		if(norm)
		{
			this.y=gc.getHeight()-limite_fond;
			bas=false;
			this.img_j=imj[pos];
			if(c==0)
			pos=1;
		if(c==1)
			pos=0;
		
		}
		 if(fat&&!poing&&!pied)
		{
			this.img_j=imj_fat[pos_fat];
			if(c_fat==0)
				pos_fat=1;
			if(c_fat==1)
				pos_fat=2;
			if(c_fat==2)
			{
				if(temps_attente_apres_tir==0)
				send_boule=true;
				 if(temps_attente_apres_tir>1)
				  {
					 temps_attente_apres_tir=0;
				    pos_fat=0;
					fat=false;
					norm=true;
					send_boule=false;
					
				 }
				
			}
			
				
		}
		 
		 if(inp.isKeyDown(Input.KEY_DOWN)&&!en_saut)
		 {
			 
			 this.y=gc.getHeight()-limite_fond+(this.imj[0].getHeight()-this.imj_bas.getHeight());
			 this.img_j=imj_bas;
			 bas=true;

		 
		 }
		 
			 
		 
		 if(poing)
		 {
			 
			 this.img_j=imj_poing;
			 //norm=false;
			if(temps_attente_apres_poing>100)
			  {
				this.img_j=imj[0];
				 temps_attente_apres_poing=0;
				 poing=false;
				// norm=true;
				 
			  }
		 }
		 if(poing_sol)
		 {
			 this.y=gc.getHeight()-limite_fond+(this.imj[0].getHeight()-this.imj_poing_sol.getHeight());
			 this.img_j=imj_poing_sol;
			 //norm=false;
			if(temps_attente_apres_poing>100)
			  {
				this.img_j=imj[0];
				 temps_attente_apres_poing=0;
				 poing_sol=false;
				 norm=true;
				 
			  }
		 }
		 if(pied)
			{
			 
				this.img_j=imj_pied[pos_pied];
				if(c_pied==0)
					pos_pied=1;
				if(c_pied==1)
				{
					this.img_j=imj_pied[pos_pied];
					//if(temps_attente_apres_pied==0)
					//send_boule=true;
					 //this.img_j=imj_pied[0];
					 if(temps_attente_apres_pied>1)
					  {
						 //this.img_j=imj_pied[0];
						
						 temps_attente_apres_pied=0;
					    pos_pied=0;
						pied=false;
						norm=true;
						//send_boule=false;
						
					 }
					
				}	
			}
		 if(pied_sol)
		 {
			 this.y=gc.getHeight()-limite_fond+(this.imj[0].getHeight()-this.imj_pied[2].getHeight());
			 this.img_j=imj_pied[2];
			 if(temps_attente_apres_pied>300)
			  {
				 //this.img_j=imj_pied[0];
				
				 temps_attente_apres_pied=0;
				pied_sol=false;
				//pied=false;
				norm=true;
				//send_boule=false;
				
			 }
			 
		 }
		
		 
		 if(en_saut)
		{
	    	
			this.img_j=imj_saut[pos_saut];
//			if(c_saut==0)
//				pos_saut=1;
//			if(c_saut==1)
//				pos_saut=2;
//			if(c_saut==2)
//				pos_saut=3;
//			if(c_saut==3)
//				pos_saut=4;
			if(c_saut==0)
			{
				
				if(this.y>=gc.getHeight()-limite_fond+45)
				{
					this.img_j=imj[1];
					pos_saut=0;
					en_saut=false;
					norm=true;
				}
			}
				

			
		}
		
		
	}
	

	public boolean isPied_sol() {
		return pied_sol;
	}

	public void setPied_sol(boolean pied_sol) {
		this.pied_sol = pied_sol;
	}

	public int getTemps_attente_apres_pied() {
		return temps_attente_apres_pied;
	}

	public void setTemps_attente_apres_pied(int temps_attente_apres_pied) {
		this.temps_attente_apres_pied = temps_attente_apres_pied;
	}

	public void dessiner_joueur(Graphics g)throws SlickException
	{
		//img_j=img_j.getFlippedCopy(true, false);
		g.drawImage(this.img_j, this.x, this.y, null);
	}
	
	
	public void Droite(int alpha,GameContainer gc)
	{
		if(this.x+v*alpha/1000f+this.img_j.getWidth()<=gc.getWidth()&&!bas)
	      this.x+=v*alpha/1000f;
	}
	public void Gauche(int alpha,GameContainer gc)
	{
		if(this.x-v*alpha/1000f>=0&&!bas)
	      this.x-=v*alpha/1000f;
	}
	public void Haut(int alpha,GameContainer gc)
	{
	
		double hauteur_max=(1.2)*img_j.getHeight();
		if(finSaut)
		{
			pos_saut=0;
			this.y+=(v+350)*alpha/1000f;
			if(this.y>=gc.getHeight()-limite_fond)
			{
				this.y=gc.getHeight()-limite_fond;
				finSaut=false;
				en_saut=false;
				norm=true;
			}
		}
		else
		{
			pos_saut=3;
			this.y-=(v+300)*alpha/1000f;
			if(this.y<=gc.getHeight()-limite_fond-hauteur_max)
			{
				finSaut=true;
			}
			
			
		}
			
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isFat() {
		return fat;
	}

	public void setFat(boolean fat) {
		this.fat = fat;
	}
	public boolean isNorm() {
		return norm;
	}

	public void setNorm(boolean norm) {
		this.norm = norm;
	}

	public boolean isEn_saut() {
		return en_saut;
	}

	public void setEn_saut(boolean en_saut) {
		this.en_saut = en_saut;
	}
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getV() {
		return v;
	}
	public void setV(float v) {
		this.v = v;
	}
	public Image getImg_j() {
		return img_j;
	}
	public void setImg_j(Image img_j) {
		this.img_j = img_j;
	}
	
	public boolean isSend_boule() {
		return send_boule;
	}

	public void setSend_boule(boolean send_boule) {
		this.send_boule = send_boule;
	}
	
	public int getTemps_attente_apres_tir() {
		return temps_attente_apres_tir;
	}

	public void setTemps_attente_apres_tir(int temps_attente_apres_tir) {
		this.temps_attente_apres_tir = temps_attente_apres_tir;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getPos_fat() {
		return pos_fat;
	}

	public void setPos_fat(int pos_fat) {
		this.pos_fat = pos_fat;
	}	
	

}
