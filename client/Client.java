package micTest;


import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread{
	public static int numOfClient;
	

	
	
	public void run() {
		try {	
		    while(true) {
		    		Random randId = new Random();
		    	    int id = randId.nextInt(Client.numOfClient);
		    	    String urlString = "http://localhost:8080/?ClientID=" + id;
		    		URL url = new URL(urlString);
		    		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    		con.setRequestMethod("GET");
		    		con.setDoOutput(true);
		    		
		    		Random rand = new Random(); 
		    		Thread.sleep(rand.nextInt(5000));
		    		
		    		int code = con.getResponseCode();
		    		if(code!=200) {
		    	//	System.out.println(code /*+ " " + new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8)*/ );
		    			
		    		}
		    }
		}catch(Exception e) {
			System.err.println(e);
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter the num of httpClient");
	    int numHttpCliet = Integer.parseInt(myObj.nextLine());  // Read user input
	    myObj.close();
	    Client.numOfClient = numHttpCliet;
	    for(int i=0;i<numHttpCliet;i++) {
	    	Client c = new Client();
	    	c.start();
	    }
	   
	}


}
