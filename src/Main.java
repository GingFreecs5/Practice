import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


    public static void main(String[] args) {

        int[][] matrix = {{0, 1, 1, 2}, {0, 5, 0, 0}, {2, 0, 3, 3}};
        int[] arr= {-1,8,5,9,10};
        int[] arr2={1,3,1};
        boolean[][] minesweeper= {{true, false, false},
                {false, true, false},
                {false, false, false}};
        String str="zAcZ";
       /* System.out.println(stringTransformation(str));
        System.out.println(largestProduct(arr));
        System.out.println(numbersNotBelowZero(matrix));
        System.out.println(serieIncreased(arr2));
        System.out.println(minesweeper(minesweeper));
        System.out.println(digitSumPairs(new int[]{25, 34, 52, 7, 16, 70}));*/
        System.out.println(equilibrumPairs(new int[]{1, 2, 3, 4},new int[]{2, 1, 4, 3}));
        // Print the result array from the minesweeper method
        boolean[][] minesweeperInput = {{true, false, false},
                                        {false, true, false},
                                        {false, false, false}};
        int[][] minesweeperResult = minesweeper(minesweeperInput);
        for (int[] row : minesweeperResult) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    //return the larget product of the adjacent numbers
    public static int largestProduct(int[] arr){
        int max = Integer.MIN_VALUE;
        int n=arr.length;
        int i=0;
        int j=1;
        if(arr.length<2){
            return -1;
        }
        while(j<n){
            if(arr[i]+arr[j]>max){
                max = arr[i]+arr[j];
            }
            i++;
            j++;
        }
        return max;
    }


    // arr = [-1,8,5,9,10]  n=5 , i=0 , j=4    arr[0] + arr[1] (arr[1] = arr[4

    public static String stringTransformation(String str){
        char[] arr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='z'){
                sb.append('a');
            }else if(arr[i]=='Z'){
                sb.append('A');
            }else{
                int asciValue=arr[i]+1;
                sb.append((char)asciValue);
            }
        }
        return sb.toString();
    }

    public static int centuryFromYear(int year){
        return (year-1)/100 + 1;
    }

    public static int numbersNotBelowZero(int[][] matrix){
        int sum=0;
        ArrayList<Integer> colToSkip=new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(!colToSkip.contains(j)){
                    if(matrix[i][j]==0){
                        colToSkip.add(j);
                    }else{
                        sum+=matrix[i][j];
                    }
                }

            }
        }
        return sum;
    }

    public static int numbersNotBelowZeroCorrection(int[][] matrix) {
        int sum = 0;
        for (int j = 0; j < matrix[0].length; j++) { // Column outer loop
            for (int i = 0; i < matrix.length; i++) { // Row inner loop
                if (matrix[i][j] == 0) break; // Hits zero? Exit THIS column immediately.
                sum += matrix[i][j];
            }
        }
        return sum;
    }


    public static boolean serieIncreased(int[] arr){
        for(int i=0;i<arr.length-2;i++){

            if(arr[i+1]<arr[i]){
                if(i+1==arr.length-1){
                    return true;
                }else if(arr[i+2]<arr[i]){
                    return false;
                }
            }
        }
        return true;
    }
   /*
   Input :
      [[true, false, false],
       [false, true, false],
       [false, false, false]]

       pour i=0 , j=1
        matrix[i][j-1]=true => result[i,j]++;
        matrix[i+1][j]==true=>result[i,j]++;

   Output :
        [[1, 2, 1],
         [2, 1, 1],
         [1, 1, 1]]
    */
    public static int[][] minesweeper(boolean[][] matrix){
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                    result[i][j]=0;
                    if( j >0 && matrix[i][j-1] ) result[i][j]++;
                    if( j+1<matrix[0].length && matrix[i][j+1]) result[i][j]++;

                    if(i+1<matrix.length && matrix[i+1][j]) result[i][j]++;
                    if(i+1<matrix.length && j>0 && matrix[i+1][j-1]) result[i][j]++;
                    if(i+1<matrix.length && j+1<matrix[0].length && matrix[i+1][j+1]) result[i][j]++;

                    if( i> 0 && matrix[i-1][j]) result[i][j]++;
                    if( i>0 && j+1<matrix[0].length && matrix[i-1][j+1]) result[i][j]++;
                    if(i>0 && j>0&&matrix[i-1][j-1]) result[i][j]++;




            }
        }
        return result;
    }

    public static int[] arrayReplace(int[] arr,int target,int sub){
        for(int j=0;j<arr.length;j++){
            if(arr[j]==target){
                arr[j]=sub;
            }
        }
        return arr;
    }

    /*
    Input: arr = [3, 5, 6, 7, 9].
    1,2,3,4,5,6,7,8,9,10
    Output: 4

     */
     static  int[][] boxBlur(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;

        // Rule 1: Result is 2 units smaller in both directions
        int[][] result = new int[rows - 2][cols - 2];

        // Rule 2: Start at (1, 1) and end 1 before the edge
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {

                int sum = 0;
                // Rule 3: The 3x3 Sum (offset from -1 to 1)
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        sum += image[i + dx][j + dy];
                    }
                }

                // Assign to (i-1, j-1) because result starts at index 0
                result[i - 1][j - 1] = sum / 9;
            }
        }
        return result;
    }

    /*
    ### **Problem Statement**
        You are given an array of non-negative integers `numbers`. Your task is to calculate the number of pairs of indices `(i, j)` such that:
        1.  i < j
        2.  The sum of the digits of `numbers[i]` is equal to the sum of the digits of `numbers[j]`.
        3.  The actual value of `numbers[i]` and `numbers[j]` have the same number of digits.

        ### **Example**
        For `numbers = [25, 34, 52, 7, 16, 70]`:
        * `25`: Digit sum = 2+5=7, Length = 2
        * `34`: Digit sum = 3+4=7, Length = 2
        * `52`: Digit sum = 5+2=7, Length = 2
        * `7`: Digit sum = 7, Length = 1
        * `16`: Digit sum = 1+6=7, Length = 2
        * `70`: Digit sum = 7+0=7, Length = 2

        **Valid Pairs:**
        * `(25, 34)`, `(25, 52)`, `(25, 16)`, `(25, 70)`
        * `(34, 52)`, `(34, 16)`, `(34, 70)`
        * `(52, 16)`, `(52, 70)`
        * `(16, 70)`
        *(Note: `7` has a sum of 7, but it is length 1, so it cannot pair with the others.)*

        **Output:** `10`

        ### **Constraints**
        * 1 \leq \text{numbers.length} \leq 10^5
        * 0 \leq \text{numbers[i]} \leq 10^9
        * **Execution Time Limit:** 4 seconds (This tells you O(n^2) will fail).
     */

    /*
      25/10=2 && 25%10=5 => 2+5=7
      34/10=3 && 34%10=4 => 3+4=7

     */
     static int digitSumPairs(int[] arr){
         int sum = 0;
         HashMap<String,Integer> digitPairsCount = new HashMap<>();
         for(int i=0;i<arr.length;i++){
             int r=arr[i]%10;
             int sumDigits=r;
             int digitLength=1;
             while(r>0){
                  arr[i]=arr[i]/10;
                  r=arr[i]%10;
                  sumDigits+=r;
                  digitLength++;
             }
             String key= sumDigits +"_"+ digitLength;
             digitPairsCount.put(key,digitPairsCount.getOrDefault(key,0)+1);

         }
            for(String key:digitPairsCount.keySet()){
                int count=digitPairsCount.get(key);
                if(count>1){
                    sum+= (count*(count-1))/2; // nC2 pairs from count
                }
            }
         return sum;
     }
    static long digitSumPairsSolution(int[] arr) {
        long totalPairs = 0;
        // Key: "sum_length", Value: Frequency
        HashMap<String, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            int sumDigits = 0;
            int digitLength = 0;
            int temp = num;

            // Special case for 0
            if (temp == 0) {
                sumDigits = 0;
                digitLength = 1;
            } else {
                while (temp > 0) {
                    sumDigits += temp % 10;
                    temp /= 10;
                    digitLength++;
                }
            }

            String key = sumDigits + "_" + digitLength;

            // OPTIMIZATION: Count pairs as you go to avoid a second loop
            int currentCount = freqMap.getOrDefault(key, 0);
            totalPairs += currentCount;
            freqMap.put(key, currentCount + 1);
        }

        return totalPairs;

    }
    /*
        ## 📝 Task 4: The "Equilibrium Pairs"

### **Problem Statement**
        You are given two integer arrays, `a` and `b`, both of the same length `n`. A pair of indices `(i, j)` is called an **Equilibrium Pair** if:
        1. 0 \le i, j < n (Note: i and j can be the same, and the order doesn't matter for the count, but usually CodeSignal specifies i < j). Let's assume **i < j**.
        2. The sum of elements in `a` between index `i` and `j` (inclusive) is equal to the sum of elements in `b` between index `i` and `j` (inclusive).

        ### **Example**
        `a = [1, 2, 3, 4]`
        `b = [2, 1, 4, 3]`

        * **Pair (0, 1):** `a[0]+a[1] = 3`, `b[0]+b[1] = 3`. **Match!**
        * **Pair (2, 3):** `a[2]+a[3] = 7`, `b[2]+b[3] = 7`. **Match!**
        * **Pair (0, 3):** `a[0...3] = 10`, `b[0...3] = 10`. **Match!**

        ### **Constraints**
        * n = 10^5.
        * O(n^2) (nested loops) will **FAIL**.
 */

    static int equilibrumPairs(int[] a,int[] b){
        int sum=0;
        int n=a.length;
        // to store the prefix sum
        ArrayList<Integer> prefixSum = new ArrayList<>();
        System.out.println(balancedArray(new int[]{1, 4, 2, 3, 5}));
        // initialize the first element
        prefixSum.add(a[0]-b[0]);
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        freqMap.put(0,1); // To count pairs where prefixSum[i] itself is 0 (i.e., sum from 0 to i is equal for both arrays)
        if(n==1){
            return 0;
        }else{
            for(int i=1;i<n;i++){
                prefixSum.add(prefixSum.get(i - 1) + (a[i]-b[i]));

                freqMap.put(prefixSum.get(i-1),freqMap.getOrDefault(prefixSum.get(i-1),0)+1);

            }
        }
        return sum;
    }

    public long equilibrumSolution(int[] a, int[] b) {
        int n = a.length;
        long totalPairs = 0;
        long runningDiffSum = 0; // Use long to prevent overflow

        // Key: The running difference, Value: How many times we've seen it
        HashMap<Long, Integer> freqMap = new HashMap<>();

        // IMPORTANT: A sum of 0 has been seen once (at the very beginning)
        freqMap.put(0L, 1);

        for (int i = 0; i < n; i++) {
            // 1. Update the running difference
            runningDiffSum += (a[i] - b[i]);

            // 2. Check: Have we seen this exact difference before?
            if (freqMap.containsKey(runningDiffSum)) {
                // If we've seen it 3 times before, it means there are 3
                // different starting points that make a valid window ending here.
                totalPairs += freqMap.get(runningDiffSum);
            }

            // 3. Record this sum in the map
            freqMap.put(runningDiffSum, freqMap.getOrDefault(runningDiffSum, 0) + 1);
        }

        return totalPairs;
    }

    static int sum100(int[] arr){
        int pairCount = 0;
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = 100 - arr[i];
            if (freqMap.containsKey(complement)) {
                pairCount += 1;
            }
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        return pairCount;
    }

    /// ### **Problem Statement**
    /// You are given an array of integers `numbers`. A subarray is called **Balanced** if it contains an **equal number of even and odd integers.** Your task is to return the total number of **Balanced subarrays**.
    ///
    /// ### **Example**
    /// `numbers = [1, 4, 2, 3, 5]`
    /// * `1` is Odd (O)
    /// * `4` is Even (E)
    /// * `2` is Even (E)
    /// * `3` is Odd (O)
    /// * `5` is Odd (O)
    ///
    /// **Balanced Subarrays:**
    /// * `[1, 4]` (One O, One E)
    /// * `[4, 3]` (One E, One O)
    /// * `[2, 3]` (One E, One O)
    /// * `[1, 4, 2, 3]` (Two O, Two E)
    ///
    /// **Output:** `4`
    static long balancedArray(int[] arr){
        int sum=0;
        long subArrCount=0;
        HashMap<Long,Integer> freqMap = new HashMap<>();
        freqMap.put(0L,1);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]%2==0){
                sum++;
            }else {
                sum--;
            }
            if(sum==0){
                sum=+freqMap.getOrDefault(subArrCount,0);
            }
            freqMap.put(subArrCount, freqMap.getOrDefault(subArrCount, 0) + 1);
        }
        return subArrCount;
    }




    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') { bfs(grid, i, j); count++; }
        return count;
    }

    private static void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        grid[i][j] = '0';
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int[] d : dirs) {
                int r = cell[0]+d[0], c = cell[1]+d[1];
                if (r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c]=='1') {
                    grid[r][c] = '0';
                    q.add(new int[]{r, c});
                }
            }
        }


    }

    /// Problem Statement
    /// You have a grid. Some cells have rotten oranges (2), some fresh (1), some empty (0).
    ///Every minute, rotten oranges rot adjacent fresh ones. How many minutes until all are rotten?

    static int rottenOrange(char[][] grid) {
        int minutes = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    minutes++;
                }
            }
        }
        return minutes;
    }

    static int rottenOrangesSolution(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // 1. Setup: Find all initially rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) freshCount++;
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 2. BFS: Spread the rot level by level
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedInThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int[] d : directions) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                        freshCount--;
                        rottedInThisRound = true;
                    }
                }
            }
            if (rottedInThisRound) minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }
    private static void dfs(char[][] grid, int r, int c) {
        // Stop if out of bounds OR water OR already visited
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (grid[r][c] != '1') return;

        grid[r][c] = '2'; // mark as rooten (sink the land)

        // Explore all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }


}


