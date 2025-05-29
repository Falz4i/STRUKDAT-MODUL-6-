
## CODELAB: Implementasi Graph dalam Perusahaan Logistik

We need to create a directed graph representing 5 warehouses and 7 delivery routes. Then, we'll implement and showcase BFS and DFS traversals starting from a designated warehouse (let's call it Gudang A).

### 1. Representasi Directed Graph

Let's define our 5 warehouses (nodes) as:
* Gudang A (0)
* Gudang B (1)
* Gudang C (2)
* Gudang D (3)
* Gudang E (4)

And let's define 7 directed delivery routes (edges):
1.  A → B
2.  A → C
3.  B → D
4.  C → D
5.  C → E
6.  D → E
7.  E → A (This creates a cycle, which is fine for graphs)

### 2. Adjacency Matrix

The adjacency matrix will be a 5x5 matrix where a '1' indicates a directed edge exists from the row warehouse to the column warehouse, and '0' indicates no direct route. [cite: 27]

|       | A (0) | B (1) | C (2) | D (3) | E (4) |
| :---- | :---: | :---: | :---: | :---: | :---: |
| **A (0)** |   0   |   1   |   1   |   0   |   0   |
| **B (1)** |   0   |   0   |   0   |   1   |   0   |
| **C (2)** |   0   |   0   |   0   |   1   |   1   |
| **D (3)** |   0   |   0   |   0   |   0   |   1   |
| **E (4)** |   1   |   0   |   0   |   0   |   0   |

### 3. Implementasi Metode BFS (Breadth First Search)

BFS explores the graph layer by layer, finding the shortest path in terms of the number of edges from the starting node. [cite: 29, 36] We'll start from Gudang A (0).

* **Queue:** Used to keep track of nodes to visit. [cite: 42, 44]
* **Visited Set/Array:** To keep track of visited nodes to avoid cycles and redundant processing. [cite: 46]

**Traversal from Gudang A (0):**

1.  Start with A. Add A to queue. Mark A as visited.
    * Queue: [A]
    * Visited: {A}
    * Path: A
2.  Dequeue A. Neighbors of A are B and C. Add B, C to queue. Mark B, C as visited.
    * Queue: [B, C]
    * Visited: {A, B, C}
    * Path: A B C
3.  Dequeue B. Neighbor of B is D. Add D to queue. Mark D as visited.
    * Queue: [C, D]
    * Visited: {A, B, C, D}
    * Path: A B C D
4.  Dequeue C. Neighbors of C are D and E. D is visited. Add E to queue. Mark E as visited.
    * Queue: [D, E]
    * Visited: {A, B, C, D, E}
    * Path: A B C D E
5.  Dequeue D. Neighbor of D is E. E is visited.
    * Queue: [E]
    * Visited: {A, B, C, D, E}
    * Path: A B C D E
6.  Dequeue E. Neighbor of E is A. A is visited.
    * Queue: []
    * Visited: {A, B, C, D, E}
    * Path: A B C D E

**Jalur yang ditemukan menggunakan BFS dari Gudang A:** **A → B → C → D → E**
(Note: The exact order of siblings like B and C might vary based on the order they are added to the adjacency list/matrix, but they will be on the same "level".)

### 4. Implementasi Metode DFS (Depth First Search)

DFS explores as far as possible along each branch before backtracking. [cite: 51, 52] We'll use a recursive approach (implicitly using a stack). [cite: 28]

* **Visited Set/Array:** To keep track of visited nodes. [cite: 57]

**Traversal from Gudang A (0):**

1.  Start with A. Mark A as visited.
    * Visited: {A}
    * Path: A
2.  Explore neighbors of A. Go to B. Mark B as visited.
    * Visited: {A, B}
    * Path: A B
3.  Explore neighbors of B. Go to D. Mark D as visited.
    * Visited: {A, B, D}
    * Path: A B D
4.  Explore neighbors of D. Go to E. Mark E as visited.
    * Visited: {A, B, D, E}
    * Path: A B D E
5.  Explore neighbors of E. Go to A. A is visited. Backtrack to E.
6.  No more unvisited neighbors for E. Backtrack to D.
7.  No more unvisited neighbors for D. Backtrack to B.
8.  No more unvisited neighbors for B. Backtrack to A.
9.  Explore next unvisited neighbor of A. Go to C. Mark C as visited.
    * Visited: {A, B, D, E, C}
    * Path: A B D E C
10. Explore neighbors of C. Neighbor D is visited. Neighbor E is visited. Backtrack to C.
11. No more unvisited neighbors for C. Backtrack to A.
12. No more unvisited neighbors for A. DFS complete.

**Jalur yang ditemukan menggunakan DFS dari Gudang A:** **A → B → D → E → C**
(Note: The exact path can vary based on the order in which unvisited neighbors are chosen. For example, if we chose C before B from A, the path would be A → C → D → E → B or A → C → E → B → D etc.)

### Output Summary

**Adjacency Matrix:**

|       | A (0) | B (1) | C (2) | D (3) | E (4) |
| :---- | :---: | :---: | :---: | :---: | :---: |
| **A (0)** |   0   |   1   |   1   |   0   |   0   |
| **B (1)** |   0   |   0   |   0   |   1   |   0   |
| **C (2)** |   0   |   0   |   0   |   1   |   1   |
| **D (3)** |   0   |   0   |   0   |   0   |   1   |
| **E (4)** |   1   |   0   |   0   |   0   |   0   |

**Jalur BFS dari Gudang A:** A → B → C → D → E

**Jalur DFS dari Gudang A:** A → B → D → E → C (One possible path, depends on implementation detail of neighbor iteration)

This fulfills the requirements of the Codelab Latihan. You would typically implement the graph structure (e.g., using an adjacency list or matrix in Java as shown in the module [cite: 41, 43]) and then the BFS [cite: 45] and DFS [cite: 60] algorithms to produce these traversals.