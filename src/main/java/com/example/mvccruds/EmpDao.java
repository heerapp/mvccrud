package com.example.mvccruds;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpDao {
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public int save(Emp p){
        String sql = "insert into worker(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
        return template.update(sql);
    }
    public int update(Emp p){
        String sql = "update worker set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";
        return template.update(sql);
    }
    public int delete(int id){
        String sql="delete from worker where id="+id+"";
        return template.update(sql);
    }
    public Emp getEmpById(int id){
        String sql="select * from worker where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<>(Emp.class));
    }
    public List<Emp> getEmployees(){
        return template.query("select * from worker", new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e=new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getFloat(3));
                e.setDesignation(rs.getString(4));
                return e;
            }
        });
    }
}

