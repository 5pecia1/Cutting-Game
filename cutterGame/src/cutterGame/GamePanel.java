package cutterGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel implements MouseListener{

	public GamePanel() {
		radnomThrow();//thread를 써야 할 듯
	}
	
	private void radnomThrow(){
		//랜덤하게 스로우를 만든다
	}
	
	private  void cutObject(Throwee throwee){
		if(throwee instanceof Boom){
			
		}
		else if(throwee instanceof CuttableObject){
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
