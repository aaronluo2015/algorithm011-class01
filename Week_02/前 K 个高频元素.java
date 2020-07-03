class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> count = new HashMap<>();
		for (int n: nums) {
			count.put(n, count.getOrDefault(n, 0)+1);
		}
		PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));
		for (int n: count.keySet()){
			heap.add(n);
			if (heap.size() > k) {
				heap.poll();
			}
		}
		int[] topK = new int[k];
		int index = k - 1;
		while (!heap.isEmpty() && index >= 0) {
			topK[index] = heap.poll();
			index--;
		}
		return topK;
	}
}