public class ObjectRelive {
	public static ObjectRelive myObject;
	
	// 重写finalize()
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		myObject = this;
		System.out.println("对象调用finalize()");
	}
	
	public static void main(String[] args) throws InterruptedException {
		myObject = new ObjectRelive();
		myObject = null;
		
		System.out.println("第一次GC：");
		System.gc();
		Thread.sleep(2000);
		if (myObject == null) {
			System.out.println("myObject对象为null.");
		} else {
			System.out.println("myObject对象不为null.");
		}
		
		System.out.println("第二次GC：");
		myObject = null;
		System.gc();
		Thread.sleep(2000);
		if (myObject == null) {
			System.out.println("myObject对象为null.");
		} else {
			System.out.println("myObject对象不为null.");
		}
		
	}

}
