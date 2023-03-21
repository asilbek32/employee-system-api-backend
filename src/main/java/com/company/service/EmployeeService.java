package com.company.service;

import com.company.dto.EmployeeDTO;
import com.company.entity.EmployeeEntity;
import com.company.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();

        BeanUtils.copyProperties(dto, entity);
        employeeRepository.save(entity);
        return dto;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = (List<EmployeeEntity>) employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS =
                employeeEntities.stream()
                        .map(emp -> new EmployeeDTO(emp.getId(),
                                emp.getFirstName(),
                                emp.getLastName(),
                                emp.getEmailId()))
                        .collect(Collectors.toList());
        return employeeDTOS;
    }


    public boolean deleteEmployee(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).get();
        employeeRepository.delete(entity);
        return true;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id).get();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(entity, employeeDTO);
        return employeeDTO;
    }


    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        EmployeeEntity entity = employeeRepository.findById(id).get();
         entity.setEmailId(dto.getEmailId());
         entity.setFirstName(dto.getFirstName());
         entity.setLastName(dto.getLastName());

         employeeRepository.save(entity);
        return dto;
    }
}
