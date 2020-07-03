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
};


class Solution {
	public List<Integer> preorder(Node root) {
		List<Integer> res = new ArrayList<Integer>();
		this.preorder(root, res);
		return res;
	}
	
	private void preorder(Node node, List<Integer> res) {
		if (null == node) {
			return;
		}
		res.add(node.val);
		if (null != node.children) {
			for (Node child: node.children) {
				this.preorder(child, res);
			}
		}
	}
}