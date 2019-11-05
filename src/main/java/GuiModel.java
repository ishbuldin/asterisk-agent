import org.asteriskjava.manager.event.QueueMemberEvent;
import java.util.*;

public class GuiModel {
    private Map<String, String> queueMap = new HashMap<String, String>();

    public void addEvent(QueueMemberEvent queueMemberEvent) {
        queueMap.put(queueMemberEvent.getName(), QueueStatus.values()[queueMemberEvent.getStatus()].toString());
    }

    public Map<String, String> getQueueMap() {
        return Collections.unmodifiableMap(queueMap);
    }
}