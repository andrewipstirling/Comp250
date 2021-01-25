package assignment1;

public class StingyBee extends HoneyBee{
	private int damage;
	
	public StingyBee(Tile pos, int d) {
		super(pos,10,1); // health = 10, cost = 1
		this.damage = d;
	}
	
	public boolean takeAction() {
		
		Tile myTile = this.getPosition();
		if (myTile.isOnThePath()) {
			
			while(!(myTile.isNest())) { //while tile is not the hornets nest
				
				if (myTile.getNumOfHornets() == 0) { 
					myTile = myTile.towardTheNest();
				}
				else {
					Hornet h = myTile.getHornet(); //found hornet, does damage
					h.takeDamage(this.damage);
					return true;
				}
			}	
		}
		return false;
	}
	
	public boolean equals(Object obj) {
		if (!(super.equals(obj))){
			return false;
		}
		StingyBee stObj = (StingyBee)obj;
		return (stObj.damage == this.damage);
	}
}
