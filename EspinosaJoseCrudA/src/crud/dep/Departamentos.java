/*
 * 
 * Code created by: JJEEVV
 * 
 */

/* Paquete */

package crud.dep;

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

public class Departamentos {
    
    private Connection conexion;
    private ArrayList<Departamento> departamentos;

    
    
    /* Agregar */
    
    public void agregar(Departamento dep) throws SQLException {
                        
        String query = "";
        Basededatos bd = new Basededatos();
        Statement sentencia = bd.conectar().createStatement();

        query = "INSERT INTO departamentos VALUES ( ?, ?, ? )";

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
    
    public void actualizar(Departamento dep, int dept_no) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "UPDATE departamentos SET dnombre=?, loc=? where dept_no=?";
            PreparedStatement sentenciaP = bd.conectar().prepareStatement(query);
            sentenciaP.setString(1, dep.getDnombre());
            sentenciaP.setString(2, dep.getLoc());
            sentenciaP.setInt(3, dep.getDept_no());

            sentenciaP.executeUpdate();
            System.out.println("El registro se actualizó exitosamente.");
            sentenciaP.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Eliminar */
    
    public void eliminar(Departamento dep) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "DELETE departamentos WHERE dept_no = ?;";
            PreparedStatement sentenciaP = bd.conectar().prepareStatement(query);
            sentenciaP.setInt(1, dep.getDept_no());

            sentenciaP.execute();
            System.out.println("El registro se eliminó exitosamente.");
            sentenciaP.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /* Obtener */
    
    public void obtener(Departamento dep) {
        String query = "";
        Basededatos bd = new Basededatos();
        try {
            query = "SELECT * FROM Departamentos WHERE dept_no = '" + dep.getDept_no() + "';";
            Statement sentencia = bd.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                int dept_no = resultado.getInt("dept_no");
                String dnombre = resultado.getString("dnombre");
                String loc = resultado.getString("loc");                

                // Imprimir los resultados.
                System.out.format("%d, %s, %s\n", dept_no, dnombre, loc);
            }

            sentencia.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
}
