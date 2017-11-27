/**
 * This is the PriorityQueue class. This class is responsible for making
 * a min heap and containing all its various functions such as 
 * add, remove, contains, etc. Almost every method in here will be used 
 * in some way by the Engine, which will allow the engine to manipulate
 * the heap however the user may want as long as it is allowed by the 
 * program. This is reused code from my previous project in which a 
 * max heap was made. This code was changed to become a min heap to 
 * act as a priority queue that will be used by the Dijkstra class 
 * when calculating the the shortest path between two cities.
 * 
 * @author blayala
 *
 */
public class PriorityQueue {
	/**
	 * This is an array that will represent the heap
	 */
	private QueueNode[] heap;
	/**
	 * The last index of the array that is representing the heap
	 */
	private int lastIndex;
	/**
	 * the number of swaps made while creating the heap using the optimal method
	 */
	private int optimalSwaps = 0;
	/**
	 * the number of swaps made while creating the heap using series of insertions
	 */
	private int seriesSwaps = 0;
	/**
	 * Boolean checking if the heap has been initialized
	 */
	private boolean initialized = false;
	/**
	 * Default size of the heap. Cannot be changed
	 */
	private static final int DEFAULT_SIZE = 100;
	

	/**
	 * Default constructor which will call on the second constructor while passing in the DEFAULT_SIZE
	 */
	public PriorityQueue() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * This constructor creates an array that will represent the heap.
	 * This is the beginning of creating a heap using a series of insertions
	 * @param startingSize the size that the initial heap will be
	 */
	public PriorityQueue(int startingSize) {
		
		QueueNode[] tempHeap = new QueueNode[startingSize + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	/**
	 * This is the third constructor and it creates a heap using the 
	 * optimal method. It does this by taking in an array of data and 
	 * inputing it into a un-heaped heap. The reheap method is then run
	 * recursively on the heap until the heap is heapified
	 * @param data the array of data that the heap will be 
	 */
	public PriorityQueue(QueueNode[] data){
		this((data.length));
		lastIndex = heap.length - 1;
		assert initialized = true;
	
		for(int i = 0; i < data.length; i++) 
			heap[i + 1] = data[i];
		
		for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
			reheap(rootIndex);	
	}
	
	/**
	 * This method adds to the heap, keeping the array a min heap 
	 * based on distance between two cities, making all the 
	 * swaps needed to make the array a min heap
	 */
	public void add(City cityEntry, int distance) {
		
		checkInitialization();
	
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while((parentIndex > 0) && (distance < heap[parentIndex].distance)) {
			heap[newIndex] = heap[parentIndex];
			seriesSwaps++;
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		}
		
		heap[newIndex] = new QueueNode(cityEntry, distance);
		lastIndex++;
	
	}
	
	/**
	 * This removes the city with the smallest distance in the array representing the heap
	 * @return the city with smallest distance
	 */
	public QueueNode removeMin() {
		
		checkInitialization();
		
		QueueNode root = null;
		if(!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
			
			if((lastIndex + 1) <= heap.length )
				heap[lastIndex + 1] = null;
		}
		
		return root;
		
	}
	
	/**
	 * This removes the city with the smallest distance that matches 
	 * the city code provided. 
	 * @param cityCode the city code of the city that needs to be removed
	 * @return returns the city that was deleted
	 */
	public QueueNode remove(int cityCode) {
		
		QueueNode deleted = null;
		
		if(!isEmpty()) {
			int removeIndex = searchRemoveIndex(cityCode);
			deleted = heap[removeIndex];
			heap[removeIndex] = heap[lastIndex];
			lastIndex--;
			reheap(removeIndex);
			
			if((lastIndex + 1) <= heap.length )
				heap[lastIndex + 1] = null;
		}
	
		return deleted;
	}
	
	/**
	 * This method searches through the heap and searches for the index
	 * that contains the city that matches the city code
	 * @param cityCode the city code of the city that needs to be removed
	 * @return the index that contains the city that needs to be removed
	 */
	private int searchRemoveIndex(int cityCode) {
		
		int removalIndex = 0;
	
		for(int i = 1; i <= lastIndex; i++) {
			
			if(cityCode == heap[i].getCityCode())
				removalIndex = i;
		}
		return removalIndex;
	}
	
	
	/**
	 * returns the city with the smallest distance
	 * @return the city with smallest distance
	 */
	public QueueNode getMin() {
		QueueNode root = null;
		if(!isEmpty())
			root = heap[1];
		return root;
	}
	
	/**
	 * checks if the heap is empty. If the last index of the heap is less than
	 * zero, that means that the heap is empty
	 * @return if the heap is empty
	 */
	public boolean isEmpty() {
		
		return lastIndex < 1;
	}

	/**
	 * gets the size of the heap
	 * @return last index of the array heap
	 */
	public int getSize() {
		
		return lastIndex;
	}
	
	/**
	 * checks if array has capacity
	 * @param startingSize the starting size
	 * @return fits boolean of whether the new element fits
	 */
	public boolean checkCapacity(int startingSize){
		boolean fits = false;
		if(startingSize <= DEFAULT_SIZE)
			fits = true;
		
		return fits;
	}
	
	/**
	 * checks if heap is initialized
	 * @return if heap is initialized
	 */
	public boolean checkInitialization() {
		return initialized;
	}
	
	/**
	 * This method reheaps a heap that is out of order at any root given.
	 * The engine enters a root and the reheap method will heapify the array
	 * from the root down. Method is also called upon when a element is removed.
	 * When a element is removed, this method will pass through the heap and 
	 * make sure a min heap is kept, if not it will transform it back into a 
	 * min heap.
	 * @param rootIndex the root of the heap or sub-heap
	 */
	private void reheap(int rootIndex) {
		
		boolean done = false;
		QueueNode orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while(!done && (leftChildIndex <= lastIndex )){
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			
				
			if((rightChildIndex <= lastIndex  && heap[rightChildIndex].getDistance() < heap[largerChildIndex].getDistance())) 
				largerChildIndex = rightChildIndex;
			
			if(orphan.distance > heap[largerChildIndex].distance) {
				heap[rootIndex] = heap[largerChildIndex];
				optimalSwaps++;
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
			}
			else
				done = true;
		}
		heap[rootIndex] = orphan;
	}
	
	/**
	 * returns the data in a particular index
	 * @param index searching for
	 * @return data in index
	 */
	public QueueNode getHeapIndexData(int index) {
		return heap[index];
	}
	public int getLastIndex(){
		return lastIndex;
	}
	/**
	 * returns array representing heap to engine
	 * @return heap the array that containts the heap
	 */
	public QueueNode[] getHeap() {
		return heap;
	}
	
	/**
	 * Erases the whole heap
	 */
	public void clean() {
		
		heap = null;
	}
	
	/**
	 * Checks for duplicates 
	 * @param entry being check for duplicates
	 * @return if the entry is already in heap
	 */
	public boolean contains(QueueNode node){
		boolean contains = false;
		for(int i = 0; i < heap.length; i++){
			if(heap[i] == node)
				contains = true;
		}
		return contains;
	}
	
	/**
	 * returns number of swaps made using series of insertions
	 * @return seriesSwaps the number of swaps made during series insertions
	 */
	public int getSeriesSwaps(){
		return seriesSwaps;
	}
	
	/**
	 * returns number of swaps made using optimal method
	 * @return optimalMethod swaps made using optimal method
	 */
	public int getOptimalSwaps(){
		return optimalSwaps;
	}
}

/**
 * This is the class of the node that will hold the information
 * in the heap. The Nodes contain the city and the distance that 
 * the city has to whatever other city.
 * @author blayala
 *
 */
class QueueNode{
	
	/**
	 * This is the city that is being checked
	 */
	City city;
	/**
	 * This is the city code of the city being checked
	 */
	int cityCode;
	/**
	 * This contains the current relaxed distance of the city being checked
	 */
	int distance;
	
	/**
	 * This is constructor of the node. It instantiates both the city and the relaxed distance
	 * @param city the city being checked
	 * @param distance the relaxed distance of the city being checked
	 */
	public QueueNode(City city, int distance) {
		
		this.city = city;
		this.distance = distance;
		cityCode = city.getCityNumber();
	}
	
	/**
	 * Gets the city being checked
	 * @return the city being checked
	 */
	public City getCity() {
		return city;
	}
	/**
	 * Gets the relaxed distance of the city being checked
	 * @return returns relaxed distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * Gets the city code of the city being checked
	 * @return the city code of the city being checked
	 */
	public int getCityCode() {
	return cityCode;
	}
	/**
	 * Overide of the toString method. Translates the node object into a readable string
	 */
	public String toString() {
		return ""+ city+ " "+ distance;
	}
}

