package entiteetit;

import java.awt.Color;

import main.IkkunaPaneeli;

/**
 * Pelin perusentiteetin luokka.
 * Luokan ilmentymill‰ on vakiona m‰‰ritelty Y-akselin suuntainen kiihtyvyys positiivisella arvolla.
 * @author Juho
 *
 */
public class PerusKupla extends PeliObjektit {


	/**Luodaan uusi PerusKupla-luokan ilmentym‰
	 * 
	 * @param x - sijainti X-akselilla
	 * @param y - sijainti Y-akselilla
	 * @param sade - kuplan s‰de
	 * @param tyyppi - kuplan tyyppi, joko {@link #VIHREA} ( 0 ) tai {@link #PUNAINEN} ( 1 )
	 */
	public PerusKupla(int x, int y, int sade, int tyyppi){
		if(tyyppi == VIHREA){
			this.vari = Color.GREEN;
		}else {
			this.vari = Color.RED;
		}
		this.x = x;
		this.y = y;
		this.sade = sade;
		this.liikeEsto = IkkunaPaneeli.tavoiteltavaPaivitusTaajuus / 3;
		this.tyyppi = tyyppi;
	}
}
