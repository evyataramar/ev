package micTest;

import java.io.IOException;
import java.io.OutputStream;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler {    
  @Override    
  public void handle(HttpExchange httpExchange) throws IOException {
    String requestParamValue=null; 
    if("GET".equals(httpExchange.getRequestMethod())) { 
       requestParamValue = handleGetRequest(httpExchange);
    }
  //  System.out.println(requestParamValue);
    handleResponse(httpExchange,requestParamValue); 
  }
   private String handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("=")[1];
   }
   private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("Hello ")
                    .append(requestParamValue)
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
            String htmlResponse = htmlBuilder.toString();
            int ClientID;
	        ClientID = Integer.parseInt(requestParamValue);
	        if(!Server.ht.containsKey(ClientID)) {
	        	//FIRST TIME KEY!!
	        	Server.timer.split();
	        	Value v = new Value(Server.timer.getSplitTime());
	        	Server.ht.put(ClientID,v);
	        	httpExchange.sendResponseHeaders(200, htmlResponse.length());
				outputStream.write(htmlResponse.getBytes());
				outputStream.flush();
				outputStream.close();
	        } else {
	        	int count = Server.ht.get(ClientID).AddAndGetCount();
	        	Server.timer.split();
	        	long endTime = Server.timer.getSplitTime();
	        	if((endTime - Server.ht.get(ClientID).getStartTime()<5000) && count<5) {
	        		//SMALLER THEN 5 SEC and count smaller then 5
	        		httpExchange.sendResponseHeaders(200,0);
	        		outputStream.write(htmlResponse.getBytes());
	        		outputStream.flush();
	        		outputStream.close();
	        	} else {
	        		//MORE THEN 5 SEC or count >5
	        		Server.ht.remove(ClientID);
	        		if(count<5) {
	        				httpExchange.sendResponseHeaders(200, htmlResponse.length());
	        				outputStream.write(htmlResponse.getBytes());
	        				outputStream.flush();
	        				outputStream.close();
	        			} else {
	        					httpExchange.sendResponseHeaders(503, htmlResponse.length());
	        					outputStream.write(htmlResponse.getBytes());
	        					outputStream.flush();
	        					outputStream.close();
	        			}
	        	}
	       }
        
		
   }
}

