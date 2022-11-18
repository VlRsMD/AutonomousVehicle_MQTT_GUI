import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class subscrSpeed {
    public static String getSpeed() {
        String topic = "GolfCart/Speed";
        return subscribeSt.mqtt_sub(topic);
    }
}
