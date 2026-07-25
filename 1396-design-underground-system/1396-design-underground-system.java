import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Helper class to store check-in information
    private static class CheckInInfo {
        String stationName;
        int time;

        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    // Helper class to store aggregated trip statistics
    private static class RouteData {
        double totalTime;
        int count;

        RouteData(double totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    // Maps card id -> CheckInInfo
    private Map<Integer, CheckInInfo> checkInMap;
    // Maps route key ("startStation->endStation") -> RouteData
    private Map<String, RouteData> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkIn = checkInMap.remove(id);
        String routeKey = checkIn.stationName + "->" + stationName;
        int travelTime = t - checkIn.time;

        RouteData route = routeMap.getOrDefault(routeKey, new RouteData(0, 0));
        route.totalTime += travelTime;
        route.count += 1;
        
        routeMap.put(routeKey, route);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteData route = routeMap.get(routeKey);
        return route.totalTime / route.count;
    }
}