class Solution {
    public int findContentChildren(int[] g, int[] s) {
		int maxCount = 0;
		Arrays.sort(g);
		Arrays.sort(s);
		int i=0, j=0;
		while (i < g.length && j < s.length) {
			if (g[i] <= s[j]) {
				i++;
				j++;
				maxCount++;
			} else {
				j++;
			}
		}
		return maxCount;
	}
	
	public int findContentChildren1(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int i=0;
		for (int j=0; i<g.length && j<s.length; j++) {
			if (g[i] <= s[j]) i++;
		}
		return i;
	}
}