package throwCollection;

public abstract class Throwee {
	private final double firstVx = 0.2, firstVy = 0.8;
	private double x;
	private double y;
	
	private double Vx;//x���ӵ�
	private double Vy;//y���ӵ�
	
	private int diameter;
	
	public Throwee(int x, int y, int diameter){
		this.setX(x);
		this.setY(y);
		this.diameter = diameter;
		
		changeLocation();
	}
	
	public void changeLocation(){
		int width = (int)x;
		x = (int)(Math.random() * (width - diameter)); // ��ü�� ���ܳ� ��ġ�� 0���� (���Ӱ��� - ����) ����
		y = y;//��ü�� ���ܳ� ��ġ�� ���� �ϴ� �Ⱥ��̴� �κк���.
		
		if(x < width / 2){//���ʿ��� ����� ���������� ������
			setVx(firstVx);
		}
		else {//�����ʿ��� ����� �������� ������
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
