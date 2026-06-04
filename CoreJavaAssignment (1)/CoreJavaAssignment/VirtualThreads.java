public class VirtualThreads {
	
	public static void main(String[] args) throws InterruptedException {
		
		int totalThreads = 100000;
		
		long startTime = System.currentTimeMillis();
		
		Thread[] threads = new Thread[totalThreads];
		
		for(int i=0; i<totalThreads; i++) {
			int threadNum = i;
			threads[i] = Thread.startVirtualThread(() -> {
				if(threadNum % 10000 == 0) {
					System.out.println("Virtual Thread " + threadNum + " running");
				}
			});
		}
		
		// wait for all threads to finish
		for(Thread t : threads) {
			t.join();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("All 100,000 virtual threads completed");
		System.out.println("Time taken: " + (endTime - startTime) + " ms");
	}
	
}
