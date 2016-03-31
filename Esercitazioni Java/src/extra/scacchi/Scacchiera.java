package extra.scacchi;

public class Scacchiera {
	private Pezzo[][] caselle;
	private static final int SIZE = 8;
	
	public Scacchiera(){
		caselle = new Pezzo[SIZE][SIZE];		
	}
	
	public Pezzo getPezzo(Casella c){
		return caselle[c.getX()][c.getY()];
	}
	
	public Pezzo setPezzo(Pezzo p, Casella c){
		Pezzo ret = this.getPezzo(c);
		this.caselle[c.getX()][c.getY()]=p;
		return ret;
	}
	
	public Pezzo unsetPezzo(Casella c){
		Pezzo ret = this.getPezzo(c);
		this.setPezzo(null, c);
		return ret;
	}
	

}
