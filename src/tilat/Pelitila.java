package tilat;

/**
 * Abstracti luokka, joka sis�lt�� pelitiloille t�rrkeit� arvoja ja muuttujia kuten my�s metodeja
 * @author Juho
 *
 */
public abstract class Pelitila {
	
	/**M��rittelee viimeisimm�n piirrett�v�n tilan normaaliksi eli viimeisint� asiaa ei piirret�*/
	protected final int NORMAALI = 0;
	/**M��rittelee viimeisimm�n piirrett�v�n tilan pimennetyksi, joten luodaan pimennys, jossa oleva heikko valokeila seuraa kursoria*/
	protected final int PIMENNETTY = 1;
	/**int tyyppinen muuttuja, joka sis�lt�� pelitilan, saatetaan poistaa koska t�rke� vain luokassa {@link Pelintilanhallitsija}*/
	protected int nykeinenTila;
	
	/**{@link Pelintilanhallitsija}-luokan ilmentym�*/
	protected Pelintilanhallitsija GM;
	
	/**Alustus*/
	public abstract void alusta();
	/**P�ivitys*/
	public abstract void paivita();
	/**
	 * Piirto
	 * @param g
	 */
	public abstract void piirra(java.awt.Graphics2D g);
	/**Sy�tteen k�sittely*/
	public abstract void kasitteleSyote();

}
