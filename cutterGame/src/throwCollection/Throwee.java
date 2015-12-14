package throwCollection;

public abstract class Throwee {
	private double x;
	private double y;
	
	private double Vx;
	private double Vy;
	
	private int diameter;
	
	public Throwee(int x, int y, int diameter){
		this.setX(x);
		this.setY(y);
		this.diameter = diameter;
		
		setVx(0.2);
		setVy(0);
	}
	
	public void changeLocation(){
		x = (int)(Math.random() * (x - diameter)); // 객체가 생겨날 위치는 0부터 (게임가로 - 지름) 사이
		y = y - diameter;//객체가 생겨날 위치는 게임 하단 안보이는 부분부터.
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
