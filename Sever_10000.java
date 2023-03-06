import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Unity連線用

public class Sever_10000 extends Thread {
	// 控制介面
	public static final int PORT = 10000;

	public synchronized void run() {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Sever_10000(Unity)開啟");
			// 等待客戶端的連線
			Socket socket = server.accept();
			String Client = "" + socket.getInetAddress();// 儲存來源端IP
			String ClientIP = Client.substring(1);
			System.out.println("Sever_10000有客戶端連線進來" + ClientIP);
			// 接收來自用戶端的內容
			A_Star_Algorithm.Node parent = new A_Star_Algorithm.Node(0, 0);
			while (true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String s2;
				if ((s2 = in.readLine()) != null) {
					// 印出用戶端傳來的內容
					System.out.println("Sever_10000收到訊息:" + s2);
					if (!s2.equals(null)) {
						String s[] = s2.split(" ");// 將字串切割成陣列
						if (s[0].equals("ambulance")) {
							int s_x, s_y, e_x, e_y;
							String input_point = "";
							for (int i = 1; i < s.length; i++) {
								input_point = input_point + s[i];
							}

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
									outmessage = outmessage + "" + arrayList.get(i).getpoint();
								}
								FileWriter fw = new FileWriter("ambulance_path.txt");
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write("1");
								bw.newLine();
								bw.write("ambulance");
								bw.newLine();
								bw.write(outmessage);
								bw.newLine();
								bw.write(input_point);
								bw.flush();
								bw.close();
							} else {
								System.out.println("Sever_10000救護車:錯誤/找不到路線/錯誤代碼01");
							}
						}

						if (s[0].equals("police")) {
							int police_start_x, police_start_y;
							police_start_x = s[1].charAt(0) - 'A';
							police_start_y = s[1].charAt(1) - 'A';
							String input_point = "";
							for (int i = 1; i < s.length; i++) {
								input_point = input_point + s[i];
							}
							int point_num = s.length - 2;
							if (point_num == 1) {
								int police_end_x, police_end_y;
								police_end_x = s[2].charAt(0) - 'A';
								police_end_y = s[2].charAt(1) - 'A';
								A_Star_Algorithm.openList.clear();
								A_Star_Algorithm.closeList.clear();
								A_Star_Algorithm.Node start_node = new A_Star_Algorithm.Node(police_start_x,
										police_start_y);
								A_Star_Algorithm.Node end_Node = new A_Star_Algorithm.Node(police_end_x, police_end_y);

								parent = A_Star_Algorithm.findPath(start_node, end_Node);

								if (parent != null) {
									ArrayList<A_Star_Algorithm.Node> arrayList = A_Star_Algorithm.getPaths(parent);
									String outmessage = arrayList.get(arrayList.size() - 1).getpoint();
									for (int i = arrayList.size() - 2; i >= 0; i--) {
										outmessage = outmessage + "" + arrayList.get(i).getpoint();
									}
									FileWriter fw = new FileWriter("police_path.txt");
									BufferedWriter bw = new BufferedWriter(fw);
									bw.write("1");
									bw.newLine();
									bw.write("police");
									bw.newLine();
									bw.write(outmessage);
									bw.newLine();
									bw.write(input_point);
									bw.flush();
									bw.close();
								} else {
									System.out.println("Sever_10000警車:錯誤/找不到路線，錯誤代碼01");
								}
							} else {
								ArrayList<Integer> police_find_x = new ArrayList();
								ArrayList<Integer> police_find_y = new ArrayList();
								A_Star_Algorithm.Node start_node = new A_Star_Algorithm.Node(police_start_x,
										police_start_y);
								int police_end_x, police_end_y;
								police_end_x = s[point_num + 1].charAt(0) - 'A';
								police_end_y = s[point_num + 1].charAt(1) - 'A';
								A_Star_Algorithm.Node end_Node = new A_Star_Algorithm.Node(police_end_x, police_end_y);
								for (int i = 0; i < point_num - 1; i++) {
									police_find_x.add(s[i + 2].charAt(0) - 'A');
									police_find_y.add(s[i + 2].charAt(1) - 'A');
								}
								String outmessage = s[1];
								while (police_find_x.size() >= 1) {
									int win = -100, cost = Integer.MAX_VALUE;
									String win_path = "";
									A_Star_Algorithm.Node win_Node = new A_Star_Algorithm.Node(0, 0);
									for (int i = 0; i < police_find_x.size(); i++) {
										A_Star_Algorithm.Node temp_Node = new A_Star_Algorithm.Node(
												police_find_x.get(i), police_find_y.get(i));
										A_Star_Algorithm.openList.clear();
										A_Star_Algorithm.closeList.clear();
										parent = A_Star_Algorithm.findPath(start_node, temp_Node);
										if (parent != null) {
											ArrayList<A_Star_Algorithm.Node> temp_list = A_Star_Algorithm
													.getPaths(parent);
											String temp_path = "";
											for (int j = temp_list.size() - 2; j >= 0; j--) {
												temp_path = temp_path + "" + temp_list.get(j).getpoint();
											}
											if (temp_list.size() < cost) {
												cost = temp_list.size();
												win = i;
												win_path = temp_path;
												win_Node = temp_Node;
											}
										} else {
											System.out.println("Sever_10000警車:錯誤/找不到路線，錯誤代碼02");
										}
									}

									police_find_x.remove(win);
									police_find_y.remove(win);
									start_node = win_Node;
									outmessage = outmessage + "" + win_path;
								}
								A_Star_Algorithm.openList.clear();
								A_Star_Algorithm.closeList.clear();
								parent = A_Star_Algorithm.findPath(start_node, end_Node);
								if (parent != null) {
									ArrayList<A_Star_Algorithm.Node> temp_list = A_Star_Algorithm.getPaths(parent);
									for (int j = temp_list.size() - 2; j >= 0; j--) {
										outmessage = outmessage + "" + temp_list.get(j).getpoint();
									}
								} else {
									System.out.println("Sever_10000警車:錯誤/找不到路線，錯誤代碼03");
								}
								FileWriter fw = new FileWriter("police_path.txt");
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write("1");
								bw.newLine();
								bw.write("police");
								bw.newLine();
								bw.write(outmessage);
								bw.newLine();
								bw.write(input_point);
								bw.flush();
								bw.close();
							}
						}
						
						if (s[0].equals("obstacle")) {
							int obstacle_x, obstacle_y;
							obstacle_x = s[1].charAt(0) - 'A';
							obstacle_y = s[1].charAt(1) - 'A';
							Sever.current_map[obstacle_x][obstacle_y] = 5;
							System.out.println("Sever_10000在地圖"+s[1]+"位置處發現障礙物");
						}
						
						if (s[0].equals("un_obstacle")) {
							int un_obstacle_x, un_obstacle_y;
							un_obstacle_x = s[1].charAt(0) - 'A';
							un_obstacle_y = s[1].charAt(1) - 'A';
							if (Sever.current_map[un_obstacle_x][un_obstacle_y] == 5) {
								Sever.current_map[un_obstacle_x][un_obstacle_y] = Sever.default_map[un_obstacle_x][un_obstacle_y];
								System.out.println("Sever_10000在地圖"+s[1]+"位置的障礙已經移除");
							}else {
								System.out.println("Sever_10000在地圖"+s[1]+"位置並沒有障礙");
							}
						}
						
						if (s[0].equals("end")) {
							break;
						}
					}
				}
			}
			socket.close();
			server.close();
			System.out.println("sever10000服務結束");
			Sever_10000 sever = new Sever_10000();
			sever.start();
		} catch (IOException e) {
			System.out.println("伺服器_10000啟動異常");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
