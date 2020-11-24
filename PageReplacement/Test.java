/**
 * Test harness for LRU and FIFO page replacement algorithms
 */

public class Test
{
	public static void main(String[] args) {

		//page generator value accepts an int value for the number of pages, default is 3
		int numpages = 3;
		
		PageGenerator ref = new PageGenerator(numpages);
		int[] referenceString = ref.getReferenceString();

		/** Use either the FIFO or LRU algorithms */
		ReplacementAlgorithm fifo = new FIFO(numpages);
		ReplacementAlgorithm lru = new LRU(numpages);

		// output a message when inserting a page
		for (int i = 0; i < referenceString.length; i++) {
			lru.insert(referenceString[i]);
		}

		// output a message when inserting a page
		for (int i = 0; i < referenceString.length; i++) {
			fifo.insert(referenceString[i]);
		}

		// report the total number of page faults
		System.out.println("LRU faults = " + lru.getPageFaultCount());
		System.out.println("FIFO faults = " + fifo.getPageFaultCount());
	}
}
