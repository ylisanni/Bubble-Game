package tilat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entiteetit.Kirjaimet;
import entiteetit.Pelaaja;
import entiteetit.PeliObjektit;
import hallinta.Grafiikka;
import hallinta.Tarkistin;
import main.IkkunaPaneeli;

/**Kokonaan BufferedImage pohjainen n�kym�
 * @author Ruumis
 */
public class Paavalikko extends Pelitila{

	
	//Attribuutteja
	private BufferedImage tausta;
	private BufferedImage kuvio;
	private BufferedImage nappi;
	private BufferedImage nappivalittu;
	private BufferedImage black;
	private Pelaaja pelaaja;
	
	private Tarkistin tarkistin;
	
	//Pit�� sis�ll��n Kirjaimet-luokan ilmentymi�
	private ArrayList<PeliObjektit> kirjaimet;
	
	//Lista josta luodaan kuplat
	private String[] pelinnimi = {
		"K", "U", "P", "L", "A", "P", "E", "L", "I"
	};
	
	//
	private String[] tilavaihtoehdot = {
			"A L O I T A   P E L I",
			"P I S T E T A U L U",
			"P O I S T U   P E L I S T �"
	};
	
	public Paavalikko() {
		alusta();
	}
	
	@Override
	public void alusta() {
		//Koitetaan ladata kaikki kuvat
		try{
			kirjaimet = new ArrayList<PeliObjektit>();
			for (int i = 0; i < pelinnimi.length; i++) {
				Kirjaimet tmp = new Kirjaimet(pelinnimi[i], 0.98, 4, 400 + (40 * i), 450,30);
				kirjaimet.add(tmp);
			}
			tausta = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.TAUSTA);
			kuvio = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.LOGO);
			nappi = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.NAPPI);
			black =  IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.PIMENNYS);
			pelaaja = new Pelaaja();
			tarkistin = new Tarkistin(kirjaimet, pelaaja);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paivita() {
		kasitteleSyote();
		pelaaja.paivita();
	
		for (int i = 0; i < kirjaimet.size(); i++) {
			kirjaimet.get(i).paivita();
		}
	}

	@Override
	public void piirra(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawImage(tausta,0,0,null);
		g.drawImage(kuvio,0,0,null);
		for (int i = 0; i < kirjaimet.size(); i++) {
			kirjaimet.get(i).piirra(g);
		}
		//Piirret��n napit
		//Lis�t��n joskus napit tekstill�
		for (int i = 0; i < 3; i++) {
			//Luodaan if-lause joka tarkistaa onko hiiri napin p��ll�, jos on nii ulkon�k�� muutetaan ja mahdollisesti toistetaan jokin ��ni
			g.drawImage(nappi, 100 + ( 300 * i), 600,null);	
			g.drawString(tilavaihtoehdot[i], 120 + ( 300 * i), 670);
		}		
		pelaaja.piirra(g);
		//g.drawImage(black,IkkunaPaneeli.hiirix - IkkunaPaneeli.LEVEYS,IkkunaPaneeli.hiiriy - IkkunaPaneeli.KORKEUS,null);
		
	}

	@Override
	public void kasitteleSyote() {
		//Tarkistetaan mist� painettiin viimeeksi
		//T�m� on testitarkoituksellinen ja tullaan korvaamaan n�pp�imist� ja hiiriluokilla
		int x = IkkunaPaneeli.painettux;
		int y = IkkunaPaneeli.painettuy;
		if( x > 100 && x < 319 && y > 600 &&  y < 712){
			IkkunaPaneeli.hallitsin.asetaTila(Pelintilanhallitsija.PELI);
		}
		if( x > 100 + 300 && x < 319 +300 && y > 600 &&  y < 712){
			IkkunaPaneeli.hallitsin.asetaTila(Pelintilanhallitsija.PISTETAULU);
		}
		if( x > 100 + 600 && x < 319 + 600 && y > 600 &&  y < 712){
			System.exit(0);
		}
		//Estet��n saman tapahtuman tapahtuminen kahdesti vahingossa
		IkkunaPaneeli.painettux = 0;
		IkkunaPaneeli.painettuy = 0;
	}

	

}
