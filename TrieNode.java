
import java.util.ArrayList;
import java.util.List;

public class TrieNode extends Node<Character> {

    private final List<TrieNode> children = new ArrayList<>();
    private boolean isWord = false;

    public TrieNode(Character value) {
        super(value);
    }

    public TrieNode addChild(char ch) {
        TrieNode child = getChild(ch);
        if (child == null) {
            TrieNode newChild = new TrieNode(ch);
            children.add(newChild);
            return newChild;
        }
        return child;

    }

    public boolean hasChild(char ch) {
        for (TrieNode node : children) {
            if (node.value == ch) {
                return true;
            }
        }
        return false;
    }

    public TrieNode getChild(char c) {
        for (TrieNode child : children) {
            if (child.value == c) {
                return child;
            }
        }
        return null;
    }

    public boolean getIsWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }
}
