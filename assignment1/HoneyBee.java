package assignment1;

public abstract class HoneyBee extends Insect{
	private int cost;
	
	public HoneyBee(Tile p, int hp, int cost) {
		super(p,hp);
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
	public boolean equals(Object obj) {
		if (!(super.equals(obj))){
			return false;
		}
		HoneyBee beeObj = (HoneyBee)obj;
		return (this.cost == beeObj.cost);
	}
	
	public String toString() {
		
		return ("Hp: " + this.getHealth());
	}
}
