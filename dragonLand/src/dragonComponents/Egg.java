/**
 * 
 */
package dragonComponents;


/**
 * @author Jenniber Franco
 *
 */
public class Egg{

	/**
	 * 
	 */
	private int x;
	private int y;
	private int w;
	private int h;
	private String link;
	private int incubationTime;
	
	//for the moment parameters will be fields (Add a general movingComponent class later)
	public Egg(int x, int y, int w, int h, String link, int incTime) {
		this.x= x;
		this.y= y;
		this.w= w;
		this.h= h;
		this.link= link;
		incubationTime = incTime;
	}
	
	public int getIncubationTime(){
		return incubationTime;
	}
	
	
}