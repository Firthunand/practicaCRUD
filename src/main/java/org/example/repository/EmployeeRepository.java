package org.example.repository;

import org.example.model.Employee;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{

    //original
    /*private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }*/
    //nueva
    //private Connection conn;

    //nuevo2
    private Connection getConectioon() throws SQLException {
        return DatabaseConnection.getConnection();
    }

   /* public EmployeeRepository(Connection conn) {
        this.conn = conn;
    }*/

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn= getConectioon();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select * from employees")) {
            //LEER todos los DATOS DE LA BASE DE DATOS///////////////////////////
            while (rs.next()) {
               Employee e = createEmployee(rs);
               employees.add(e);
            }
            //////////////////////////////////////////////////////////////////
        }
        return employees;
    }
    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee= null;
        //LEEMOS el DATO correspondiente al id  DE LA BASE DE DATOS///////////////////////////
        try(Connection conn= getConectioon();
            PreparedStatement ps= conn.prepareStatement("select * from employees where id = ?")){
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                    employee = createEmployee(rs);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {

        String querySQL;
        if (employee.getId()!=null && employee.getId()>0){
            querySQL="update employees set first_name =?, pa_surname=?, ma_surname=?, email=?, salary=?, curp=? where id=?";
        }else{
            querySQL="insert into employees(first_name, pa_surname, ma_surname, email, salary, curp) value (?,?,?,?,?,?)";
        }

        try(Connection conn= getConectioon();
            PreparedStatement ps=conn.prepareStatement(querySQL)){

            ps.setString(1,employee.getFirst_name());
            ps.setString(2,employee.getPa_surname());
            ps.setString(3,employee.getMa_surname());
            ps.setString(4,employee.getEmail());
            ps.setDouble(5,employee.getSalary());
            ps.setString(6,employee.getCurp());
            if (employee.getId()!=null && employee.getId()>0){
                ps.setInt(7,employee.getId());
            }
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String querySQL="delete from employees where id=?";
        try(Connection conn= getConectioon();
            PreparedStatement ps = conn.prepareStatement(querySQL)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }

    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee e= new Employee();
        e.setId(rs.getInt("id"));
        e.setFirst_name(rs.getString("first_name"));
        e.setPa_surname(rs.getString("pa_surname"));
        e.setMa_surname(rs.getString("ma_surname"));
        e.setEmail(rs.getString("email"));
        e.setSalary(rs.getDouble("salary"));
        e.setCurp(rs.getString("curp"));

        return e;
    }
}
