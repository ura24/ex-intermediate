package com.example.exintermediate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.exintermediate.domain.Team;
import com.example.exintermediate.repository.TeamRepository;
import com.example.exintermediate.service.TeamService;

@SpringBootTest
public class TeamServiceTest {
    
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    public void testShowList() {
        // モックの設定
        List<Team> mockTeamList = new ArrayList<>();
        Team mockTeamA = new Team();
        mockTeamA.setId(1);
        mockTeamA.setTeamName("Team A");
        Team mockTeamB = new Team();
        mockTeamB.setId(2);
        mockTeamB.setTeamName("Team B");
        mockTeamList.add(mockTeamA);
        mockTeamList.add(mockTeamB);
        when(teamRepository.findAll()).thenReturn(mockTeamList);

        // テスト対象のメソッド呼び出し
        List<Team> result = teamService.showList();

        // メソッドの結果確認
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getTeamName());
        assertEquals("Team B", result.get(1).getTeamName());

        // モックが適切に呼び出されたことを確認
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    public void testShowDetail() {
        // モックの設定
        Team mockTeam = new Team();
        mockTeam.setId(1);
        mockTeam.setTeamName("Team A");
        when(teamRepository.findById(1)).thenReturn(mockTeam);

        // テスト対象のメソッド呼び出し
        Team result = teamService.showDetail(1);

        // メソッドの結果確認
        assertEquals(1, result.getId());
        assertEquals("Team A", result.getTeamName());

        // モックが適切に呼び出されたことを確認
        verify(teamRepository, times(1)).findById(1);
    }
}
