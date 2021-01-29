
public class StringTest {

	public static void main(String[] args) {
		//doStringTest();
		doStringBufferTest();
		//doStringBuildTest();
	}
	
	public static void doStringTest() {
		String str = new String("hello");
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 30000; i++) {
			str = str + " world" + i;
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
	
	public static void doStringBufferTest() {
		StringBuffer sb = new StringBuffer("hello");
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 300000; i++) {
			sb = sb.append("world" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
	
	public static void doStringBuildTest() {
		StringBuilder sb = new StringBuilder();
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 300000; i++) {
			sb = sb.append("world" + i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
	
}
