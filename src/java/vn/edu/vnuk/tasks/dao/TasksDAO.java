/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.tasks.dao;

/**
 *
 * @author Hoang Trung
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import vn.edu.vnuk.tasks.jdbc.ConnectionFactory;
import vn.edu.vnuk.tasks.model.Tasks;
public class TasksDAO {
private Connection connection;

    public TasksDAO(){
            this.connection = new ConnectionFactory().getConnection();
    }


    //  CREATE
    public void create(Tasks tasks) throws SQLException{

        String sqlQuery = "insert into contacts (name, email, address, date_of_birth) "
                        +	"values (?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, tasks.getId());
                statement.setString(2, tasks.getDescription());
                statement.setString(3, tasks.getComplete());
                statement.setDate(4, new Date(tasks.getDateOfCompletion().getTimeInMillis()));

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
                connection.close();
        }

    }
    
    
    //  READ (List of Contacts)
    @SuppressWarnings("finally")
    public List<Tasks> read() throws SQLException {

        String sqlQuery = "select * from tasks";
        PreparedStatement statement;
        List<Tasks> tasks = new ArrayList<Tasks>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            while(results.next()){

                Tasks tasks = new tasks();
                tasks.setId(results.getLong("id"));
                tasks.setDescription(results.getString("description"));
                tasks.setComplete(results.getString("complete"));
               

                Calendar date = Calendar.getInstance();
                date.setTime(results.getDate("date_of_completion"));
                tasks.setDateOfCompletion(date);

                tasks.add(tasks);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return tasks;
        }


    }


    //  READ (Single Contact)
    @SuppressWarnings("finally")
    public Tasks read(int id) throws SQLException{

        String sqlQuery = "select * from contacts where id=?";

        PreparedStatement statement;
        Tasks tasks = new tasks();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                tasks.setId(results.getLong("id"));
                tasks.setDescription(results.getString("description"));
                tasks.setComplete(results.getString("complete"));
               

                Calendar date = Calendar.getInstance();
                date.setTime(results.getDate("date_of_completion"));
                tasks.setDateOfCompletion(date);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return tasks;
        }

    }


    //  UPDATE
    public void update(Tasks tasks) {
        String sqlQuery = "update contacts set name=?, email=?," 
                            + "address=?, date_of_birth=? where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, tasks.getDescription());
            statement.setString(2, tasks.getComplete());
            statement.setDate(4, new Date(tasks.getDateOfCompletion().getTimeInMillis()));
            statement.setLong(5, tasks.getId());
            statement.execute();
            statement.close();
            
            System.out.println("Contact successfully modified.");
        } 

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    
    //  DELETE
    public void delete(Tasks tasks) {
        String sqlQuery = "delete from contacts where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, tasks.getId());
            statement.execute();
            statement.close();
            
            System.out.println("Contact successfully deleted.");

        } 

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
}
