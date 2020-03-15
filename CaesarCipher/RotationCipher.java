
public abstract class RotationCipher implements Cipher
{		
	protected int shift;
		
	public RotationCipher(int key)
	{
			
		shift = key;
	}
		
	public abstract String encrypt(String plainText);
		
	public abstract String decrypt(String cipherText);	
}