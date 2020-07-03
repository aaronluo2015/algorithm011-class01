class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		return this.groupAnagrams2(strs);
	}
	
	public List<List<String>> groupAnagrams1(String[] strs) {
		Map<String, List<String> listMap = new HashMap<String, List<String>>();
		for (String str: strs) {
			char[] array = str.toCharArray();
			Arrays.sort(array);
			String key = String.valueOf(array);
			List<String> list = listMap.get(key);
			if (null == list) {
				list = new ArrayList<String>();
			}
			list.add(str);
			listMap.put(key, list);
		}
		return new ArrayList<List<String>>(listMap.values());
	}
	
	public List<List<String>> groupAnagrams2(String[] strs) {
		Map<String, List<String>> listMap = new HashMap<String, List<String>>();
		for (String str: strs) {
			int[] counter = new int[26];
			char[] array = str.toCharArray();
			for (char c: array) {
				counter[c - 'a']++;
			}
			String key = "";
			for (int count: counter) {
				key += "#" + count;
			}
			List<String> list = listMap.get(key);
			if (list == null) {
				list = new ArrayList<String>();
			}
			list.add(str);
			listMap.put(key, list);
		}
		return new ArrayList<List<String>>(listMap.values());
	}
}