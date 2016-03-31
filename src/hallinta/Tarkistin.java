package hallinta;

import java.util.ArrayList;

import entiteetit.Pelaaja;
import entiteetit.PeliObjektit;

public class Tarkistin {

	private ArrayList<PeliObjektit> objektit;
	private Pelaaja pelaaja;
	
	public Tarkistin(ArrayList<PeliObjektit> obj, Pelaaja pelaaja){
		this.objektit = obj;
		this.pelaaja = pelaaja;
	}
	
	/**
	 * Tarkistaa osumat
	 */
	public void tarkistaOsumat() {
		for(int i = 0; i < objektit.size(); i++){
			//PElaaja
			
			if(objektit.get(i).leikkaavat(pelaaja)){
				System.out.println("Leikkaa....");
				if(objektit.get(i).voikoLiikuttaa()){
					if(pelaaja.annaLiikeX() == 0 && pelaaja.annaLiikeY() == 0){
						System.out.println("Pelaaja paikallaan, kimmotetaan!");
						objektit.get(i).asetaLiikex(objektit.get(i).annaLiikeX() *- 1.5 );
						objektit.get(i).asetaLiikey(objektit.get(i).annaLiikeY() *- 1.5 );
					}else{
						System.out.println("Liikutetaan palloa");
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
for(int y = i + 1; y < objektit.size(); y++){
				
				if(objektit.get(i).leikkaavat(objektit.get(y))){
					
					if(objektit.get(i).annaTyyppi() != objektit.get(y).annaTyyppi() && objektit.get(i).annaTyyppi() != 2 && objektit.get(y).annaTyyppi() != 2 ){
						System.out.println("Luo minipallot");
						//objektit.add(new PerusKupla((int)objektit.get(i).annaX(), (int)objektit.get(y).annaY(), 200, 2));
						objektit.remove(i);
						objektit.remove(y-1);
						
					}else{
					
					objektit.get(i).asetaLiikey(-objektit.get(y).annaLiikeY());
					
					objektit.get(y).asetaLiikey(-objektit.get(i).annaLiikeY());
					
					objektit.get(i).asetaLiikex(-objektit.get(y).annaLiikeX());
					
					objektit.get(y).asetaLiikex(-objektit.get(i).annaLiikeX());
					
					if(objektit.get(y).annaX() > objektit.get(i).annaX()){
						objektit.get(i).asetaX(objektit.get(i).annaX() - objektit.get(i).annaSade() /*+ objektit.get(i).annaLiikeX() * kimmoitin*/);
						
						objektit.get(y).asetaX(objektit.get(y).annaX() + objektit.get(y).annaSade() /*+ objektit.get(y).annaLiikeX() * kimmoitin*/);
					}else{
						objektit.get(i).asetaX(objektit.get(i).annaX() + objektit.get(i).annaSade() /*+ objektit.get(i).annaLiikeX()* -kimmoitin*/);
						
						objektit.get(y).asetaX(objektit.get(y).annaX() - objektit.get(y).annaSade() /*+ objektit.get(y).annaLiikeX()* -kimmoitin*/);
					}
					if(objektit.get(y).annaY() > objektit.get(i).annaY()){
						objektit.get(i).asetaY(objektit.get(i).annaY() - objektit.get(i).annaSade() /*+ objektit.get(i).annaLiikeY()* kimmoitin*/);
						
						objektit.get(y).asetaY(objektit.get(y).annaY() + objektit.get(y).annaSade() /*+ objektit.get(y).annaLiikeY()* kimmoitin*/);
					}else{
						objektit.get(i).asetaY(objektit.get(i).annaY() + objektit.get(i).annaSade()  /*+ objektit.get(i).annaLiikeY() * kimmoitin*/ );
						
						objektit.get(y).asetaY(objektit.get(y).annaY() - objektit.get(y).annaSade()
								/*+objektit.get(y).annaLiikeY() * -kimmoitin*/);
					}
					}
				}	
			}
		}

	}
	
}
