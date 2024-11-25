package com.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeeController {

	
	private final EmployeeService emService;
	
	@PostMapping("/employee")
	public Employee postEmployee(@RequestBody Employee employee) {
		
		return emService.postEmployee(employee);
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEM(){
		return emService.getAll();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getIdByEMployee(@PathVariable Long id){
		Employee emp=emService.getEmployeeById(id);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		 }
			return  ResponseEntity.ok(emp);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
		try {
			emService.deleteEmp(id);
			return new ResponseEntity<>("Employee with ID"+ id+ "deleted successfully",HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
	Employee emp=	emService.updateEMployee(id, employee);
	if(emp==null) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	return ResponseEntity.ok(emp);
	}
}
