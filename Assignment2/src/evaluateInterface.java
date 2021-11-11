import java.rmi.Remote;
import java.rmi.RemoteException;

public interface evaluateInterface extends Remote{
	public double eval(String exp) throws RemoteException;
}
