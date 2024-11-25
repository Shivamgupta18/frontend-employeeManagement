package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	
	private final EmployeeRepository emRep;
	
	public Employee postEmployee(Employee employee) {
	return 	emRep.save(employee);
	}
	
	public List<Employee> getAll(){
		return emRep.findAll();
	}
	
	
	public Employee getEmployeeById(long id){
		return emRep.findById(id).orElse(null);
	}
	
	public void deleteEmp(long id) {
		if(!emRep.existsById(id)) {
			throw new EntityNotFoundException("EMployee with id"+ "not found");
		}
		 emRep.deleteById(id);
	}
	
	public Employee updateEMployee(Long id ,Employee employee) {
	Optional<Employee> optionalEmp=emRep.findById(id);
	if(optionalEmp.isPresent()) {
	Employee emp=optionalEmp.get();
	  emp.setEmail(employee.getEmail());
	  emp.setName(employee.getName());
	  emp.setDepartment(employee.getDepartment());
	  emp.setPhone(employee.getPhone());
	  return emRep.save(emp);
	}
	return null;
	}
	
}
