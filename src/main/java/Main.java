import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        while(true){
            try {

                String url = "http://api.f2pool.com/bitcoin/rickaelf";

                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                conn.setRequestMethod("GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null){

                    response.append(inputLine+"\r\n");
                }
                br.close();
                System.out.println("**************");
                System.out.println(df.format(new Date()));

                JSONObject json = new JSONObject(response.toString());
                Integer onlineWorkers = json.getInt("worker_length_online");
                Integer allWorkers = json.getInt("worker_length");
                if(onlineWorkers!=allWorkers){
                    System.out.println("ALARM!!!!!11");
                    System.out.println("ALARM!!!!!11");
                    System.out.println("ALARM!!!!!11");

                    System.out.println(allWorkers-onlineWorkers+" are not online. Check the site");
                }else{
                    System.out.println("Everything is ok");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(900000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
