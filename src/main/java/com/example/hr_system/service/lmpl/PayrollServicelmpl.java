package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.*;
import com.example.hr_system.payload.request.payroll.PayrollRecordDTO;
import com.example.hr_system.repository.*;
import com.example.hr_system.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PayrollServicelmpl implements PayrollService {

    @Autowired
    private PayrollRecordRepository payrollRecordRepository;

    @Autowired
    private PayrollSalaryRepository payrollSalaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private MonthRepository monthRepository;

    public PayrollServicelmpl() {
    }

    public PayrollServicelmpl(PayrollRecordRepository payrollRecordRepository, PayrollSalaryRepository payrollSalaryRepository, EmployeeRepository employeeRepository, YearRepository yearRepository, MonthRepository monthRepository) {
        this.payrollRecordRepository = payrollRecordRepository;
        this.payrollSalaryRepository = payrollSalaryRepository;
        this.employeeRepository = employeeRepository;
        this.yearRepository = yearRepository;
        this.monthRepository = monthRepository;
    }

    @Override
    public void createNewPayrollSalaryForNewEmployee(long EmployeeId, long PositionID) {
        final long salaryYaerID = 1;

        Date newDate = new Date();

        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(()->new RuntimeException("The user is not found."));

        PayrollSalary payrollSalary = new PayrollSalary();
        if(PositionID == 1){
            payrollSalary.setBasisSalary(20000);
            payrollSalary.setBonus(1000);
            payrollSalary.setPoint(1);
        }else if(PositionID == 2){
            payrollSalary.setBasisSalary(25000);
            payrollSalary.setBonus(1500);
            payrollSalary.setPoint(2);
        }else if(PositionID == 3){
            payrollSalary.setBasisSalary(30000);
            payrollSalary.setBonus(2000);
            payrollSalary.setPoint(3);
        }
        payrollSalary.setCreatedDate(newDate);
        payrollSalary.setUpdatedDate(newDate);
        Year year = yearRepository.findById(salaryYaerID).orElseThrow(()->new RuntimeException("The year is not found."));

        payrollSalary.setYear(year);
        payrollSalary.setEmployee(employee);

        payrollSalaryRepository.save(payrollSalary);
    }

    @Override
    public void createNewPayrollRecord(Long EmployeeID, PayrollRecordDTO payrollRecordDTO) {

        final long monthID = 12;
        final long yearID = 1;


        Date newDate = new Date();

        PayrollRecord payrollRecord = new PayrollRecord();

        Employee employee = employeeRepository.findById(EmployeeID).orElseThrow(()->new RuntimeException("The user is not found"));
        Year year = yearRepository.findById(yearID).orElseThrow(()-> new RuntimeException("the year is not found."));
        Month month = monthRepository.findById(monthID).orElseThrow(()->new RuntimeException("The month is not found."));


        payrollRecord.setBasisSalary(payrollRecordDTO.getBasisSalary());
        payrollRecord.setBonus(payrollRecordDTO.getBonus());
        payrollRecord.setNetPayment(payrollRecordDTO.getNet_payment());
        payrollRecord.setTotalAmount(payrollRecordDTO.getTotal_amount());
        payrollRecord.setEmployeeContribution(payrollRecordDTO.getEmployee_mpf());
        payrollRecord.setEmployerContribution(payrollRecordDTO.getEmployer_mpf());
        payrollRecord.setCreatedDate(newDate);
        payrollRecord.setUpdatedDate(newDate);

        payrollRecord.setYear(year);
        payrollRecord.setMonth(month);
        payrollRecord.setEmployee(employee);

        payrollRecordRepository.save(payrollRecord);
        employeeRepository.save(employee);
    }

    @Override
    public List<PayrollRecord> findAllPayrollRecordByEmployeeID(Long employeeID) {
        return payrollRecordRepository.findPayrollRecordByEmployeeID(employeeID);
    }
}
