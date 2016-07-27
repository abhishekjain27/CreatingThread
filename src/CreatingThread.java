
public class CreatingThread {

	public static void main(String[] args) {
		System.out.println("Entered main");
		TryThread doit = new TryThread();
		Thread t = new Thread(doit);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed");
	}

}

class TryThread implements Runnable{

	@Override
	public void run() {
		System.out.println("Inside TryThread");
		try {
			System.out.println("Before sleeping");
			Thread.sleep(5000);
			System.out.println("After sleeping");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
