package com.example.exintermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.repository.TeamRepository;

@Service
@Transactional
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> showList() {
        return teamRepository.findAll();
    }

    public Team showDetail(Integer id) {
        return teamRepository.findById(id);
    }
}
