import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.NewStateEvent;
import org.asteriskjava.manager.event.QueueMemberEvent;

public class GuiController {
    private GuiModel model = new GuiModel();
    private GuiView view = new GuiView(this);
    private AsterConnection connection;
    private String extension;

    public static void main(String[] args) {
        GuiController guiController = new GuiController();
        guiController.run();
    }

    public void run() {
        String username = view.getUsername();
        String password = view.getPassword();
        String host = view.getHost();
        this.extension = view.getExtension();
        //10.8.55.5
        //admin
        //a36d7c6bfe03ed655aaf0d60bb72cb77
        this.connection = new AsterConnection(host, username, password);
        mainLoop();
    }

    public void mainLoop() {
        while (true) {
            connection.getManager().addEventListener(new ManagerEventListener() {
                public void onManagerEvent(ManagerEvent managerEvent) {
                    if (managerEvent instanceof NewStateEvent) {
                        newState(managerEvent, extension);
                    }
                    else if (managerEvent instanceof QueueMemberEvent) {
                        queueState(managerEvent);
                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void newState(ManagerEvent managerEvent, String extension) {
        if (managerEvent.getExten().equals(extension) && ((NewStateEvent) managerEvent).getChannelStateDesc().equals("Up")) {
            String info = "Вам звонит: " + managerEvent.getConnectedLineNum();
            System.out.println("Вам звонит: " + managerEvent.getConnectedLineNum());
            view.refreshMessages(info);
        }
    }

    public void queueState(ManagerEvent managerEvent) {
        model.addEvent((QueueMemberEvent) managerEvent);
        view.refreshQueues();
    }

    public GuiModel getModel() {
        return this.model;
    }

}