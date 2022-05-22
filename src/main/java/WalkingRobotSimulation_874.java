import java.util.HashSet;
import java.util.Set;

/**
 * 模拟机器人行走，没有什么难度。直接按照commands模拟即可。
 * 考点:
 * 1. 使用四个方向转换的技巧
 * 2. 使用"x,y"字符串表示哈希的key
 *
 * Time: O(m + n * c)
 * Space: O(m)
 */
public class WalkingRobotSimulation_874 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int idx = 0;
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + "," + obstacle[1]);
        }

        int res = 0;
        int x = 0, y = 0;
        for (int command : commands) {
            if (command == -2) {
                idx = (idx + 3) % 4;
            } else if (command == -1) {
                idx = (idx + 1) % 4;
            } else {
                int[] dir = dirs[idx];
                for (int i = 0; i < command; i++) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (set.contains(nx + "," + ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;
                }
            }

            res = Math.max(res, x * x + y * y);
        }

        return res;
    }
}
