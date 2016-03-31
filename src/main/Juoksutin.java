package main;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Luokka, joka sis‰lt‰‰ main-metodin
 * Luokalla ei ole muuta tarkoitusta
 * @author Juho
 * 
 */
public class Juoksutin {
	
	/**Luotavan JFramen nimi*/
	public static final String OTSIKKO = "Bubble Game";
	
	/**Sovelluksen iconin sis‰lt‰v‰ muuttuja*/
	public static ImageIcon iconi;

	public static BufferedImage bi;
	
	/**
	 * Main-metodi, joka luo JFramen ja aloittaa sovelluksen.
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		try{
			bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		}catch(Exception e){
			e.printStackTrace();
		}
		JFrame ikkuna = new JFrame(OTSIKKO); //Luodaan uusi JFrame-luokan olio ja luonnin yhteydess‰ asetetaan sille nimi
		ikkuna.setContentPane(new IkkunaPaneeli()); //Luodaan IkkunaPaneeli-luokan olio, joka asetetaan ikkunan ContentPaneksi
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//M‰‰ritell‰‰n, ett‰ mit‰ tapahtuu, kun ikkuna suljetaan. T‰ss‰ tapauksessa ohjelman suorittaminen lopetetaan
		ikkuna.setResizable(false);//Estet‰‰n k‰ytt‰j‰n muuttamasta ikkunan kokoa
		ikkuna.setUndecorated(false);
		ikkuna.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(bi, new Point(0, 0), "."));
		
		ikkuna.pack();//Yksinkertaisestuna pakkaa
		ikkuna.setVisible(true);//Asetetaan ikkuna n‰kyv‰ksi
		ikkuna.setLocationRelativeTo(null);

	}

}
