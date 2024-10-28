package jcrud_serialization;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {

    static int MainOption() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                int op = sc.nextInt();
                if (op > 5 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 5:\n1)Agregar Auto\n2)Leer\n3)Actualizar\n4)Eliminar\n5)Salir\n>");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:\n1)Agregar Auto\n2)Leer\n3)Actualizar\n4)Eliminar\n5)Salir\n>");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static int YesOrNoOption() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                int op = sc.nextInt();
                if (op > 2 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 2:\n1)Si\n2)No\n>");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:\n1)Si\n2)No\n>");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static int ReadOptions() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\n1)Generar Reporte\n2)Volver\n>");
            try {
                int op = sc.nextInt();
                if (op > 2 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 2:");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static int ReportOptions() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                int op = sc.nextInt();
                if (op > 5 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 5:\n1)Matrícula\n2)Modelo\n3)Marca\n4)Velocidad\n5)Generar reporte\n>");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:\n1)Matrícula\n2)Modelo\n3)Marca\n4)Velocidad\n5)Generar reporte\n>");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static int FilterOptions() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                int op = sc.nextInt();
                if (op > 3 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 3:\n1)Inicia con...\n2)Es igual A...\n3)Termina con...\n>");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:\n1)Inicia con...\n2)Es igual A...\n3)Termina con...\n>");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static int FilterNumOption() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                int op = sc.nextInt();
                if (op > 5 || op < 1) {
                    throw new InvalidOptionException("Por favor solo introduzca opciones entre 1 y 5:\n1)Menor que...\n2)Menor ó igual que...\n3)Es igual A...\n4)Mayor que...\n5)Mayor ó igual que...\n>");
                }
                return op;
            } catch (InputMismatchException e) {
                System.out.print("Por favor solo introduzca numeros:\n1)Menor que...\n2)Menor ó igual que...\n3)Es igual A...\n4)Mayor que...\n5)Mayor ó igual que...\n>");
            } catch (InvalidOptionException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    static String plate() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Por favor especifique la placa del auto:");
            String plate = sc.nextLine();
            if (!plate.matches("[0-9A-Za-z\\s]{1,12}$")) {
                System.out.println("Placa no valida, intente de nuevo:");
                continue;
            }
            return plate;
        }
    }

    static String model() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Por favor especifique el modelo del auto:");
            String model = sc.nextLine();
            if (!model.matches("[A-Za-z\\s]{1,30}$")) {
                System.out.println("Modelo no valida, intente de nuevo:");
                continue;
            }
            return model;
        }
    }

    static String brand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Por favor especifique la Marca del auto:");
            String brand = sc.nextLine();
            if (!brand.matches("[A-Za-z\\s]{1,15}$")) {
                System.out.println("Marca no valida, intente de nuevo:");
                continue;
            }
            return brand;
        }
    }

    static float speed() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Por favor especifique la velocidad del auto:");
            String speed = sc.nextLine();
            if (!speed.matches("\\d+(\\.\\d{1,2})?$")) {
                System.out.println("Velocidad no valida, intente de nuevo:");
                continue;
            }
            return Float.parseFloat(speed);
        }
    }
}
