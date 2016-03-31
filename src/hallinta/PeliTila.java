package hallinta;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entiteetit.Pelaaja;
import entiteetit.PeliObjektit;

/** Luokka, joka käsittelee pelitilaa
 * @author Juho
 *
 */

public class PeliTila {
	
	/**Haluttun luoteiskulman paikka x-koordinaatistossa*/
	private int x1;
	/**Haluttun luoteiskulman paikka y-koordinaatistossa*/
	private int y1;
	/**Haluttun kaakkoiskulman paikka x-koordinaatistossa*/
	private int x2;
	/**Haluttun kaakkoiskulman paikka x-koordinaatistossa*/
	private int y2;
	/**Pelitilaan haluttava tausta
	 * Suositellaan kooksi ( {@link #x2}-{@link#x1} ) x ( {@link #y2}-{@link #y1} )*/
	private BufferedImage tausta;
	/**ArrayList {@link PeliObjektit}-luokan ilmentymistä*/
	private ArrayList<PeliObjektit> objektit;
	
	private Pelaaja pelaaja;
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public PeliTila(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.objektit = new ArrayList<PeliObjektit>();
		this.pelaaja = new Pelaaja();
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param tausta
	 */
	public PeliTila(int x1, int y1, int x2, int y2, BufferedImage tausta){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.tausta = tausta;
		this.objektit = new ArrayList<PeliObjektit>();
		this.pelaaja = new Pelaaja();
	}


	public void piirra(Graphics2D g2d){
		g2d.drawImage(tausta, x1, y1, x2, y2,null);
	}
	
	public void paivita(){
		pelaaja.paivita();
		for(int i = 0; i < objektit.size(); i++){
			objektit.get(i).paivita();
		}
	}
	
	/**
	 * @return
	 */
	public int annaX1() {
		return x1;
	}

	/**
	 * @param x1
	 */
	public void asetaX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * @return
	 */
	public int annaY1() {
		return y1;
	}

	/**
	 * @param y1
	 */
	public void asetaY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * @return
	 */
	public int annaX2() {
		return x2;
	}

	/**
	 * @param x2
	 */
	public void asetaX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * @return
	 */
	public int annaY2() {
		return y2;
	}

	/**
	 * @param y2
	 */
	public void asetaY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * @return
	 */
	public BufferedImage annaTausta() {
		return tausta;
	}

	/**
	 * @param tausta
	 */
	public void asetaTausta(BufferedImage tausta) {
		this.tausta = tausta;
	}

	/**
	 * @return
	 */
	public ArrayList<PeliObjektit> annaObjektit() {
		return objektit;
	}

	/**
	 * @param objektit
	 */
	public void asetaObjektit(ArrayList<PeliObjektit> objektit) {
		this.objektit = objektit;
	}
	
	

}
