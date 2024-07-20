package org.example;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

//import java.util.Map;
public class remoteDB {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7720195";
        String USERNAME = "sql7720195";
        String PASSWORD = "IJAXupWyZL";
        //private Jdbi jdbi;

        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet=null;
        PreparedStatement preparedStatement = null;


        Jdbi jdbi = Jdbi.create(URL, USERNAME, PASSWORD);

        //region[create]
            try (Handle handle = jdbi.open()) {
                handle.execute("CREATE TABLE IF NOT EXISTS info_employee3 ( name VARCHAR(255), surname VARCHAR(255), department VARCHAR(255), job_time DATETIME, position VARCHAR(255))");
                System.out.println("Tablo oluşturuldu");
            }catch (StatementException e){
                e.printStackTrace();
            }
  //endregion

//region[insert]
           try (Handle handle = jdbi.open()) {
        handle.execute("INSERT INTO info_employee3 (name,surname,department,job_time,position) VALUES (?,?,?,?,?)", "Ozen", "Tastan", "Meva", "2022-09-17", "YazilimTestMuhendisi");
              handle.execute("INSERT INTO info_employee3 (name,surname,department,job_time,position) VALUES (?,?,?,?,?)",  "X", "Y", "Z", "2022-09-29", "YazilimGelistirici");
             handle.execute("INSERT INTO info_employee3 (name,surname,department,job_time,position) VALUES (?,?,?,?,?)", "A", "B", "C", "2022-09-20", "YazilimMuhendisi");
               System.out.println("Insert işlemi tamamlandı");
           }catch (StatementException e){
               e.printStackTrace();
            }
        //endregion

//region[update]
            try (Handle handle = jdbi.open()) {
                handle.createUpdate("UPDATE info_employee3 SET department= :department WHERE name= :name").bind("name", "Ozen").bind("department", "MevaOkc").execute();
                System.out.println("update işlemi tamamlandı");
            }catch (StatementException e){
                e.printStackTrace();
            }
        //endregion
//region[select]
            try (Handle handle = jdbi.open()) {
                handle.createUpdate("SELECT * FROM info_employee3 WHERE name= :name").bind("name", "Ozen").execute();//.mapTo(String.class).one();
                System.out.println("select işlemi tamamlandı");
            }catch (StatementException e){
                e.printStackTrace();
            }
//endregion
        //region[delete]
            try (Handle handle = jdbi.open()) {
                handle.createUpdate("DELETE FROM info_employee3 WHERE position= :position").bind("position", "YazilimMühendisi").execute();
                System.out.println("delete işlemi tamamlandı");
            }catch (StatementException e){
                e.printStackTrace();
            }
        }
    //endregion

}

