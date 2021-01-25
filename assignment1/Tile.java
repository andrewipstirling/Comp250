package assignment1;

public class Tile {
	private int food;
	private boolean isBeeHive;
	private boolean isHornetNest;
	private boolean isOnPath;
	private Tile hornetToBee;
	private Tile beeToHornet;
	private HoneyBee bee;
	private SwarmOfHornets swarm;
	
	public Tile() {
		this.food = 0;
		this.isBeeHive = false;
		this.isHornetNest = false;
		this.isOnPath = false;
		this.hornetToBee = null;
		this.beeToHornet = null;
		this.bee = null;
		this.swarm = null;
					
	}
	public Tile(int food, boolean isHive, boolean isNest,boolean isOnPath, Tile hornetToBee, Tile beeToHornet, HoneyBee bee, SwarmOfHornets swarm) {
		this.food = food;
		this.isBeeHive = isHive;
		this.isHornetNest = isNest;
		this.isOnPath = isOnPath;
		this.hornetToBee = hornetToBee;
		this.beeToHornet = beeToHornet;
		this.bee = bee;
		this.swarm = swarm;
	}
	
	public boolean isHive() {
		return this.isBeeHive;
	}
	public boolean isNest() {
		return this.isHornetNest;
	}
	public void buildHive() {
		this.isBeeHive = true;
	}
	public void buildNest() {
		this.isHornetNest = true;
	}
	public boolean isOnThePath() {
		
		return this.isOnPath;
	}
	public Tile towardTheHive() {
		if (!(this.isOnPath)) {
			return null;
		}
		return this.hornetToBee;
	}
	
	public Tile towardTheNest() {
		if (!(this.isOnPath)) {
			return null;
		}
		return this.beeToHornet;
	}
		
	
	public void createPath(Tile twdHive, Tile twdNest) {
		this.hornetToBee = twdHive;
		this.beeToHornet = twdNest;
		this.isOnPath = true;
	}
	public int collectFood() {
		int tempFood = this.food;
		this.food = 0;
		return tempFood;
	}
	public void storeFood(int moreFood) {
		this.food += moreFood;
	}
	public HoneyBee getBee() {
		if (this.bee ==null) {
			return null;
		}
		else {
			return this.bee;
		}
		
	}
	public Hornet getHornet() {
		return this.swarm.getFirstHornet();
	}
	public int getNumOfHornets() {
		if (this.swarm ==null) {
			return 0;
		}
		else {
			return this.swarm.sizeOfSwarm();
		}

	}
	public boolean addInsect(Insect insect) {
		boolean placed = false; 

		if (insect instanceof HoneyBee) {
			if((this.bee == null && !this.isHornetNest)) {
				this.bee = (HoneyBee)insect;	
				this.bee.setPosition(this);
				placed = true;
			}
		}
		if (insect instanceof Hornet) {
			if (((this.isHornetNest || this.isBeeHive) || this.isOnPath)) {
				
				if(this.swarm == null) { //adds swarm to tile if there is no swarm there	
					this.swarm = new SwarmOfHornets();
				}
				
				this.swarm.addHornet((Hornet)insect);
				insect.setPosition(this);
				
				//this.swarm.addHornet((Hornet)insect);
				placed = true;
			}
		}
		return placed;	
	}
	public boolean removeInsect(Insect insect) {
		
		boolean removed = false;
		
		if (insect instanceof HoneyBee ) {
			if (this.bee.equals(insect)) {
				this.bee.setPosition(null);
				this.bee = null;
				removed = true;
				
			}
		}
		if (insect instanceof Hornet) {
			Hornet h = (Hornet)insect;
			this.swarm.removeHornet(h);
			h.setPosition(null);
			
			if (this.swarm.sizeOfSwarm() == 0) { //removes swarm from tile if swarm is empty
				this.swarm = null;
			}
			
			removed = true;
		}
		return removed;
	}
	/*
	public String toString() {
		String s = "";
		String s2 = "";
		if (this.bee != null) {
			s = this.bee.toString();
		}
		if (this.swarm != null) {
			s2 = this.swarm.toString();
		}
		return ("Bee- " + s + " Swarm- " + s2);
	}
	*/

}
