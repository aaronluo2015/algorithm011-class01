class Solution {
	//暴力破解
    public int[] twoSum1(int[] nums, int target) {
		for (int i=0; i<nums.length-1; i++) {
			for (int j=i + 1; j<nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
    }
	//使用hash缓存，空间换时间
	public int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<nums.length; i++) {
			int com = target - nums[i];
			if (map.containsKey(com)) {
				return new int[] {map.get(com), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
    }
}