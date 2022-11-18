public class subscrDrivingDir {
    public static String getDrivingDir() {
        String topic = "GolfCart/DrivingDirection";
        return subscribeSt.mqtt_sub(topic);
    }
}
