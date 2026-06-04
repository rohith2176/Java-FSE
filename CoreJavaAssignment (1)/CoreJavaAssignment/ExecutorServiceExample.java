import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		
		tasks.add(() -> {
			System.out.println("Task 1 running on: " + Thread.currentThread().getName());
			Thread.sleep(500);
			return 10 * 10;
		});
		
		tasks.add(() -> {
			System.out.println("Task 2 running on: " + Thread.currentThread().getName());
			Thread.sleep(300);
			return 20 * 20;
		});
		
		tasks.add(() -> {
			System.out.println("Task 3 running on: " + Thread.currentThread().getName());
			Thread.sleep(200);
			return 30 * 30;
		});
		
		try {
			List<Future<Integer>> futures = executorService.invokeAll(tasks);
			
			System.out.println("Results:");
			for(int i=0; i<futures.size(); i++) {
				System.out.println("Task " + (i+1) + " result: " + futures.get(i).get());
			}
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		executorService.shutdown();
		System.out.println("All tasks completed");
	}
	
}
