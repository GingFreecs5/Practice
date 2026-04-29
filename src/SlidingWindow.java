import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    /// Given an array nums and integer k, find the maximum sum of a subarray of size k.
    static class SlidingWindowFixed {
        public static int maxSum(int[] nums, int k) {
            int maxSum = 0, windowSum = 0;

            // First window
            for (int i = 0; i < k; i++) {
                windowSum += nums[i];
            }
            maxSum = windowSum;

            // Slide the window
            for (int i = k; i < nums.length; i++) {
                windowSum += nums[i] - nums[i - k];
                maxSum = Math.max(maxSum, windowSum);
            }

            return maxSum;
        }

        public static void main(String[] args) {
            int[] nums = {2, 1, 5, 1, 3, 2};
            int k = 3;
            System.out.println(maxSum(nums, k)); // Output: 9 (5+1+3)
        }
    }

    /// Given a string s, return the length of the longest substring with at most k distinct characters.
    static class SlidingWindowVariable {
        public static int longestSubstringKDistinct(String s, int k) {
            Map<Character, Integer> charCount = new HashMap<>();
            int left = 0, maxLen = 0;

            for (int right = 0; right < s.length(); right++) {
                char rightChar = s.charAt(right);
                charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
                charCount.merge(rightChar,1,Integer::sum);
                // Shrink window if more than k distinct characters
                while (charCount.size() > k) {
                    char leftChar = s.charAt(left);
                    charCount.put(leftChar, charCount.get(leftChar) - 1);
                    if (charCount.get(leftChar) == 0) {
                        charCount.remove(leftChar);
                    }
                    left++;
                }

                maxLen = Math.max(maxLen, right - left + 1);
            }

            return maxLen;
        }

        public static void main(String[] args) {

            System.out.println(longestSubstringKDistinct("ecebaabf", 2)); // Output: 3 ("ece")
        }
    }
}
