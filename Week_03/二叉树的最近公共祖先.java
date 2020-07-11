class Solution {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return this.lowestCommonAncestor3(root, p, q);
	}
	
	private TreeNode ans;
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		this.dfs1(root, p, q);
		return ans;
	}
	private boolean dfs1(TreeNode root, TreeNode p, TreeNode q){
		if (null == root) return false;
		boolean lson = this.dfs1(root.left, p, q);
		boolean rson = this.dfs1(root.right, p, q);
		if ((lson && rson) || ((root.val==p.val || root.val == q.val) && (lson || rson))) {
			ans = root;
		}
		return lson || rson || (root.val==p.val || root.val==q.val);
	}
	
	private Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
	private Set<Integer> visited = new HashSet<Integer>();
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		this.dfs2(root);
		while (null != p) {
			visited.add(p.val);
			p = parent.get(p.val);
		}
		while (null != q) {
			if (visited.contains(q.val)) {
				return q;
			}
			q = parent.get(q.val);
		}
		return null;
	}
	private void dfs2(TreeNode root) {
		if (null != root.left) {
			parent.put(root.left.val, root);
			dfs2(root.left);
		}
		if (null != root.right) {
			parent.put(root.right.val, root);
			dfs2(root.right);
		}
	}
	
	public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) return root;
		TreeNode left = this.lowestCommonAncestor3(root.left, p, q);
		TreeNode right = this.lowestCommonAncestor3(root.right, p, q);
		return left == null ? right : right == null ? left : root;
	}
}