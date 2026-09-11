/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Map<Node, Node> clonedNodes;
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        this.clonedNodes = new HashMap<>();
        return dfs(node);
    }
    
    public Node dfs(Node node) {
        if (clonedNodes.containsKey(node)) {
            return clonedNodes.get(node);
        }
        
        Node clonedNode = new Node(node.val);
        clonedNodes.put(node, clonedNode);
        
        for(Node neighbor: node.neighbors) {
            clonedNode.neighbors.add(dfs(neighbor));
        }
        
        return clonedNode;
    }
}
