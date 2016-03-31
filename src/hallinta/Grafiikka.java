package hallinta;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**Luokka, jota hyödyntämällä voidaan hakea sovellukseen riittyvää grafiikkaa
 * @author Juho
 */
public class Grafiikka {
	
	//KUVIIN LIITTYVÄT
	private ArrayList<BufferedImage> kuvat;
	
	/**final int, joka liittyy arvoon 0*/
	public static final int TAUSTA = 0;
	
	/**final int, joka liittyy arvoon 1*/
	public static final int LOGO = 1;
	
	/**final int, joka liittyy arvoon 2*/
	public static final int NAPPI = 2;
	
	/**final int, joka liittyy arvoon 3*/
	public static final int PISTETAULULOGO = 3;
	
	/**final int, joka liittyy arvoon 4*/
	public static final int PIMENNYS = 4;
	
	/**final int, joka liittyy arvoon 5*/
	public static final int GUI = 5;
	
	/**final int, joka liittyy arvoon 6*/
	public static final int PAUSSI = 6;
	
	//FONTIT
	private ArrayList<Font> fontit;
	public static final int PERUSFONTTI = 0;
	public static final int JKINFONTTI = 1;
	
	//BASICSTROKE
	private ArrayList<BasicStroke> viivat;
	
	/** Lataa kuvat, fontit ja basicstroket koneen muistiin myöhempää käyttöa varten */
	public void alusta(){
		try{
			//Ladataan kuvat
			kuvat = new ArrayList<BufferedImage>();
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/Tausta.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/Splashscreenlogo.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/Nappi.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/PeliOhi.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/matafackinblackwidow.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/GUI.png")));
			kuvat.add(ImageIO.read(getClass().getResourceAsStream("/Paussi.png")));
			
			//Fontit
			fontit = new ArrayList<Font>();
			fontit.add(new Font("Comic Sans", Font.BOLD, 30));
			
			
			//BASICSTROKET
			viivat = new ArrayList<BasicStroke>();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void asetaFontti(int fontti, Graphics2D g){
		g.setFont(fontit.get(fontti));
	}
	
	/**Asettaa parametrina annettuun Graphics2D-luokan ilmentymäksi parametrina annetun int-tyyppisen arvoa vastaavan indeksin tiedot
	 * @param int viivan indeksi
	 * @param Graphics2D g
	 */
	public void asetaViiva(int viiva, Graphics2D g){
		g.setStroke(viivat.get(viiva));
	}
	
	/** Palauttaa BufferedImage-luokan ilmentymän ArrayListasta parametrina annetun integer tyyppisen indeksin mukaan
	 * @param int kuvan indeksi
	 * @return BufferedImage
	 */
	public BufferedImage annaKuva(int kuva){
		return kuvat.get(kuva);
	}
}
