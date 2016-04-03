package extra.scacchi;

import java.util.Scanner;

//Needs UTF-8 encoding to print

public class Scacchiera {
	private Pezzo[][] caselle;
	private Colore currentPlayer;
	private int turn_counter;
	private static final int SIZE = 8;
	private Scanner scanner = new Scanner(System.in);
	
	public Scacchiera(){
		caselle = new Pezzo[SIZE][SIZE];
		reinitialize();
	}
	
	public int getTurnCounter(){
		return this.turn_counter;
	}
	
	public void turn(){
		int x,y;
		Casella c1, c2;
		Pezzo p;
		try {
			System.out.println("TURNO " + turn_counter);
			System.out.println("Fai la tua mossa, giocatore " + this.currentPlayer);
			System.out.println("Muovi dalla casella di posizione X1: ");
			x = scanner.nextInt();
			System.out.println("               e di posizione Y1: ");
			y = scanner.nextInt();
			c1 = new Casella(x-1, y-1);
			System.out.println
			("Alla casella di posizione X2: ");
			x = scanner.nextInt();
			System.out.println("        e di posizione Y2: ");
			y = scanner.nextInt();
			c2 = new Casella(x-1, y-1);			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		p = this.getPezzo(c1);
		if(p!=null && p.getColor()==currentPlayer && p.mossaValida(this, c1, c2)){
			this.setPezzo(this.unsetPezzo(c1), c2);
			
			//avanza turno
			this.turn_counter++;
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
		this.turn_counter=1;
		this.currentPlayer=Colore.WHITE;
		
		caselle[0][0] = new Torre(Colore.BLACK);
		caselle[1][0] = new Cavallo(Colore.BLACK);
		caselle[2][0] = new Alfiere(Colore.BLACK);
		caselle[3][0] = new Regina(Colore.BLACK);
		caselle[4][0] = new Re(Colore.BLACK);
		caselle[5][0] = new Alfiere(Colore.BLACK);
		caselle[6][0] = new Cavallo(Colore.BLACK);
		caselle[7][0] = new Torre(Colore.BLACK);

		for (int i = 0; i < SIZE; i++)
			caselle[i][1] = new Pedone(Colore.BLACK);
		for (int i = 0; i < SIZE; i++)
			caselle[i][6] = new Pedone(Colore.WHITE);

		caselle[0][7] = new Torre(Colore.WHITE);
		caselle[1][7] = new Cavallo(Colore.WHITE);
		caselle[2][7] = new Alfiere(Colore.WHITE);
		caselle[3][7] = new Regina(Colore.WHITE);
		caselle[4][7] = new Re(Colore.WHITE);
		caselle[5][7] = new Alfiere(Colore.WHITE);
		caselle[6][7] = new Cavallo(Colore.WHITE);
		caselle[7][7] = new Torre(Colore.WHITE);
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
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				p=caselle[x][y];
				if(p!=null)
					buffer.append(p.toString());
				else
					buffer.append('_');
				buffer.append("("+(x+1)+","+(y+1)+")");
			}
			buffer.append('\n');
		}	
		return buffer.toString();
	}

}
