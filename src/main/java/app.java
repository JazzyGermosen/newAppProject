import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class app {

    public static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // setting up for a username and password to be set up
        // if not the application will not work

        if (args.length != 2) {
            // displaying a message to the user to prompt for username and password
            System.out.println(" this application needs two arguments to run: a username and password for the db");
            // exiting the app due to failure
            System.exit(1);
        }

        // get the username and password from the args[]
        String username = args[0];
        String password = args[1];

        // the main goal with this is to create a connection between java and MySQL
        // we are going to use a Try catch with resources to be able to declare more then one resource

        try (Connection connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/northwind"), username, password)) {
            // creating the while loop inside the try catch block.
            /*
            Im assuming we are keeping the menue loop inside the try catch block because in order
            for this information to be accessed It needs to have the username and password entered
            in correctly to access the information
            * */
            boolean isRunning = true;
            // unsure the difference between setting the menu loop to false and exiting the app
            //  assuming system.exit stops the app but probably just a difference of what we are doing
            while (isRunning){
                System.out.println("""
                        
                        What do you want to do?
                        
                        1)Display All Products
                        2)Display All Customers
                        3)Display ALl Employees
                        4)Display All Suppliers
                        0)Exit the app
                        
                        
                        """);


            }
            switch (myScanner.nextInt()){
                case 1:
                    displayAllProducts(connection);
                    break;
                case 2:
                    displayAllCustomers(connection);
                    break;
                case 3:
                    displayAllEmployees(connection);
                    break;
                case 4:
                    displayAllSuppliers(connection);
                    break;
                case 0:
                    System.out.println("Exiting program now");
                    isRunning = false;
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }

    public static void displayAllProducts(Connection connection){}

    public static void displayAllCustomers(Connection connection){}

    public static void displayAllEmployees(Connection connection){}

    public static void displayAllSuppliers(Connection connection){}

}