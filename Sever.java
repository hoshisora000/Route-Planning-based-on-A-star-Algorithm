import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Sever {
	// 宣告地圖大小、陣列
	public static int[][] default_map = new int[15][15];
	public static int[][] current_map = new int[15][15];
	public static final int step = 20;

	public static void main(String[] args) {
		// 將地圖所有網格預設為0
		Arrays.fill(default_map[0], 0);
		for (int i = 0; i < 15; i++) {
			Arrays.fill(default_map[i], 0);
		}

		// 開始讀地圖檔
		String inpString;
		int y = 0;
		try {
			//FileReader fr = new FileReader("C:\\Users\\GF75\\Desktop\\物聯網專題\\map-1.txt");
			FileReader fr = new FileReader("map-1.txt");
			BufferedReader br = new BufferedReader(fr);
			while ((inpString = br.readLine()) != null) {
				String st[] = inpString.split(" ");
				for (int i = 0; i < 15; i++) {
					default_map[y][i] = Integer.parseInt(st[i]);
					current_map[y][i] = Integer.parseInt(st[i]);
				}
				y++;
			}
			br.close();
		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("讀檔錯誤:" + e1);
		}
		try {
			FileWriter fw1 = new FileWriter("ambulance_path.txt");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			bw1.write("0");
			bw1.flush();
			bw1.close();
			
			FileWriter fw2 = new FileWriter("police_path.txt");
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw2.write("0");
			bw2.flush();
			bw2.close();
			
			FileWriter fw3 = new FileWriter("duckie_location.txt");
			BufferedWriter bw3 = new BufferedWriter(fw3);
			bw3.write("0");
			bw3.flush();
			bw3.close();
			
			FileWriter fw4 = new FileWriter("r_pi_1_location.txt");
			BufferedWriter bw4 = new BufferedWriter(fw4);
			bw4.write("0");
			bw4.flush();
			bw4.close();
			FileWriter fw5 = new FileWriter("r_pi_2_location.txt");
			BufferedWriter bw5 = new BufferedWriter(fw5);
			bw5.write("0");
			bw5.flush();
			bw5.close();
			
			FileWriter fw6 = new FileWriter("r_pi_1_avoid.txt");
			BufferedWriter bw6 = new BufferedWriter(fw6);
			bw6.write("0");
			bw6.flush();
			bw6.close();
			FileWriter fw7 = new FileWriter("r_pi_2_avoid.txt");
			BufferedWriter bw7 = new BufferedWriter(fw7);
			bw7.write("0");
			bw7.flush();
			bw7.close();
		} catch (Exception e2) {
			System.out.println("系統配置檔案初始化錯誤:" + e2);
		}
		System.out.println("初始伺服器開啟完畢");
		Sever_10000 sever = new Sever_10000();
		sever.start();
		Sever_11000 sever1 = new Sever_11000();
		sever1.start();
		Sever_12001 sever2 = new Sever_12001();
		sever2.start();
	}

}


