package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    private CarsServer CarsServer;
    private Scanner sc;
    private CarView CarView;

    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();

    }

    @SneakyThrows
    private void run() {
        Properties props = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))) {
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
            CarsServer = new CarsServer(connection);
            CarView = new CarView();
        }

        sc = new Scanner(System.in);
        int m;

        while ((m = menu()) != 0) {
            switch (m) {
                case 1 -> {
                    showAll();
                }
                case 2 -> {
                    getCarsExploitedMoreThenThisYear();
                }
                case 3 -> {
                    sortCarsByPrice();
                }
                case 4 -> {
                    getCcarsPriceDown();

                }

                case 5 -> {
                    getCarsByModel();

                }
                case 6 -> {
                    getCarsOfEachModel();
                }

            }
        }
    }


    private int menu() {
        System.out.println("""                   
                1-show all cars;                                        
                2- getCarsExploitedMoreThenThisYear;
                3-sortCarsByPriceAndYear;
                4-getCarsPriceDown;           
                5-modelSort;
                6-getCarsByModelMap;
                Chose the number of menu:
                    """
        );
        return Integer.parseInt(sc.next());
    }

    private void showAll() {
        List<Car> cars = CarsServer.findList();
        org.example.CarView.showList(cars);
    }

    public void getCarsExploitedMoreThenThisYear() {
        List<Car> cars = CarsServer.getCarsExploitedMoreThenThisYear(sc);
        org.example.CarView.showList(cars);

    }

    public void sortCarsByPrice() {
        List<Car> cars = CarsServer.sortCarsByPriceAndYear(sc);
        org.example.CarView.showList(cars);

    }

    public void getCarsByModel() {
        List<String> cars = CarsServer.modelSort();
        org.example.CarView.showListt(cars);
    }

    public void getCarsOfEachModel() {
        Map<String, Car> cars = CarsServer.getCarsByModelMap();
        org.example.CarView.showListtt(cars);
    }

    public void getCcarsPriceDown() {
        List<Car> cars = CarsServer.getCarsPriceDown(sc);
        org.example.CarView.showList(cars);
    }
}
