package extra.scacchi;

enum Colore {
	BLACK, WHITE;
}

public abstract class Pezzo {
	public static final String 
	W_KING_UTF8 = "\u2654",
	W_QUEEN_UTF8 = "\u2655",
	W_ROOK_UTF8 = "\u2656",
	W_BISHOP_UTF8 = "\u2657",
	W_KNIGHT_UTF8 = "\u2658",
	W_PAWN_UTF8 = "\u2659",
	B_KING_UTF8 = "\u265A",
	B_QUEEN_UTF8 = "\u265B",
	B_ROOK_UTF8 = "\u265C",
	B_BISHOP_UTF8 = "\u265D",
	B_KNIGHT_UTF8 = "\u265E",
	B_PAWN_UTF8 = "\u265F";
	
	private Colore color;
	
	public Pezzo(Colore color){
		this.color=color;
	}
	
	public Colore getColor(){
		return this.color;
	}
	
	public abstract boolean mossaValida(Scacchiera s, Casella c1, Casella c2);
	public abstract String toString();

}

class Pedone extends Pezzo{

	public Pedone(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if(this.getColor()==Colore.BLACK)
			return B_PAWN_UTF8;
		else
			return W_PAWN_UTF8;
	}
	
}

class Torre extends Pezzo {

	public Torre(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		//colore del pezzo da muovere e boundary scacchiera gi� controllati
		
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
		if(this.getColor()==Colore.BLACK)
			return B_ROOK_UTF8;
		else
			return W_ROOK_UTF8;
	}
	
}

class Alfiere extends Pezzo {

	public Alfiere(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if(this.getColor()==Colore.BLACK)
			return B_BISHOP_UTF8;
		else
			return W_BISHOP_UTF8;
	}
	
}

class Cavallo extends Pezzo {

	public Cavallo(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if(this.getColor()==Colore.BLACK)
			return B_KNIGHT_UTF8;
		else
			return W_KNIGHT_UTF8;
	}
	
}

class Re extends Pezzo {

	public Re(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if(this.getColor()==Colore.BLACK)
			return B_KING_UTF8;
		else
			return W_KING_UTF8;
	}
	
}

class Regina extends Pezzo {

	public Regina(Colore color) {
		super(color);
	}

	@Override
	public boolean mossaValida(Scacchiera s, Casella c1, Casella c2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if(this.getColor()==Colore.BLACK)
			return B_QUEEN_UTF8;
		else
			return W_QUEEN_UTF8;
	}
	
}