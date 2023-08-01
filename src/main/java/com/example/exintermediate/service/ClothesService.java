package com.example.exintermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exintermediate.domain.Clothes;
import com.example.exintermediate.repository.ClothesRepository;

@Service
@Transactional
public class ClothesService {
    
    @Autowired
    private ClothesRepository clothesRepository;

    public List<Clothes> showList(Integer gender, String color) {
        return clothesRepository.findByGenderAndColor(gender, color);
    }
}
