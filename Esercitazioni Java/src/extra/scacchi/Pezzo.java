package extra.scacchi;

enum Colore {
	BLACK, WHITE;
}

abstract class Pezzo {
	private Colore color;
	
	public Colore getColor(){
		return this.color;
	}
	
	public abstract boolean mossaValida(Scacchiera s, Casella c1, Casella c2);
	public abstract String toString();
}


