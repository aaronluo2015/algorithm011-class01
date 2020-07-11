class Solution {
	private List<List<Integer>> output = new LinkedList<>();
	private int n;
	private int k;
	
	private void backtrack(int first, LinkedList<Integer> curr) {
		if (curr.size() == this.k) {
			output.add(new LinkedList(curr));
		}
		for (int i=first; i<=n; i++) {
			curr.add(i);
			this.backtrack(i + 1, curr);
			curr.removeLast();
		}
	}
	
	public List<List<Integer>> combine(int n, int k) {
		this.n = n;
		this.k = k;
		this.backtrack(1, new LinkedList<Integer>());
		return this.output;
	}
}