public class CaesarCipher extends RotationCipher
{
	public CaesarCipher(int key) {
		super(key);
	}

	public String encrypt(String plainText)
	{
		return rotate(shift, plainText);			
	}		
		
	public String decrypt(String cipherText)
	{
		return rotate(shift * -1, cipherText);			
	}	
	
	private String rotate(int shift, String plainText)
	{
	
		String cipherText = "";
		int length = plainText.length();
		int i = 0;
		
		for (i=0; i<length; i++)
		{
			char letter = plainText.charAt(i);
			if (Character.isLetter(letter))
			{
				if (Character.isUpperCase(letter))
				{
					char newLetter = (char)(letter + shift);
					if (newLetter < 'A')
					{
						cipherText = cipherText + (char) (letter + (26 + shift));
					}
					else if (newLetter > 'Z')
					{
						cipherText = cipherText + (char)(letter-(26-shift));
					}
					else
					{
						cipherText = cipherText + newLetter;
					}
				}
				else if (Character.isLowerCase(letter))
				{
					char newLetter = (char)(letter + shift);
					if (newLetter < 'a')
					{
						cipherText = cipherText + (char) (letter + (26 + shift));
					}
					else if (newLetter>'z')
					{
						cipherText = cipherText + (char)(letter-(26-shift));
					}
					else
					{
						cipherText = cipherText + newLetter;
					}
				}
			}
			else
			{
				cipherText = cipherText + letter;
			}
		}
		
		return cipherText;	
	}
}