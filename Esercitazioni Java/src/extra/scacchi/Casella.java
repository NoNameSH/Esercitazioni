package extra.scacchi;

public class Casella {
	
	private final int x,y;

	public Casella(int x, int y) {
		if(x<0 || x>7 || y<0 || y>7)
			throw new IllegalArgumentException("Numero di riga e colonna deve essere compreso tra 1 e 8!");
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
