package micTest;

//import com.sun.net;
import com.sun.net.httpserver.HttpServer;


import java.net.*;
import java.util.Hashtable;
import java.util.Scanner;




public class Server {
		public int clientID;
		public static Hashtable<Integer,Value> ht= new Hashtable<Integer, Value>();
		public static StopWatch timer = new StopWatch();
		public static boolean keypressed;
	//	public DataOutputStream out;
		
		public Server(int tid) {
			this.clientID= tid;
		}
	
	public static void main(String[] args) {



		Server.timer.start();
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
			server.createContext("/", new  MyHttpHandler());
		//	ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
			server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
			server.start();
			Server.keypressed = false;
		    Scanner myObj = new Scanner(System.in);
		    while(myObj.nextLine()==null) {}//key press to finish
		    server.stop(0);
		}  catch (Exception e) {
	      System.err.println(e);
	      System.err.println("Server ERROR!!");
	    }
		
	}

}

