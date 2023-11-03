package org.example.main;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;
//import org.example.view.SwingApp;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
/*
        PreparedStatement preparedStatement=null;
        //BLOQUE TRY WITH RESOURCES //////////////////////////////////////
        try(Connection conn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("-----Listando empleados------");
            repository.findAll().forEach(System.out::println); //probamos la extraccion/lectura de todos los datos de la bbdd

            //System.out.println(repository.getById(4));//probamos la extraccion/lectura segun un id especifico

            //Pribando la insercion y actualizacion
            *//*System.out.println("------Insertando un empleado------");
            Employee employee = new Employee();
            employee.setId(9);
            employee.setFirst_name("Valesca");
            employee.setPa_surname("Iturrieta");
            employee.setMa_surname("Gaete");
            employee.setEmail("valescaIturrieta@mail.com");
            employee.setSalary((double)635000);
            repository.save(employee);
            System.out.println("-----Se Agrego empleado nuevo------");
            repository.findAll().forEach(System.out::println);*//*

            //pobando la eliminacion
            System.out.println("Empleado eliminado");
            repository.delete(4);
            System.out.println("-----Listando empleados despues de la eliminacion------");
            repository.findAll().forEach(System.out::println);


        }*/
       /* SwingApp app = new SwingApp();
        app.setVisible(true);*/

        //generando transacciones
        /*try(Connection conn = DatabaseConnection.getInstance()){
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                Repository<Employee> repository = new EmployeeRepository(conn);
                System.out.println("-----Insertar un nuevo employee------");
                Employee employee = new Employee();
              *//*  employee.setFirst_name("America");
                employee.setPa_surname("Chavez");
                employee.setMa_surname("Villa");
                employee.setEmail("americachavez@mail.com");
                employee.setSalary(660000D);
                employee.setCurp("AMERI123457ERTYDFH");
                repository.save(employee);
                conn.commit();*//*

                employee.setFirst_name("Valesca");
                employee.setPa_surname("Iturrieta");
                employee.setMa_surname("Gaete");
                employee.setEmail("valelepew@mail.com");
                employee.setSalary(666000D);
                employee.setCurp("AMERI123457ERTYDFH");
                repository.save(employee);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        }*/

        System.out.println("---Listando todos----");
        Repository<Employee> repository=new EmployeeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("---Buscando por id------");
        System.out.println(repository.getById(2));


    }
}
