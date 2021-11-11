import java.rmi.Remote;
import java.rmi.RemoteException;

public interface sortNumbersInterface extends Remote{
	public String sortNumbers(int[] arr) throws RemoteException;
}
