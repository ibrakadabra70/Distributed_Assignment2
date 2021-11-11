import java.io.*;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.rmi.*;
import java.time.LocalDate;
import java.util.Scanner;





public class Client{
   public static void main(String argv[]) throws Exception{
      Scanner in = new Scanner(System.in);
      //this loop runs until finish command is given
      while(true){
          //takes command from users
          System.out.println("Enter command for server or use help: ");
          String cmd = in.nextLine();
          //if finish command is received the socket connection will be closed
          if(cmd.equals("finish")){
              //Sends Exiting message
              System.out.println("A connection is closed");
              break;
              //this if statement handles get request
          }else if(cmd.startsWith("get ")){
              //get file name
              String filename = cmd.replaceFirst("get ", "");
              
              File f = new File(filename);
              getFileInterface file = (getFileInterface) Naming.lookup("file");
              String output = file.getFile(f);
              System.out.println(output);
          //this part handles info commands
          }else if(cmd.equals("info")){
        	  systemInfoInterface info = (systemInfoInterface) Naming.lookup("info");
              String output = info.getInfo();
              System.out.println(output);
              
          }else if(cmd.startsWith("evaluate ")){
        	  String exp = cmd.replaceFirst("evaluate ","");
        	  evaluateInterface evaluate = (evaluateInterface) Naming.lookup("eval");
              String output = Double.toString(evaluate.eval(exp));
              System.out.println(output);
          }else if(cmd.equals("help")){
              System.out.println("Usable commands are: \ninfo: Get info about server" +
                      "\nget <filename>: To get the content of the file in server" +
                      "\nevaluate <math expression>: Server calculates the expression and returns result" +
                      "\nfinish: Client closes and also server stops the thread assigned to client"+
                      "\ndate: Server gets date and sends it to client"+
                      "\nsort: User enters numbers and a sorted array will be returned");
          }
          else if(cmd.equals("date")){
        	  getDateInterface date = (getDateInterface) Naming.lookup("date");
        	  LocalDate output = date.getDate();
        	  System.out.println(output);
          }
          else if(cmd.startsWith("sort")){
        	  int[] num;
        	  Scanner scanner=new Scanner(System.in);
              System.out.print("How many integers you want to enter: ");
              int n = 0;
              if(scanner.hasNextInt()) {
                  n=scanner.nextInt();
              }
              num=new int[n];
              for(int i=0;i<n;i++) {
                  System.out.printf("Enter integer %d: ",i+1);
                  if(scanner.hasNextInt()) {
                      num[i]=scanner.nextInt();
                  }           
              }
              
              
              sortNumbersInterface sort = (sortNumbersInterface) Naming.lookup("sort");
              String output = sort.sortNumbers(num);
              System.out.println("Sorted Array:" + output);
          
          }
          else {
              System.out.println("Invalid Request");
          }
          
      
  }
 }
}