public class subscrDistToObstacle {
    public static String getDistToObstacle() {
        String topic = "GolfCart/DistanceToObstacle";
        return subscribeSt.mqtt_sub(topic);
    }
}
