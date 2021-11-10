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
        this.setOperations = redisTemplate.opsForSet();
        this.redisTemplate = redisTemplate;

    }

    public void saveEmployee(Employee employee){
        setOperations.add(employee.getId(), employee);

    }
//    public List<Employee> findAll(){
//
//        return hashOperations.values("EMPLOYEE");
//    }
    public Employee findById(Integer id){

        return (Employee) setOperations.members(id);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){
        setOperations.pop(id);
    }

}
