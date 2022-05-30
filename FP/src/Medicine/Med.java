/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medicine;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 *
 * @author mac
 */
public class Med implements CalculateMed{
    double percent;
    double usage;
    double priceMed;
    String medName;
    public Connection conn;
    public Statement statement;
    
    public Med(String medName, double percent, double usage, double priceMed){
        this.medName = medName;
        this.percent = percent;
        this.usage = usage;
        this.priceMed = priceMed;
    }

    Med() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double hitungDosis(double x, double y){
        return x * y;
    }
    
    @Override
    public double hitungDosis() {
        return percent*usage;
    }

    @Override
    public double hitungHarga() {
        return priceMed*(percent*usage);
    }
    double getPercent(){
        return percent;
    }
    double getUsage(){
        return usage;
    }
    double getPriceMed(){
        return priceMed;
    }
    public void Connector(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/fp_pbo?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            System.out.println("Connect Success");
        }catch(Exception ex){
            System.out.println("Connect Failed " + ex.getMessage());
        }
    }
    public void insertData(){
        try{
            String query = "INSERT INTO `data_user`(`medName`,`percent`,`usage`, `PriceMed`,`dose`) VALUES ('"+this.medName+"','"+this.percent+"','"+this.usage+"','"+hitungHarga()+"','"+hitungDosis()+"')";
            this.Connector();
                statement = conn.createStatement();
                statement.execute(query);
                JOptionPane.showMessageDialog(null,"Insert Successful");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex, "Cannot Open", JOptionPane.WARNING_MESSAGE);
        }
    }
}
