public class subscrSteeringPos {
    public static String getSteeringPos() {
        String topic = "GolfCart/SteeringPosition";
        return subscribeSt.mqtt_sub(topic);
    }
}
