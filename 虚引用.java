public class PhantomRef {
	public static PhantomRef ins;
	static ReferenceQueue<PhantomRef> phantomRefQueue = null; // 引用队列
	
	public static class TraceRefQueue extends Thread {
		@Override
		public void run() {
			while (true) {
				if(phantomRefQueue != null) {
					// 虚引用队列
					PhantomReference<PhantomRef> phantomQueue = null;
					try {
						phantomQueue = (PhantomReference<PhantomRef>)phantomRefQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(phantomQueue != null) {
						System.out.println("对象已被GC回收.");
					}
				}
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		ins = this;
		System.out.println("对象调用finalize()");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new TraceRefQueue());
		t1.setDaemon(true);
		t1.start();
		
		phantomRefQueue = new ReferenceQueue<PhantomRef>(); // 初始化引用队列
		ins = new PhantomRef();
		
		// 建立虚引用
		PhantomReference<PhantomRef> phantomQueue = new PhantomReference<PhantomRef>(ins, phantomRefQueue);
		
		System.out.println("第一次GC:");
		ins = null;
		System.gc();
		Thread.sleep(2000);
		if (ins == null) {
			System.out.println("对象为null.");
		} else {
			System.out.println("对象不为null.");
		}
		
		System.out.println("第二次GC:");
		ins = null;
		System.gc();
		Thread.sleep(2000);
		if (ins == null) {
			System.out.println("对象为null.");
		} else {
			System.out.println("对象不为null.");
		}
		
		return;
	}

}
