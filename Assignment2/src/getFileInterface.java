import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface getFileInterface extends Remote {
	public String getFile(File f) throws RemoteException;
}
