package tilat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entiteetit.Pelaaja;
import hallinta.Grafiikka;
import main.IkkunaPaneeli;

/**
 * Luokka, jota käyttämällä voidaan luoda peliin paussi, kuten myös hallinnoida sitä
 * Ilmentymän avulla voidaan myös hallita {@link Pelintilanhallitsija}:n metodeja
 * 
 * @author Juho
 *
 */
public class Paussi extends Pelitila{

	private BufferedImage paussi;
	private BufferedImage Nappi;
	private String[] Vaihtoehdot = {
			"P   A   L   A      P   E   L   I   I   N",
			"A   L   O   I   T   A      A   L   U   S   T   A",
			"P   O   I   S   T   U      P   E   L   I   S   T   Ä"
	};
	
	/**
	 * Luokan Paussi ilmentymän luonti
	 */
	public void Paussi(){
		alusta();
	}
	
	/**
	 * Luokan ilmentymän alustus
	 */
	@Override
	public void alusta() {
		try{
			paussi = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.PAUSSI);
			//IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.PAUSSI);
			Nappi = IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.NAPPI);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Ei latautunut");
		}
	}
	/**
	 * Päivittäminen
	 */
	@Override
	public void paivita() {
		kasitteleSyote();
	}

	/**
	 * Piirto
	 */
	@Override
	public void piirra(Graphics2D g) {
		//SELVITÄ MIKSEI LATAA LUOKAN MUUTTUJIA
		g.drawImage(IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.PAUSSI), 0, 0, null);
		g.drawImage(IkkunaPaneeli.grafiikka.annaKuva(Grafiikka.NAPPI), 20, 20, null);
		IkkunaPaneeli.grafiikka.asetaFontti(NORMAALI, g);
		for(int i = 0; i < Vaihtoehdot.length; i++){
			g.setColor(Color.WHITE);
			g.drawString(Vaihtoehdot[i], 300, 280+ 80 * i);
		}
	}

	/**
	 * Syötteen käsittely...
	 */
	@Override
	public void kasitteleSyote() {
		// TODO Auto-generated method stub
		
	}

}
