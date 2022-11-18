import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class subscribeSt {
    public static String mqtt_sub (String topic) {
        final String[] messageReceived = new String[1];
        String broker = "tcp://broker.mqttdashboard.com:1883";
        String username = "emqx";
        String password = "public";
        String clientId = "subscribe_client";
        int qos = 0;
        try {
            MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
            // connect options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // setup callback
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + message.getQos());
                    messageReceived[0] = new String(message.getPayload());
                    System.out.println("message content: " + new String(message.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            client.subscribe(topic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageReceived[0];
    }
}
