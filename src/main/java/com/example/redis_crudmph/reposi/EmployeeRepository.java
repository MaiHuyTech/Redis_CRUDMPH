package com.example.redis_crudmph.reposi;


import com.example.redis_crudmph.entity.Employee;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;//crud hash
    private ListOperations listOperations;
    private SetOperations setOperations;
    private ZSetOperations zsetOperations;
     

    private RedisTemplate redisTemplate;



    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.listOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;

    }

    public void saveEmployee(Employee employee){
        listOperations.rightPush(employee.getId(), employee);

    }
//    public List<Employee> findAll(){
//
//        return  listOperations.
//    }
    public Employee findById(Integer id){

        return (Employee) listOperations.range(id, 0, -1);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){
        listOperations.remove(id,0,Employee.class);
    }

}
