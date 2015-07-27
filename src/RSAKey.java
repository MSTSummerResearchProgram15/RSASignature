/*
Quan Gip
Based on the sample code from source: http://www.programcreek.com/java-api-examples/index.php?api=java.security.KeyPairGenerator
RSA signature scheme
Input: textfile
Output: encrypted textfile
*/

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.security.*; //import everything from security
import java.security.cert.*;

import javax.crypto.*;

public class RSAKey 
{
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException
	{
		//create the public key and private key
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		Cipher cipher = Cipher.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(2048, random); //KeySize = 2048
		KeyPair key = keyGen.generateKeyPair();
		PrivateKey privateKey = key.getPrivate();
		PublicKey publicKey = key.getPublic();
		
		//check value of private and public keys
		System.out.println("Private key : " + privateKey);
		System.out.println("Public key : " + publicKey);
		
		//Initialize the cipher in encryption mode
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		//feed the program with the text file
		String textFile = "Food.txt";
		String cipherFile = "cipherFood.txt";

		//Create Input stream
		FileInputStream fis = new FileInputStream(textFile);
		//Create Output stream
		FileOutputStream fos = new FileOutputStream(cipherFile);
		//Write the output to a file 
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);

		byte[] block = new byte[32];
		int i;
		while((i = fis.read(block)) != -1)
		{
			cos.write(block, 0, i);
		}
		cos.close();
		
		
	}
}
	
	
	
/*
	private final static BigInteger one = new BigInteger("1");
	private final static SecureRandom random = new SecureRandom(); //random number
	
	//now create the keys. Will replace later with KeyPairGenerator class
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger modulus;
	
	//generate the keys
	RSAKey(int N)
	{
		BigInteger p = BigInteger.probablePrime(N/2, random);
		BigInteger q = BigInteger.probablePrime(N/2, random);
		BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
		
		modulus = p.multiply(q);
		//now is the keys
		publicKey = new BigInteger("65537"); //just a common value
		privateKey = publicKey.modInverse(phi);				
	}
	
	//Let try to use keypairGenerator
	
	
	
	
	BigInteger encrypt(BigInteger x1)
	{
		return x1.modPow(publicKey, modulus);
	}
	
	BigInteger decrypt(BigInteger encrypted)
	{
		return encrypted.modPow(privateKey, modulus);
	}
	
	public String toString() 
	{
		String s = "";
		s += "public = " + publicKey + "\n";
		s += "private = " + privateKey + "\n";
		s += "modulus = " + modulus;
		return s;
	}
	
	public static void main(String[] args) 
	{	 
		RSAKey key = new RSAKey(100);
		System.out.println(key);
		 
		BigInteger x1;
		BigInteger x2;
		
		Scanner in = new Scanner(System.in);
		
		//Input 2 value
		System.out.println("Enter 2 numbers : ");
		x1 = in.nextBigInteger();
		x2 = in.nextBigInteger();

		BigInteger enc_x1 = key.encrypt(x1);
		BigInteger enc_x2 = key.encrypt(x2);
		BigInteger dec_x1 = key.decrypt(enc_x1);
		BigInteger dec_x2 = key.decrypt(enc_x2);
		   
		 
		BigInteger homomorphic = enc_x1.multiply(enc_x2);
		BigInteger dec_h = key.decrypt(homomorphic);
		   
		System.out.println("x1 = " + x1);
		System.out.println("x2 = " + x2);
		   
		System.out.println("E ( x1 ) = " + enc_x1);
		System.out.println("E ( x2 ) = " + enc_x2);
		System.out.println("E ( x1 ) * E ( x2 ) = " + homomorphic);
		System.out.println("D ( E ( x1 ) * E ( x2 ) ) = " + dec_h);
	}
		 
}
*/

