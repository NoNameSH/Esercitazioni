package extra.scacchi;

import java.util.Scanner;

//Needs UTF-8 encoding to print

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
		int riga,colonna;
		Casella c1, c2;
		Pezzo p;
		try {
			System.out.println("Fai la tua mossa, giocatore " + this.currentPlayer);
			System.out.println("Muovi dalla casella di riga: ");
			riga = scanner.nextInt();
			System.out.println("               e di colonna: ");
			colonna = scanner.nextInt();
			c1 = new Casella(riga-1,colonna-1);
			System.out.println("Alla casella di riga: ");
			riga = scanner.nextInt();
			System.out.println("        e di colonna: ");
			colonna = scanner.nextInt();
			c2 = new Casella(riga-1,colonna-1);			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		p = this.getPezzo(c1);
		if(p!=null && p.getColor()==currentPlayer && p.mossaValida(this, c1, c2)){
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
		//just for testing
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(j%2==0)//colonne bianche e nere alternate
					caselle[i][j] = new Torre(Colore.WHITE);
				else
					caselle[i][j] = new Torre(Colore.BLACK);
					
			}
		}	
	}
	
	public Colore getPlayerColor(){
		return this.currentPlayer;
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
	
	@Override
	public String toString(){
		//TODO: simply for quick test purpose. 
		StringBuffer buffer = new StringBuffer();
		Pezzo p;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				p=caselle[i][j];
				if(p!=null)
					buffer.append(p.toString());
				else
					buffer.append('_');
				buffer.append("("+(i+1)+","+(j+1)+")");
			}
			buffer.append('\n');
		}	
		return buffer.toString();
	}

}
