package extra.scacchi;

enum Colore {
	BLACK, WHITE;
	
	String symbol(){
		if(this==Colore.BLACK)
			return "b";
		else
			return "w";
	}
}

abstract class Pezzo {
	private Colore color;
	
	public Colore getColor(){
		return this.color;
	}
	
	public abstract boolean mossaValida(Scacchiera s, Casella c1, Casella c2);
	public abstract String toString();
	

}

class Pedone extends Pezzo{

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

class Torre extends Pezzo {

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		//colore del pezzo da muovere e boundary scacchiera già controllati
		
		//controllo casella di arrivo: ok se vuota o con pezzo di colore diverso;
		if(s.getPezzo(c2)!=null && s.getPezzo(c2).getColor()==this.getColor())
			return false;
		
		//controllo mossa: muove solo in orizzontale o in verticale
		int dx = c1.getX()-c2.getX();
		int dy = c1.getY()-c2.getY();
		
		if(dx==0 && dy==0)
			return false;
		if(Math.abs(dx)>0 && Math.abs(dy)>0)
			return false;
		
		//controllo percorso libero
		if(dy==0)
			for (int i = 1; i < dx-1; i++) {
				if(s.getPezzo(new Casella(c1.getX()+i,c1.getY()))!=null)
					return false;
			}
		else//dx==0
			for (int i = 1; i < dy-1; i++) {
				if(s.getPezzo(new Casella(c1.getX(),c1.getY()+i))!=null)
					return false;
			}
		
		//mossa corretta
		return true;
		
	}

	@Override
	public String toString() {
		return "T"+this.getColor().symbol();
	}
	
}

class Alfiere extends Pezzo {

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

class Cavallo extends Pezzo {

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

class Re extends Pezzo {

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

class Regina extends Pezzo {

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