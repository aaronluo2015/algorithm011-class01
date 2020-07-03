//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		this.preorderTraversal(root, res);
		return res;
	}
	
	private void preorderTraversal(TreeNode node, List<Integer> res) {
		if (null == node) {
			return;
		}
		res.add(node.val);
		if (null != node.left) {
			this.preorderTraversal(node.left, res);
		}
		if (null != node.right) {
			this.preorderTraversal(node.right, res);
		}
	}
}