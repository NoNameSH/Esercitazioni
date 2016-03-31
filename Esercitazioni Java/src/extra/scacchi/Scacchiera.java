package extra.scacchi;

import java.util.Scanner;

public class Scacchiera {
	private Pezzo[][] caselle;
	private Colore currentPlayer;
	private static final int SIZE = 8;
	private Scanner scanner = new Scanner(System.in);
	
	public Scacchiera(){
		caselle = new Pezzo[SIZE][SIZE];
		reinitialize();
	}
	
	public void turn(){
		int x,y;
		Casella c1, c2;
		System.out.println("Fai la tua mossa, giocatore" + this.currentPlayer);
		System.out.println("Muovi dalla casella di riga: ");
		x = scanner.nextInt();
		System.out.println("               e di colonna: ");
		y = scanner.nextInt();
		c1 = new Casella(x,y);
		System.out.println("Alla casella di riga: ");
		x = scanner.nextInt();
		System.out.println("        e di colonna: ");
		y = scanner.nextInt();
		c2 = new Casella(x,y);
		
		if(this.getPezzo(c1).mossaValida(this, c1, c2)){
			this.setPezzo(this.unsetPezzo(c1), c2);
			
			//cambia giocatore
			if(this.currentPlayer==Colore.BLACK)
				currentPlayer=Colore.WHITE;
			else
				currentPlayer=Colore.BLACK;
		}
		else{
			System.out.println("MOSSA NON VALIDA!");
		}
	}
	
	public void reinitialize(){
		this.currentPlayer=Colore.WHITE;
		//TODO: posizionare i pezzi
	}
	
	public void close(){
		scanner.close();
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
	
	public Colore getPlayerColor
	

}
