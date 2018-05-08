/*
 * 
 * Code created by: JJEEVV
 * 
 */

/* Paquete */

package crud.emp;

/* Imports */

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import  crud.bbdd.Basededatos;

/**
 *
 * @author jjeevv
 *
 */

public class Empleados {
    
    private Connection conexion;
    private ArrayList<Empleado> Empleados;
    
    /* Agregar */
    
    public void agregar(Empleado emp) throws SQLException {
                        
        String query = "";
        Basededatos bd = new Basededatos();
        Statement sentencia = bd.conectar().createStatement();

        query = "INSERT INTO empleados VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";

        if (sentencia.executeUpdate(query) > 0) {
            System.out.println("El registro se insertó exitosamente.");
        } else {
            System.out.println("No se pudo insertar el registro.");
        }

        System.out.println(query);
        sentencia.close();
        bd.conexion.close();        
    
    }
    
    /* Actualizar */
    
    public void actualizar(Empleado emp, int emp_no) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "UPDATE  empleados SET apellido=?, oficio=?, dir=?, fecha_alt=?, salario=?, comision=?, dept_no=? WHERE emp_no=?";
            PreparedStatement sentenciaP = bd.conectar().prepareStatement(query);
            sentenciaP.setString(1, emp.getApellido());
            sentenciaP.setString(2, emp.getOficio());
            sentenciaP.setInt(3, emp.getDir());
            sentenciaP.setDate(4, emp.getFecha_alt());
            sentenciaP.setFloat(5, emp.getSalario());
            sentenciaP.setFloat(6, emp.getComision());
            sentenciaP.setInt(7, emp.getDept_no());
            sentenciaP.setInt(8, emp.getEmp_no());

            sentenciaP.executeUpdate();
            System.out.println("El registro se actualizó exitosamente.");
            sentenciaP.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Eliminar */
    
    public void eliminar(Empleado emp) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "DELETE empleados WHERE emp_no = ?;";
            PreparedStatement sentenciaP = bd.conectar().prepareStatement(query);
            sentenciaP.setInt(1, emp.getEmp_no());

            sentenciaP.execute();
            System.out.println("El registro se eliminó exitosamente.");
            sentenciaP.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Obtener */
    
    public void obtener(Empleado emp) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "SELECT * FROM empleados WHERE Emp_no = '" + emp.getEmp_no() + "';";
            Statement sentencia = bd.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                int Emp_no = resultado.getInt("Emp_no");               

                // Imprimir los resultados.
                System.out.format("%d \n", Emp_no);
            }

            sentencia.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
}
