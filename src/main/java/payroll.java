import java.sql.*;
import java.time.LocalDate;
import java.util.Enumeration;

public class payroll {
    static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_servicee";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection;

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.print("Driver Loaded!");
//        } catch (ClassNotFoundException e) {
//            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
//        }
//
//        listDrivers();

        try {
            System.out.println("Connecting to database: " + DB_URL);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection is successful!!!! " + connection);

            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            PreparedStatement preparedStatement3;

            System.out.println("\n");
            preparedStatement1 = connection.prepareStatement("update employee_payroll set basic_pay = ? where id = ?;");
            preparedStatement1.setDouble(1, 3000000.0);
            preparedStatement1.setInt(2, 2);
            int count = preparedStatement1.executeUpdate();
            System.out.println(count);

            System.out.println("\n");
            preparedStatement2 = connection.prepareStatement("select * from employee_payroll where name = ?");
            preparedStatement2.setString(1, "Apeksha");
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", Name: " + resultSet.getString("name"));
                System.out.print(", Gender: " + resultSet.getString("gender"));
                System.out.print(", Salary: " + resultSet.getDouble("salary"));
                System.out.print(", Basic Pay: " + resultSet.getString("basic_pay"));
                System.out.print(", Deductions: " + resultSet.getString("deductions"));
                System.out.print(", Taxable Pay: " + resultSet.getString("taxable_pay"));
                System.out.print(", Income Tax: " + resultSet.getString("income_tax"));
                System.out.print(", Net Pay: " + resultSet.getString("net_pay"));
                System.out.print(", Department: " + resultSet.getString("department"));
                System.out.print(", Start Date: " + resultSet.getDate("start"));
                System.out.print(", Phone Number: " + resultSet.getString("phonenumber"));
                System.out.print(", Address: " + resultSet.getString("address"));
                System.out.println();
            }

            System.out.println("\n");
            preparedStatement3 = connection.prepareStatement("select * from employee_payroll where start between ? and ?;");
            preparedStatement3.setDate(1, Date.valueOf("2021-06-10"));
            preparedStatement3.setDate(2, Date.valueOf(LocalDate.now()));
            boolean value = preparedStatement3.execute();
            if (value) {
                ResultSet resultSet1 = preparedStatement3.getResultSet();
                while (resultSet1.next()) {
                    System.out.print("ID: " + resultSet1.getInt("id"));
                    System.out.print(", Name: " + resultSet1.getString("name"));
                    System.out.print(", Gender: " + resultSet1.getString("gender"));
                    System.out.print(", Salary: " + resultSet1.getDouble("salary"));
                    System.out.print(", Basic Pay: " + resultSet1.getString("basic_pay"));
                    System.out.print(", Deductions: " + resultSet1.getString("deductions"));
                    System.out.print(", Taxable Pay: " + resultSet1.getString("taxable_pay"));
                    System.out.print(", Income Tax: " + resultSet1.getString("income_tax"));
                    System.out.print(", Net Pay: " + resultSet1.getString("net_pay"));
                    System.out.print(", Department: " + resultSet1.getString("department"));
                    System.out.print(", Start Date: " + resultSet1.getDate("start"));
                    System.out.print(", Phone Number: " + resultSet1.getString("phonenumber"));
                    System.out.print(", Address: " + resultSet1.getString("address"));
                    System.out.println();
                }
            } else {
                int count2 = preparedStatement3.getUpdateCount();
                System.out.println(count2);
            }
            System.out.println("\n");
            Statement statement1 = connection.createStatement();
            ResultSet sumResultSet = statement1.executeQuery("select sum(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (sumResultSet.next())
                System.out.println("Sum Of All Male Employee's Salaries: "+ sumResultSet.getInt("sum(salary)"));

            System.out.println();
            Statement statement2 = connection.createStatement();
            ResultSet avgResultSet = statement2.executeQuery("select avg(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (avgResultSet.next())
                System.out.println("Average Of All Male Employee's Salaries: "+ avgResultSet.getInt("avg(salary)"));

            System.out.println();
            Statement statement3 = connection.createStatement();
            ResultSet minResultSet = statement3.executeQuery("select min(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (minResultSet.next())
                System.out.println("Minimum Of All Male Employee's Salaries: "+ minResultSet.getInt("min(salary)"));

            System.out.println();
            Statement statement4 = connection.createStatement();
            ResultSet maxResultSet = statement4.executeQuery("select max(salary) from employee_payroll where gender = \"Male\" group by gender;");
            while (maxResultSet.next())
                System.out.println("Maximum Of All Male Employee's Salaries: "+ maxResultSet.getInt("max(salary)"));

            System.out.println();
            Statement statement5 = connection.createStatement();
            ResultSet noOfMaleEmpResultSet = statement5.executeQuery("select count(id) from employee_payroll where gender = \"Male\" group by gender;");
            while (noOfMaleEmpResultSet.next())
                System.out.println("Number Of Male Employees: "+ noOfMaleEmpResultSet.getInt("count(id)"));

            System.out.println();
            Statement statement6 = connection.createStatement();
            ResultSet noOfFemaleEmpResultSet = statement6.executeQuery("select count(id) from employee_payroll where gender = \"Female\" group by gender;");
            while (noOfFemaleEmpResultSet.next())
                System.out.println("Number Of Female Employees: "+ noOfFemaleEmpResultSet.getInt("count(id)"));

        } catch (SQLException e) {
            System.out.println("Catch");
        }
    }

//    private static void listDrivers() {
//        Enumeration<Driver> driverList = DriverManager.getDrivers();
//        while (driverList.hasMoreElements()) {
//            Driver driverClass = driverList.nextElement();
//            System.out.println(" " + driverClass.getClass().getName());
//        }
//    }
}
