//==============================================================================
/**
 * The <code>SubstitutionCipher</code> encrypts/dectrypts by substituting each 
 * character with a different character. This substitution depends on the random * shuffling of characters with an array that contains all possible characters. 
 *
 * @author Ana Verma 
 **/
//==============================================================================

//==============================================================================
//IMPORTS
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
//==============================================================================

//==============================================================================
public class SubstitutionCipher extends Cipher{
//==============================================================================
    
    //==========================================================================
    /**
     * The specialized constructor.
     * 
     * @param key The seed which is used in shuffling the ordrered array of 
     * characters.
     **/
    public SubstitutionCipher (long key){
	super(key);
    }
    
    //==========================================================================

    //==========================================================================
   /**
     * Replace each character in the original message by with a different 
     * character from the array of shuffled characters.The integer value of the 
     * character is the index of the new character t is replaced with from 
     * the shuffled array.
     *
     * @param cleartext The unencrypted source data.
     * @returns The ciphertext -- the encrypted result.
     **/
    public List<Character> encrypt (List<Character> cleartext){
     
	List<Character> ciphertext = new LinkedList<Character>();
	
	int[] code = createCode();
	shuffle(code);
	

	for(char clearchar : cleartext){
	    //the integer value of the clearchar is the index of the cipherchar
	    //in the shuffled array 
	    char cipherchar = (char)code[clearchar];
	    ciphertext.add(cipherchar);
	}
	
	return ciphertext;
    }
    //==========================================================================

    //==========================================================================
    /**
     * Replace each character in the given ciphertext by reversing the role of 
     * the character's integer value, and the position. 
     *
     * @param ciphertext The encrypted source data.
     * @returns The cleartext -- the decrypted result.
     **/    
    public List<Character> decrypt (List<Character> ciphertext){
	
	List<Character> cleartext = new LinkedList<Character>();
	
	int[] code = createCode();
	shuffle(code);

	//inverseCode is an array with the indices and values of the "code" 
	//array are inverted
	int[] inverseCode = new int[266];

	for(int i = 0; i< code.length; i += 1){
	    inverseCode[code[i]] = i;
	}
	
	
	for(char cipherchar : ciphertext){
	    char clearchar = (char)inverseCode[cipherchar];
	    cleartext.add(clearchar);
	}
	
	
	return cleartext;

    }
    //==========================================================================

    //==========================================================================
    //HELPER METHODS

    //==========================================================================
    /**
     * This helper method creates an array of characters in order of their 
     * integer values.
     *
     * @return array of characters 
     **/
    public int[] createCode(){
	int[] code = new int[256];
	
	for(int i = 0; i < code.length; i += 1){
	    code[i] = i;
	}
	
	return code;
    }
    //==========================================================================
    /**
     * This is a helper method of the helper method, shuffle. It takes in two 
     * indices and an integer array, and switches the values of the two indices
     * entered.
     *
     * @param fixedIndex One of the indices with which the swap is to occur.
     * @param randomIndex The other index with which the swap is to occur. 
     *                    This index was "randomly" picked by an earlier method.
     * @param code The array of characters in order. 
     *
     **/
    private void swap (int fixedIndex, int randomIndex, int[] code){
	int a = code[fixedIndex];
	code[fixedIndex] = code[randomIndex];
	code[randomIndex] = a;
    }
    //==========================================================================
    /**
     * Helper method that shuffles the contents of the ordered array of 
     * all possible characters.
     *
     * @param code The ordered array of all possible characters.
     **/

    private void shuffle (int[] code){
	
	//the random object with the seed 
	Random r = new Random(getKey());
	
	for(int i = code.length-1; i > 0; i -= 1){
	    //the max number of characters is the max limit of the random 
	    //number generator 
	    int swapIndex = r.nextInt(code.length);
	    swap(i,swapIndex % 256,code);
	}
    }
    //==========================================================================
}
