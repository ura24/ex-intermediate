package com.example.exintermediate.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.exintermediate.domain.Clothes;
import com.example.exintermediate.service.ClothesService;

@Controller
@RequestMapping("/exam03")
public class ClothesController {

    @Autowired
    private ClothesService clothesService;
    
    @GetMapping("")
    public String index(Model model) {
        List<String> genderList = Arrays.asList("男性", "女性");
        model.addAttribute("genderList", genderList);

        List<String> colorList = Arrays.asList("赤", "青", "白", "黄");
        model.addAttribute("colorList", colorList);
        
        return "exam03/clothes-search";
    }

    @PostMapping("/search")
    public String search(Integer gender, String color, Model model, RedirectAttributes redirectAttrs) {
        List<Clothes> clothesList = clothesService.showList(gender, color);
        redirectAttrs.addFlashAttribute("clothesList", clothesList);
        redirectAttrs.addFlashAttribute("selectedGender", gender);
        redirectAttrs.addFlashAttribute("selectedColor", color);

        return "redirect:/exam03";
    }

}
