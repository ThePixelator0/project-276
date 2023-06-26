package com.etb.eattrainbalance.service.impl;

import com.etb.eattrainbalance.exception.NotFoundException;
import com.etb.eattrainbalance.modal.request.NutritionCreateRequest;
import com.etb.eattrainbalance.persistence.entity.Nutrition;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.NutritionRepository;
import com.etb.eattrainbalance.persistence.repository.UserRepository;
import com.etb.eattrainbalance.service.AuthService;
import com.etb.eattrainbalance.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class NutritionServiceImpl implements NutritionService {
    private final NutritionRepository nutritionRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Transactional
    @Override
    public Long addNutrition(NutritionCreateRequest request) {
        // TO BE DONE...
    }

    @Override
    public Nutrition getNutrition(Long id) {
        // TO BE DONE...
    }

    @Override
    public Nutrition updateNutrition(Long id, NutritionCreateRequest request) {
        // TO BE DONE...
    }

    @Override
    public Long deleteNutrition(Long id) {
        // TO BE DONE...
    }

    @Override
    public List<Nutrition> getAllNutrition() {
        // TO BE DONE...
    }
}
