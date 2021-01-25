package assignment1;

public class Hornet extends Insect{
	private int damage;
	
	public Hornet(Tile p, int hp, int d) {
		super(p,hp);
		this.damage = d;
	}
	/*
	public String toString() {
		return "HP: " + super.getHealth() + " Attack Damage: " + this.damage ;
	}
	*/
	public boolean takeAction() {
		Tile myTile = this.getPosition();
		HoneyBee bee= myTile.getBee();
		
		if ( bee == null) { //no bee on tile, must move to next tile
			
			if (myTile.isHive()) { //hornets have won!!
				return false; 
			}
			
			myTile.removeInsect(this);
			Tile nextTile = myTile.towardTheHive();
			nextTile.addInsect(this);
			
			return true;
		}
		// bee on tile
		bee.takeDamage(this.damage);
		return true;
		
	}
	public boolean equals(Object obj) {
		if (!(super.equals(obj))) {
			return false;
		}
		Hornet h = (Hornet)obj;
		return (h.damage == this.damage);
	}

}
