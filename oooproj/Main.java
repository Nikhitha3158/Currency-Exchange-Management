package oooproj;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        Passenger passenger;
        CurrencyConverter converter = new CurrencyConverter();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Currency Exchange Management System ---");
            System.out.println("1. Add Passenger and Exchange Currency");
            System.out.println("2. View Passenger by Exchange ID");
            System.out.println("3. Add Employee");
            System.out.println("4. View Employees");
            System.out.println("5. Update Currency Rates");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Add Passenger and Exchange Currency
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = sc.next();
                    System.out.print("Enter Passport Number: ");
                    String passportNumber = sc.next();
                    passenger = new Passenger(passengerName, passportNumber);

                    System.out.print("Enter From Currency (e.g., USD): ");
                    String fromCurrency = sc.next();
                    System.out.print("Enter To Currency (e.g., EUR): ");
                    String toCurrency = sc.next();
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    double exchangedAmount = converter.exchangeCurrencyForPassenger(fromCurrency, toCurrency, amount);
                    System.out.println("Exchanged Amount: " + exchangedAmount);
                    passenger.saveToDatabase(fromCurrency, toCurrency, amount, exchangedAmount);
                    break;

                case 2:
                    // View Passenger by Exchange ID
                    System.out.print("Enter Exchange ID: ");
                    String exchangeId = sc.next();
                    Passenger retrievedPassenger = Passenger.getPassengerByExchangeId(exchangeId);
                    if (retrievedPassenger != null) {
                        System.out.println("Passenger Name: " + retrievedPassenger.getName());
                        System.out.println("Passport Number: " + retrievedPassenger.getPassportNumber());
                        System.out.println("From Currency: " + retrievedPassenger.getFromCurrency());
                        System.out.println("To Currency: " + retrievedPassenger.getToCurrency());
                        System.out.println("Amount: " + retrievedPassenger.getAmount());
                        System.out.println("Exchanged Amount: " + retrievedPassenger.getExchangedAmount());
                    } else {
                        System.out.println("Passenger not found with Exchange ID: " + exchangeId);
                    }
                    break;

                case 3:
                    // Add Employee
                    System.out.print("Enter Employee Name: ");
                    String employeeName = sc.next();
                    System.out.print("Enter Employee Position: ");
                    String position = sc.next();
                    System.out.print("Enter Airport: ");
                    String airport = sc.next();
                    Employee newEmployee = new Employee(employeeName, position, airport);
                    newEmployee.saveToDatabase();
                    System.out.println("Employee added successfully.");
                    break;

                case 4:
                    // View Employees
                    Employee.viewEmployees();
                    break;

                case 5:
                    // Update Currency Rates
                    System.out.print("Enter From Currency: ");
                    String currencyFrom = sc.next();
                    System.out.print("Enter To Currency: ");
                    String currencyTo = sc.next();
                    System.out.print("Enter New Bid Rate: ");
                    double bidRate = sc.nextDouble();
                    System.out.print("Enter New Ask Rate: ");
                    double askRate = sc.nextDouble();

                    CurrencyConverter.updateCurrencyRate(currencyFrom, currencyTo, bidRate, askRate);
                    System.out.println("Currency rates updated successfully.");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting the program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
