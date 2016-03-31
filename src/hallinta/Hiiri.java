package hallinta;

/**
 * Staattinen luokka, josta voidaan saada luokan tietoja missä tahansa luokassa
 * @author Juho
 *
 */
/**
 * @author Juho
 *
 */
public class Hiiri {

	
	private static int x;
	private static int y;
	
	/**Hiiren tapahtuma*/
	public static int tapahtuma;
	/**Hiiren aiempi tapahtuma*/
	public static int vanhaTapahtuma;
	
	/**Asettaa {@link #tapahtuma}-muuttujan arvo muuttujan {@link #vanhaTapahtuma} arvoksi*/
	public static void paivita(){
		vanhaTapahtuma = tapahtuma;
	}
	
	public static void asetaSijainti(int x, int y){
		Hiiri.x = x;
		Hiiri.y = y;
	}
	
	/** Palauttaa hiiren x-koordinaatin
	 * @return int
	 */
	public static int annaX(){
		return x;
	}
	
	/** Palauttaa hiiren y-koordinaatin
	 * @return int
	 */
	public static int annaY(){
		return y;
	}

	
	/** Asettaa parametrina annetun int-tyyppisen arvon muuttujan {@link #tapahtuma} arvoksi
	 * @param tapahtuma
	 */
	public static void asetaTapahtuma(int tapahtuma){
		Hiiri.tapahtuma = tapahtuma;
	}
		
}