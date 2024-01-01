package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.*;
import com.example.hr_system.payload.request.leave.EmployeeLeaveBalanceDTO;
import com.example.hr_system.payload.request.leave.EmployeeLeaveRecordDTO;
import com.example.hr_system.repository.*;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private LeaveStatusRepository leaveStatusRepository;

    public LeaveServicelmpl() {
    }

    public LeaveServicelmpl(LeaveBalanceRepository leaveBalanceRepository, LeaveTypeRepository leaveTypeRepository, LeaveRecordRepository leaveRecordRepository, EmployeeRepository employeeRepository, LeaveStatusRepository leaveStatusRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveRecordRepository = leaveRecordRepository;
        this.employeeRepository = employeeRepository;
        this.leaveStatusRepository = leaveStatusRepository;
    }

    @Override
    public List<LeaveType> getAllLeaveType() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public List<LeaveStatus> getAllLeaveStatus() {
        return leaveStatusRepository.findAll();
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

    @Override
    public LeaveRecord createNewLeaveRecord(Long EmployeeId, EmployeeLeaveRecordDTO employeeLeaveRecordDTO) {

        LeaveRecord newLeaveRecord = new LeaveRecord();
        newLeaveRecord.setLeaveYear(employeeLeaveRecordDTO.getLeaveYear());
        newLeaveRecord.setCountedDays(employeeLeaveRecordDTO.getLeave_counted_days());
        newLeaveRecord.setLeaveStartDate(employeeLeaveRecordDTO.getLeaveStartDate());
        newLeaveRecord.setLeaveEndDate(employeeLeaveRecordDTO.getLeaveEndDate());

        LeaveType selectedLeaveType = leaveTypeRepository.findById(employeeLeaveRecordDTO.getLeaveTypeID()).orElseThrow(()->new RuntimeException("The leave type is not found."));
        newLeaveRecord.setLeaveType(selectedLeaveType);

        LeaveStatus selectedleaveStatus = leaveStatusRepository.findById(employeeLeaveRecordDTO.getLeaveStatusID()).orElseThrow(()->new RuntimeException("The leave status is not found"));
        newLeaveRecord.setLeaveStatus(selectedleaveStatus);

        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(()->new RuntimeException("The user is not found."));
        newLeaveRecord.setEmployee(employee);

        leaveRecordRepository.save(newLeaveRecord);
        employeeRepository.save(employee);

        return newLeaveRecord;

    }

    @Override
    public Page<LeaveRecord> getAllLeaveRecordByEmployeeID(Long employeeID, Pageable pageable) {

       return leaveRecordRepository.findAllRecordByEmployeeID(employeeID, pageable);
    }


}
