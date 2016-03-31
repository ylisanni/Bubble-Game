package entiteetit;

import java.awt.Color;
import java.awt.Graphics2D;

import hallinta.Tarkistin;
import main.IkkunaPaneeli;

/**
 * Luokka, jonka ilmentymät ovat kuplia, mutta niissä näkyy kirjain
 * Toisin kuin {@link PerusKupla}:ssa, niin tämän luokan ilmentymillä ei ole jatkuvaa Y-suuntaista kiihtyvyyttä
 * @author Juho
 *
 */
public class Kirjaimet extends PeliObjektit{

	private String kirjain;
	private double pehmennin;
	private int nopeusMaksimi;
	private double nopeusY;
	
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	
	private double x;
	private double y;
	
	
	/**
	 * Kirjaimet-luokan ilmentymän luominen
	 * @param kirjain - kirjain joka tullaan näyttämään kuplan keskellä
	 * @param pehmennin - double tyyppinen arvo, joka määrää liikkeen arvojen muuttumisen pehmeyden
	 * @param nopeusMaksimi - int tyypinnen arvo, joka määrää kuplan maksimimaalisen nopeuden
	 * @param x - kuplan sijainti X-akselilla
	 * @param y - kuplan sijainti Y-akselilla
	 * @param sade - Kuplan säde
	 */
	public Kirjaimet (String kirjain, double pehmennin, int nopeusMaksimi, int x, int y, int sade){
		this.x = x;
		this.y = y;
		this.sade = sade;
		this.liikeEsto = IkkunaPaneeli.tavoiteltavaPaivitusTaajuus / 3;
		this.kirjain = kirjain;
		this.maksimiPutoaminen = nopeusMaksimi;
		/*this.kirjain = k;
		this.pehmennin = p;
		this.nopeusMaksimi = n;
		this.x = x;
		this.y = y;
		this.nopeusY = this.nopeusMaksimi;
		minX = 100;
		maxX = 924;
		minY = 400;
		maxY = 500;*/
	}
	
	public void paivita(){
		
		this.x += this.liikeX;
		this.y += this.liikeY;
		liikeX *= 0.96;
		liikeY += 0.03;
		if(liikeY - 0.03 > maksimiPutoaminen){
			liikeY = maksimiPutoaminen;
		}
		if(this.x + liikeX < 0){
			kaannaLiikeX();
		}
		if(this.y + liikeY < 0){
			if(this.liikeY < 0){
				kaannaLiikeY();
			}
		}
		if(this.x + liikeX > IkkunaPaneeli.LEVEYS){
			kaannaLiikeX();
		}
		if(this.y + liikeY > IkkunaPaneeli.KORKEUS){
			kaannaLiikeY();
		}
		if(this.liikeEstoAjastin < liikeEsto){
			this.liikeEstoAjastin++;
		}
		
		
		/*if(this.y > maxY && this.nopeusY > -0.05){
			this.nopeusY = this.nopeusMaksimi * -1;
		}
		if (this.y < minY && this.nopeusY < 0.05) {
			this.nopeusY = this.nopeusMaksimi;
		}
		this.y += this.nopeusY;
		
		this.nopeusY *= this.pehmennin;*/
	}
	
	public void piirra(Graphics2D g2d){
		g2d.setColor(Color.GREEN);
		g2d.fillOval((int)(this.x - (this.sade/2)),(int)(this.y - (this.sade / 2)), this.sade, this.sade);
		g2d.setColor(Color.WHITE);
		g2d.fillOval((int)(this.x - (this.sade/2)),(int)(this.y - (this.sade / 2)), this.sade, this.sade);
		g2d.setColor(Color.BLACK);
		g2d.drawString(kirjain,(int) this.x - 4, (int)this.y + 4);
	}
}
