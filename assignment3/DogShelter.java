package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DogShelter implements Iterable<Dog> {
	public DogNode root;
	

	public DogShelter(Dog d) {
		this.root = new DogNode(d);
	}

	private DogShelter(DogNode dNode) {
		this.root = dNode;
	}


	// add a dog to the shelter
	public void shelter(Dog d) {
		if (root == null) 
			root = new DogNode(d);
		else
			root = root.shelter(d);
	}

	// removes the dog who has been at the shelter the longest
	public Dog adopt() {
		if (root == null)
			return null;

		Dog d = root.d;
		root =  root.adopt(d);
		return d;
	}
	
	// overload adopt to remove from the shelter a specific dog
	public void adopt(Dog d) {
		if (root != null)
			root = root.adopt(d);
	}


	// get the oldest dog in the shelter
	public Dog findOldest() {
		if (root == null)
			return null;
		
		return root.findOldest();
	}

	// get the youngest dog in the shelter
	public Dog findYoungest() {
		if (root == null)
			return null;
		
		return root.findYoungest();
	}
	
	// get dog with highest adoption priority with age within the range
	public Dog findDogToAdopt(int minAge, int maxAge) {
		return root.findDogToAdopt(minAge, maxAge);
	}

	// Returns the expected vet cost the shelter has to incur in the next numDays days
	public double budgetVetExpenses(int numDays) {
		if (root == null)
			return 0;
		
		return root.budgetVetExpenses(numDays);
	}
	
	// returns a list of list of Dogs. The dogs in the list at index 0 need to see the vet in the next week. 
	// The dogs in the list at index i need to see the vet in i weeks. 
	public ArrayList<ArrayList<Dog>> getVetSchedule() {
		if (root == null)
			return new ArrayList<ArrayList<Dog>>();
			
		return root.getVetSchedule();
	}


	public Iterator<Dog> iterator() {
		return new DogShelterIterator(this);
	}
	
			
	

	public class DogNode {
		public Dog d;
		public DogNode younger;
		public DogNode older;
		public DogNode parent;
		private boolean notVisited;

		public DogNode(Dog d) {
			this.d = d;
			this.younger = null;
			this.older = null;
			this.parent = null;
			notVisited = true;
		}

		private DogNode rightRotation() {
			DogNode newRoot = this;
			DogNode oldRoot = this.parent;
			DogNode parent = this.parent.parent;
			//Switch pointers
			oldRoot.younger = newRoot.older;
			if(newRoot.older != null) {
				newRoot.older.parent = oldRoot;
			}
			newRoot.older = oldRoot;
			oldRoot.parent = newRoot;
			newRoot.parent = parent;
			if(parent!=null) {
				if(parent.younger == oldRoot)
					parent.younger = newRoot;
				else
					parent.older = newRoot;
			}
			
			return newRoot;
	
		}
		private DogNode leftRotation() {
			DogNode newRoot = this;
			DogNode oldRoot = this.parent;
			DogNode parent = this.parent.parent;
			
			oldRoot.older = newRoot.younger;
			if(newRoot.younger!=null) {
				newRoot.younger.parent = oldRoot;
			}
			newRoot.younger = oldRoot;
			oldRoot.parent = newRoot;
			newRoot.parent = parent;
			if(parent!=null) {
				if(parent.younger==oldRoot) 
					parent.younger=newRoot;
					
				else
					parent.older=newRoot;
			}
			
			return newRoot;
			
		}
	
		public DogNode shelter(Dog d) {
			DogNode newRoot = this;
			
			if(d.getAge()>this.d.getAge()) {
				if(this.older!=null) {
					this.older = this.older.shelter(d);
				}
				
				else {
					this.older = new DogNode(d);
					this.older.parent = this;
				}
				if(this.older.d.getDaysAtTheShelter()>this.d.getDaysAtTheShelter()) {
					newRoot= this.older.leftRotation();
				}
			}
			else {
				if(this.younger!=null) {
					this.younger = this.younger.shelter(d);
					
				}
					
				else {
					this.younger = new DogNode(d);
					this.younger.parent = this;
		
				}
				if(this.younger.d.getDaysAtTheShelter()>this.d.getDaysAtTheShelter()) {
					newRoot = this.younger.rightRotation();
				
				}
			}
			
		return newRoot;	
		}
		
		public DogNode adopt(Dog d) {
            // ADD YOUR CODE HERE
			
			if(this.d.compareTo(d)>0) {
				if(this.younger!=null) {
					this.younger = this.younger.adopt(d);
				}
				else {
					//TODO if dog is not in tree
				}
					
			}

			else if(this.d.compareTo(d)<0) {
				if(this.older!=null) {
					this.older = this.older.adopt(d);
				}
				else {
					//TODO if dog is not in tree
				}
			}	
			else {	//found dog
				//only one child, not root, and child is younger
				if(this.older==null && this.younger==null) {
					return null;

				}
				else if(this.older==null && this.younger!=null) {
					
					if(this.parent == null) { //is a root node
						this.younger.parent = null;
						return this.younger;
					}
					else if(this == this.parent.younger) { //is younger child
						this.younger.parent= this.parent;
						this.parent.younger = this.younger;
						return this.younger;
					}
					else { //is older child
						this.younger.parent= this.parent;
						this.parent.older = this.younger;
						return this.younger;
					}
					
				}
				else if(this.older!=null && this.younger==null) {
					if(this.parent == null) {
						this.older.parent = null;
						return this.older;
					}
					else if(this == this.parent.younger) { //is younger child
						this.older.parent= this.parent;
						this.parent.younger = this.older;	
						return this.older;
					}
					else {
						this.older.parent= this.parent;
						this.parent.older = this.older;
						return this.older;
					}
				}
				else {
					DogNode newRoot = this.younger.findOldestDog();
					if(this.parent!=null) {
						if(this.parent.younger == this) {
							this.parent.younger = newRoot;
						}
						else {
							this.parent.older = newRoot;
						}
					}
					newRoot.younger = this.younger;
					newRoot.older = this.older;
					this.younger.parent = newRoot;
					this.older.parent = newRoot;
					
					if(newRoot.younger.d.getDaysAtTheShelter()>newRoot.d.getDaysAtTheShelter() 
							&& newRoot.older.d.getDaysAtTheShelter()>newRoot.d.getDaysAtTheShelter()) {
						if(newRoot.older.d.getDaysAtTheShelter()>newRoot.younger.d.getDaysAtTheShelter()) {
							newRoot = newRoot.older.leftRotation();
							newRoot = newRoot.younger.younger.rightRotation();
							return newRoot.parent;
						}
						else {
							newRoot = newRoot.younger.rightRotation();
							newRoot = newRoot.older.older.leftRotation();
							return newRoot.parent;
						}
					}
					else if(newRoot.younger.d.getDaysAtTheShelter()
							>newRoot.d.getDaysAtTheShelter()) {
						newRoot = newRoot.younger.rightRotation();
						//return newRoot;
					}
					else if(newRoot.older.d.getDaysAtTheShelter()
							>newRoot.d.getDaysAtTheShelter()) {
						newRoot = newRoot.older.leftRotation();
						//return newRoot;
					}
					
					return newRoot;
				}
					
			
			}
            return this; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
		private DogNode findOldestDog() {
			if(this.older!=null) {
				return this.older.findOldestDog();
			}
			else {
				if(this.younger!=null) { //oldest node has younger child
					this.parent.older = this.younger;
					this.younger.parent = this.parent;
					this.younger = null;
					this.parent = null;
				}
				else {//oldest node is a leaf
					this.parent.older = null;
					this.parent = null;
				}

				return this;
			}
		}
		public Dog findOldest() {
            // ADD YOUR CODE HERE
			if(this.older!=null) {
				return this.older.findOldest();
			}
			else
				return this.d;
            
		}

		public Dog findYoungest() {
            // ADD YOUR CODE HERE
			if(this.younger!=null) {
				return this.younger.findYoungest();
			}
			else
				return this.d;// DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public Dog findDogToAdopt(int minAge, int maxAge) {
            if(this.d.getAge()>= minAge  && this.d.getAge()<=maxAge) {
            	return this.d;
            }
            else if (this.d.getAge()>maxAge) {
            	if(this.younger != null)
            		return this.younger.findDogToAdopt(minAge,maxAge);
            	else
            		return null;
            }
            else {
            	if(this.older != null)
            		return this.older.findDogToAdopt(minAge,maxAge);
            	else
            		return null;
            }
           
		}
		
		public double budgetVetExpenses(int numDays) { //TODO: test more rigourously
            double cost = 0.0;
            if(this.d.getDaysToNextVetAppointment()<= numDays) {
            	cost += this.d.getExpectedVetCost();
            	
            }
            
            if(this.older!=null) {
            	cost+= this.older.budgetVetExpenses(numDays);
            }
            if(this.younger!= null) {
            	cost+= this.younger.budgetVetExpenses(numDays);
            }
            return cost; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public ArrayList<ArrayList<Dog>> getVetSchedule() {
			ArrayList<Dog> dogsToSeeVet = new ArrayList<Dog>(this.d.getDaysAtTheShelter());
			
			DogShelterIterator dog = this.dogIterator();
            int maxIndex = 0;
			
            while(dog.hasNext()) {

            	int index = (dog.cur.d.getDaysToNextVetAppointment())/7;
            	if(index>maxIndex) {
            		maxIndex = index;
            	}
            	dogsToSeeVet.add(dog.cur.d);
            	dog.next();
	
            }
			ArrayList<ArrayList<Dog>> vetSchedule = new ArrayList<ArrayList<Dog>>(maxIndex);
			while(vetSchedule.size()<=maxIndex) {
				vetSchedule.add(new ArrayList<Dog>());
			}
			
			for(Dog d:dogsToSeeVet) {
				int index = (d.getDaysToNextVetAppointment())/7;
				vetSchedule.get(index).add(d);
				
			}
			return vetSchedule;
			
            
            
		}
		private DogShelterIterator dogIterator(){
			return new DogShelterIterator(this);
		}
		
		public String toString() {
			String result = this.d.toString() + "\n";
			if (this.younger != null) {
				result += "younger than " + this.d.toString() + " :\n";
				result += this.younger.toString();
			}
			if (this.older != null) {
				result += "older than " + this.d.toString() + " :\n";
				result += this.older.toString();
			}
			/*if (this.parent != null) {
				result += "parent of " + this.d.toString() + " :\n";
				result += this.parent.d.toString() +"\n";
			}*/
			return result;
		}
		
	}


	private class DogShelterIterator implements Iterator<Dog> {
			DogNode cur;
			
		private DogShelterIterator(DogShelter shelter){
			cur = shelter.root;
			while(cur.younger!=null) {
				cur = cur.younger;
			}
		}
		private DogShelterIterator(DogNode root) {
			cur = root;
			while(cur.younger!=null) {
				cur = cur.younger;
			}
		}
		
		public Dog next(){
            if(!hasNext())
            	throw new NoSuchElementException();
            
            
            cur.notVisited = false; //sets flag on node for later
            DogNode tmpNode = cur;
            
            if(cur.older!= null) { //gets smallest from right subtree
            	cur = cur.older; 
            	while(cur.younger!=null) {
            		cur = cur.younger;
            	}
            	
            		
            }
            else { //if no subtree we know we have to go back up
            	
            	while(cur.parent!=null) { //search for non-visited node
            		cur = cur.parent;
            		if(cur.notVisited) {
            			break;
            		}
            		
            	}
            	if(!(cur.notVisited)) //if there are no more non-visited trees
            		cur = null;
            }
            

            return tmpNode.d; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public boolean hasNext() {
            return (cur != null);
			// DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

	}

}
