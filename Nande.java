package com.package1;

import java.util.*;	

public class Nande 
{
	
	//Prime Number Checker
	public static boolean Prime(int x)
	{
	    
		int i;
		
		//1 is not a prime
		if(x<=1) 
		{
			
			return false;
			
		}
		
		//2 is a prime
		if(x==2)
		{
			
			return true;
			
		}
		
		//skipping every even iteration
		for(i=3;i<x;i+=2) 
		{
			
			if(x%i==0)
			{
				
				return false;
				
			}
			
		}
		
		return true;

	}
	
	//GCD
	public static int GCD(int x,int y)
	{
		
		//base cases
		if (x == 0)
		{
			
			  return y;
			
		}
		
	    if (y == 0)
	    {
	     
	    	return x;
	    
	    }
	    
	    //if values are equal
	    if (x == y)
	    {
	        
	    	return x;
	    	
	    }
	       
	    if (x > y)
	    {
	            
	    	return GCD(x-y, y);
	        
	    }
	    
	    return GCD(x, y-x);
		
	}
	
	public static void main(String args[])
	{
		
		int p,q,N,Phi,e;
		
		Random r = new Random();
		
		p=r.nextInt(65535);
		q=r.nextInt(65535);
		
		//in case either one of them is not a prime
		while(Prime(p)==false || Prime(q)==false)
		{
			
			p=r.nextInt(65535);
			q=r.nextInt(65535);
			
		}
		
		
		System.out.println("p: "+p);
		System.out.println("q: "+q);
		
		N=p*q;
		
		System.out.println("N: "+N);
		
		Phi=(p-1)*(q-1);
		
		System.out.println("Phi(N): "+Phi);
		
		e=r.nextInt(Phi);
		
		//case in which 
		while(Prime(e)==false || GCD(e,Phi)!=1)
		{
			
			e=r.nextInt(Phi);
			
		}
		
		if(e<Phi)
		{
			
			System.out.println("e: "+e);
			
		}
				
	}

}
