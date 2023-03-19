import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main_jeu {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		Main_KOF J =new Main_KOF("KOF");
		   AppGameContainer app = new AppGameContainer(J);
		  //app.setFullscreen(true);
		   app.setDisplayMode(800, 600, false);
		   app.setShowFPS(false);
		   app.start();
		   
		   
	}

}
