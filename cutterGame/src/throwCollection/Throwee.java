package throwCollection;

public abstract class Throwee {
	private final double firstVx = 0.2, firstVy = 0.8;
	private double x;
	private double y;
	
	private double Vx;//x가속도
	private double Vy;//y가속도
	
	private int diameter;
	
	public Throwee(int x, int y, int diameter){
		this.setX(x);
		this.setY(y);
		this.diameter = diameter;
		
		changeLocation();
	}
	
	public void changeLocation(){
		int width = (int)x;
		x = (int)(Math.random() * (width - diameter)); // 객체가 생겨날 위치는 0부터 (게임가로 - 지름) 사이
		y = y;//객체가 생겨날 위치는 게임 하단 안보이는 부분부터.
		
		if(x < width / 2){//왼쪽에서 생기면 오른쪽으로 움직임
			setVx(firstVx);
		}
		else {//오른쪽에서 생기면 왼쪽으로 움직임
			setVx(-firstVx);
		}
		setVy(-firstVy);
		
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
	
	
}
