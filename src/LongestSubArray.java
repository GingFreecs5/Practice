import java.util.HashSet;

public class LongestSubArray {

    public static void main(String[] args) {
        int[] nums= {1,0,1,1,0};
        System.out.println(longestSubarrayWithOneFlip(nums));
    }

    /// Given a binary array nums (containing only 0s and 1s),
    /// you are allowed to flip at most one 0 to a 1.
    /// Return the length of the longest contiguous subarray of 1s you can get.
    /// Input: nums = [1, 0, 1, 1, 0]             / [1, 0, 1, 1, 0, 1 , 1 , 1]
    /// Output: 4 (Flip the 0 at index 1 to get [1, 1, 1, 1, 0])
    static int longestSubarrayWithOneFlip(int[] arr) {
        int left = 0;
        int maxLen = 0;
        int zeroCount = 0;

        for (int right = 0; right < arr.length; right++) {
            // If we find a zero, increment our 'budget' usage
            if (arr[right] == 0) {
                zeroCount++;
            }

            // If we have more than one zero, shrink the window from the left
            while (zeroCount > 1) {
                if (arr[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
    static int longestSubarrayWithKFlip(int[] arr,int k) {
        int left = 0;
        int maxLen = 0;
        int zeroCount = 0;

        for (int right = 0; right < arr.length; right++) {
            // If we find a zero, increment our 'budget' usage
            if (arr[right] == 0) {
                zeroCount++;
            }

            // If we have more than one zero, shrink the window from the left
            while (zeroCount > 1) {
                if (arr[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }


}
