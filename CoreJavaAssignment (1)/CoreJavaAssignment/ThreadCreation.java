public class ThreadCreation {
	
	public static void main(String[] args) {
		
		MyThread t1 = new MyThread("Thread-1");
		MyThread t2 = new MyThread("Thread-2");
		
		t1.start();
		t2.start();
	}
	
}

class MyThread extends Thread {
	
	String threadName;
	
	MyThread(String name) {
		this.threadName = name;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			System.out.println(threadName + " - Message " + i);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
