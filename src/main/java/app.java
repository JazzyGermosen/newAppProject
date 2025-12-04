import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
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
            while (isRunning) {
                System.out.println("""
                        
                        What do you want to do?
                        
                        1)Display All Products
                        2)Display All Customers
                        3)Display ALl Employees
                        4)Display All Suppliers
                        0)Exit the app
                        
                        
                        """);

                switch (myScanner.nextInt()) {
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
                    case 5:
                        displayAllCategories(connection);
                    case 0:
                        System.out.println("Exiting program now");
                        isRunning = false;
                    default:
                        System.out.println("Invalid Choice");
                }

            }
        } catch (SQLException e) {
            System.out.println("Could not connect to the database");
            System.exit(1);
        }

    }

    public static void displayAllProducts(Connection connection) {
        // what we are doing in these methods is creating the queries we want to be executed in MySql
        //we are going ot need to start by using a try catch block
        // we are getting the results with a prepared statement
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        Select
                            ProductId,
                            ProductName,
                            UnitPrice,
                            unitsInStock
                        From
                            Products
                        Order By
                            ProductName
                        """);
        ) {

            //get the results from the query
            ResultSet results = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Could not get all the Products");
            System.exit(1);
        }

    }

    public static void displayAllCustomers(Connection connection) {
        // following the same steps as done in Display all products with updated logic
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        Select
                            ContactName,
                            CompanyName,
                            City,
                            Country,
                            Phone
                        From
                            Customer
                        Order By
                            Country
                        
                        """);
        ) {

            ResultSet results = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Could not get all the Customers");
            System.exit(1);
        }
    }

    public static void displayAllEmployees(Connection connection) {

        try (

                //create the prepared statment using the passed in connection
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT
                            ContactName,
                            CompanyName,
                            City,
                            Country,
                            Phone
                        FROM
                            Customers
                        ORDER BY
                            Country
                        """
                );

                //get the results from the query
                ResultSet results = preparedStatement.executeQuery();

        ) {

            //print the results
            printResults(results);

        } catch (SQLException e) {
            System.out.println("Could not get all the customers");
            System.exit(1);

        }

    }

    public static void printResults(ResultSet results) throws SQLException {
        //get the meta data so we have access to the field names
        ResultSetMetaData metaData = results.getMetaData();
        //get the number of rows returned
        int columnCount = metaData.getColumnCount();

        //this is looping over all the results from the DB
        while (results.next()) {

            //loop over each column in the rown and display the data
            for (int i = 1; i <= columnCount; i++) {
                //gets the current colum name
                String columnName = metaData.getColumnName(i);
                //get the current column value
                String value = results.getString(i);
                //print out the column name and column value
                System.out.println(columnName + ": " + value + " ");
            }

            //print an empty line to make the results prettier
            System.out.println();

        }

    }

    public static void displayAllSuppliers(Connection connection) {
    }

    //this method will be used in the displayMethods to actually print the results to the screen

    public static void displayAllCategories(Connection connection) {

        try (
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        Select
                            CatergoryId,
                            CategoryName
                        From
                            Categories
                        Order By
                            CategoryID
                        
                        """);
        ) {
            ResultSet results = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Could not print all the categories");
            System.exit(1);
        }
    }

}