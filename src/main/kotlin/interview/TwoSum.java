package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// two sum
class RunProgram {
    public static void main(String args[]) {
       int[] array = new TwoSum().twoSum(new int[]{1,2,3,4,5}, 5);
       System.out.println(Arrays.toString(Arrays.stream(array).toArray()));
    }
}
class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
