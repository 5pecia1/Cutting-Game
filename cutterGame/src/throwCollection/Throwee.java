package throwCollection;

import cutterGame.CutterGame;

public abstract class Throwee {
	private final double firstVx = 0.2, firstVy = -0.6;
	private double x;
	private double y;
	
	private double Vx;//x가속도
	private double Vy;//y가속도
	
	private int diameter;
	private int score;
	
	public Throwee(int x, int y, int diameter, int score){
		this.setY(y);
		this.diameter = diameter;
		this.score = score;
		changeLocation();
	}
	
	public void changeLocation(){
		x = (int)(Math.random() * (CutterGame.GAMEWIDTH - diameter)); // 객체가 생겨날 위치는 0부터 (게임가로 - 지름) 사이
		
		if(x < CutterGame.GAMEWIDTH / 2){//왼쪽에서 생기면 오른쪽으로 움직임
			setVx(firstVx);
		}
		else {//오른쪽에서 생기면 왼쪽으로 움직임
			setVx(-firstVx);
		}
		setVy(firstVy);
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return Vx;
	}

	public void setVx(double vx) {
		Vx = vx;
	}

	public double getVy() {
		return Vy;
	}

	public void setVy(double vy) {
		Vy = vy;
	}

	public int getDiameter() {
		return diameter;
	}

	public int getScore() {
		return score;
	}
	
	
}
