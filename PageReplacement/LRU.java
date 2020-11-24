package whatever;


public class LRU extends ReplacementAlgorithm
{
	// LRU list of page frames
	private LRUList frameList;


	public LRU(int pageFrameCount) {
		super(pageFrameCount);
		frameList = new LRUList(pageFrameCount);
	}


	public void insert(int pageNumber) {
		frameList.insert(pageNumber);
		if (System.getProperty("debug") != null) {
			System.out.print("Inserting " + pageNumber);
			frameList.dump();
			System.out.println();
		}
	}
		
	class LRUList
	{
		// the page frame list
		int[] pageFrameList;

		// the number of elements in the page frame list
		int elementCount;

		// the last page inserted
		int lastInserted = -1;

		LRUList(int pageFrameCount) {
			pageFrameList = new int[pageFrameCount];
            java.util.Arrays.fill(pageFrameList,-1);
			elementCount = 0;
		}


		void insert(int pageNumber) {
			int searchVal;

			// if we didn't find it, replace the LRU page
			if ((searchVal = search(pageNumber)) == -1) { 
				if (System.getProperty("debug") != null)
					System.out.print("*");

				pageFaultCount++;
				pageFrameList[(elementCount++ % pageFrameCount)] = pageNumber;
				lastInserted = pageNumber;
			}
			else if (pageNumber != lastInserted) 
				updatePageTable(searchVal);
		}


		void updatePageTable(int searchVal) {
			// first save the next page to be replaced  
			int savedPage = pageFrameList[elementCount % pageFrameList.length];

			int insertedPage = pageFrameList[searchVal];
	
			// if the page to be updated is the next page to be modified
			// just increment elementCount and return
			if (savedPage == insertedPage) {
				elementCount++;
				return;
			}

			// now copy the page just referenced to this position
			//pageFrameList[elementCount % pageFrameList.length] = pageFrameList[searchVal];
			if (System.getProperty("debug") != null) 
				System.out.println("sp = " + savedPage + " ec = " + elementCount + " sv = " + searchVal);

			// now shift all elements
			int rightIndex = searchVal;
			int leftIndex = (rightIndex==0) ? pageFrameList.length-1 : searchVal-1;
			
			while (rightIndex != (elementCount % pageFrameList.length) ) {
				pageFrameList[rightIndex] = pageFrameList[leftIndex];
				rightIndex = leftIndex;

				leftIndex = (leftIndex==0) ? pageFrameList.length-1 : leftIndex-1;
			}
			pageFrameList[rightIndex] = insertedPage;
			elementCount++;
		}

		void dump() {
			for (int i = 0; i < pageFrameList.length; i++)
				System.out.print("["+i+"]"+pageFrameList[i]+", ");
			System.out.print(" element count = " + elementCount);
		}


		int search(int pageNumber) {
			int returnVal = -1;

			for (int i = 0; i < pageFrameList.length; i++) {
				if (pageNumber == pageFrameList[i]) {
					returnVal = i;
					break;
				}
			}
			return returnVal;
		}
	}
}
