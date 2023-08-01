package com.example.exintermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.service.HotelService;

@Controller
@RequestMapping("/exam02")
public class HotelControrller {

    @Autowired
    private HotelService hotelService;
    
    @GetMapping("")
    public String index(Model model) {
        return "exam02/hotel-search";
    }

    @PostMapping("/search")
    public String search(Integer price, Model model) {
        List<Hotel> hotelList = hotelService.searchByLessThanPrice(price);
        model.addAttribute("hotelList", hotelList);
        return "exam02/hotel-search";
    }

}
