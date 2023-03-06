import java.util.ArrayList;
import java.util.List;

public class A_Star_Algorithm {

	// 紀錄待檢查方格的表
	public static ArrayList<Node> openList = new ArrayList<Node>();
	// 儲存不需要再刺檢查的方格
	public static ArrayList<Node> closeList = new ArrayList<Node>();// 紀錄已經掃過的點

	public static class Node {
		// 建構元
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// 每個方格的座標
		public int x;
		public int y;

		public int F;// 預測路徑距離
		public int G;// 從起點到當前點的距離長度(準確)
		public int H;// 當前點到目的地的估計移動代價(估計)
		
		// 預測路徑距離:F=G+H
		public void calcF() {
			this.F = this.G + this.H;
		}

		// 紀錄父節點
		public Node parent;

		// 將座標轉換成網格名稱
		public String getpoint() {
			char c_x = (char) ('A' + x);
			char c_y = (char) ('A' + y);
			return c_x + "" + c_y;
		}
	}

	// 判斷該座標是否可以抵達
	public static boolean canReach(int x, int y) {
		if (x >= 0 && x < 15 && y >= 0 && y < 15) {
			return true;
		}
		return false;
	}

	// 從OpneList中尋找F最小的網格
	public static Node findMinFNodeInOpneList() {
		Node tempNode = openList.get(0);
		for (Node node : openList) {
			if (node.F < tempNode.F) {
				tempNode = node;
			}
		}
		return tempNode;
	}

	// 使用noed判斷該網格是否存在list中
	public static boolean exists(List<Node> map, Node node) {
		for (Node n : map) {
			if ((n.x == node.x) && (n.y == node.y)) {
				return true;
			}
		}
		return false;
	}

	// 使用座標判斷該網格是否存在list中
	public static boolean exists(List<Node> map, int x, int y) {
		for (Node n : map) {
			if ((n.x == x) && (n.y == y)) {
				return true;
			}
		}
		return false;
	}

	// 根據道路判斷可以往哪個方向前進，尋找可以前往的網格，並將結果透過ArrayList回傳
	public static ArrayList<Node> findNeighborNodes(Node currentNode) {
		ArrayList<Node> arrayList = new ArrayList<Node>();
		int new_x, new_y;
		switch (Sever.current_map[currentNode.x][currentNode.y]) {
		case 10:
			// 往左走
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// 往右走
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 1:
			// 往左走
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// 往下走
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 2:
			// 往下走
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 3:
			// 往左走
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// 往下走
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 4:
			// 往左走
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 5:
			// 執法車回傳障礙用
			break;
		case 6:
			// 往右走
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));

			break;
		case 7:
			// 往上走
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// 往左走
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 8:
			// 往上走
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 9:
			// 往上走
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// 往右走
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// 可以到達且不在closeList才加到回傳的清單
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		default:
			break;
		}
		return arrayList;
	}

	// 輸入起點與終點尋找路線
	public static Node findPath(Node startNode, Node endNode) {
		// 將起點加入openList
		openList.add(startNode);
		// 當openList不空時，持續檢查
		while (openList.size() > 0) {
			// 找到當前openList中最小F的網格作為currentNode
			Node currentNode = findMinFNodeInOpneList();
			// 將currentNode從openList移除
			openList.remove(currentNode);
			// 將currentNode新增到closeList
			closeList.add(currentNode);
			// 找尋currentNode可以前往的格子
			ArrayList<Node> neighborNodes = findNeighborNodes(currentNode);
			for (Node node : neighborNodes) {
				if (exists(openList, node)) {
					// 如果已經在openList，則重新計算G
					foundPoint(currentNode, node);
				} else {
					//如果currentNode不存在openList，則將其新增進去
					notFoundPoint(currentNode, endNode, node);
				}
			}
			//如果openList裡面有終點，則代表已經找到路線了
			if (find(openList, endNode) != null) {
				//將終點回傳回去，可以藉由父節點找尋路線
				return find(openList, endNode);
			}
		}
		//如果找不到路線，直接回傳null
		return find(openList, endNode);
	}
	
	//檢查輸入的List裡面是否有Node，有的話將該Node回傳
	public static Node find(List<Node> map, Node point) {
		for (Node n : map)
			if ((n.x == point.x) && (n.y == point.y)) {
				return n;
			}
		return null;
	}

	// 重新計算G，如果有變動則更新F
	private static void foundPoint(Node tempStart, Node node) {
		// 重新計算G
		int G = calcG(tempStart, node);
		//如果G比原本的小
		if (G < node.G) {
			//更新父節點
			node.parent = tempStart;
			//更新G
			node.G = G;
			//重新計算F
			node.calcF();
		}
	}
	
	//將不存在openList的點新增進去
    private static void notFoundPoint(Node tempStart, Node end, Node node) {
        node.parent = tempStart;
        node.G = calcG(tempStart, node);
        node.H = calcH(end, node);
        node.calcF();
        openList.add(node);
    }

	// 計算G
	private static int calcG(Node start, Node node) {
		int G = Sever.step;
		int parentG = node.parent != null ? node.parent.G : 0;
		return G + parentG;
	}

	// 使用曼哈頓方法（Manhattan method）估算H
	private static int calcH(Node end, Node node) {
		int step = Math.abs(node.x - end.x) * 20 + Math.abs(node.y - end.y) * 20;
		return step;
	}
	
	//從終點的父節點開始往回推尋路線
	public static ArrayList<Node> getPaths(Node endNode) {
        ArrayList<Node> arrayList = new ArrayList<Node>();
        Node pre = endNode;
        while (pre != null) {
            arrayList.add(new Node(pre.x, pre.y));
            pre = pre.parent;
        }
        return arrayList;
    }

}
