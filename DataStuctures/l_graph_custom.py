"""
Structure (Adjacency List):
[ A ] -> [ B ] -> [ C ]
[ B ] -> [ A ] -> [ D ]
[ C ] -> [ A ]
[ D ] -> [ B ]
"""

from collections import deque

class GraphCustom:
    """Custom Graph Implementation using Adjacency List."""
    def __init__(self):
        self.adjacency_list = {}

    def add_vertex(self, vertex):
        if vertex not in self.adjacency_list:
            self.adjacency_list[vertex] = []

    def add_edge(self, source, destination, bidirectional=True):
        self.add_vertex(source)
        self.add_vertex(destination)
        self.adjacency_list[source].append(destination)
        if bidirectional:
            self.adjacency_list[destination].append(source)

    def bfs(self, start):
        visited = {start}
        queue = deque([start])
        print("BFS: ", end="")
        while queue:
            vertex = queue.popleft()
            print(f"{vertex} ", end="")
            for neighbor in self.adjacency_list.get(vertex, []):
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
        print()

    def dfs(self, start):
        visited = set()
        print("DFS: ", end="")
        self._dfs_helper(start, visited)
        print()

    def _dfs_helper(self, vertex, visited):
        visited.add(vertex)
        print(f"{vertex} ", end="")
        for neighbor in self.adjacency_list.get(vertex, []):
            if neighbor not in visited:
                self._dfs_helper(neighbor, visited)


if __name__ == "__main__":
    graph = GraphCustom()
    graph.add_edge(0, 1)
    graph.add_edge(0, 4)
    graph.add_edge(1, 2)
    graph.add_edge(1, 3)
    graph.add_edge(1, 4)
    graph.add_edge(2, 3)
    graph.add_edge(3, 4)

    graph.bfs(0)
    graph.dfs(0)
