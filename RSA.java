
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private BigInteger n, privateKey, publicKey;
	private int bitlength;
	public RSA(int bits)
	{
		bitlength = bits;
		SecureRandom randomNumber = new SecureRandom();

		//choosing prime number P		
		BigInteger primeNumber_P = new BigInteger(bitlength / 2, 100, randomNumber);
		System.out.println("PrimeNumer P value is"+primeNumber_P);

		//		choosing prime number Q
		BigInteger primeNumber_Q = new BigInteger(bitlength / 2, 100, randomNumber);
		System.out.println("PrimeNumer Q value is"+primeNumber_Q);

		//		calculating n i.e n=P*Q.
		n = primeNumber_P.multiply(primeNumber_Q);
		System.out.println("Value of n"+n);

		//calculating phi(n) i.e. phi(n) = (p-1)(q-1)
		BigInteger phi_Of_n = (primeNumber_P.subtract(BigInteger.ONE)).multiply(primeNumber_Q.subtract(BigInteger.ONE));
		System.out.println("Value of phi(n) "+phi_Of_n);

		//choosing public key 
		publicKey = new BigInteger(bitlength/2,10,randomNumber);

		//public key has to satisfy 2 conditions (1. 1 < public key < phi(n), 2. gcd(public key, phi(n) = 1)
		//so checking for the conditions and updating public key
		while (phi_Of_n.gcd(publicKey).compareTo(BigInteger.ONE)>0 &&
				publicKey.compareTo(phi_Of_n)<0)
		{
			publicKey = publicKey.add(new BigInteger("1"));
		}

		//calculating private key 
		privateKey = publicKey.modInverse(phi_Of_n);

		System.out.println("Private Key is "+privateKey);
		System.out.println("Public Key is "+publicKey);
	}
	/** Encrypt the given plaintext message. */
	public  BigInteger encrypt(BigInteger plainText_message) {
		return plainText_message.modPow(publicKey, n);
	}
	/** Decrypt the given ciphertext message. */
	public  BigInteger decrypt(BigInteger cipherText_message) {
		return cipherText_message.modPow(privateKey, n);
	}

	public static void main(String[] args) throws IOException
	{
		RSA rsa = new RSA(128);

		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

		System.out.println("enter the plaintext ");
		String plainText=bufferedReader.readLine();
		System.out.println("Plaintext: " + plainText);

		BigInteger plainTextinBytes = new BigInteger(plainText.getBytes());

		//calling encrypt method
		BigInteger ciphertext = rsa.encrypt(plainTextinBytes);
		System.out.println("Ciphertext: " + ciphertext);

		//calling decrypt method
		plainTextinBytes = rsa.decrypt(ciphertext);

		String decryptedText = new String(plainTextinBytes.toByteArray());
		System.out.println("Plaintext: " + decryptedText);
	}
}


