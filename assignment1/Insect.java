package assignment1;

public abstract class Insect {
	
	private Tile position;
	private int hp;
	
	public Insect(Tile p, int hp) {
		if (p.addInsect(this)) {
			this.position = p;
			this.hp = hp;
		}
		else {
			throw new IllegalArgumentException("You cannot place this insect at this tile!");
		}
	}	
	public final Tile getPosition() {
		return this.position;
	}
	public final int getHealth() {
		return this.hp;
	}
	public void setPosition(Tile p) {
		this.position = p;
	}
	public void takeDamage(int damage) {
		
		if ((this instanceof HoneyBee && this.position.isHive())) {
			damage = (int) ((int) damage*0.9); //reduces by 10%
		}
		this.hp = this.hp - damage;
		if (this.hp<= 0) {
			this.position.removeInsect(this);
		}	
	}
	public abstract boolean takeAction();
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Insect)) {
			return false;
		}
		Insect insectObj = (Insect)obj;
		
		if ((insectObj.position == this.position && insectObj.hp == this.hp)) {
			return true;
		}
		else {
			return false;
		}
	}
}
