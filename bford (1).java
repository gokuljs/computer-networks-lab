package pkg4;

import java.util.Scanner;

public class bford {
	
	public void shortest(int s,int a[][],int[] d,int n)
	{
		for(int i=1;i<n;i++)
		{
			d[i]=99;
		}
		d[s]=0;
		for(int k=0;k<n-1;k++)
		{
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					if(a[i][j]!=99)
					{
						if(d[j]>d[i]+a[i][j])
						{
							d[j]=d[i]+a[i][j];
						}
					}
				}
			}
		}
		System.out.println("The minimum distance : ");
		for(int i=0;i<n;i++)
		{
			System.out.print(d[i]+" ");
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(a[i][j]!=99)
				{
					if(d[j]>d[i]+a[i][j])
					{
						System.out.println("negative cycle");
					}
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of vertices");
		int n=sc.nextInt();
		System.out.println("Enter the adjacency matrix");
		int[][] a=new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				a[i][j]=sc.nextInt();
			}
		}
		int[] d=new int[n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(a[i][j]==0)
				{
					a[i][j]=99;
				}
			}
		}
		bford obj=new bford();
		obj.shortest(0, a, d, n);
	}

}
