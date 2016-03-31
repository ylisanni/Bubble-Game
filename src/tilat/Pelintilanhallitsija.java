package tilat;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entiteetit.Pelaaja;
import entiteetit.PeliObjektit;
import hallinta.Grafiikka;
import hallinta.Nappaimisto;

/**
 * Hallitsee {@link Pelitila}-luokan aliluokkia 
 * @author Juho
 *
 */
public class Pelintilanhallitsija {

	private ArrayList<Pelitila> pelitilat;
	
	private int nykyinenTila;
	
	//Pelin erilaiset n‰kym‰t, ei sis‰ll‰ paussia koska se sis‰ltyy tilaan PELI
	/**P‰‰menun indeksi*/
	public static final int PAAMENU = 0;
	/**Pistetaulun indeksi*/
	public static final int PISTETAULU = 1;
	/**Pelin indeksi*/
	public static final int PELI = 2;
	
	/**Pelintilanhallitsija-luokan ilmentym‰n alustus*/
	public Pelintilanhallitsija(){
		nykyinenTila = 0;
		pelitilat = new ArrayList<Pelitila>();
		pelitilat.add(new Paavalikko()); //P‰‰valikko
		pelitilat.add(new Pistetaulu()); //Pistetaulu
		pelitilat.add(new Peli()); //Itse peli
	}
	
	/**
	 * Asettaa parametrina annetun int tyyppisen arvon {@link #nykyinenTila}-muuttujan arvoksi
	 * @param x int
	 */
	public void asetaTila(int x){
		nykyinenTila = x;
	}
	
	/**P‰ivitt‰‰ {@link #nykyinenTila}-muuttujan arvoa vastaavaa pelitilaa*/
	public void paivita(){
		pelitilat.get(nykyinenTila).paivita();
	}
	
	/**Piirt‰‰ {@link #nykyinenTila}-muuttujan arvoa vastaavan pelitilaa*/
	public void piirra(Graphics2D g){
		pelitilat.get(nykyinenTila).piirra(g);
	}

}
