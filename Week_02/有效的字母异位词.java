class Solution {
    public boolean isAnagram(String s, String t) {
        return this.isAnagram1(s, t);
    }
	
	public boolean isAnagram1(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] sarray = s.toCharArray();
		char[] tarray = t.toCharArray();
		Arrays.sort(sarray);
		Arrays.sort(tarray);
		return Arrays.equals(sarray, tarray);
	}
	
	public boolean isAnagram2(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int n = s.length();
		int[] counter = new int[26];
		for (int i=0; i<n; i++) {
			counter[s.charAt(i) - 'a']++;
			counter[t.charAt(i) - 'a']--;
		}
		for (int c: counter) {
			if (c != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isAnagram3(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int n = s.length();
		int[] counter = new int[26];
		for (int i=0; i<n; i++) {
			counter[s.charAt(i) - 'a']++;
		}
		for (int i=0; i<n; i++) {
			int index = t.charAt(i) - 'a';
			counter[index]--;
			if (counter[index] < 0) {
				return false;
			}
		}
		return true;
	}
}