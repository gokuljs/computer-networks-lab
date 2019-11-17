import java.util.Scanner;
import java.util.*;
public class crc {
	
	public static void main(String args[])
	{
		int i,j;
		int generator[]=new int[20];   /// 111
	Scanner sc=new Scanner(System.in);
	int poly[]=new int[20];   // for taking normall polynomila 1101
	int b[]=new int[20];
	System.out.println("Enter the degree");
	int n=sc.nextInt(); // 4
	int m=n;
	System.out.println("Enter the polynomial");
	for(i=0;i<n;i++)
	{
		poly[i]=sc.nextInt();  // enter 1101
		
	}
	System.out.print("Enter the no.of generator elemts");
	int gen=sc.nextInt();  // enter 3
	System.out.println("Enter the generator");
	for(j=0;j<gen;j++)
	{
		generator[j]=sc.nextInt();  // enter 111
	}
	 int countgen=gen-1; //countgen=3-1=2
	 n=n+countgen; // 4+2=6
	 for(i=m;i<=n;i++)
	 {
		 poly[i]=0; //1101 two 00 gets apended =110100
	 }
	System.out.println("the sender poly array is"); // 
	for(i=0;i<n;i++)
	{
		System.out.println(poly[i]);
	}
	int a[]=new int[20]; //creating another array and passing 110100
	for(i=0;i<n;i++)
	{
		a[i]=poly[i]; //a[i]=110100
	}
	crc obj=new crc(); 
	obj.div(a,m,generator,n);// ur passing 110100,4,111,,6
	System.out.println("the remainder");
	for(i=0;i<n;i++)
	{
		System.out.println(a[i]);
	}
	for(i=0;i<3+m;i++)
	{
		poly[i]=poly[i]^a[i];
	}
	System.out.println("the RECIEVER array is");
	for(i=0;i<n;i++)
	{
		System.out.println(poly[i]);
	}
	for(i=0;i<n;i++)
	{
		poly[i]=b[i];
		
	}
	obj.check(b,m,generator,n);
	int flag=0;
	for(i=0;i<=n;i++)
	{
		if(b[i]!=0)
		{
			flag=1;
		}
		
	}
	if(flag==0)
	{
		System.out.println("No error");
	}
	else
		System.out.println("error");
	}
	
	void div(int a[],int m, int[] generator,int n)  //ur passing 110100,4,111,,6
	{
		int i, count=0;
		for(i=0;i<m;i++) // loops from 0 to 4
		{
			if(a[i]==generator[0]) //1==1
			{
				for(int j=i;j<4+i;j++) //j=1;j<4+i
				{
					a[j]=a[j]^generator[count++];
				}
				count=0;
				
			}
		}
	}
	void check(int b[],int m, int[] generator,int n)
	{
		int i, count=0;
		for(i=0;i<m;i++)
		{
			if(b[i]==generator[0])
			{
				for(int j=i;j<4+i;j++)
				{
					b[j]=b[j]^generator[count++];
				}
				count=0;
				
			}
		}
	}
}