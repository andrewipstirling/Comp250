package assignment1;

public class SwarmOfHornets {
	private Hornet[] swarm;
	private int swarmSize;
	
	public SwarmOfHornets() {
		this.swarm = new Hornet[0];
		this.swarmSize = 0;
	}
	public int sizeOfSwarm() {
		return this.swarmSize;
	}
	public Hornet[] getHornets() {
		
		Hornet[] hornetList = new Hornet[this.swarmSize];
		
		for(int i = 0, k=0; i<this.swarm.length;i++) {
			if (this.swarm[i] != null) {
				hornetList[k] = this.swarm[i];
				k++;
			}
		}
		return hornetList;
	}
	
	public Hornet getFirstHornet() { 
		if (this.swarmSize==0) {
			return null;
		}
		else {
			return this.swarm[0];
		}
	}
	
	public void addHornet(Hornet h) {
		
		if (this.swarmSize == this.swarm.length) {
			resize();
		}
		this.swarm[this.swarmSize] = h;
		this.swarmSize++;
	}
	
	private void resize() {
		
		Hornet[] newSwarm = new Hornet[this.swarmSize+1];
		
		for (int i =0;i<this.swarm.length;i++) {
			newSwarm[i] = this.swarm[i];
		}
		this.swarm = newSwarm;
	}
	
	public boolean removeHornet(Hornet h) {
		boolean x = false;
		for (int i=0;i<this.swarm.length;i++) {
			if (this.swarm[i].equals(h)) {
				delHornet(i);
				x=true;
				break;
			}
		}
		this.swarmSize--;
		return x;
	}
	private void delHornet(int loc) {
		Hornet[] newSwarm = new Hornet[this.swarmSize-1];
		
		for (int i=0,k=0;i<newSwarm.length;i++,k++) {
			if (i == loc) {
				k++;
			}
			newSwarm[i] = this.swarm[k];
		}
		this.swarm = newSwarm;	
	}
	
	public String toString() {
		Hornet[] mySwarm = this.getHornets();
		
		String s = "";
		for(int i =0; i<mySwarm.length;i++) {
			s+= mySwarm[i].toString()+", ";
		}
		return s;
	}
	
}	
