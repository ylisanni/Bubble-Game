package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import hallinta.Grafiikka;
import hallinta.Hiiri;
import hallinta.Kaappaaja;
import hallinta.Nappaimisto;
import hallinta.Pisteet;
import tilat.Paavalikko;
import tilat.Peli;
import tilat.Pelintilanhallitsija;

/**
 * Luokka, jota käytetään JFramen contentPanena
 * Sisältää pelin pyörittämisen ja muita elintärkeitä metodeja
 * mm. pelin päivittämiseen ja piirtämiseen liittyviä metodeja
 * @author Juho
 */
@SuppressWarnings("serial")
public class IkkunaPaneeli extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener  {
	
	//ATTRIBUUTIT
	
	//Luotavan JFramen mitat
	/**Luotavan JFramen leveysm kun käytetään IkkunaPaneelia*/
	public static final int LEVEYS = 1024;
	
	/**Luotavan JFramen korkeus, kun käytetään IkkunaPaneelia*/
	public static final int KORKEUS = 768;
	
	/**Dimensio-luokan ilmentymä, joka sisältää LEVYES ja KORKEUS muuttujien arvot komponentteina*/
	public static final Dimension dimensio = new Dimension(LEVEYS, KORKEUS);
	
	/**Pisteet-luokan ilmentymä*/
	public static Pisteet pisteet;
	
	//Päivittämiseen tarvittavia asioita
	/**Muuttuja, jonka arvo on tavoiteltava näytönpäivitys taajuus aka FPS*/
	public static final int tavoiteltavaPaivitusTaajuus = 100;
	private long tavoiteltavaAika = 1000 / tavoiteltavaPaivitusTaajuus;
	private boolean pyorimassa;
	
	private Thread peli;

	
	//FOR TEST PURPOSE
	public static int hiirix;
	public static int hiiriy;	
	public static int painettux;
	public static int painettuy;

	//Grafiikkaan liittyviä asioita
	public static Grafiikka grafiikka;
	private BufferedImage kuva;
	private Graphics2D g2d;
	
	private BufferedImage tausta;
	private BufferedImage logo;
	
	public static Pelintilanhallitsija hallitsin;
	

	
	//KONSTRUKTORI
	/**
	 * Kun luodaan uusi IkkunaPaneeli-luokan ilmentymä, niin tämä luokka suoritetaan
	 */
	public IkkunaPaneeli(){	
		super(); //Mahdollistaa JPanelin osion luomisen
		setMinimumSize(dimensio);
		setMaximumSize(dimensio);
		setPreferredSize(dimensio);
		setFocusable(true);
		requestFocus();
	}
	
	/**
	 * Alustaa IkkunaPaneelille tarpeellisia asioita
	 */
	public void alustus(){
		grafiikka = new Grafiikka();
		grafiikka.alusta();
		kuva = new BufferedImage(LEVEYS, KORKEUS, BufferedImage.TYPE_4BYTE_ABGR);
		g2d = (Graphics2D) kuva.getGraphics();
		g2d.setColor(Color.BLACK);
		pyorimassa = true;
		hallitsin = new Pelintilanhallitsija();
		pisteet = new Pisteet();
		pisteet.alusta();
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		if(peli == null){
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			peli = new Thread(this);
			peli.start();
		}
	}
	
	/** Metodi, joka piirtää aluksi taustavärin, jonka jälkeen kutsuu Pelintilahallitsija-luokan ilmentymän piirrä-metodia
	 * @param g
	 */
	public void piirra(Graphics2D g){
		g.fillRect(0, 0, LEVEYS, KORKEUS);
		hallitsin.piirra(g2d);

	}
	
	/**
	 * Hoitaa grafiikkaa
	 */
	private void piirraRuudulle() {
		Graphics g2 = getGraphics();
		g2.drawImage(kuva, 0, 0, LEVEYS, KORKEUS, null);

		g2.dispose();
		
	}

	/**
	 * Päivittää
	 */
	public void paivita(){
		hallitsin.paivita();
		Hiiri.paivita();
		Nappaimisto.paivita();
	}
	

	@Override
	public void run() {	
		alustus();

		long aloitusaika;
		long kulunutaika;
		long odotusaika;
		
		while(pyorimassa){
			aloitusaika  = System.nanoTime();
			//System.out.println("ALOITUS AIKA ON " + aloitusaika); //DEMOSTROINTITARKOITUKSESSA RYHMÄLLE
			paivita();
			piirraRuudulle();
			piirra(g2d);

			kulunutaika = System.nanoTime() - aloitusaika; 
			//System.out.println("KULUNUT AINA ON " + kulunutaika); //DEMOSTROINTITARKOITUKSESSA RYHMÄLLE
			odotusaika = tavoiteltavaAika - kulunutaika / 1000000;
			//System.out.println("ODOTUSAIKA ON " + odotusaika); //DEMOSTROINTITARKOITUKSESSA RYHMÄLLE
			if(odotusaika < 0)odotusaika = 0;
			try{peli.sleep(odotusaika);
			}catch(Exception e){e.printStackTrace();}
		}
	}

	//TÄSTÄ ALASPÄIN KOKEILUKOODIA
	
	public void keyPressed(KeyEvent key) {
		Nappaimisto.asetaPainettu(key.getKeyCode());
		Nappaimisto.asetaMerkki(key.getKeyChar());
		//TESTAUSTARKOITUKSESSA
		System.out.println("Arvo: " + key.getKeyCode() + " Merkki: " + key.getKeyChar());
	}
	
	public void keyReleased(KeyEvent key) {}
	
	public void mousePressed(MouseEvent me) {}
	
	public void mouseReleased(MouseEvent me) {}
	
	public void mouseMoved(MouseEvent me) {
		Hiiri.asetaSijainti(me.getX(), me.getY());
		hiirix = me.getX();
	}
	
	public void mouseDragged(MouseEvent me) {
		Hiiri.asetaSijainti(me.getX(), me.getY());
	}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mouseClicked(MouseEvent me) {
		painettux = me.getX();
		painettuy = me.getY();
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		//Nappaimisto.asetaPainettu(ke.getID());
	
	}

}
