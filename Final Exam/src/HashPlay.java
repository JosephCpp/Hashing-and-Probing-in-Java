//Joseph Castro
public class HashPlay {
    private Integer[] table;
    private    Integer[] keys;
    private    Integer[] h1;
    private Integer[] h2;
    private    int table_size;
    
    public HashPlay(Integer[] table, Integer[] keys, Integer[] h1, Integer[] h2) {
        this.table = table;
        this.keys = keys;
        this.h1 = h1;
        this.h2 = h2;
        this.table_size = table.length;
    }

    public static void main(String[] args) {
//        Integer[] keys = {18,21,43,52,17,15, 6,13};
//        Integer[] h1 =   {12, 3, 5, 8, 7, 5, 5, 5};
//        Integer[] h2 =   {11,11,11,11,11, 8, 7, 7};
//        int table_size = 13;

    	 Integer[] keys = {27,51,69,26,38,62,73};
         Integer[] h1 =   { 5, 7, 3, 4, 5, 7, 7};
         Integer[] h2 =   { 1, 7, 7, 9, 3, 9, 2};
         int table_size = 11;
//    output should be: null   62 null   69   26   27 null   51   38   73 null 

        Integer[] table = new Integer[table_size];

        HashPlay hp1 = new HashPlay(table, keys, h1, h2);

        map2Table(hp1);    
        printArray(table);        
    }//end main


  //add your code here
    private static void map2Table(HashPlay hp1) 
    {
    	final int table_size = hp1.table_size;
    	Integer[] keys = hp1.keys;
    	for(int i = 0; i < keys.length; i++)
    	{
    		int h1Val = hp1.h1[i];
    		int h2Val = hp1.h2[i];
    		//System.out.println("-------\nh1 val: " + h1Val + "\nh2 val: " + h2Val);
    		//if the index is occupied, probing is needed to resolve the collision.
    		if (hp1.table[h1Val] != null)
    		{
//    			System.out.println("occupied val detected at index " + i + " with key " + hp1.table[h1Val] + 
//    					"\nattempted to insert " + keys[i]);
    			//setup a while loop to conduct the hops, where a variable for the current index
    			//and the index that needs to be checked are defined. the indexToCheck variable
    			//doesn't get defined since it'll get the value of the currentIndex at the end of the while loop
    			boolean unresolved = true; //collision is unresolved
    			int indexToCheck;
    			int currentIndex = h1Val;
    			while(unresolved)
    			{
    				//circle around the array if the hop exceeds the max index
    				if( (table_size  - 1 - currentIndex) < h2Val)
    				{
    					//the index will wrap around, and will be equal to the h2Val minus the difference between
    					//the max index and the h1Val. finally, subtract 1 since arrays start at 0
    					indexToCheck = h2Val - (table_size - 1 - currentIndex) - 1;
    					//System.out.println("hop will wrap around to " + indexToCheck + " from " + currentIndex);
    				}
    				//normal hopping/probing
    				else
    				{
    					indexToCheck = currentIndex + h2Val;
    					//System.out.print("normal hop of " + h2Val + " from " + currentIndex + " to " + indexToCheck);
    				}
    				//empty index found, collision is resolved
    				if (hp1.table[indexToCheck] == null)
    				{
    					unresolved = false;
    					hp1.table[indexToCheck] = keys[i];
    					//System.out.println(keys[i] + " ended up at index " + indexToCheck);
    				}
    				//the current index gets the value of the failed index, so the hop is done again from the correct index
    				currentIndex = indexToCheck;
    			}
    		}
    		//normal hash, no probing.
    		else
    		{
    			hp1.table[h1Val] = keys[i];
    		}
    		
    	}
    }

    private static void printArray(Integer[] a) {
        for(Integer x: a)
            System.out.printf("%4d ",x);	
        System.out.println();
    }
}