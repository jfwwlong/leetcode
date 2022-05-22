package trie;

/**
 * 对字典的每个单词建立反向trie。然后对于每一个query反向去trie中查找
 *
 * Time: O(nq)
 * Space: O(w + n)
 */
public class StreamOfCharacters_1032 {

    class StreamChecker {
        private StringBuilder sb = new StringBuilder();
        private TrieNode root = new TrieNode();

        public StreamChecker(String[] words) {
            for (String word : words) {
                TrieNode node = root;
                for (int i = word.length() - 1; i >= 0; i--) {
                    char ch = word.charAt(i);
                    if (node.next[ch - 'a'] == null) {
                        node.next[ch - 'a'] = new TrieNode();
                    }

                    node = node.next[ch - 'a'];
                }

                node.isWord = true;
            }
        }

        public boolean query(char letter) {
            sb.append(letter);
            TrieNode node = root;
            for (int i = sb.length() - 1; i >= 0; i--) {
                char ch = sb.charAt(i);
                node = node.next[ch - 'a'];
                if (node == null) {
                    return false;
                }

                if (node.isWord) {
                    return true;
                }
            }

            return false;
        }

        class TrieNode {
            TrieNode[] next = new TrieNode[26];
            boolean isWord = false;
        }
    }
}
