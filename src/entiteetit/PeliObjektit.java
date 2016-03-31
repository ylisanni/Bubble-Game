package entiteetit;

import java.awt.Color;
import java.awt.Graphics2D;

import main.IkkunaPaneeli;

/** Abstrakti-luokka, joka sis‰lt‰‰ peliss‰ esiintyville objekteille oleellisia muuttujia, arvoja ja metodeja
 * @author Juho
 */
public abstract class PeliObjektit {

	//Sijainti
	/**double-tyyppinen muuttuja, joka sis‰lt‰‰ ilmentym‰n sijainnin x-koordinaatistossa*/
	protected double x;
	
	/**double-tyyppinen muuttuja, joka sis‰lt‰‰ ilmentym‰n sijainnin y-koordinaatistossa*/
	protected double y;
	
	//liike
	/**double-tyyppinen muuttuja, joka sis‰lt‰‰ ilmentym‰n liikkenopeuden x-akselilla*/
	protected double liikeX;
	
	/**double-tyyppinen muuttuja, joka sis‰lt‰‰ ilmentym‰n liikkenopeuden y-akselilla*/
	protected double liikeY;
	
	/**double-tyyppinen muuttuja, joka sis‰lt‰‰ maksimaalisen arvon {@link #liikeY}:lle*/
	protected double maksimiPutoaminen = 1.76;
	
	/**int-tyyppinen muuttuja, jolla m‰‰r‰t‰‰n kauanko pit‰‰ aikaa kulua ennenkuin pelaaja voi liikuttaa objektia taas*/
	protected int liikeEsto;
	
	/**int-tyyppinen muuttuja, joka toimii ajastimena*/
	protected int liikeEstoAjastin;
	
	//////////////////////Graafisia attribuutteja
	
	/**Color-tyyppinen muuttuja, joka sis‰lt‰‰ v‰rin*/
	protected Color vari;
	
	/**int-tyyppinen muuttuja, joka sis‰lt‰‰ objektin s‰teen*/
	protected int sade;
	
	/**int-tyyppinen muuttuja, joka sis‰lt‰‰ objektin tyypin
	 * Arvo voi olla joko 0 tai 1
	 * N‰it‰ arvoja vastaavasti voidaan k‰ytt‰‰ {@link #VIHREA} ( 0 ) ja {@link #PUNAINEN} ( 1 )*/
	protected int tyyppi;
	
	/**final int-tyyppinen muuttuja, joka vastaa arvoa 0*/
	protected final int VIHREA = 0;
	
	/**final int-tyyppinen muuttuja, joka vastaa arvoa 1*/
	protected final int PUNAINEN = 1;
	
	
	
	///////////////////Tarkeimpia metodeja
	
	/**Perus piirtometodi*/
	public void piirra(Graphics2D g2d){
		g2d.setColor(this.vari);
		g2d.fillOval((int)(this.x - (this.sade/2)),(int)(this.y - (this.sade / 2)), this.sade, this.sade);
	}
	
	/**
	 * Tarkistaa leikkaavatko parametrina annettu PeliObjektit-luokan ilmentym‰ nykyisen ilmentym‰n kanssa
	 * @param kupla
	 * @return true / false
	 */
	public boolean leikkaavat(PeliObjektit kupla){
		boolean leikkaavat = false;
		if(kupla == null){
			System.out.println("Kupla on null");
		}
		double valinenX = Math.abs(Math.abs(this.x) - Math.abs(kupla.annaX()));
		double valinenY = Math.abs(Math.abs(this.y) - Math.abs(kupla.annaY()));
		double sateidenSumma = this.sade / 2 + kupla.annaSade() / 2;
		if((valinenX * valinenX) + (valinenY * valinenY) < sateidenSumma*sateidenSumma){
			leikkaavat = true;
		}
		return leikkaavat;
	}
	
