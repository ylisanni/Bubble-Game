package hallinta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Luokka, jonka avulla hallinnoidaan pisteitä pelissä
 * Kykenee tulostamaan pisteet, muokkaamaan ja vertaamaan niitä
 * @author Juho
 *
 */
public class Pisteet {
	
	private ArrayList<String> pelaajat;
	private ArrayList<Integer> pisteet;
	private static final String JAKAJA = "#";
	
	private String nimi;
	
	/**
	 * Metodi jonka avulla alustetaan tarvittavat asiat
	 */
	public void alusta(){
		try{
			pelaajat = new ArrayList<String>();
			pisteet = new ArrayList<Integer>();
			String rivi;
			BufferedReader lukija = new BufferedReader(new FileReader("res/pistetaulu.txt"));
			while((rivi = lukija.readLine()) != null){
				String[] tmp = rivi.split(JAKAJA);
				pelaajat.add(tmp[0].toString());
				pisteet.add(Integer.parseInt(tmp[1]));
			}
			lukija.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Palauttaa parametrina annettua int tyyppistä lukua vastaavan indeksin String-tyyppisen arvon
	 * @param i int tyyppinen
	 * @return String tyyppinen arvo
	 */
	public String annaNimi(int i){
		return pelaajat.get(i);
	}
	
	/** Antaa parametrina annettua indeksiä vastaavan int tyyppisen arvon
	 * @param i int
	 * @return int
	 */
	public int annaPisteet(int i){
		return pisteet.get(i);
	}
	
	/**
	 * Tarkistaa onko parametrina annettu int tyyppinen arvo jollain kohdalla pistetaulussa
	 * @param piste
	 */
	public void onkoPistesijalla(int piste){
		System.out.println("Pisteet syötetty....");
		if(piste > pisteet.get(0)){
			
			System.out.println("WAZA CHAMP!");
			//PYYDETÄÄN PELAAJAN NIMI JA LISÄTÄÄN EKAKSI
			//POISTETAAN VIIMEISIN
		}else if(piste > pisteet.get(pisteet.size() - 1)){
			int sijainti = 9;
			int i = 8;
			System.out.println("Pelaajan pisteet ovat tarpeeksi korkeat pistetalulle\nAloitetaan sijoituksen etsiminen");
			while(i > 0){
				if(piste < pisteet.get(i)){
					sijainti = i;
					System.out.println("Sijainti löytynyt: " + sijainti);
					break;
				}else{
					System.out.println("Ei väkkisin");
					i--;
				}
			}
		}
	}
	
	/**
	 * Kysyy nimeä
	 * TODO
	 * @return
	 */
	public String kysyNimi(){		
		
		return nimi;
	}
	
	/**Perrus piirto metodi
	 * */
	public void piirra(Graphics2D g, int x, int y){
		g.setFont(new Font("Comic Sans", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		for(int i = 0; i < pisteet.size(); i++){
			g.drawString((i+1)+")",x, y + 32*i); 
			g.drawString(pelaajat.get(i) , x +70, y + 32*i);
			g.drawString( Integer.toString(pisteet.get(i)) , x + 220, y + 32*i);
		}
	}
	
}
