package jcrud_serialization;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class JCRUD_Serialization {

    public static void main(String[] args) {
        HashMap<String, Car> cars;
        System.out.println("BIENVENIDO AL SISTEMA DEL CONCESIONARIO");
        while (true) {
            System.out.print("Por favor escoga su opción de preferencia:\n1)Agregar Auto\n2)Leer\n3)Actualizar\n4)Eliminar\n5)Salir\n>");
            int option = Validate.MainOption();
            if (option == 5) {
                System.out.println("Hasta luego");
                break;
            }
            switch (option) {
                case 1:
                    add();
                    break;
                case 2:
                    cars = Unserialize();
                    if (cars.isEmpty()) {
                        System.out.println("La lista de Autos está vacia.");

                    } else {
                        read();
                    }
                    break;
                case 3:
                    cars = Unserialize();
                    if (cars.isEmpty()) {
                        System.out.println("La lista de Autos está vacia.");

                    } else {
                        update();
                    }
                    break;
                case 4:
                    cars = Unserialize();
                    if (cars.isEmpty()) {
                        System.out.println("La lista de Autos está vacia.");

                    } else {
                        delete();
                    }
                    break;
            }
        }
    }

    static void add() {
        File carsFile = new File("Cars.elements");
        HashMap<String, Car> cars = new HashMap<>();
        if (carsFile.exists()) {
            cars = Unserialize();
        }
        String plate = Validate.plate();
        String model = Validate.model();
        String brand = Validate.brand();
        float speed = Validate.speed();
        cars.put(plate, new Car(plate, model, brand, speed));
        serialize(cars);
    }

    static void read() {
        HashMap<String, Car> cars = Unserialize();
        cars.entrySet().stream().map(Map.Entry::getValue)
                .forEach(e -> System.out.println(String.format("------------------------------------\nMatrícula:%s\nModelo:%s\nMarca:%s\nVelocidad:%.2f\n------------------------------------", e.getLicensePlate(), e.getModel(), e.getBrand(), e.getSpeed())));
        int option = Validate.ReadOptions();
        if (option == 1) {
            report(cars);
        }
    }

    static void update() {
        HashMap<String, Car> cars = Unserialize();
        String plate;
        do {
            plate = Validate.plate();
            if (!cars.containsKey(plate)) {
                System.out.println("Placa no encontrada, Intente de nuevo");
            }
        } while (!cars.containsKey(plate));
        String model = Validate.model();
        String brand = Validate.brand();
        float speed = Validate.speed();
        System.out.println("¿Seguro que desea modificar este auto?");
        System.out.print("1)Si\n2)No\n>");
        int option = Validate.YesOrNoOption();
        if (option == 1) {
            cars.replace(plate, new Car(plate, model, brand, speed));
            serialize(cars);
            System.out.println("Auto Modificado");
        }
    }

    static void delete() {
        String plate = Validate.plate();
        HashMap<String, Car> cars = Unserialize();
        if (cars.containsKey(plate)) {
            System.out.println("¿Seguro que desea eliminar este auto?");
            System.out.print("1)Si\n2)No\n>");
            int option = Validate.YesOrNoOption();
            if (option == 1) {
                cars.remove(plate);
                serialize(cars);
                System.out.println("Auto Eliminado");
            }
        } else {
            System.out.println("Matrícula no encontrada");
        }
    }

    static void serialize(HashMap<String, Car> cars) {
        try (FileOutputStream file = new FileOutputStream("Cars.elements")) {
            ObjectOutputStream writer = new ObjectOutputStream(file);
            writer.writeObject(cars);
        } catch (IOException e) {
            System.out.println("¡Ha Ocurrido un error!\n" + e.getMessage());
        }
    }

    static HashMap<String, Car> Unserialize() {
        File carsFile = new File("Cars.elements");
        HashMap<String, Car> cars = new HashMap<>();
        if (!carsFile.exists()) {
            return cars;
        }
        try (FileInputStream file = new FileInputStream("Cars.elements")) {
            ObjectInputStream reader = new ObjectInputStream(file);
            cars = (HashMap<String, Car>) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("¡Ha Ocurrido un error!\n" + e.getMessage());
        }
        return cars;
    }

    static void report(HashMap<String, Car> cars) {
        int option;
        int filterOption;
        while (true) {
            cars.entrySet().stream().map(Map.Entry::getValue)
                    .forEach(e -> System.out.println(String.format("------------------------------------\nMatrícula:%s\nModelo:%s\nMarca:%s\nVelocidad:%.2f\n------------------------------------", e.getLicensePlate(), e.getModel(), e.getBrand(), e.getSpeed())));
            System.out.print("¿Que desea filtrar de la información?\n1)Matrícula\n2)Modelo\n3)Marca\n4)Velocidad\n5)Generar reporte\n>");
            option = Validate.ReportOptions();
            if (option == 5) {
                File file = new File("reporte.txt");
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("¡Ha Ocurrido un error!\n" + e.getMessage());
                }
                cars.entrySet().stream().map(Map.Entry::getValue).forEach(e -> {
                    try (FileWriter fw = new FileWriter("reporte.txt", true)) {
                        fw.write(String.format("------------------------------------\nMatrícula:%s\nModelo:%s\nMarca:%s\nVelocidad:%.2f\n------------------------------------", e.getLicensePlate(), e.getModel(), e.getBrand(), e.getSpeed()));
                        fw.close();
                    } catch (IOException ex) {
                        System.out.println("¡Ha Ocurrido un error!\n" + ex.getMessage());
                    }
                });
                break;
            }
            switch (option) {
                case 1:
                    System.out.print("¿Cómo desea filtrar su opción?\n1)Inicia con...\n2)Es igual A...\n3)Termina con...\n>");
                    filterOption = Validate.FilterOptions();
                    String plate = Validate.plate();
                    switch (filterOption) {
                        case 1:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getLicensePlate().startsWith(plate)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 2:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getLicensePlate().equals(plate)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 3:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getLicensePlate().endsWith(plate)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                    }
                    break;
                case 2:
                    System.out.print("¿Cómo desea filtrar su opción?\n1)Inicia con...\n2)Es igual A...\n3)Termina con...\n>");
                    filterOption = Validate.FilterOptions();
                    String model = Validate.model();
                    switch (filterOption) {
                        case 1:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getModel().startsWith(model)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 2:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getModel().equals(model)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 3:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getModel().endsWith(model)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                    }
                    break;
                case 3:
                    System.out.print("¿Cómo desea filtrar su opción?\n1)Inicia con...\n2)Es igual A...\n3)Termina con...\n>");
                    filterOption = Validate.FilterOptions();
                    String brand = Validate.plate();
                    switch (filterOption) {
                        case 1:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getBrand().startsWith(brand)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 2:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getBrand().equals(brand)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 3:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getBrand().endsWith(brand)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                    }
                    break;
                case 4:
                    System.out.print("¿Cómo desea filtrar su opción?\n1)Menor que...\n2)Menor ó igual que...\n3)Es igual A...\n4)Mayor que...\n5)Mayor ó igual que...\n>");
                    filterOption = Validate.FilterNumOption();
                    float speed = Validate.speed();
                    switch (filterOption) {
                        case 1:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getSpeed() < speed).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 2:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getSpeed() <= speed).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 3:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getSpeed() == speed).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 4:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getSpeed() > speed).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                        case 5:
                            cars = cars.entrySet().stream().filter(e -> e.getValue().getSpeed() >= speed).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (Value, skippedValue) -> Value, HashMap::new));
                            break;
                    }
                    break;
            }
        }

    }

}
