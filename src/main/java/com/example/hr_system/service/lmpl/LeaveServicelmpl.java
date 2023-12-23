package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Employee;
import com.example.hr_system.entity.LeaveBalance;
import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;
import com.example.hr_system.repository.EmployeeRepository;
import com.example.hr_system.repository.LeaveBalanceRepository;
import com.example.hr_system.repository.LeaveRecordRepository;
import com.example.hr_system.repository.LeaveTypeRepository;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LeaveServicelmpl implements LeaveService {

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    @Autowired
    private LeaveRecordRepository leaveRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public LeaveServicelmpl() {
    }

    public LeaveServicelmpl(LeaveBalanceRepository leaveBalanceRepository, LeaveTypeRepository leaveTypeRepository, LeaveRecordRepository leaveRecordRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveRecordRepository = leaveRecordRepository;
    }

    @Override
    public List<LeaveType> getAllLeaveType() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public void createNewLeaveBalance(Long EmployeeId, EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO) {
        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(()->new RuntimeException("The user is not existed in database"));
        LeaveBalance newLeaveBalance = new LeaveBalance();

        newLeaveBalance.setBalanceAnnualLeave(employeeLeaveBalanceDTO.getBalanceAL());
        newLeaveBalance.setBalanceSickLeave(employeeLeaveBalanceDTO.getBalanceSL());
        newLeaveBalance.setBalanceSpecialLeave(employeeLeaveBalanceDTO.getBalanceSPL());
        newLeaveBalance.setLeaveBalanceYear(employeeLeaveBalanceDTO.getLeaveYear());
        Date newDate = new Date();
        newLeaveBalance.setCreatedDate(newDate);
        newLeaveBalance.setEmployee(employee);
        Set<LeaveBalance> newRecord = new HashSet<>();
        newRecord.add(newLeaveBalance);

        leaveBalanceRepository.save(newLeaveBalance);
        employeeRepository.save(employee);
    }
}
