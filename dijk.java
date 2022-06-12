import java.util.Scanner;
import java.util.Arrays;

public class dijk{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Dijkstra d = new Dijkstra(20);
		d.input("a","b",2);
		d.input("a","e",5);
		d.input("b","c",2);
		d.input("b","f",5);
		d.input("c","d",3);
		d.input("c","g",5);
		d.input("d","i",6);
		d.input("e","f",2);
		d.input("e","j",1);
		d.input("f","g",2);
		d.input("g","o",3);
		d.input("g","h",2);
		d.input("h","i",1);
		d.input("i","t",3);
		d.input("i","r",10);
		d.input("t","r",11);
		d.input("j","k",4);
		d.input("k","l",5);
		d.input("l","m",4);
		d.input("o","p",2);
		d.input("o","n",3);
		d.input("n","m",3);
		d.input("m","q",2);
		d.input("q","p",8);
		d.input("p","r",6);
		d.input("r","s",5);
		
		
		System.out.print("�� ǰ��:"); 
		String list = sc.nextLine(); 
		String []shoplist=list.split("");
		d.rearray(shoplist);
	 
	}
}

class Dijkstra {
	Scanner sc = new Scanner(System.in);
	private int n; 
	private int[][] weight; 
	private String[] saveRoute;
	private String[] vertex = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"};
	
	
	public Dijkstra(int n) {
		super();
		this.n = n; 
		weight = new int[n][n]; 
		saveRoute = new String[n];
	}
	
	public int stringToInt(String s) {
		int x = 0;
		for(int i=0; i<vertex.length; i++) {
			if(vertex[i]==s) x=i;
			
		}
		return x;
	}
	public void rearray(String[] r) {
		int []array = new int[r.length];
		for(int i=0;i<r.length;i++) {
			for(int j=0;j<vertex.length;j++) {
				if(r[i].equals(vertex[j])) {
					array[i]=j;
					
				}
			}
		}
		Arrays.sort(array);
		System.out.print("��� ����:"); 
		String start = sc.next();
		if(start.equals("i")) { 
			  algorithm("i", vertex[array[0]]);
			  
			  for(int i=0;i<array.length-1;i++) { 
				  algorithm(vertex[array[i]], vertex[array[i+1]]); 
				 } 
			  }
		else if(start.equals("t")) { 
			  algorithm("t", vertex[array[0]]);
			  for(int i=0;i<array.length-1;i++) { 
				  algorithm(vertex[array[i]], vertex[array[i+1]]); 
				  }
		  
		  }
			
		
	}
	
	public void input(String v1, String v2, int w) {
		int x1 = stringToInt(v1);
		int x2 = stringToInt(v2);
		weight[x1][x2] = w;
		weight[x2][x1] = w;
	}
	
	public void algorithm(String a, String k) {
		boolean[] visited = new boolean[n];
		int distance[] = new int[n]; 
		
		for(int i=0; i<n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
				
		
		int x = 0;
		for(int i=0; i<vertex.length; i++) {
			if(a.equals(vertex[i])) 
				x = i;
			
		}
		
		distance[x] = 0;
		visited[x] = true; 
		saveRoute[x] = vertex[x]; 
		
		int y = stringToInt(k);
		
		for(int i=0; i<n; i++) {
			if(!visited[i] && weight[x][i]!=0) {
				distance[i] = weight[x][i];
				saveRoute[i] = vertex[x]; 
			}
		}
		
		for(int i=0; i<n-1; i++) {
			int minDistance = Integer.MAX_VALUE; 
			int minVertex = -1; 
			for(int j=0; j<n; j++) {
				if(!visited[j] && distance[j]!=Integer.MAX_VALUE) {
					if(distance[j]<minDistance) {
						minDistance = distance[j];
						minVertex = j;
					}
				}
			}	
			visited[minVertex] = true; 
			for(int j=0; j<n; j++) {
				if(!visited[j] && weight[minVertex][j]!=0) {
					if(distance[j]>distance[minVertex]+weight[minVertex][j]) {
						distance[j] = distance[minVertex]+weight[minVertex][j];
						saveRoute[j] = vertex[minVertex]; 
					}
				}
			}
		}				
		
		
		for(int i=0;i<n;i++) {
			if(k.equals(vertex[i])==true) {
					System.out.println("���� ������ "+a+"���� ������ "+k+"������ �Ÿ� :"+ distance[i]);
					
			}
		}
		
		
		/*
		 * for(int j=0;j<3;j++) { list[j]=distance[i];
		 * System.out.println(j+" "+list[i]); }
		 */
		/*for(int j=0;j<1;j++) {
			for(int i=0;i<n;i++) {
				if(k.equals(vertex[i])==true) {
					list[j]=distance[i];
					//System.out.print(list[j]+" ");
					Arrays.sort(list);
					for(int num: list) {
						System.out.print(Arrays.toString(visited));
					}
					//compare(list[j]);
					//compare(a,k,distance[i]);
					
					//System.out.println("���� ������ "+a+"���� ������ "+k+"������ �Ÿ� :"+ distance[i]);
				}
				
			}
		
		}*/
		
		
		
					
		for(int i=0; i<n; i++) {
			if(k.equals(vertex[i])==true) {
			String route = "";
			System.out.println("���� ������ "+a+"���� ������ "+k+"������ ���");
			
			int index = i;
			while(true) {
				if(saveRoute[index] == vertex[index]) break; 
				route += " " + saveRoute[index];
				index = stringToInt(saveRoute[index]);
			}
			StringBuilder sb = new StringBuilder(route);
			System.out.print(sb.reverse() + vertex[i]);
			System.out.println("\n");
			}
		}
	}
}