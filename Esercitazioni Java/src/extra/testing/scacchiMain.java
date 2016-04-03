package extra.testing;

import extra.scacchi.*;

public class scacchiMain {

	public static void main(String[] args) {
		Scacchiera s = new Scacchiera();
		try {
			while (true) {
				System.out.println(s.toString());
				s.turn();
			}
		} finally {
			System.out.println("Unexpected error! Closing...");
			s.close();
		}
	}

}