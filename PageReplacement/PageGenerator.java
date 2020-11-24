
/**
 * This class holds the reference string
 */

public class PageGenerator
{
	int[] referenceString;


	public PageGenerator(int count) {
		referenceString = new int[count];
	}

	public int[] getReferenceString() {
		/**
		 * This is the reference string
		 */
        	int[] str = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
        	return str;

	}
}
