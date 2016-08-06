
public class test {
	
	static void hanita(int n,String a,String b,String c){
		if (n==0) return;
		hanita(n-1, a, c, b);
		System.out.println("把" + n +"移到"+ c);
		hanita(n-1, b, a, c);
		
	}
	public static void main(String[] args) {
		hanita(3,"A","B","C");
	}
}
