package math;

import java.util.ArrayList;
import java.util.List;

/**
 * 很复杂的题，重点是怎么实现边计算表达式边算出当前的值。
 * 这里需要几个变量
 *   prev: 上一个数字的值
 *   cur: 当前表达式的值
 * 根据prev和cur，可以计算出+,-,*的时候的最新的prev和cur
 *
 * 这个视频讲的很详细: https://youtu.be/v05R1OIIg08
 */
public class ExpressionAddOperators_282 {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, 0, target, 0, 0, "", res);
        return res;
    }

    private void dfs(String num, int idx, int target, long prev, long cur, String exp, List<String> res) {
        if (idx == num.length()) {
            if (cur == target) {
                res.add(exp);
            }

            return;
        }

        for (int i = idx; i < num.length(); i++) {
            long val = Long.parseLong(num.substring(idx, i + 1));
            if (val > Integer.MAX_VALUE) {
                break;
            }
            if (i > idx && num.charAt(idx) == '0') {
                break;
            }

            if (idx == 0) {
                dfs(num, i + 1, target, val, val, val + "", res);
                continue;
            }

            dfs(num, i + 1, target, val, cur + val, exp + "+" + val, res);
            dfs(num, i + 1, target, -val, cur - val, exp + "-" + val, res);
            dfs(num, i + 1, target, prev * val, prev * val + cur - prev, exp + "*" + val, res);
        }
    }
}
