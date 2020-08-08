class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;

        char[] charset = {'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>();
        for (String bk: bank) bankSet.add(bk);

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                if (curr.equals(end)) return level;

                char[] currArray = curr.toCharArray();
                for (int i=0; i<currArray.length; i++) {
                    char old = currArray[i];
                    for (char ch: charset) {
                        currArray[i] = ch;
                        String next = new String(currArray);
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }
            }
            level++;
        }
        return -1;
    }
}