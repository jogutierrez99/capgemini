package procedimientos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ramon
 */
public class Procedimientos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
       
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int id = 0;
        Connection cn = null;
 
        try {
            
            // Carga el driver de oracle
           // DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			 // Carga el driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Conecta con la base de datos XE con el usuario system y la contraseña password
           // cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "password");
           
           
           
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicioregiones",
			"root", "1234");
            System.out.println(cn);
            
            // Llamada al procedimiento con objeto java
            CallableStatement cs = cn.prepareCall("{call getNivelSalarialProc(?,?)}");
             System.out.println(cs);
 
            do {
                System.out.println("\nIntroduce el ID del empleado:");
                try {
                    id = Integer.parseInt(entrada.readLine());
                } catch (IOException ex) {
                    System.out.println("Error en entrada por linea comandos");
                }
                
                // Parametro 1 de entrada del procedimiento almacenado
                cs.setInt(1, id);
             
                // Definimos los tipos de los parametros de salida del procedimiento 
                cs.registerOutParameter(2,java.sql.Types.INTEGER);
             
                
                // Ejecución
                cs.execute();
                
                // Se obtienen datos de la salida 
                
               // int miid = cs.getInt(1);
               // String nombre = cs.getString(2);
                int sal =cs.getInt(2);
               // String curso = cs.getString(9);
                System.out.println("id: " + id);
                //System.out.println("nombre: " + nombre);
                System.out.println("salario: " + sal);
               // System.out.println("Curso: " + curso);
                
            } while (id > 0);
 
        } catch (SQLException ex) {
			 System.out.println("Error en carga callable statement: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (SQLException ex1) {
                System.out.println("Error: " + ex1.getMessage());
            }
        }
    }
    
}