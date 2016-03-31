package hallinta;

/**
 * Staattinen luokka, jonka avulla voidaan saada tietoa näppäimistön tapahtumista
 * TODO-luokka, voi jäädä implementoimatta
 * @author Juho
 *
 */
public class Nappaimisto {

	private static int painettuNappi;
	private static int viimeeksiPainettu;
	
	private static char merkki;
	
	public static void paivita(){
		viimeeksiPainettu = painettuNappi;
		painettuNappi = -1;
	}
	
	public static int annaPainettu(){
		return painettuNappi;
	}
	
	public static void asetaPainettu(int x){
		painettuNappi = x;
	}
	
	public static void asetaMerkki(char m){
		merkki = m;
	}
	
	public static char annaMerkki(){
		char merkki = 0;
		
		return merkki;
	}
	
	//KÄSITELLÄÄN NÄPPÄIMISTÖN TAPAHTUMAT
	
}
