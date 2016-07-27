import java.util.ArrayList;
import java.util.List;

public class AddingAndRemoving {

	public static void main(String[] args) {
		CreateThread myThread = new CreateThread();
		Thread adder = new Thread(myThread);
		adder.setName("adder");
		Thread remover = new Thread(myThread);
		remover.setName("remover");
		adder.start();
		remover.start();
	}
}

class CreateThread implements Runnable {
	List<Integer> items = new ArrayList<>();

	@Override
	public void run() {
		for (int num = 0; num < 11; num++) {
			if (Thread.currentThread().getName().equals("remover")) {
				remove();
			} else if (Thread.currentThread().getName().equals("adder")) {
				add(num);
			}
		}
	}

	public void add(int element) {
		synchronized (CreateThread.class) {
			items.add(element);
			System.out.println("Adding " + element);
			CreateThread.class.notify();
		}
	}

	public void remove() {
		synchronized (CreateThread.class) {
			if (items.isEmpty()) {
				try {
					CreateThread.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Removing " + items.remove(0));
		}
	}
}
