class Solution {
    public int firstUniqChar(String s) {
        if (null == s || s.length() == 0) return -1;
        int len = s.length();
        int[] counter = new int[26];
        for (int i = 0; i < len; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len; i++) {
            if (1 == counter[s.charAt(i) - 'a']) {
                return i;
            }
        }
        return -1;
    }
}