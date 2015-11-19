package cutterGame;

public abstract class Throwee {
	/*
	 * test code
	 * 마지막 주차에 만든다.
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
