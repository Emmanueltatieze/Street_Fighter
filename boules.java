import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class boules {
	float x;
	float y;
	float v=400;
	Image img_b;
	private boolean lancer_boule=false;

	
	public boules(GameContainer gc,Joueur j) throws SlickException {
		this.x=j.getX()+j.getImg_j().getWidth();
		//this.img_j=new Image("");
		this.y=j.getY()+20;//this.img_j.getHeight();
		img_b=new Image("images/b_1.png");
		this.lancer_boule=true;
	}
	public void dessiner_boule(Graphics g)throws SlickException
	{
		if(this.lancer_boule)
		g.drawImage(img_b, x, y);
		
	}
	public void Droite(int alpha,GameContainer gc)
	{
		if(this.x+v*alpha/1000f<=gc.getWidth())
	      this.x+=v*alpha/1000f;
		else 
			this.lancer_boule=false;
	}
	public void Gauche(int alpha,GameContainer gc)
	{
		if(this.x-v*alpha/1000f>=0)
	      this.x-=v*alpha/1000f;
		else 
			this.lancer_boule=false;
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
	public Image getImg_b() {
		return img_b;
	}
	public void setImg_j(Image img_b) {
		this.img_b = img_b;
	}
	
	public boolean isLancer_boule() {
		return lancer_boule;
	}

	public void setLancer_boule(boolean lancer_boule) {
		this.lancer_boule = lancer_boule;
	}
	

}
