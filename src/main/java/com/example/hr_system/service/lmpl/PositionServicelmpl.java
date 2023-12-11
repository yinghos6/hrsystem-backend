package com.example.hr_system.service.lmpl;

import com.example.hr_system.entity.Position;
import com.example.hr_system.repository.PositionRepository;
import com.example.hr_system.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServicelmpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPosition() {
        return positionRepository.findAll();
    }

    @Override
    public Position findPositionById(Long id) {
        Position position = positionRepository.findById(id).orElseThrow(()->new RuntimeException("the position is not found"));
        return position;
    }

}
