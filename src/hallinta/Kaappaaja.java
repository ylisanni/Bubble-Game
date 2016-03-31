package hallinta;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.IkkunaPaneeli;


/**KOKEILULUOKKA
 * K‰ytt‰‰ erillist‰ Threadia kuvien tallentamiseen, ei toimi
 * 
 * @author Juho
 * @category TESTILUOKAT
 */
public class Kaappaaja implements Runnable {

	private static ArrayList<BufferedImage> kuvat;
	
	private Thread kuva;
	
	private int kuvanIndeksi;
	
	private boolean kaappaa;
	
	public Kaappaaja() {
		if(kuva == null){
			System.out.println("uusi thread luotu");
			kuva = new Thread(this);
			kuva.start();
		}
	}
	
	@Override
	public void run() {
		alusta();
		kaappaa = true;
		System.out.println("kappa");

			while(kuvat.size() > -1){
				while(kaappaa){
					try {
						kuva.sleep(1000);
						System.out.println("Nukutaan");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				tallennaKuva();
			}
			
		}
	}
	
	
	public void lisaaKuva(BufferedImage bi){
		kuvat.add(bi);
		System.out.println("Kuva lis‰ttiin");
	}
	
	public void alusta(){
		kuvanIndeksi = 1;
		kuvat = new ArrayList<BufferedImage>();
	}
	
	public void tallennaKuva(){
		try {
			java.io.File out = new java.io.File("kuva" + kuvanIndeksi + ".png");
			while(!out.exists()){
			javax.imageio.ImageIO.write(kuvat.get(0), "png", out);
			System.out.println("TALLENNETTUUN KUYVA");
			}
			kuvanIndeksi++;
			kuvat.remove(0);
			System.out.println("Tallennettiin");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
