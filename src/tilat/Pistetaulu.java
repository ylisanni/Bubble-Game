package tilat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entiteetit.Kirjaimet;
import entiteetit.Pelaaja;
import main.IkkunaPaneeli;

/**
 * Luokka joka vastaa pistetaulusta
 * @author Juho
 *
 */
public class Pistetaulu extends Pelitila{
	
	private BufferedImage tausta;
	private BufferedImage peliohi;
	private BufferedImage nappi;
	private BufferedImage nappivalittu;
	private Pelaaja pelaaja;
	
	BufferedReader lukija;
	ArrayList<String> pisteet;
	
	public Pistetaulu() {
		alusta();
	}
	
	@Override
	public void alusta() {
		//Koitetaan ladata kaikki kuvat
		try{
			pisteet = new ArrayList<String>();
			pelaaja = new Pelaaja();
			tausta = ImageIO.read(getClass().getResourceAsStream("/Tausta.png"));
			nappi = ImageIO.read(getClass().getResourceAsStream("/Nappi.png"));
			peliohi = ImageIO.read(getClass().getResourceAsStream("/PeliOhi.png"));
			lukija = new BufferedReader(new FileReader("res/pistetaulu.txt"));
			String rivi;
			while((rivi = lukija.readLine()) != null)pisteet.add(rivi);
			lukija.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paivita() {
		pelaaja.paivita();
	}

	@Override
	public void piirra(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawImage(tausta,0,0,null);
		g.drawImage(peliohi,0,0,null);
		//Piirret‰‰n napit
		g.drawImage(nappi, 10, 10, null);
		g.drawString("T  O  P     1  0     L  I  S  T  A", 280, 50);
		IkkunaPaneeli.pisteet.piirra(g, 363, 230);
		pelaaja.piirra(g);
	}

	@Override
	public void kasitteleSyote() {
		// TODO Auto-generated method stub
		
	}

}
