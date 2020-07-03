class Solution {
    public int[] twoSum(int[] nums, int target) {
       return twoSum1(nums, target);
    }
	
	public int[] twoSum1(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i=0; i<nums.length; i++) {
			int com = target - nums[i];
			if (map.containsKey(com)) {
				return new int[]{map.get(com), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
}
