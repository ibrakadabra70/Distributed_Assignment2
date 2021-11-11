import java.io.*;
import java.rmi.*;

public class Server {
   public static void main(String argv[]) throws Exception{
      if(System.getSecurityManager() == null) {
         System.setSecurityManager(new SecurityManager());
      }
		System.out.println("Starting Server");
      try {
         systemInfoInterface infoImpl = new Implement();
         Naming.rebind("info", infoImpl);
         
         getDateInterface dateImpl = new Implement();
         Naming.rebind("date", dateImpl);
         
         evaluateInterface evalImpl = new Implement();
         Naming.rebind("eval", evalImpl);
         
         getFileInterface fileImpl = new Implement();
         Naming.rebind("file", fileImpl);
         
         sortNumbersInterface sortImpl = new Implement();
         Naming.rebind("sort", sortImpl);
         
         
         System.out.println("Server is running.....");
      } catch(Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }
}