	/**
	 * P‰ivitt‰‰ PeliObjektit-luokkaa jatkavan aliluokan sijainnin
	 */
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
			System.out.println("H‰visit....");
			//asetaSijainti(this.x, IkkunaPaneeli.KORKEUS - (this.sade));
		}
		if(this.liikeEstoAjastin < liikeEsto){
			this.liikeEstoAjastin++;
		}

	}
	
	/**Asettaa ilmentym‰n x-akselillisen liikkeen vastaluvuksensa*/
	public void kaannaLiikeX(){
		this.liikeX = -this.liikeX;
	}
	
	/**Asettaa ilmentym‰n y-akselillisen ( {@link #liikeY} ) liikkeen vastaluvuksensa*/
	public void kaannaLiikeY(){
		this.liikeY = -this.liikeY;
	}
	
	/**
	 * Tarkistaa voiko pelaaja liikuttaa PeliObjektit-luokan oliota
	 * @return true/false
	 */
	 
	public boolean voikoLiikuttaa(){
		if(this.liikeEstoAjastin < liikeEsto){
			return false;
		}
		//System.out.println("Liikutetaan");
		return true;
	}
	
	/**
	 * Asettaa, {@link #liikeEstoAjastin} muuttujan arvon nollaksi
	 */
	public void liikutettu(){
		this.liikeEstoAjastin = 0;
		//System.out.println("Palloa liikutettiin");
	}
	
	///////////////////GETTERIT
	
	/**
	 * Palauttaa ilmentym‰n (double) {@link #x}
	 * @return
	 */
	public double annaX(){
		return this.x;
	}
	
	/**
	 * Palauttaa ilmentym‰n (double) {@link #y}
	 * @return
	 */
	public double annaY(){
		return this.y;
	}
	
	/**
	 * Palauttaa ilmentym‰n (double) {@link #liikeX}
	 * @return
	 */
	public double annaLiikeX(){
		return this.liikeX;
	}
	
	/**
	 * Palauttaa ilmentym‰n (double) {@link #liikeY}
	 * @return
	 */
	public double annaLiikeY(){
		return this.liikeY;
	}
	
	/**
	 * Palauttaa ilmentym‰n (double) {@link #maksimiPutoaminen}
	 * @return
	 */
	public double annaMaksimiPutoaminen(){
		return this.maksimiPutoaminen;
	}
	
	/**
	 * Palauttaa ilmentym‰n (int) {@link #sade}
	 * @return
	 */
	public int annaSade(){
		return this.sade;
	}
	
	/**
	 * Palauttaa ilmentym‰n tyypin
	 * @return
	 */
	public int annaTyyppi(){
		return this.tyyppi;
	}

	///////////////////SETTERIT
	
	
	/** Asettaa {@link #x}:n arvoksi parametrina annetun double-tyyppisen arvon
	 * @param x - double tyyppinen arvo
	 */
	public void asetaX(double x) {
		this.x = x;
	}

	/** Asettaa {@link #y}:n arvoksi parametrina annetun double-tyyppisen arvon
	 * @param y - double tyyppinen arvo
	 */
	public void asetaY(double y) {
		this.y = y;
	}

	/** Asettaa {@link #liikeX};n arvoksi parametrina annetun double tyyppisen arvon
	 * @param liikex - double-tyyppinen arvo
	 */
	public void asetaLiikex(double liikex) {
		this.liikeX = liikex;
	}

	/** Asettaa {@link #liikeY}:n arvoksi parametrina annetun double tyyppisen arvon
	 * @param liikey - double-tyyppinen arvo
	 */
	public void asetaLiikey(double liikey) {
		this.liikeY = liikey;
	}

	/**Asettaa {@link #maksimiPutoaminen} nimisen muuttujan arvoksi parametrina annetun double-tyyppisen arvon
	 * @param maksimiPutoaminen - double tyyppinen arvo
	 */
	public void asetaMaksimiPutoaminen(double maksimiPutoaminen) {
		this.maksimiPutoaminen = maksimiPutoaminen;
	}

	/**Asettaa {@link vari} nimisen muuttujan arvoksi parametrina annetun {@link Color}-luokan ilmentym‰n
	 * @param vari - {@link Color}-luokan ilmentym‰
	 */
	public void asetaVari(Color vari) {
		this.vari = vari;
	}

	/**Asettaa {@link sade} nimisen muuttujan arvoksi parametrina annetun int-tyyppisen arvon
	 * @param sade - int tyyppien arvo
	 */
	public void asetaSade(int sade) {
		this.sade = sade;
	}
	
	/**
	 * Asettaa parametrina annetut double tyyppiset arvot {@link #x} ja {@link #y} arvoiksi, joka kuvastavat ilmentym‰n sijaintia XY-koordinaatistossa
	 * @param x - Double tyyppinen arvo
	 * @param y - Double tyyppinen arvo
	 */
	public void asetaSijainti(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Asettaa {@link #tyyppi}:n arvoksi parametria annetun arvon
	 * @param x - int tyyppinen arvo, joko {@link #VIHREA} ( 0 ) tai {@link #PUNAINEN} ( 1 )
	 */
	public void asetaTyyppi(int x){
		this.tyyppi = x;
	}
}