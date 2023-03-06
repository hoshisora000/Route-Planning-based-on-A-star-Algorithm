import java.util.ArrayList;
import java.util.List;

public class A_Star_Algorithm {

	// �������ˬd��檺��
	public static ArrayList<Node> openList = new ArrayList<Node>();
	// �x�s���ݭn�A���ˬd�����
	public static ArrayList<Node> closeList = new ArrayList<Node>();// �����w�g���L���I

	public static class Node {
		// �غc��
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// �C�Ӥ�檺�y��
		public int x;
		public int y;

		public int F;// �w�����|�Z��
		public int G;// �q�_�I���e�I���Z������(�ǽT)
		public int H;// ��e�I��ت��a�����p���ʥN��(���p)
		
		// �w�����|�Z��:F=G+H
		public void calcF() {
			this.F = this.G + this.H;
		}

		// �������`�I
		public Node parent;

		// �N�y���ഫ������W��
		public String getpoint() {
			char c_x = (char) ('A' + x);
			char c_y = (char) ('A' + y);
			return c_x + "" + c_y;
		}
	}

	// �P�_�Ӯy�ЬO�_�i�H��F
	public static boolean canReach(int x, int y) {
		if (x >= 0 && x < 15 && y >= 0 && y < 15) {
			return true;
		}
		return false;
	}

	// �qOpneList���M��F�̤p������
	public static Node findMinFNodeInOpneList() {
		Node tempNode = openList.get(0);
		for (Node node : openList) {
			if (node.F < tempNode.F) {
				tempNode = node;
			}
		}
		return tempNode;
	}

	// �ϥ�noed�P�_�Ӻ���O�_�s�blist��
	public static boolean exists(List<Node> map, Node node) {
		for (Node n : map) {
			if ((n.x == node.x) && (n.y == node.y)) {
				return true;
			}
		}
		return false;
	}

	// �ϥήy�ЧP�_�Ӻ���O�_�s�blist��
	public static boolean exists(List<Node> map, int x, int y) {
		for (Node n : map) {
			if ((n.x == x) && (n.y == y)) {
				return true;
			}
		}
		return false;
	}

	// �ھڹD���P�_�i�H�����Ӥ�V�e�i�A�M��i�H�e��������A�ñN���G�z�LArrayList�^��
	public static ArrayList<Node> findNeighborNodes(Node currentNode) {
		ArrayList<Node> arrayList = new ArrayList<Node>();
		int new_x, new_y;
		switch (Sever.current_map[currentNode.x][currentNode.y]) {
		case 10:
			// ������
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// ���k��
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 1:
			// ������
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// ���U��
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 2:
			// ���U��
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 3:
			// ������
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// ���U��
			new_x = currentNode.x + 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 4:
			// ������
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 5:
			// ���k���^�ǻ�ê��
			break;
		case 6:
			// ���k��
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));

			break;
		case 7:
			// ���W��
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// ������
			new_x = currentNode.x;
			new_y = currentNode.y - 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 8:
			// ���W��
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		case 9:
			// ���W��
			new_x = currentNode.x - 1;
			new_y = currentNode.y;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			// ���k��
			new_x = currentNode.x;
			new_y = currentNode.y + 1;
			// �i�H��F�B���bcloseList�~�[��^�Ǫ��M��
			if (canReach(new_x, new_y) && !exists(closeList, new_x, new_y))
				arrayList.add(new Node(new_x, new_y));
			break;
		default:
			break;
		}
		return arrayList;
	}

	// ��J�_�I�P���I�M����u
	public static Node findPath(Node startNode, Node endNode) {
		// �N�_�I�[�JopenList
		openList.add(startNode);
		// ��openList���ŮɡA�����ˬd
		while (openList.size() > 0) {
			// ����eopenList���̤pF������@��currentNode
			Node currentNode = findMinFNodeInOpneList();
			// �NcurrentNode�qopenList����
			openList.remove(currentNode);
			// �NcurrentNode�s�W��closeList
			closeList.add(currentNode);
			// ��McurrentNode�i�H�e������l
			ArrayList<Node> neighborNodes = findNeighborNodes(currentNode);
			for (Node node : neighborNodes) {
				if (exists(openList, node)) {
					// �p�G�w�g�bopenList�A�h���s�p��G
					foundPoint(currentNode, node);
				} else {
					//�p�GcurrentNode���s�bopenList�A�h�N��s�W�i�h
					notFoundPoint(currentNode, endNode, node);
				}
			}
			//�p�GopenList�̭������I�A�h�N��w�g�����u�F
			if (find(openList, endNode) != null) {
				//�N���I�^�Ǧ^�h�A�i�H�ǥѤ��`�I��M���u
				return find(openList, endNode);
			}
		}
		//�p�G�䤣����u�A�����^��null
		return find(openList, endNode);
	}
	
	//�ˬd��J��List�̭��O�_��Node�A�����ܱN��Node�^��
	public static Node find(List<Node> map, Node point) {
		for (Node n : map)
			if ((n.x == point.x) && (n.y == point.y)) {
				return n;
			}
		return null;
	}

	// ���s�p��G�A�p�G���ܰʫh��sF
	private static void foundPoint(Node tempStart, Node node) {
		// ���s�p��G
		int G = calcG(tempStart, node);
		//�p�GG��쥻���p
		if (G < node.G) {
			//��s���`�I
			node.parent = tempStart;
			//��sG
			node.G = G;
			//���s�p��F
			node.calcF();
		}
	}
	
	//�N���s�bopenList���I�s�W�i�h
    private static void notFoundPoint(Node tempStart, Node end, Node node) {
        node.parent = tempStart;
        node.G = calcG(tempStart, node);
        node.H = calcH(end, node);
        node.calcF();
        openList.add(node);
    }

	// �p��G
	private static int calcG(Node start, Node node) {
		int G = Sever.step;
		int parentG = node.parent != null ? node.parent.G : 0;
		return G + parentG;
	}

	// �ϥΰҫ��y��k�]Manhattan method�^����H
	private static int calcH(Node end, Node node) {
		int step = Math.abs(node.x - end.x) * 20 + Math.abs(node.y - end.y) * 20;
		return step;
	}
	
	//�q���I�����`�I�}�l���^���M���u
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
