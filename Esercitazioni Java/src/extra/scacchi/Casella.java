package extra.scacchi;

public class Casella {
	
	private final int x,y;

	public Casella(int x, int y) {
		if(x<1 || x>8 || y<1 || y>8)
			throw new IllegalArgumentException("Numero di riga e colonna deve essere compreso tra 1 e 8!");
		this.x = x-1;
		this.y = y-1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
