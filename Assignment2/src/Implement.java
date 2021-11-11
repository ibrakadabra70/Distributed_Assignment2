import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Arrays;

public class Implement extends UnicastRemoteObject implements getFileInterface, getDateInterface, evaluateInterface, systemInfoInterface, sortNumbersInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Implement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFile(File f) {
		// TODO Auto-generated method stub
		//checks if file exist in the root directory
        if(f.exists()){
            //if file exists reads all the content and sends to client
            String content;
			try {
				content = Files.readString(f.toPath(), StandardCharsets.UTF_8);
				content = "200 OK\n\n" + content;
	            System.out.println(content);
	            return content;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}
            
     
        }else {
            //sends that file is not found
        	String error = "Error 404 File not found";
            System.out.println(error);
            return error;
        }
	}
	
	public String getInfo() throws UnknownHostException {
		String info = "Host name:"+ InetAddress.getLocalHost ();
		return info;
		
	}
	
	public double eval(String str) {
		 return new Object() {
	            int pos = -1, ch;

	            void nextChar() {
	                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	            }

	            boolean eat(int charToEat) {
	                while (ch == ' ') nextChar();
	                if (ch == charToEat) {
	                    nextChar();
	                    return true;
	                }
	                return false;
	            }

	            double parse() {
	                nextChar();
	                double x = parseExpression();
	                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	                return x;
	            }

	            // Grammar:
	            // expression = term | expression `+` term | expression `-` term
	            // term = factor | term `*` factor | term `/` factor
	            // factor = `+` factor | `-` factor | `(` expression `)`
	            //        | number | functionName factor | factor `^` factor

	            double parseExpression() {
	                double x = parseTerm();
	                for (;;) {
	                    if      (eat('+')) x += parseTerm(); // addition
	                    else if (eat('-')) x -= parseTerm(); // subtraction
	                    else return x;
	                }
	            }

	            double parseTerm() {
	                double x = parseFactor();
	                for (;;) {
	                    if      (eat('*')) x *= parseFactor(); // multiplication
	                    else if (eat('/')) x /= parseFactor(); // division
	                    else return x;
	                }
	            }

	            double parseFactor() {
	                if (eat('+')) return parseFactor(); // unary plus
	                if (eat('-')) return -parseFactor(); // unary minus

	                double x;
	                int startPos = this.pos;
	                if (eat('(')) { // parentheses
	                    x = parseExpression();
	                    eat(')');
	                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                    x = Double.parseDouble(str.substring(startPos, this.pos));
	                } else if (ch >= 'a' && ch <= 'z') { // functions
	                    while (ch >= 'a' && ch <= 'z') nextChar();
	                    String func = str.substring(startPos, this.pos);
	                    x = parseFactor();
	                    if (func.equals("sqrt")) x = Math.sqrt(x);
	                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                    else throw new RuntimeException("Unknown function: " + func);
	                } else {
	                    throw new RuntimeException("Unexpected: " + (char)ch);
	                }

	                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	                return x;
	            }
	        }.parse();
		
	}
	
	public LocalDate getDate()
	{
		LocalDate myObj = LocalDate.now(); 
		return myObj;
	}

	@Override
	public String sortNumbers(int[] arr) throws RemoteException {
		// TODO Auto-generated method stub
		Arrays.sort(arr);
		
		return Arrays.toString(arr);
	}

}
