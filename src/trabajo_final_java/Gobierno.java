/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo_final_java;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Gobierno {

    /**
     * @param args the command line arguments
     */
    private static final Logger LOGGER = Logger.getLogger(Gobierno.class.getName());
    ArrayList<Ciudadano> Lista;
    static int opcion;

    public static void main(String[] args) {
        Ciudadano ciudadano = new Ciudadano();
        try {
            // TODO code application logic here
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gobierno.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {
            // Menu principal
            System.out.println("*********       Menu principal     ********");
            System.out.println("1. Cargar informacion de la base de datos");
            System.out.println("2. Crear ciudadano en la base de datos");
            System.out.println("3. Crear inmueble en la base de datos");
            System.out.println("4. Generar reporte por ciudadano");
            System.out.println("5. Salir");
            System.out.println("*******************************************");

            String opcion = new Scanner(System.in).next();

            switch (opcion) {
                case "1":
                    leerInfo();
                    break;
                case "2":
                    CrearCiudadano(cargarCiudadano());
                    break;
                case "3":
                    cargarInmueble();
                    break;
                case "4":
                    ReporteInmueblesPorCiudadano(cargarID());

            }
        } while (opcion != '5');
    }

    public static void leerInfo() {

        String sql = "select * from ciudadanos";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taller_java", "root", "");
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {

            String out = "ID Nombres Apellidos ";
            System.out.println(out);
            while (rs.next()) {

                System.out.println(rs.getString("Id") + " " + rs.getString("Nombres") + " " + rs.getString("Apellidos"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public static Ciudadano cargarCiudadano() {
        Ciudadano ciu = null;
        ciu = new Ciudadano();

        System.out.println("Ingrese id ciudadano");
        ciu.setId(new Scanner(System.in).next());
        
        System.out.println("Ingrese nombres");
        ciu.setNombres(new Scanner(System.in).next());
        
        System.out.println("Ingrese apellidos");
        ciu.setApellidos(new Scanner(System.in).next());

        return ciu;
    }

    public static Ciudadano cargarID() {
        Ciudadano ciuda = null;

        ciuda = new Ciudadano();

        System.out.println("Ingrese id");
        ciuda.setId(new Scanner(System.in).next());
        return ciuda;
    }

    public static void cargarInmueble() {
        Ciudadano ciu = null;

        System.out.println("Ingrese el codigo nacional");
        String codigoNacional = new Scanner(System.in).next();
        do {
            
            System.out.println("Ingrese id ciudadano");
            String id = new Scanner(System.in).next();
            ciu = getCiudadanoByID(id);
        } while (ciu == null);

        System.out.println("Ingrese direccion");
        String direccion = new Scanner(System.in).next();

        System.out.println("Ingrese area");
        Double area = Double.parseDouble(new Scanner(System.in).next());

        System.out.println("Ingrese valor comercial");
        BigDecimal valorComercial = new BigDecimal(new Scanner(System.in).next());

        System.out.println("Ingrese estrato");
        int estrato = Integer.parseInt(new Scanner(System.in).next());

        System.out.println("Ingrese tipo de inmueble");
        int tipoInm = 0;
        do {
        System.out.println("1. Crear casa");
        System.out.println("2. Crear lote");
        System.out.println("3. Crear apartamento");
        tipoInm = Integer.parseInt(new Scanner(System.in).next());
        } while (tipoInm!=1 && tipoInm!=2 && tipoInm!=3);
        switch (tipoInm) {
            case 1:                             ;
                Casa casa = new Casa(codigoNacional, direccion, valorComercial, estrato, TipoInmuebleEnum.CASA);
                crearInmueble(ciu, casa);
                break;
            case 2:
                Lote lote = new Lote(codigoNacional, direccion, valorComercial, estrato, TipoInmuebleEnum.LOTE);
                crearInmueble(ciu, lote);
                break;
            case 3:
                Apartamento apto = new Apartamento(codigoNacional, direccion, valorComercial, estrato, TipoInmuebleEnum.APARTAMENTO);
                crearInmueble(ciu, apto);
                break;
        }

    }

    public static void CrearCiudadano(Ciudadano ciudadano) {
        PreparedStatement pst = null;
        String sql = "Insert into Ciudadanos(id,nombres,apellidos) values (?,?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taller_java", "root", "");) {
            pst = conn.prepareStatement(sql);
            pst.setString(1, ciudadano.getId());
            pst.setString(2, ciudadano.getNombres());
            pst.setString(3, ciudadano.getApellidos());
            pst.execute();
            System.out.println("Ciudadano creado correctamente");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static void crearInmueble(Ciudadano ciudadano, Inmueble inmueble) {
        PreparedStatement pst = null;
        String sql = "Insert into Inmuebles values (?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taller_java", "root", "");) {

            pst = conn.prepareStatement(sql);
            pst.setString(1, inmueble.getCodigoNacional());
            pst.setString(2, ciudadano.getId());
            pst.setString(3, inmueble.getTipo().name());
            pst.setString(4, inmueble.getDireccion());
            pst.setDouble(5, inmueble.getArea());
            pst.setBigDecimal(6, inmueble.getValorComercial());
            pst.setInt(7, inmueble.getEstrato());
            pst.execute();
            System.out.println("Registro exitoso");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public static String ReporteInmueblesPorCiudadano(Ciudadano ciudadano) {
        PreparedStatement pst = null;
        String sql = "select id_ciudadano, codigo_nacional,"
                + "area, "
                + "estrato, "
                + "valor_comercial "
                + "from inmuebles "
                + "where id_ciudadano = ?"
                + "order by estrato";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taller_java", "root", "");) {

            pst = conn.prepareStatement(sql);
            pst.setString(1, ciudadano.getId());
            ResultSet rs = pst.executeQuery();

            System.out.println("IdCiudadano, CodigoNacional, Area , Estrato, valorComercial");
            while (rs.next()) {
                System.out.println(rs.getString("id_ciudadano")+","+
                        rs.getString("codigo_nacional")+","+
                        rs.getString("area")+","+                             
                        rs.getInt("estrato")+","+
                        rs.getString("valor_comercial"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        String reporte = "Reporte exitoso";
        return reporte;
    }

    public static Ciudadano getCiudadanoByID(String id) {
        Ciudadano ciudadano = null;
        PreparedStatement pst = null;
        String sql = "select * from ciudadanos where id = ? ";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taller_java", "root", "");) {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ciudadano = new Ciudadano();
                ciudadano.setId(rs.getString("Id"));
                ciudadano.setNombres(rs.getString("Nombres"));
                ciudadano.setApellidos(rs.getString("Apellidos"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return ciudadano;
    }
}
