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
	
	class Torre extends Pezzo {

		@Override
		public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}


