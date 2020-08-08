class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(0, 0, n, res, "");
        return res;
    }

    private void generate(int left, int right, int max, List<String> res, String str) {
        //terminator
        if (left == max && right == max) {
            res.add(str);
            return;
        }
        //curr logic
        String str1 = str + "(";
        String str2 = str + ")";

        //drill down
        if (left < max) {
            generate(left + 1, right, max, res, str1);
        }
        if (right < left) {
            generate(left, right + 1, max, res, str2);
        }

        //reverse state

    }
}