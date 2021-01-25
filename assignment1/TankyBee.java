package assignment1;

public class TankyBee extends HoneyBee{
	private int damage;
	private int armor;
	
	public TankyBee(Tile pos, int d, int a) {
		super(pos,30,3); //health = 30, cost = 3
		this.damage = d;
		this.armor = a;
	}
	
	public boolean takeAction() {
		Tile myTile = this.getPosition();
		if (myTile.getNumOfHornets()>0) {
			myTile.getHornet().takeDamage(this.damage);
			return true;
		}
		return false;	
	}
	
	public void takeDamage(int damage) {
		double damageMulti = (100.0/(100.0+this.armor));
		damage = (int)((int)damage*damageMulti);
		super.takeDamage(damage);
	}
	public boolean equals(Object obj) {
		if (!(super.equals(obj))){
			return false;
		}
		TankyBee tbObj = (TankyBee)obj;
		boolean isequal = ((tbObj.armor == this.armor) && (tbObj.damage == this.damage));
		return isequal;
	}

}
