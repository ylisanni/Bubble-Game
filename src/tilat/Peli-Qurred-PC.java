package tilat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import entiteetit.Pelaaja;
import entiteetit.PeliObjektit;
import entiteetit.PerusKupla;
import hallinta.Grafiikka;
import main.IkkunaPaneeli;

/**
 * Luokka, joka on itse peli
 * @author Juho
 *
 */
public class Peli extends Pelitila{

	//Kuvat
	private BufferedImage tausta;
	private BufferedImage GUI;
	private BufferedImage peliohi;
	private Pelaaja pelaaja;
	private boolean paussi;
	private boolean pelikesken;
	private int pisteet;
	private int tick;
	
	private int elamat = 5;
	private double kimmoitin = 0.075767;
	
	private ArrayList<PeliObjektit> objektit;
	
	private Paussi paussitila;
	

	public Peli(){
		alusta();
	}
	
	@Override
	public void alusta() {
		//Koitetaan ladata kaikki kuvat
		try{
			paussitila = new Paussi();
			tausta = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.TAUSTA);
			GUI = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.GUI);
			peliohi = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.PISTETAULULOGO);
			pelaaja = new Pelaaja();
			paussi = false;
			pelikesken = true;
			Random r = new Random();
			objektit = new ArrayList<PeliObjektit>();
			for(int i = 0; i < 1; i++){
				objektit.add(new PerusKupla((r.nextInt(6)+1) * 100, 50, 40, r.nextInt(2)));
				objektit.get(i).asetaLiikex((r.nextInt(5) - 10) * 0.3f);
			}
			pisteet = 0;
		}catch (Exception e) {
				e.printStackTrace();
		}
	}

	@Override
	public void paivita() {
		if(paussi){
			paussitila.paivita();
			pelaaja.paivita();
			//Laitetaan Paussi pyörimään
			//Kursori takaisin näykyviin
		}else{
			//Normaalit päivittelyt
			//kursori piilotetaan, täytyy painaa oikeasta kohdasta ruutua paussin jälkeen että peli jatkuu
			//Tämä paikka on hiiren sen hetkinen sijainti silloin kun paussi valittiin
			//Tämä estää pelaajan hyödyntämästä paussia pelissä
			if(elamat == 0 ||  objektit.size() == 0){
				pelikesken = false;
				IkkunaPaneeli.pisteet.onkoPistesijalla(pisteet);
			}
			
			if(tick == 60){
				tick = 0;
				pisteet += objektit.size(); 
			}
			
			tick++;
			
			pelaaja.paivita();
			
			vaikeutin();
			
			tarkistaOsumat();
			
			for(int i = 0; i < objektit.size(); i++){
				objektit.get(i).paivita();
			}
		}
		
	}

	@Override
	public void piirra(Graphics2D g) {
		g.setColor(Color.WHITE);
		IkkunaPaneeli.grafiikka.asetaFontti(Grafiikka.PERUSFONTTI, g);
		g.drawImage(tausta,0,0,null);
		g.drawImage(GUI,0,0,null);
		g.drawString("Kuplien määrä: "+objektit.size(), 20, 700);
		g.drawString("pisteet: "+pisteet, 20, 750);
		g.setColor(Color.RED);
		g.drawLine(0, 650, IkkunaPaneeli.LEVEYS, 650);
		for(int i = 0; i < objektit.size(); i++){
			objektit.get(i).piirra(g);
		}
		if(!pelikesken){
			g.drawImage(tausta,0,0,null);
			g.drawImage(peliohi, 0, 0, null);
			g.setColor(Color.WHITE);
			g.drawString("P  E  L  I   O  H  I  ", 400, 50);
		}	
		if(paussi){
			paussitila.piirra(g);
		}
		pelaaja.piirra(g);
		
	}

	/**
	 * Tarkistaa Peli-luokan ilmentymien osumat
	 */
	public void tarkistaOsumat(){

		
		//Käydään läpi
		for(int i = 0; i < objektit.size(); i++){
			//PElaaja
			if(objektit.get(i).leikkaavat(pelaaja)){
				if(objektit.get(i).voikoLiikuttaa()){
					if(pelaaja.annaLiikeX() == 0 && pelaaja.annaLiikeY() == 0){
						objektit.get(i).asetaLiikex(objektit.get(i).annaLiikeX() *- 1.5 );
						objektit.get(i).asetaLiikey(objektit.get(i).annaLiikeY() *- 1.5 );
					}else{
						objektit.get(i).asetaLiikey(objektit.get(i).annaLiikeY() -pelaaja.annaLiikeY() * 0.8f);
						System.out.println(pelaaja.annaLiikeX());
						if(pelaaja.annaLiikeX() < 0){
						//	System.out.println("VASEN");
							objektit.get(i).asetaLiikex(objektit.get(i).annaLiikeX() + Math.abs(pelaaja.annaLiikeX() * 0.8f));
						}else{
						//	System.out.println("OIKEA");
							objektit.get(i).asetaLiikex(objektit.get(i).annaLiikeX() - Math.abs(pelaaja.annaLiikeX() * 0.8f));
						}
					}
					objektit.get(i).liikutettu();
				}
			}
			
			
			//Muut pallot
			for(int y = i + 1; y < objektit.size(); y++){
				
				if(objektit.get(i).leikkaavat(objektit.get(y))){
					
					if(objektit.get(i).annaTyyppi() != objektit.get(y).annaTyyppi() && objektit.get(i).annaTyyppi() != 2 && objektit.get(y).annaTyyppi() != 2 ){
						System.out.println("Luo minipallot");
						//objektit.add(new PerusKupla((int)objektit.get(i).annaX(), (int)objektit.get(y).annaY(), 200, 2));
						objektit.remove(i);
						objektit.remove(y-1);
						pisteet +=100;
						
					}else{
					
					objektit.get(i).asetaLiikey(-objektit.get(y).annaLiikeY() * kimmoitin);
					
					objektit.get(y).asetaLiikey(-objektit.get(i).annaLiikeY() * kimmoitin);
					
					objektit.get(i).asetaLiikex(-objektit.get(y).annaLiikeX() * kimmoitin);
					
					objektit.get(y).asetaLiikex(-objektit.get(i).annaLiikeX() * kimmoitin);
					
					if(objektit.get(y).annaX() > objektit.get(i).annaX()){
						objektit.get(i).asetaX(objektit.get(i).annaX() - objektit.get(i).annaSade()  * kimmoitin /*+ objektit.get(i).annaLiikeX() * kimmoitin*/);
						
						objektit.get(y).asetaX(objektit.get(y).annaX() + objektit.get(y).annaSade()  * kimmoitin /*+ objektit.get(y).annaLiikeX() * kimmoitin*/);
					}else{
						objektit.get(i).asetaX(objektit.get(i).annaX() + objektit.get(i).annaSade()  * kimmoitin /*+ objektit.get(i).annaLiikeX()* -kimmoitin*/);
						
						objektit.get(y).asetaX(objektit.get(y).annaX() - objektit.get(y).annaSade()  * kimmoitin /*+ objektit.get(y).annaLiikeX()* -kimmoitin*/);
					}
					if(objektit.get(y).annaY() > objektit.get(i).annaY()){
						objektit.get(i).asetaY(objektit.get(i).annaY() - objektit.get(i).annaSade()  * kimmoitin /*+ objektit.get(i).annaLiikeY()* kimmoitin*/);
						
						objektit.get(y).asetaY(objektit.get(y).annaY() + objektit.get(y).annaSade() * kimmoitin /*+ objektit.get(y).annaLiikeY()* kimmoitin*/);
					}else{
						objektit.get(i).asetaY(objektit.get(i).annaY() + objektit.get(i).annaSade()  * kimmoitin /*+ objektit.get(i).annaLiikeY() * kimmoitin*/ );
						
						objektit.get(y).asetaY(objektit.get(y).annaY() - objektit.get(y).annaSade() * kimmoitin
								/*+objektit.get(y).annaLiikeY() * -kimmoitin*/);
					}
					}
				}	
			}
			
			if(objektit.size() > i){
				if(objektit.get(i).annaY() > 650){
					elamat--;
					objektit.remove(i);
				}
			}
		}
	}
	
	/**
	 * Lisää {@link PerusKupla}-luokan ilmentymiä peliin käyttäen {@link Random}-luokkaa hyväksi
	 */
	public void vaikeutin(){
		Random random = new Random();
		if(random.nextInt(10000) > 9950){
			objektit.add(new PerusKupla(random.nextInt(IkkunaPaneeli.LEVEYS), - 50,40, random.nextInt(2)));
		}
	}
	
	@Override
	public void kasitteleSyote() {
		// TODO Auto-generated method stub
		//Sisältää metodit jotka katsovat hiiren ja näppäimistön syötteen
		//Luultavasti luodaan omaluokka molemmista
	}

}
