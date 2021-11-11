import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface systemInfoInterface extends Remote {
	public String getInfo() throws RemoteException, UnknownHostException;
	
}
