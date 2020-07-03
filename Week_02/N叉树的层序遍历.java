// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class Solution {
	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (null == root) {
			return list;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			List<Integer> level = new ArrayList<Integer>();
			int size = queue.size();
			for (int i=0; i<size; i++) {
				Node node = queue.poll();
				level.add(queue.val);
				if (null != node.children) {
					queue.addAll(node.children);
				}
			}
			list.add(level);
		}
		return list;
	}
}