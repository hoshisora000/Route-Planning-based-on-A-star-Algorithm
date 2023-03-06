import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Sever_10001 extends Thread {
	// �����
	public static final int PORT = 10001;

	public synchronized void run() {
		try {

			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Sever_10001�}��");
			// ���ݫȤ�ݪ��s�u
			Socket socket = server.accept();
			String Client = "" + socket.getInetAddress();// �x�s�ӷ���IP
			String ClientIP = Client.substring(1);
			System.out.println("���Ȥ�ݳs�u�i��" + ClientIP);
			// �����ӦۥΤ�ݪ����e
			A_Star_Algorithm.Node parent = new A_Star_Algorithm.Node(0, 0);
			while (true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String s2 = in.readLine();
				// �L�X�Τ�ݶǨӪ����e
				System.out.println("2����T��:" + s2);
				if (!s2.equals(null)) {
					String s[] = s2.split(" ");// �N�r����Φ��}�C
					if (s[0].equals("find_path")) {
						int s_x, s_y, e_x, e_y;
						s_x = s[1].charAt(0) - 'A';
						s_y = s[1].charAt(1) - 'A';
						e_x = s[2].charAt(0) - 'A';
						e_y = s[2].charAt(1) - 'A';
						A_Star_Algorithm.openList.clear();
						A_Star_Algorithm.closeList.clear();
						A_Star_Algorithm.Node start_node = new A_Star_Algorithm.Node(s_x, s_y);
						A_Star_Algorithm.Node end_Node = new A_Star_Algorithm.Node(e_x, e_y);

						parent = A_Star_Algorithm.findPath(start_node, end_Node);

						if (parent != null) {
							ArrayList<A_Star_Algorithm.Node> arrayList = A_Star_Algorithm.getPaths(parent);
							String outmessage = arrayList.get(arrayList.size() - 1).getpoint();
							for (int i = arrayList.size() - 2; i >= 0; i--) {
								outmessage = outmessage + " " + arrayList.get(i).getpoint();
							}
							// �ǰe���e�ܥΤ��
							PrintWriter out = new PrintWriter(socket.getOutputStream());
							// ��X�ܫȤ��
							out.println(outmessage);
							out.flush();
						} else {
							System.out.println("���~/�䤣����u");
							PrintWriter out = new PrintWriter(socket.getOutputStream());
							out.println("ERROR");
							out.flush();
						}

					}
					if (s[0].equals("obstacle")) {
						int obstacle_x, obstacle_y;
						obstacle_x = s[1].charAt(0) - 'A';
						obstacle_y = s[1].charAt(1) - 'A';
						Sever.current_map[obstacle_x][obstacle_y] = 5;
						PrintWriter out = new PrintWriter(socket.getOutputStream());
						out.println("obstacle_OK");
						out.flush();
					}
					if (s[0].equals("un_obstacle")) {
						int un_obstacle_x, un_obstacle_y;
						un_obstacle_x = s[1].charAt(0) - 'A';
						un_obstacle_y = s[1].charAt(1) - 'A';
						if (Sever.current_map[un_obstacle_x][un_obstacle_y] == 5) {
							Sever.current_map[un_obstacle_x][un_obstacle_y] = Sever.default_map[un_obstacle_x][un_obstacle_y];

						}
						PrintWriter out = new PrintWriter(socket.getOutputStream());
						out.println("un_obstacle_OK");
						out.flush();
					}
					if (s[0].equals("end")) {
						break;
					}
				}
			}
			socket.close();
			server.close();
			System.out.println("���A������");
		} catch (IOException e) {
			System.out.println("���A��_10001�Ұʲ��`");
			System.out.println(e);
			e.printStackTrace();
		}

	}
}
