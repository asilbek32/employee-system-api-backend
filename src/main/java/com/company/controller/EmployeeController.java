package com.company.controller;

import com.company.dto.EmployeeDTO;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO dto){
       return employeeService.createEmployee(dto);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
    return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeId(@PathVariable Long id){
        boolean deleted = false;
         deleted = employeeService.deleteEmployee(id);
         Map<String, Boolean> response = new HashMap<>();
         response.put("delete", deleted) ;
         return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employeeById = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeById);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                      @RequestBody EmployeeDTO dto){
         dto = employeeService.updateEmployee(id, dto);
         return ResponseEntity.ok(dto);
    }
}
