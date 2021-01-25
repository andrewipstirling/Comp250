package assignment1;

public class BusyBee extends HoneyBee {
	
	
	public BusyBee(Tile pos) {
		super(pos,5,2); //hp = 5, food cost = 2
	}
	public boolean takeAction() {
		Tile myTile = this.getPosition();
		myTile.storeFood(2);
		return true;
	}
}
