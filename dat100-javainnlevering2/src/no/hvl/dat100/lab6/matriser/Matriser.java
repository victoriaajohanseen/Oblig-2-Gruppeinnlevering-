package no.hvl.dat100.lab6.matriser;

public class Matriser {

	// a) en metode som skriver ut matrisen
	public static void skrivUt(int[][] matrise) {
		System.out.println("- - -");
		for (int[] x : matrise) {
			for (int i : x) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println("- - -");
	}

	// b) 
	public static String tilStreng(int[][] matrise) {
		String s = "";

		for (int x = 0; x < matrise.length; x++) {
			for (int y = 0; y < matrise[0].length; y++) {
				s += matrise[x][y] + " ";
			}
			s += "\n";
		}
		return s;
	}

	// c) en metode som multipliserer matrise med tall
	public static int[][] skaler(int tall, int[][] matrise) {
		int[][] nyMatrise = new int[matrise.length][];
		for (int x = 0; x < matrise.length; x++) {
			nyMatrise[x] = new int[matrise[x].length];
			for (int y = 0; y < matrise[x].length; y++) {
				nyMatrise[x][y] = matrise[x][y] * tall;
			}
		}
		return nyMatrise;
	}
	
	

	// d) en metode som returnerer true dersom matrisen er lik, false ellers
	// her har vi i gruppa kommet fram til flere løsningsforslager

	public static boolean erLik(int[][] a, int[][] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	 
/*  d)
	public static boolean erLik(int[][] a, int[][] b) {
		boolean isSame = false; 
		int i = 0;

		if (a.length == b.length) {
			isSame = true;

			while (isSame && i < a.length) {
				if (a[i] != b[i]) {
					isSame = false;
				} 
				i++;
			}		
		}
		return isSame;
	}

	

	d)
	public static boolean erLik(int[][] a, int[][] b) {
		for (int x = 0; x < a.length - 1; x++) {
			for (int y = 0; y < a[x].length - 1; y++) {
				if(a[x][y] != b[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
*/
	
}

