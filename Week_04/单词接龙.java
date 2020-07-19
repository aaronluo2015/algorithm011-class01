class Solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int len = beginWord.length();
		Map<String, List<String>> allComboDict = new HashMap<>();
		
		wordList.forEach(
			word -> {
				for (int i=0; i<len; i++) {
					String newWord = word.substring(0, i) + "*" + word.substring(i+1, len);
					List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
					transformations.add(word);
					allComboDict.put(newWord, transformations);
				}
			}
		);
		
		Queue<Pair<String, Integer>> queue = new LinkedList<>();
		queue.add(new Pair(beginWord, 1));
		
		Map<String, Boolean> visited = new HashMap<>();
		visited.put(beginWord, true);
		
		while (!queue.isEmpty()){
			Pair<String, Integer> node = queue.poll();
			String word = node.getKey();
			int level = node.getValue();
			
			for (int i=0; i<len; i++) {
				String newWord = word.substring(0, i) + "*" + word.substring(i+1, len);
				for (String adjustword: allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					if (adjustword.equals(endWord)) {
						return level + 1;
					}
					if (!visited.containsKey(adjustword)) {
						visited.put(adjustword, true);
						queue.add(new Pair(adjustword, level + 1));
					}
				}
			}
		}
		return 0;
	}
}