package cutterGame;

public abstract class Throwee {
	/*
	 * test code
	 * ������ ������ �����.
	 * */
	public double x;
	public double y;
	
	public double Vx;
	public double Vy;
	
	public Throwee(int x, int y){
		this.x = x;
		this.y = y;
		Vx = 0.2;
		Vy = 0;
	}
	
	
}
