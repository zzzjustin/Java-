public class SoftRefInstance {
	
	public static class Student {
		public int studentID;
		public String studentName;
		
		public Student(int studentID, String studentName) {
			this.studentID = studentID;
			this.studentName = studentName;
		}
		
		@Override
		public String toString() {
			return "studnetID = " + studentID + ", studentName = " + studentName;
		}
	}
	public static void main(String[] args) {
		Student s = new Student(11, "张三");
		SoftReference<Student> studentSoft = new SoftReference<Student>(s);
		s = null;
		System.out.println(studentSoft.get());
		
		System.gc();
		System.out.println(studentSoft.get());
		
		byte[] data = new byte[1024*819*10];
		System.gc();
		System.out.println(studentSoft.get());
	}

}
