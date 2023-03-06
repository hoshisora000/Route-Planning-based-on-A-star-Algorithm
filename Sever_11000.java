import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//duckie�s�u��

public class Sever_11000 extends Thread {
	// �����
	public static final int PORT = 11000;

	public synchronized void run() {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Sever_11000�}��");
			// ���ݫȤ�ݪ��s�u
			Socket socket = server.accept();
			String Client = "" + socket.getInetAddress();// �x�s�ӷ���IP
			String ClientIP = Client.substring(1);
			System.out.println("Sever_11000���Ȥ�ݳs�u�i��" + ClientIP);
			// �����ӦۥΤ�ݪ����e

			while (true) {
				try {
					FileReader fr1 = new FileReader("ambulance_path.txt");
					BufferedReader br1 = new BufferedReader(fr1);
					int ambulance_path_state = Integer.parseInt(br1.readLine());
					
					if (ambulance_path_state == 1) {
						String send_type = br1.readLine();
						String send_path = br1.readLine();
						String send_point = br1.readLine();
						PrintWriter out = new PrintWriter(socket.getOutputStream());
						// ��X�ܫȤ��
						out.println(send_type);
						out.println(send_path);
						out.println(send_point);
						out.flush();
						
						FileWriter fw6 = new FileWriter("r_pi_1_avoid.txt");
						BufferedWriter bw6 = new BufferedWriter(fw6);
						bw6.write("1");
						bw6.newLine();
						bw6.write(send_path);
						bw6.flush();
						bw6.close();
						FileWriter fw7 = new FileWriter("r_pi_2_avoid.txt");
						BufferedWriter bw7 = new BufferedWriter(fw7);
						bw7.write("1");
						bw7.newLine();
						bw7.write(send_path);
						bw7.flush();
						bw7.close();
						
						FileWriter fw1 = new FileWriter("ambulance_path.txt");
						BufferedWriter bw1 = new BufferedWriter(fw1);
						bw1.write("0");
						bw1.flush();
						bw1.close();
						
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String s2 = in.readLine();
						// �L�X�Τ�ݶǨӪ����e
						System.out.println("Sever_11000����T��:" + s2);
						if (!s2.equals(null)) {
							String s[] = s2.split(" ");// �N�r����Φ��}�C
							if (s[0].equals("obstacle")) {
								int obstacle_x, obstacle_y;
								obstacle_x = s[1].charAt(0) - 'A';
								obstacle_y = s[1].charAt(1) - 'A';
								Sever.current_map[obstacle_x][obstacle_y] = 5;
								System.out.println("Sever_10000�b�a��"+s[1]+"��m�B�o�{��ê��");
							}
							
							if (s[0].equals("un_obstacle")) {
								int un_obstacle_x, un_obstacle_y;
								un_obstacle_x = s[1].charAt(0) - 'A';
								un_obstacle_y = s[1].charAt(1) - 'A';
								if (Sever.current_map[un_obstacle_x][un_obstacle_y] == 5) {
									Sever.current_map[un_obstacle_x][un_obstacle_y] = Sever.default_map[un_obstacle_x][un_obstacle_y];
									System.out.println("Sever_10000�b�a��"+s[1]+"��m����ê�w�g����");
								}else {
									System.out.println("Sever_10000�b�a��"+s[1]+"��m�èS����ê");
								}
							}
							
							
							if (s[0].equals("end")) {
								break;
							}
						}
					}
					br1.close();
					FileReader fr2 = new FileReader("police_path.txt");
					BufferedReader br2 = new BufferedReader(fr2);
					int police_path_state = Integer.parseInt(br2.readLine());
					
					if (police_path_state == 1) {
						String send_type = br2.readLine();
						String send_path = br2.readLine();
						String send_point = br2.readLine();
						PrintWriter out = new PrintWriter(socket.getOutputStream());

						out.println(send_type);
						
						out.println(send_path);
						System.out.println("���A��_11000�e�X���u:"+send_path);
						out.println(send_point);
						out.flush();
						FileWriter fw2 = new FileWriter("police_path.txt");
						BufferedWriter bw2 = new BufferedWriter(fw2);
						bw2.write("0");
						bw2.flush();
						bw2.close();
						
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String s2 = in.readLine();
						// �L�X�Τ�ݶǨӪ����e
						System.out.println("Sever_11000����T��:" + s2);
						if (!s2.equals(null)) {
							String s[] = s2.split(" ");// �N�r����Φ��}�C
							if (s[0].equals("end")) {
								FileWriter fw6 = new FileWriter("r_pi_1_avoid.txt");
								BufferedWriter bw6 = new BufferedWriter(fw6);
								bw6.write("2");
								bw6.flush();
								bw6.close();
								FileWriter fw7 = new FileWriter("r_pi_2_avoid.txt");
								BufferedWriter bw7 = new BufferedWriter(fw7);
								bw7.write("2");
								bw7.flush();
								bw7.close();
								break;
							}
							if (s[0].equals("obstacle")) {
								int obstacle_x, obstacle_y;
								obstacle_x = s[1].charAt(0) - 'A';
								obstacle_y = s[1].charAt(1) - 'A';
								Sever.current_map[obstacle_x][obstacle_y] = 5;
								System.out.println("Sever_10000�b�a��"+s[1]+"��m�B�o�{��ê��");
							}
							
							if (s[0].equals("un_obstacle")) {
								int un_obstacle_x, un_obstacle_y;
								un_obstacle_x = s[1].charAt(0) - 'A';
								un_obstacle_y = s[1].charAt(1) - 'A';
								if (Sever.current_map[un_obstacle_x][un_obstacle_y] == 5) {
									Sever.current_map[un_obstacle_x][un_obstacle_y] = Sever.default_map[un_obstacle_x][un_obstacle_y];
									System.out.println("Sever_10000�b�a��"+s[1]+"��m����ê�w�g����");
								}else {
									System.out.println("Sever_10000�b�a��"+s[1]+"��m�èS����ê");
								}
							}
						}
					}
					br2.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("11000���~"+e);
				}
				

			}
			socket.close();
			server.close();
			System.out.println("sever11000�A�ȵ���");
			Sever_11000 sever = new Sever_11000();
			sever.start();
		} catch (IOException e) {
			System.out.println("���A��_11000�Ұʲ��`");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
}
