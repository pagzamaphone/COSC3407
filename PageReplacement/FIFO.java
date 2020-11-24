package whatever;

/**
 * This class implements the FIFO page-replacement strategy.
 */

public class FIFO extends ReplacementAlgorithm
{
	// FIFO list of page frames
	private FIFOList frameList;


	public FIFO(int pageFrameCount) {
		super(pageFrameCount);
		frameList = new FIFOList(pageFrameCount);
	}


	public void insert(int pageNumber) {
		frameList.insert(pageNumber);
		if (System.getProperty("debug") != null) {
			System.out.print("Inserting " + pageNumber);
			frameList.dump();
			System.out.println();
		}
	}
		
	class FIFOList
	{
		// the page frame list
		int[] pageFrameList;

		// the number of elements in the page frame list
		int elementCount;

		FIFOList(int pageFrameCount) {
			pageFrameList = new int[pageFrameCount];

            java.util.Arrays.fill(pageFrameList,-1);
			elementCount = 0;
		}


		void insert(int pageNumber) {
			if (!search(pageNumber)) {
				// an asterisk indicates a page fault
				if (System.getProperty("debug") != null)
					System.out.print("*");
				pageFaultCount++;
				pageFrameList[(elementCount++ % pageFrameCount)] = pageNumber;
			}
		}

		// dump the page frames
		void dump() {
			for (int i = 0; i < pageFrameList.length; i++)
				System.out.print("["+i+"]"+pageFrameList[i]+", ");
		}



		boolean search(int pageNumber) {
			boolean returnVal = false;

			for (int i = 0; i < pageFrameList.length; i++) {
				if (pageNumber == pageFrameList[i]) {
					returnVal = true;
					break;
				}
			}
			return returnVal;
		}
	}
}
