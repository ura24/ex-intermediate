package com.example.exintermediate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.exintermediate.domain.Hotel;
import com.example.exintermediate.repository.HotelRepository;
import com.example.exintermediate.service.HotelService;

@SpringBootTest
public class HotelServiceTest {
    
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void testSearchByLessThanPrice() {
        // モックの設定
        List<Hotel> mockHotelList = new ArrayList<>();

        Hotel mockHotelA = new Hotel();
        mockHotelA.setId(1);
        mockHotelA.setHotelName("Hotel A");
        mockHotelA.setPrice(1000);

        Hotel mockHotelB = new Hotel();
        mockHotelB.setId(2);
        mockHotelB.setHotelName("Hotel B");
        mockHotelB.setPrice(10000);

        mockHotelList.add(mockHotelA);
        mockHotelList.add(mockHotelB);

        // モックの動作を設定
        when(hotelRepository.findAll()).thenReturn(mockHotelList);
        when(hotelRepository.findByPrice(5000)).thenReturn(List.of(mockHotelA));
        
        // テスト対象のメソッド呼び出し
        List<Hotel> noConditionalResult = hotelService.searchByLessThanPrice(null);
        List<Hotel> conditionalResult = hotelService.searchByLessThanPrice(5000);

        // メソッドの結果確認
        assertEquals(2, noConditionalResult.size());
        assertEquals("Hotel A", noConditionalResult.get(0).getHotelName());
        assertEquals("Hotel B", noConditionalResult.get(1).getHotelName());

        assertEquals(1, conditionalResult.size());
        assertEquals("Hotel A", conditionalResult.get(0).getHotelName());

        // モックが適切に呼び出されたことを確認
        verify(hotelRepository, times(1)).findAll();
        verify(hotelRepository, times(1)).findByPrice(5000);
    }
}
