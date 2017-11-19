
public class PriorityQueue {
	/**
	 * This is an array that will represent the heap
	 */
	private QueueNode[] heap;
	/**
	 * The last index of the array that is representing 
	 * the heap
	 */
	private int lastIndex;
	/**
	 * the number of swaps made while creating the heap 
	 * using the optimal method
	 */
	private int optimalSwaps = 0;
	/**
	 * the number of swaps made while creating the heap 
	 * using series of insertions
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
	 * Default constructor which will call on the second 
	 * constructor while passing in the DEFAULT_SIZE
	 */
	public PriorityQueue() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * This constructor creates an array that will represent the heap.
	 * This is the beginning of creating a heap using a series of insertions
	 * @param startingSize
	 */
	public PriorityQueue(int startingSize) {
		//if(startingSize < DEFAULT_SIZE)
			//startingSize = DEFAULT_SIZE;
		//else
			//checkCapacity(startingSize);
		
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
	 * @param data
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
	 * This method adds to the heap with a series of integers, making all the 
	 * swaps needed to make the array a heap
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
		System.out.println(cityEntry);
		System.out.println("test3");
		
	}
	
	
	/**
	 * This removes the max number in the array representing the heap
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
	
	
	
	public QueueNode remove(int cityCode) {
		
		QueueNode deleted = null;
		if(!isEmpty()) {
			deleted = heap[searchRemoveIndex(cityCode)];
			heap[searchRemoveIndex(cityCode)] = heap[lastIndex];
			lastIndex--;
			reheap(searchRemoveIndex(cityCode));
			
			if((lastIndex + 1) <= heap.length )
				heap[lastIndex + 1] = null;
		}
		
		
		
		
		
		return deleted;
	}
	
	
	public int searchRemoveIndex(int cityCode) {
		System.out.println(cityCode);
		int removalIndex = 0;
		System.out.print("Test: ");
		for(int i = 1; i <= heap.length+1; i++) {
			System.out.print( heap[i].getCityCode() + " ");
			if(cityCode == heap[i].getCityCode())
				removalIndex = i;
		}
		return removalIndex;
	}
	
	
	/**
	 * This returns the max number in the array representing the max heap
	 */
	public QueueNode getMin() {
		QueueNode root = null;
		if(!isEmpty())
			root = heap[1];
		return root;
	}
	
	/**
	 * Checks if the array representing the heap is empty
	 */
	public boolean isEmpty() {
		
		return lastIndex < 1;
	}

	/**
	 * returns size of the heap
	 */
	public int getSize() {
		
		return lastIndex;
	}
	
	/**
	 * checks if array has capacity
	 * @param startingSize the starting size
	 * @return fits
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
	 * from the root down
	 * @param rootIndex the root of the heap or sub-heap
	 */
	private void reheap(int rootIndex) {
		boolean done = false;
		QueueNode orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while(!done && (leftChildIndex <= lastIndex )){
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			
				
			if((rightChildIndex <= lastIndex  && heap[rightChildIndex].distance < heap[largerChildIndex].distance)) 
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
	 * @return heap array
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
	 * @return seriesSwaps
	 */
	public int getSeriesSwaps(){
		return seriesSwaps;
	}
	
	/**
	 * returns number of swaps made using optimal method
	 * @return optimalMethod
	 */
	public int getOptimalSwaps(){
		return optimalSwaps;
	}
}

class QueueNode{
	
	City city;
	int cityCode;
	int distance;
	City previous;
	
	public QueueNode(City city, int distance, City previous) {
	
		this.city = city;
		this.distance = distance;
		this.previous = previous;
		
		
	}
	public QueueNode(City city, int distance) {
		
		this.city = city;
		this.distance = distance;
		cityCode = city.getCityNumber();
	}
	
	public City getCity() {
		return city;
	}
	public int getDistance() {
		return distance;
	}
	public int getCityCode() {
	return cityCode;
	}
	public String toString() {
		return ""+ city+ " "+ distance+" "+cityCode;
	}
}

