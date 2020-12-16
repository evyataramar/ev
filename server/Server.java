package micTest;

//import com.sun.net;
import com.sun.net.httpserver.HttpServer;


import java.net.*;
import java.util.Hashtable;




public class Server {
		public int clientID;
		public static Hashtable<Integer,Value> ht= new Hashtable<Integer, Value>();
		public static StopWatch timer = new StopWatch();
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
		}  catch (Exception e) {
	      System.err.println(e);
	      System.err.println("Server ERROR!!");
	    }
		
	}

}

