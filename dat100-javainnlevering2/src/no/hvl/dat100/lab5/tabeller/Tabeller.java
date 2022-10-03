package no.hvl.dat100.lab5.tabeller;

public class Tabeller {
	
	// a) en metode som skriver ut tabellen
	public static void skrivUt(int[] tabell) {
		System.out.print("[ ");
		for (int i = 0; i < tabell.length; i++) {
			System.out.print(tabell[i] + " ");
		}
		System.out.println("]");
	}

	
	// b)
	public static String tilStreng(int[] tabell) {
		String s = "[";
		for (int i = 0; i < tabell.length; i++) {
			if (i != tabell.length - 1) {
				s += tabell[i] + ",";
			} else {
				s += tabell[i];
			}
		}
		s += "]";
		System.out.println(s);
		return s;
	}
 
	// c) en metode som summerer elementene i tabellen ved bruk av for-loop, while-loop og utvidet for-loop (for-off loop)
	public static int summer(int[] tabell) {		
		// for-loop
		int sum = 0;
		for (int i = 0; i < tabell.length; i++) {
			sum += tabell[i];
		}
		System.out.println(sum);
		

		// while-loop
		int sum2 = 0, index = 0;

		while (index < tabell.length) {
			sum2 += tabell[index];
			index++;
		}
		System.out.println(sum2);


		// utvidet for-loop
		int sum3 = 0;
		for (int i : tabell) {
			sum3 += i;
		}
		System.out.println(sum3);
		
		
		return sum;
	}

	// d) en metode som skriver ut 'true' dersom tallet finnes i array'en, false ellers
	public static boolean finnesTall(int[] tabell, int tall) {
		boolean tallFunnet = false;
		int pos = 0;
		while (!tallFunnet && pos < tabell.length) {
			if (tall == tabell[pos]) {
				tallFunnet = true;
			}
			pos++;
		}
		return tallFunnet;
	}

	// e) en metode som finner indeks-posisjonen til tallene i array'en
	public static int posisjonTall(int[] tabell, int tall) {
		int teller = -1;
		for (int i = 0; i < tabell.length; i++) {
			if (tabell[i] == tall) {
				teller = i;
			}
		}
		return teller;
	}

	// f) en metode som begynner bakerst i tallrekken, også skriver ut elementene i en ny tabell
	public static int[] reverser(int[] tabell) {
		int[] nyTabell = new int[tabell.length];
		int j = 0;
		
		for (int i = tabell.length - 1; i >= 0; i--) {
			nyTabell[j] = tabell[i];
			j++;
		}
		return nyTabell;
	}

	// g) en metode som returnerer til true dersom listen er sortert, ellers false
	public static boolean erSortert(int[] tabell) {
		int i = 1;
		boolean sortert = true;
		
		while (sortert && i < tabell.length - 1) {
			if (tabell[i] > tabell[i + 1]) {
				sortert = false;
			}
			i++;
		}
		return sortert;
	}

	// h) en metode som setter sammen tabellene til én vha utvidet for-lokke
	public static int[] settSammen(int[] tabell1, int[] tabell2) {
		int lengde = tabell1.length + tabell2.length;
		int[] mergedTabell = new int[lengde];
		int pos = 0;

		for (int e : tabell1) {
			mergedTabell[pos] = e;
			pos++;
		}
		
		for (int e : tabell2) {
			mergedTabell[pos] = e;
			pos++;
		}
		return mergedTabell;
	}
	
	
/*
	h) annet forslag fra gruppen, gi gjerne tilbakemelding på begge
	public static int[] settSammen(int[] tabell1, int[] tabell2) {
			int[] nyTabell = new int[tabell1.length + tabell2.length];
			int j = 0;
	
			for (int i = 0; i < nyTabell.length; i++) {
				if (i < tabell1.length) {
					nyTabell[i] = tabell1[i];
				} else if (j < tabell2.length) {
					nyTabell[i] = tabell2[j];
				}
				j++;
			}
			return nyTabell;
		}
*/
}
