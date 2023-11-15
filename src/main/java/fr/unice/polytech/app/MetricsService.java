//package fr.unice.polytech.app;
//
//import fr.unice.polytech.app.*;
//import fr.unice.polytech.app.DataCollector;
//
//import java.time.LocalTime;
//import java.util.*;
//import java.util.stream.*;
//
//public class MetricsService {
//
//    private DataCollector dataCollector;
//
//    public MetricsService(DataCollector dataCollector) {
//        this.dataCollector = dataCollector;
//    }
//
//    public Map<LocalTime, Long> getOrderVolumeOverTime() {
//        return dataCollector.getOrders().stream()
//                .collect(Collectors.groupingBy(Order::getDeliveryTime, Collectors.counting()));
//    }
//
//    public Map<Restaurant, List<Order>> getOrdersByRestaurant() {
//        return dataCollector.getOrders().stream()
//                .collect(Collectors.groupingBy(Order::getRestaurant));
//    }
//
//    public Map<String, Long> getDeliveryPersonLocationMetrics() {
//        return DeliveryPerson.getDeliveryPeople().stream()
//                .collect(Collectors.groupingBy(DeliveryPerson::getDeliveryLocation, Collectors.counting()));
//    }
//
//    public Map<Item, Integer> getPopularMenuItems() {
//            return dataCollector.getMenuItemPopularity().entrySet().stream()
//                    .sorted(Map.Entry.<Item, Integer>comparingByValue().reversed())
//                    .collect(Collectors.toMap(
//                            Map.Entry::getKey,
//                            Map.Entry::getValue,
//                            (e1, e2) -> e1,
//                            LinkedHashMap::new));
//        }
//
//        public Map<CampusUser, Long> getUserOrderCounts() {
//            return dataCollector.getUserOrders().entrySet().stream()
//                    .collect(Collectors.toMap(
//                            Map.Entry::getKey,
//                            entry -> (long) entry.getValue().size()));
//        }
//
//        public Map<CampusUser, Double> getUserAverageOrderValue() {
//            return dataCollector.getUserOrders().entrySet().stream()
//                    .collect(Collectors.toMap(
//                            Map.Entry::getKey,
//                            entry -> entry.getValue().stream()
//                                    .mapToDouble(Order::getPrice)
//                                    .average()
//                                    .orElse(0.0)));
//        }
//
//
//    }
//
//
