package com.package1;

public class EncryptDecrypt 
{
	
	//function for square and multiply
	public static int SquareMult(int x,int y, int z)
    {
        
		// Base cases
        if (x==0)
        {
        
        	return 0;
        	
        }
         
        if (y==0)
        {
        	
        	return 1;
        	
        }
           
        // If y is even
        
        long l;
        if (y%2==0) 
        {
        	
            l=SquareMult(x, y/2, z);
            l=(l*l)%z;
        
        }

        // If y is odd
        else 
        {
            
        	l=x%z;
            l=(l*SquareMult(x,y-1,z)%z)%z;
        
        }
        
        return (int)((l+z)%z);
    
    }
	
	/*function for modular multiplicative inverse (doesn't work for large numbers for some reason)
	public static void MultInvAlt(int x, int y)
	{
		
		//we'll use brute force till it staisfies the condition
		int i;
		
		for (i=1;i<y;i++) 
	    {
	    	
	    	if((x*i)%y==1)
	    	{
	    		
	    		System.out.println("d: "+i);
	    		
	    	}
	    	
	    }
		
	}*/
	
	//function for modular multiplicative inverse
	public static int MultInv(int a, int b)
	{
		
		int b1=b; 
		int x=1, y=0; 

		if (b==1)
		{
			
			return 0;
			
		}

		while(a>1) 
		{ 
			 
			int u=a/b; 

			int v=b; 
			
			b=a%b; 
			a=v; 
			v=y; 

			y=x-u*y; 
			x=v; 
		
		} 

		// making x positive 
		if(x<0)
		{
			
			x=x+b1;
			
		}

		return x;
		
	}
	
	//function for String text to Hexadecimal
	public static String StringtoHex(String s)
	{
		
		StringBuffer sb = new StringBuffer();
	   
		//converting string to character array
	    char ch[] = s.toCharArray();
	    
	    for(int i = 0; i < ch.length; i++) 
	    {
	    	
	    	String hex=Integer.toHexString(ch[i]);
	        sb.append(hex);
	    
	    }
	    
	    return sb.toString();
		
	}
	
	//function for Hexadecimal to String text
	private static String HextoString(String s) 
	{
	    
		StringBuffer sb = new StringBuffer();
	    
	    for (int i=0; i<s.length(); i+=2) 
	    {
	        
	    	String str=s.substring(i, i+2);
	        sb.append((char)Integer.parseInt(str, 16));
	    
	    }
	    
	    return sb.toString();
	}
	
	//function for Hexadecimal to Integer 
	public static int HextoInt(String s)
	{  
		  
		return Integer.parseInt(s,16);  
		
	}
	
	//function for Integer to Hexadecimal
	public static String InttoHex(int x)
	{
		
		return Integer.toHexString(x);
		
	}
	
	public static void main(String[] args)
	{
		
		//message to be encrypted is valar morghulis
		
		//encryption
		
		String s1="val", s2="ar ", s3="mor", s4="ghu", s5="lis";
		String h1, h2, h3, h4, h5;
		int i1, i2, i3, i4, i5, e1, e2, e3, e4, e5;
		
		//string to hex
		h1=StringtoHex(s1);
		h2=StringtoHex(s2);
		h3=StringtoHex(s3);
		h4=StringtoHex(s4);
		h5=StringtoHex(s5);
		
		//Hex to Int
		i1=HextoInt(h1);
		i2=HextoInt(h2);
		i3=HextoInt(h3);
		i4=HextoInt(h4);
		i5=HextoInt(h5);
		
		int Np=93772001, ep=82133047; //N and e of partner
		
		//encrypt
		e1=SquareMult(i1,ep,Np);
		e2=SquareMult(i2,ep,Np);
		e3=SquareMult(i3,ep,Np);
		e4=SquareMult(i4,ep,Np);
		e5=SquareMult(i5,ep,Np);
		
		System.out.println("encrypted message: "+e1+" "+e2+" "+e3+" "+e4+" "+e5);
		
		//decryption
		
		int N=11961097, e=5275889, Phi=11954176, d;
		
		int x1=3640389, x2=5851418, x3=1026512, x4=3376978, x5=10316826, x6=3922440, d1, d2, d3, d4, d5, d6;
		
		//private key
		d=MultInv(e,Phi);
		System.out.println();
		System.out.println("d: "+d);
		
		//decrypt
		d1=SquareMult(x1,d,N);
		d2=SquareMult(x2,d,N);
		d3=SquareMult(x3,d,N);
		d4=SquareMult(x4,d,N);
		d5=SquareMult(x5,d,N);
		d6=SquareMult(x6,d,N);
		
		String u1,u2,u3,u4,u5,u6;
		String m1,m2,m3,m4,m5,m6;
		
		//Int to Hex
		u1=InttoHex(d1);
		u2=InttoHex(d2);
		u3=InttoHex(d3);
		u4=InttoHex(d4);
		u5=InttoHex(d5);
		u6=InttoHex(d6);
		
		//Hex to String
		m1=HextoString(u1);
		m2=HextoString(u2);
		m3=HextoString(u3);
		m4=HextoString(u4);
		m5=HextoString(u5);
		m6=HextoString(u6);
		
		System.out.println();
		System.out.println("decrypted message in chunks: "+m1+","+m2+","+m3+","+m4+","+m5+","+m6+",");
		System.out.println("decrypted message: "+m1+m2+m3+m4+m5+m6);
		
		//Signature
		String n1="Meh", n2="ak";
		
		//string to hex
		h1=StringtoHex(n1);
		h2=StringtoHex(n2);
				
		//Hex to Int
		i1=HextoInt(h1);
		i2=HextoInt(h2);
		
		e1=SquareMult(i1,d,N);
		e2=SquareMult(i2,d,N);
		
		System.out.println();
		System.out.println("signature encrypted (mine): "+e1+" "+e2);
		
		//Signature Partner Verification
		//17051708 51417491 68069610 25900023 8293988
		int p1=17051708, p2=51417491, p3=68069610, p4=25900023,p5=8293988;
		
		//decrypt
		d1=SquareMult(p1,ep,Np);
		d2=SquareMult(p2,ep,Np);
		d3=SquareMult(p3,ep,Np);
		d4=SquareMult(p4,ep,Np);
		d5=SquareMult(p5,ep,Np);
		
		//int to hex
		u1=InttoHex(d1);
		u2=InttoHex(d2);
		u3=InttoHex(d3);
		u4=InttoHex(d4);
		u5=InttoHex(d5);
		
		//hex to string
		m1=HextoString(u1);
		m2=HextoString(u2);
		m3=HextoString(u3);
		m4=HextoString(u4);
		m5=HextoString(u5);
		
		System.out.println();
		System.out.println("decrypted signature (partner): "+m1+m2+m3+m4+m5);
		
	}
	
}
