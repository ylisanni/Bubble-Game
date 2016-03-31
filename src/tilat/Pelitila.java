package tilat;

/**
 * Abstracti luokka, joka sisältää pelitiloille tärrkeitä arvoja ja muuttujia kuten myös metodeja
 * @author Juho
 *
 */
public abstract class Pelitila {
	
	/**Määrittelee viimeisimmän piirrettävän tilan normaaliksi eli viimeisintä asiaa ei piirretä*/
	protected final int NORMAALI = 0;
	/**Määrittelee viimeisimmän piirrettävän tilan pimennetyksi, joten luodaan pimennys, jossa oleva heikko valokeila seuraa kursoria*/
	protected final int PIMENNETTY = 1;
	/**int tyyppinen muuttuja, joka sisältää pelitilan, saatetaan poistaa koska tärkeä vain luokassa {@link Pelintilanhallitsija}*/
	protected int nykeinenTila;
	
	/**{@link Pelintilanhallitsija}-luokan ilmentymä*/
	protected Pelintilanhallitsija GM;
	
	/**Alustus*/
	public abstract void alusta();
	/**Päivitys*/
	public abstract void paivita();
	/**
	 * Piirto
	 * @param g
	 */
	public abstract void piirra(java.awt.Graphics2D g);
	/**Syötteen käsittely*/
	public abstract void kasitteleSyote();

}
