public class WeakRefInstance {
	
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
		WeakReference<Student> studentWeak = new WeakReference<Student>(s);
		s = null;
		System.out.println(studentWeak.get());
		
		System.gc();
		System.out.println(studentWeak.get());
	}

}
