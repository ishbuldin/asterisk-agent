import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.ManagerConnection;

public class AsterConnection {
    private AsteriskServer asteriskServer;
    private ManagerConnection managerConnection;

    AsterConnection(String hostname, String username, String password) {
        this.asteriskServer = new DefaultAsteriskServer(hostname, username, password);
        asteriskServer.initialize();
    }

    public ManagerConnection getManager() {
        if (managerConnection == null) {
            managerConnection = asteriskServer.getManagerConnection();
        }
        return managerConnection;
    }
}