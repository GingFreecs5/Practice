import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayListConcept {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(5);               // append to end — O(1) amortized
        list.add(0, 5);            // insert at index — O(n), avoid in hot loops
        list.get(2);               // access by index — O(1)
        list.set(2, 99);           // update at index — O(1)
        list.remove(Integer.valueOf(5)); // remove by value — O(n)
        list.remove(2);            // remove by index — O(n)
        list.size();               // number of elements
        list.contains(5);          // O(n) — use HashSet if you need this often
        Collections.sort(list);    // sort in place — O(n log n)

// Convert array → List and back:
        Integer[] arr = {1, 2, 3, 5}; // Integer array instead of int[]
        List<Integer> fromArray = new ArrayList<>(Arrays.asList(arr));
        int[] backToArray = list.stream().mapToInt(Integer::intValue).toArray();
    }

    public List<Integer> evenNumbers(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int n : arr)
            if (n % 2 == 0) result.add(n);
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);

        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>(); // new list per level
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public List<String> mostFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) freq.merge(w, 1, Integer::sum);

        return freq.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()); // → ArrayList under the hood
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // Default constructor
        TreeNode() {}

        // Constructor with value
        TreeNode(int val) {
            this.val = val;
        }

        // Constructor with value and children
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
