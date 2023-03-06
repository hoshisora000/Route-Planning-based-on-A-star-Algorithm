import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//樹梅派1連線用

public class Sever_12001 extends Thread {
	// 控制介面
	public static final int PORT = 12001;

	public void run() {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Sever_12001開啟");
			// 等待客戶端的連線
			Socket socket = server.accept();
			String Client = "" + socket.getInetAddress();// 儲存來源端IP
			String ClientIP = Client.substring(1);
			System.out.println("Sever_12001有客戶端連線進來" + ClientIP);
			// 接收來自用戶端的內容

			while (true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String s2;
				if ((s2 = in.readLine()) != null) {
					// 印出用戶端傳來的內容
					System.out.println("Sever_12001收到訊息:" + s2);
					if (!s2.equals(null)) {
						String s[] = s2.split(" ");// 將字串切割成陣列
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
											//socket送避讓
											PrintWriter out = new PrintWriter(socket.getOutputStream());
											// 輸出至客戶端
											out.println("avoid");
											out.flush();
											check=1;
										}

									}
									for(int i = 0;i<point_num;i++) {
										if(next_point.equals(avoid_path.substring(i*2, i*2+1))) {
											//socket送等待
											PrintWriter out = new PrintWriter(socket.getOutputStream());
											// 輸出至客戶端
											out.println("wait");
											out.flush();
											check=1;
										}
									}
								}								
								if(pi_1_avoid_state==2) {
									//socket送可以繼續走
									PrintWriter out = new PrintWriter(socket.getOutputStream());
									// 輸出至客戶端
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
									// 輸出至客戶端
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
				System.out.println("Sever_12001服務結束");
				Sever_12001 sever = new Sever_12001();
				sever.start();
			}
		} catch (IOException e) {
			System.out.println("伺服器Sever_12001啟動異常");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
