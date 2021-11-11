import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface getDateInterface extends Remote {
	public LocalDate getDate() throws RemoteException;

}
