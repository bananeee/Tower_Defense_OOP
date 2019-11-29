import Database.Map;

import java.util.ArrayList;

public class Node {
    private int value;
    private int x;
    private int y;
    private Node previous;
    private ArrayList<Node> nodesNext = new ArrayList<>();

    public Node(int x, int y, Node previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
    }

    public void findNext() {
        if (x - 1 >= 0 && Map.oriMap[y][x - 1] == 5) {
            Node left = new Node(x - 1, y, this);
            nodesNext.add(left);
        }
        if (y - 1 >= 0 && Map.oriMap[y - 1][x] == 5) {
            Node up = new Node(x, y - 1, this);
            nodesNext.add(up);
        }
        if (x + 1 <= 24 && Map.oriMap[y][x + 1] == 5) {
            Node right = new Node(x + 1, y, this);
            nodesNext.add(right);
        }
        if (y + 1 <= 11 && Map.oriMap[y + 1][x] == 5) {
            Node down = new Node(x, y + 1, this);
            nodesNext.add(down);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getPrevious() {
        return previous;
    }

    public ArrayList<Node> getNodesNext() {
        return nodesNext;
    }
}
