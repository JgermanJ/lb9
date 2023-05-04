package org.example;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
public class CarsServer {
    private Connection connection;
    // Усі моделі
    //1
    public List<Car> findList() {
        ArrayList<Car> car = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from cars  ")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                LocalDate createYear = rs.getDate("createYear").toLocalDate();
                double price = rs.getDouble("price");
                String winCode= rs.getString("winCode");
                car.add(new Car(id,model,createYear, price,winCode));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

// Список автомобілів заданої моделі, які експлуатуються більше n років;
    //2
        public List<Car> getCarsExploitedMoreThenThisYear(Scanner sc) {
            System.out.println("Please, enter year:");
            String yearSearch = sc.nextLine();
            ArrayList<Car> car = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement("select * from cars where createYear > ?")) {
                ps.setString(1, yearSearch);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String model = rs.getString("model");
                    LocalDate createYear = rs.getDate("createYear").toLocalDate();
                    double price = rs.getDouble("price");
                    String winCode = rs.getString("winCode");
                    car.add(new Car(id, model, createYear, price, winCode));

                }
                } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return car;

            }
            //4
            public List<Car> getCarsPriceDown(Scanner sc) {
        ArrayList<Car> car = new ArrayList<>();
                try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM cars ORDER BY price DESC, createYear ASC;")) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String model = rs.getString("model");
                        LocalDate createYear = rs.getDate("createYear").toLocalDate();
                        double price = rs.getDouble("price");
                        String winCode = rs.getString("winCode");
                        car.add(new Car(id, model, createYear, price, winCode));

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return car;
            }
//5
    public List<String> modelSort() {
        ArrayList<String> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select distinct model from cars")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                cars.add(rs.getString("model"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    //3
    public List<Car> sortCarsByPriceAndYear(Scanner scanner) {
        System.out.println("Please enter year:");
        int year = scanner.nextInt();
        System.out.println("Please enter price:");
        double price = scanner.nextDouble();

        ArrayList<Car> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM cars WHERE createYear = ? AND price > ? ORDER BY price ASC")) {
            ps.setInt(1, year);
            ps.setDouble(2, price);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                LocalDate createYear = rs.getDate("createYear").toLocalDate();
                double carPrice = rs.getDouble("price");
                String winCode = rs.getString("winCode");
                cars.add(new Car(id, model, createYear, carPrice, winCode));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }


//6
public Map<String, Car> getCarsByModelMap() {
    Map<String, Car> carMap = new HashMap<>();
    try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM cars")) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String model = rs.getString("model");
            LocalDate createYear = rs.getDate("createYear").toLocalDate();
            double price = rs.getDouble("price");
            String winCode = rs.getString("winCode");
            Car car = new Car(id, model, createYear, price, winCode);
            if (!carMap.containsKey(model)) {
                carMap.put(model, car);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return carMap;
}
}