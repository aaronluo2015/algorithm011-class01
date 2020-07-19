class Solution {
	/**
	North, direction = 0, directions[direction] = {0, 1}
	East,  direction = 1, directions[direction] = {1, 0}
	South, direction = 2, directions[direction] = {0, -1}
	West,  direction = 3, directions[direction] = {-1, 0}
	**/
	public int robotSim(int[] commands, int[][] obstacles) {
		int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		Set<String> obstaclesSet = new HashSet<>();
		for (int[] obstacle: obstacles) {
			obstaclesSet.add(obstacle[0] + " " + obstacle[1]);
		}
		
		int x = 0, y = 0, direction = 0, maxDistSquare = 0;
		for (int command: commands) {
			if (command == -2) {//Turn left
				direction = (direction + 3) % 4;
			} else if (command == -1) {//Turn right
				direction = (direction + 1) % 4;
			} else {
				for (int step = 0; step < command; step++){
					boolean isObstacle = obstacleSet.contains((x+directions[direction][0]) + " " + (y + directions[direction][1]));
					if (isObstacle) {
						break;
					}
					x += directions[direction][0];
					y += directions[direction][1];
				}
			}
			maxDistSquare = Math.max(maxDistSquare, (x * x + y * y));
		}
		return maxDistSquare;
	}
}