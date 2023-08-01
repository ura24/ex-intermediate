package com.example.exintermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.service.TeamService;

@Controller
@RequestMapping("/exam01")
public class TeamController {
    
    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public String index(Model model) {
        List<Team> teamList = teamService.showList();
        model.addAttribute("teamList", teamList);
        return "exam01/list";
    }

    @GetMapping("/detail")
    public String detail(Integer id, Model model) {
        Team team = teamService.showDetail(id);
        model.addAttribute("team", team);
        return "exam01/detail";
    }
}
