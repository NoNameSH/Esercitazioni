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
		//colore del pezzo da muovere e boundary scacchiera gi� controllati
		
		//raccolta dati per analisi
		int dx = c2.getX()-c1.getX();
		int dy = c2.getY()-c1.getY();
		
		if(this.getColor()==Colore.WHITE)//oss:può sembrare il contrario ma è perchè le righe sono numerate dall'alto verso il basso
			dy = -dy; //in questo modo l'analisi è la stessa di quella delle mosse di un pedone bianco
		
		//controllo mossa	
		
		if(dx==0){
			//spostamento senza mangiare
			if(c1.getY()==1 || c1.getY()==6){
				//il pedone può fare due salti se si trova nella posizione originale
				//per verificarlo basta la riga in quanto il pedone non può tornare indietro e quando raggiunge la riga dei pedoni avversari andare avanti di 2 non è comunque consentito (out of boundary)
				if(dy==1||dy==2)
					return true;//non serve verificare se il percorso è libero
				else
					return false;
			}
			else/*non è il primo spostamento*/ if(dy==1 && s.getPezzo(c2)==null)
				return true;
			else
				return false;
		}
		if(Math.abs(dx)==1){
			//spostamento per mangiare
			if(dy==1 && s.getPezzo(c2)!=null && s.getPezzo(c2).getColor()!=this.getColor())
				return true;
		}
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
		int dx = c2.getX()-c1.getX();
		int dy = c2.getY()-c1.getY();
					
		if(dx==0 && dy==0)
			return false;
		if(Math.abs(dx)>0 && Math.abs(dy)>0)
			return false;
		
		//controllo percorso libero
		int verso_x=1, verso_y=1;
		if(dx<0)
			verso_x=-1;
		if(dy<0)
			verso_y=-1;
		
		
		if(dy==0)//muove in orizzontale
			for (int i = 1; i < Math.abs(dx); i++) {
				if(s.getPezzo(new Casella(c1.getX()+verso_x*i,c1.getY()))!=null)
					return false;
			}
		else//dx==0, muove in verticale
			for (int i = 1; i < Math.abs(dy); i++) {
				if(s.getPezzo(new Casella(c1.getX(),c1.getY()+verso_y*i))!=null)
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
		//colore del pezzo da muovere e boundary scacchiera gi� controllati
		
		//controllo casella di arrivo: ok se vuota o con pezzo di colore diverso;
		if(s.getPezzo(c2)!=null && s.getPezzo(c2).getColor()==this.getColor())
			return false;
				
		//controllo mossa: muove solo in diagonale
		int dx = c2.getX()-c1.getX();
		int dy = c2.getY()-c1.getY();
						
		if(Math.abs(dx)!=Math.abs(dy)){
			System.out.println("Mossa non diagonale");
			return false;
		}
		
		//controllo strada libera
		int min, i;
		if (c1.getX()>c1.getY()) //valuta se la pedina è più vicina al bordo sinistro o a quello superiore
			min = c1.getY(); //è più vicina al superiore
		else min = c1.getX(); //è più vicina al sinistro oppure è sulla diagonale
		if (dx * dy > 0){//spostamenti concordi su traiettoria = \
			if (dx > 0){//verso destra/giù
				for (i=1;i<=min;i++)
					if(s.getPezzo(new Casella(c1.getX()+i,c1.getY()+i))!=null)
						return false;
			}
			else // verso sinistra/su
				for (i=1;i<=min;i++)
					if(s.getPezzo(new Casella(c1.getX()-i,c1.getY()-i))!=null)
						return false;
		}
		else{//traiettoria = /
			if (dx > 0){//verso destra/su
				for (i=1;i<=min;i++)
					if(s.getPezzo(new Casella(c1.getX()+i,c1.getY()-i))!=null)
						return false;
				;
			}
			else //verso sinistra/giù
				for (i=1;i<=min;i++)
					if(s.getPezzo(new Casella(c1.getX()-i,c1.getY()+i))!=null)
						return false;
		}
		
		
		//mossa corretta
		return true;
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