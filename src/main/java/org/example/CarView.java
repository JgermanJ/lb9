package org.example;

import java.util.List;
import java.util.Map;

public class CarView {
    public static void showList(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        System.out.println("--------------------");
    }
    public static void showListt(List<String> cars) {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        System.out.println("--------------------");
    }
    public static void showListtt (Map<String, Car> cars){
        for (Map.Entry entry : cars.entrySet()) {
            System.out.println(entry.getKey()+" - "+ entry.getValue().toString());
        }
    }


}
