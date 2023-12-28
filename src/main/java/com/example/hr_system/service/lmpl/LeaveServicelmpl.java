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

        final long initialLeaveTotal = 0;

        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(()->new RuntimeException("The user is not existed in database"));
        LeaveBalance newLeaveBalance = new LeaveBalance();

        newLeaveBalance.setBalanceAnnualLeave(employeeLeaveBalanceDTO.getBalanceAL());
        newLeaveBalance.setBalanceSickLeave(employeeLeaveBalanceDTO.getBalanceSL());
        newLeaveBalance.setBalanceSpecialLeave(employeeLeaveBalanceDTO.getBalanceSPL());
        newLeaveBalance.setLeaveBalanceYear(employeeLeaveBalanceDTO.getLeaveYear());

        newLeaveBalance.setAppliedSickLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSickLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSpecialLeave(initialLeaveTotal);

        Date newDate = new Date();
        newLeaveBalance.setCreatedDate(newDate);
        newLeaveBalance.setEmployee(employee);
        Set<LeaveBalance> newRecord = new HashSet<>();
        newRecord.add(newLeaveBalance);

        leaveBalanceRepository.save(newLeaveBalance);
        employeeRepository.save(employee);
    }

    @Override
    public void createNewLeaveBalanceForNewEmployee(long EmployeeId) {
        final String initialLeaveYear = "2023";
        final long initialAL = 12;
        final long initialSL = 14;
        final long initalSPL = 16;
        final long initialLeaveTotal = 0;

        LeaveBalance newLeaveBalance = new LeaveBalance();

        newLeaveBalance.setBalanceAnnualLeave(initialAL);
        newLeaveBalance.setBalanceSickLeave(initialSL);
        newLeaveBalance.setBalanceSpecialLeave(initalSPL);
        newLeaveBalance.setLeaveBalanceYear(initialLeaveYear);
        newLeaveBalance.setAppliedSickLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSickLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSpecialLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedAnnualLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSickLeave(initialLeaveTotal);
        newLeaveBalance.setAppliedSpecialLeave(initialLeaveTotal);

        Date newDate = new Date();
        newLeaveBalance.setCreatedDate(newDate);

        Employee employee= employeeRepository.findById(EmployeeId).orElseThrow(()->new RuntimeException("the user is not existed."));
        newLeaveBalance.setEmployee(employee);
        Set<LeaveBalance> newRecord = new HashSet<>();
        newRecord.add(newLeaveBalance);

        leaveBalanceRepository.save(newLeaveBalance);
        employeeRepository.save(employee);

    }
}
