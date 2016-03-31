package entiteetit;

import java.awt.Color;
import java.awt.Graphics2D;

import hallinta.Hiiri;
import main.IkkunaPaneeli;

/**
 * Luokka, jonka ilmentym� seuraa k�ytt�j�n kursoria JPaneelissa.
 * T�m�n luokan ilmentym�t kykenev�t vuorovaikuttamaan {@link Kirjaimet}- ja {@link PerusKupla}-luokan ilmentymien kanssa
 * @author Juho
 *
 */
public class Pelaaja extends PeliObjektit{
	
	//ATTRIBUUTIT
	/**Arvo, joka rajoittaa pelaajan liikkuvuutta reunoihin n�hden*/
	private int rajaaja = 5;
	/**Pelaajan sijainti viime tickiss� X-akselissa*/
	private double vanhaX;
	/**Pelaajan sijainti viime tickiss� Y-akselissa*/
	private double vanhaY;
	
	/**
	 * Luodaan uusi Pelaaja-luokan ilmentym�
	 */
	public Pelaaja(){
		super();
		vanhaX = 0;
		vanhaY = 0;
		this.sade = 30;
		this.vari = Color.WHITE;
	}
	
	/**P�ivitt�� pelaajan sijainnin kursorin sijaintiin n�hden JPaneelissa*/
	public void paivita(){
		if(Hiiri.annaX()> rajaaja && Hiiri.annaX() < IkkunaPaneeli.LEVEYS - rajaaja ){
			this.x = Hiiri.annaX();
		}else if(Hiiri.annaX() < rajaaja){
			this.x = rajaaja;
		}else if(Hiiri.annaX() > IkkunaPaneeli.LEVEYS - (rajaaja * 3)){
			this.x = IkkunaPaneeli.LEVEYS - (rajaaja * 3);
		}
		this.y = Hiiri.annaY();
		
		this.liikeX = (this.vanhaX - this.x);
		this.liikeY = (this.vanhaY - this.y);
		this.vanhaX = this.x;
		this.vanhaY = this.y;		
	}
	
	public void piirra(Graphics2D g2d){
		g2d.setColor(this.vari);
		g2d.fillOval((int)(this.x - (this.sade/2)),(int)(this.y - (this.sade / 2)), this.sade, this.sade);
	}
}
