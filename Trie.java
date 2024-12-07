
public class Trie {

    private final TrieNode root = new TrieNode(null);

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.addChild(c);
        }
        curr.setIsWord(true);
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.getChild(c);
            if (curr == null) {
                return false;
            }
        }
        return curr.getIsWord();
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            curr = curr.getChild(c);
            if (curr == null) {
                return false;
            }
        }
        return true;
    }

}
