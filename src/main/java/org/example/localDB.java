package org.example;

import java.sql.*;

public class localDB {
    public static void main(String[] args) {
        String url="jdbc:mysql://127.0.0.1:3306/ozendb?createDatabaseIfNotExist=true";
        String username = "root";
        String password = "2wsx3edc..";
        int result;

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Statement statement=null;

        try {
            connection= DriverManager.getConnection(url,username,password);

           //region[create]
            statement=connection.createStatement();
            String createTableScript = "CREATE TABLE IF NOT EXISTS info_employee ( name VARCHAR(20),department VARCHAR(30), position VARCHAR(30) )";
            statement.execute(createTableScript);
            //endregion
            //region[insert]
            String insertScript="INSERT INTO info_employee (name,department,position) VALUES (?,?,?)";
            preparedStatement=connection.prepareStatement(insertScript);

            preparedStatement.setString(1,"X");
            preparedStatement.setString(2,"Y");
            preparedStatement.setString(3,"Z dep");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1,"A");
            preparedStatement.setString(2,"B");
            preparedStatement.setString(3,"C dep");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1,"P");
            preparedStatement.setString(2,"O");
            preparedStatement.setString(3,"L dep");
            preparedStatement.executeUpdate();

     //endregion
            // region[update]
            String updateScript="UPDATE info_employee SET department=? WHERE (name=?)";
            preparedStatement=connection.prepareStatement(updateScript);
            preparedStatement.setString(1,"X");
            preparedStatement.setString(2,"L dep");
            result=preparedStatement.executeUpdate();
            System.out.println(result);
            //endregion
            //region[delete]
            String deleteScript="DELETE FROM info_employee WHERE position=?";
            preparedStatement=connection.prepareStatement(deleteScript);
            preparedStatement.setString(1,"L dep");
            result= preparedStatement.executeUpdate();
            System.out.println(result);
            //endregion
//region[select]
            //endregion
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (resultSet!=null) resultSet.close();
                if (preparedStatement!=null) preparedStatement.close();
                if (connection!=null) connection.close();
                if (statement!=null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
