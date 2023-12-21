package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.LeaveType;
import com.example.hr_system.repository.LeaveBalanceRepository;
import com.example.hr_system.repository.LeaveRecordRepository;
import com.example.hr_system.repository.LeaveTypeRepository;
import com.example.hr_system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServicelmpl implements LeaveService {

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    @Autowired
    private LeaveRecordRepository leaveRecordRepository;


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
}
