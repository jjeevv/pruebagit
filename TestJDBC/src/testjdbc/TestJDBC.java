/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario 1 DAM
 */
public class TestJDBC {
    
public static void main(String[] args)
    {
        Connection conexion = null;
        Statement sentencia = null;
        Statement sentencia2 = null;
        Statement sentencia3 = null;
        
        try {
            //Class.forName("com.mysql.jdbc.Driver"); // No necesario a partir del JDK 7
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "nba");
        } catch (SQLException e) {
            System.err.println("Error al acceder a la BBDD " + e.getMessage());
        }
        System.out.println("Conexión satisfactoria");

        String sql = "SELECT * FROM empleados";
        try {
            sentencia = conexion.createStatement();

            sentencia.execute(sql);

            ResultSet rs = sentencia.getResultSet();
            while (rs.next()) {
                System.out.printf("%05d %-10s %-15s %n",
                        rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();

            sentencia.close();
            //conexion.close();
        } catch (SQLException ex) {
            System.err.println("Error en BBDD" + ex.getMessage());
        }
        
                
        System.out.println("\n");
        System.out.println("Conexión satisfactoria");
        String sql1 = "select dnombre from departamentos where loc = 'SEVILLA'";
        try {
            sentencia2 = conexion.createStatement();

            sentencia2.execute(sql1);

            ResultSet rs1 = sentencia2.getResultSet();
            while (rs1.next()) {
                System.out.printf("%s %n",
                        rs1.getString(1));
            }
            rs1.close();

            sentencia2.close();
            //conexion.close();
        } catch (SQLException ex) {
            System.err.println("Error en BBDD" + ex.getMessage());
        }
              
        
        System.out.println("\n");
        System.out.println("Conexión satisfactoria");
        String sql2 = "select empleados.emp_no, empleados.apellido, departamentos.dnombre, departamentos.loc from empleados left join departamentos on empleados.dept_no = departamentos.dept_no";
        try {
            sentencia3 = conexion.createStatement();

            sentencia3.execute(sql2);

            ResultSet rs = sentencia3.getResultSet();
            while (rs.next()) {
                System.out.printf("%05d %-10s %-15s %-15s %n",
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            rs.close();

            sentencia3.close();
            conexion.close();
        } catch (SQLException ex) {
            System.err.println("Error en BBDD" + ex.getMessage());
        }
        
    }
}
