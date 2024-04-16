package co.edu.escuelaing.cvds.lab7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getEmployees(Model model){
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("list", employees);
        return "employees";
    }

    @GetMapping("/new")
    public String getNewEmployee(){
        return "create";
    }

    @PostMapping("/new/confirm")
    public String createEmployee(@RequestParam Long employeeId, String firstName,String lastName, String role, String salary){
        employeeService.createEmployee(employeeId, firstName, lastName, role, salary);
        return "redirect:/employees";
    }

    @PostMapping("/update")
    public String updateEmployee(Model model, Long employeeId){
        model.addAttribute("employeeId",employeeId);
        return "update";
    }

    @PostMapping("/update/confirm")
    public String updateEmployee(@RequestParam Long employeeId, String firstName, String lastName){
        employeeService.updateEmployee(employeeId, firstName, lastName);
        return "redirect:/employees";
    }

    @PostMapping("/delete/confirm")
    public String deleteEmployee(@RequestParam Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }

    
}