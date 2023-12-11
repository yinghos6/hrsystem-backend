package com.example.hr_system.service;

import com.example.hr_system.entity.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    List<Position> getAllPosition();

    Position findPositionById(Long id);
}
