import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//�����1�s�u��

public class Sever_12001 extends Thread {
	// �����
	public static final int PORT = 12001;

	public void run() {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Sever_12001�}��");
			// ���ݫȤ�ݪ��s�u
			Socket socket = server.accept();
			String Client = "" + socket.getInetAddress();// �x�s�ӷ���IP
			String ClientIP = Client.substring(1);
			System.out.println("Sever_12001���Ȥ�ݳs�u�i��" + ClientIP);
			// �����ӦۥΤ�ݪ����e

			while (true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String s2;
				if ((s2 = in.readLine()) != null) {
					// �L�X�Τ�ݶǨӪ����e
					System.out.println("Sever_12001����T��:" + s2);
					if (!s2.equals(null)) {
						String s[] = s2.split(" ");// �N�r����Φ��}�C
						if (s[0].equals("report")) {
							String now_point = s[1];
							String next_point = s[2];
							FileWriter fw = new FileWriter("r_pi_1_location.txt");
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(now_point);
							bw.newLine();
							bw.write(next_point);
							bw.flush();
							bw.close();
							FileReader fr1 = new FileReader("r_pi_1_avoid.txt");
							BufferedReader br1 = new BufferedReader(fr1);
							String s3;
							int check=0;
							if ((s3 = in.readLine()) != null) {
								int pi_1_avoid_state = Integer.parseInt(br1.readLine());
								if(pi_1_avoid_state==1) {
									String avoid_path=in.readLine();
									int point_num = avoid_path.length()/2;
									for(int i = 0;i<point_num;i++) {
										if(now_point.equals(avoid_path.substring(i*2, i*2+1))) {
											//socket�e����
											PrintWriter out = new PrintWriter(socket.getOutputStream());
											// ��X�ܫȤ��
											out.println("avoid");
											out.flush();
											check=1;
										}

									}
									for(int i = 0;i<point_num;i++) {
										if(next_point.equals(avoid_path.substring(i*2, i*2+1))) {
											//socket�e����
											PrintWriter out = new PrintWriter(socket.getOutputStream());
											// ��X�ܫȤ��
											out.println("wait");
											out.flush();
											check=1;
										}
									}
								}								
								if(pi_1_avoid_state==2) {
									//socket�e�i�H�~��
									PrintWriter out = new PrintWriter(socket.getOutputStream());
									// ��X�ܫȤ��
									out.println("can_go");
									out.flush();
									check=1;
									FileWriter fw6 = new FileWriter("r_pi_1_avoid.txt");
									BufferedWriter bw6 = new BufferedWriter(fw6);
									bw6.write("0");
									bw6.flush();
									bw6.close();
								}
								if(check==0) {
									PrintWriter out = new PrintWriter(socket.getOutputStream());
									// ��X�ܫȤ��
									out.println("fine");
									out.flush();
								}
							}
						}
						if (s[0].equals("end")) {
							break;
						}
					}
				}

				socket.close();
				server.close();
				System.out.println("Sever_12001�A�ȵ���");
				Sever_12001 sever = new Sever_12001();
				sever.start();
			}
		} catch (IOException e) {
			System.out.println("���A��Sever_12001�Ұʲ��`");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
