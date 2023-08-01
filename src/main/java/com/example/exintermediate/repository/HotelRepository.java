package com.example.exintermediate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Hotel;

@Repository
public class HotelRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setAreaName(rs.getString("area_name"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setNearestStation(rs.getString("nearest_station"));
        hotel.setPrice(rs.getInt("price"));
        hotel.setParking(rs.getString("parking"));
        return hotel;
    };

    /**
     * 全てのホテルを検索する
     * @return 全てのホテルのリスト
     */
    public List<Hotel> findAll() {
        String sql = "SELECT id, area_name, hotel_name, address, nearest_station, price, parking FROM hotels";
        List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
        return hotelList;
    }

    /**
     * 条件の値段以下のホテルを検索する
     * @param price ホテルの値段
     * @return 条件に合致するホテルのリスト
     */
    public List<Hotel> findByPrice(Integer price) {
        String sql = "SELECT id, area_name, hotel_name, address, nearest_station, price, parking FROM hotels WHERE price <= :price";
        SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
        List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);
        return hotelList;
    }
}
