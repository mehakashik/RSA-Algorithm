import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

public class Signature_40130006 
{

	//SHA-256 function
	public static String SHA256() throws NoSuchAlgorithmException
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
        	System.out.print("Enter Message: ");
        	String in = sc.nextLine();
        	System.out.println("Message Entered: "+in);

        	MessageDigest md = MessageDigest.getInstance("SHA-256");
        	md.update(in.getBytes());
        	byte[] digest = md.digest();
        	StringBuffer sb = new StringBuffer();
        
        	sc.close();
        
        	for (byte b : digest)
        	{
            
        		sb.append(String.format("%02x", b & 0xff));
        
        	}

        	return sb.toString();
        
	}
	
	//function to generate  p and q
	private static BigInteger RandomPrime() 
	{
		
		BigInteger prime = BigInteger.probablePrime(512, new Random());
		return prime;
		
	}
	
	//function to generate random e
	public static BigInteger GenE(BigInteger phi) 
	{
		
		Random rand = new Random();
		BigInteger e = new BigInteger(1024, rand);
		
		do 
		{
			
			e = new BigInteger(1024, rand);
			
			//if phi<e we need to look for new e
			while (e.min(phi).equals(phi)) 
			{ 
			
				e=new BigInteger(1024, rand);
				
			}
		
		} 
		
		while (!e.gcd(phi).equals(BigInteger.ONE)); //if gcd(e,phi) isn't 1 then stay in loop
		
		return e;
		
	}
	
	//function for padding
	public static String Pad(String s) 
    	{ 
  
        	String pad="0001ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff003031300d060960864801650304020105000420";
        	String result;
        
        	StringBuffer sb = new StringBuffer();  
        	result = sb.append(pad+s).toString();
        
        	return result; 
    
    	}
	
	//function to remove paddding
	public static String Unpad(String s)
	{
		
		String pad="1ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff003031300d060960864801650304020105000420";
		String tempWord = pad; 
        	s = s.replaceAll(tempWord, "");
	
        	return s;
        
	}
	
	//function to convert hexstring to biginteger
	public static BigInteger HtoB(String s)
	{
		
        	BigInteger bi = new BigInteger(s,16);
        	
        	return bi;
		
	}
	
	//function to convert biginteger to hexstring
	public static String BtoH(BigInteger b)
	{
		
		String s = b.toString(16); 
	    
	    	return s;
		
	}
	
	public static void main(String[] args) throws Exception
	{
		
		BigInteger p,q,N,Phi,a,b,e,d,htob,sign,hash;
		
		BigInteger one = new BigInteger("1");
		
		p=RandomPrime(); 							//funtion call to generate random prime p
		
		System.out.println();
		System.out.println("p: "+p);
		
		q=RandomPrime(); 							//funtion call to generate random prime q
		
		System.out.println();
		System.out.println("q: "+q);
		
		N=p.multiply(q); 							//function call to compute the value of N
		System.out.println();
		System.out.println("N: "+N);
		
		a=p.subtract(one);
		b=q.subtract(one);
		
		Phi=a.multiply(b); 							//funtion call to compute the value of Phi(N)
		System.out.println();
		System.out.println("Phi(N): "+Phi);
		
		e=GenE(Phi); 								//function call to generate random prime e(public key) such that gcd(e,Phi(n)=1) & e<Phi(N)
		System.out.println();
		System.out.println("e: "+e);
		
		d=e.modInverse(Phi);							//function call to compute the value of d(private key)
		System.out.println();
		System.out.println("d: "+d);
		
		String s,pm,btoh,rp;
		
		s=SHA256(); 								//function call to hash the message with SHA-256
		System.out.println();
		System.out.println("Hashed Message: "+s);
		
		pm=Pad(s); 								//function call to Pad the hashed message
		System.out.println();
		System.out.println("Padded Hash Message: "+"0x"+pm);
		
		htob=HtoB(pm); 								//function call to convert from Hex String to BigInteger
		
		sign=htob.modPow(d, N); 						//function call to implement (s=m^d mod N)
		System.out.println();
		System.out.println("Signed Message: "+sign);
		
		//to verify
		hash=sign.modPow(e, N); 						//function call to obtain the unsigned BigInteger value (H(m)=s^e mod N)
		
		btoh=BtoH(hash); 							//function call to convert from BigInteger to Hex String
		
		rp=Unpad(btoh); 							//function call to remove the padding from the message
		System.out.println();
		System.out.println("Hash of the Message after Unpadding: "+rp);
		
	}
	
}
