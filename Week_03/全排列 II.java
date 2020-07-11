class Solution {
	public List<List<Integer>> permuteUnique(int[] nums) {
		int len = nums.length;
		List<List<Integer>> res = new LinkedList<>();
		if (0 == len) return res;
		Arrays.sort(nums);
		Deque<Integer> path = new ArrayDeque<Integer>();
		boolean[] used = new boolean[len];
		this.dfs(nums, len, 0, path, used, res);
		return res;
	}
	
	private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
		if (len == depth) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		for (int i=0; i<len; i++) {
			if (used[i]) {
				continue;
			}
			if (i > 0 && (nums[i] == nums[i-1]) && (!used[i-1])) {
				continue;
			}
			used[i] = true;
			path.addLast(nums[i]);
			this.dfs(nums, len, depth + 1, path, used, res);
			used[i] = false;
			path.removeLast();
		}
	}
}