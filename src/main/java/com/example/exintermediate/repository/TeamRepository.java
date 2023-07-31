package com.example.exintermediate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.exintermediate.domain.Team;

@Repository
public class TeamRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setLeagueName(rs.getString("league_name"));
        team.setTeamName(rs.getString("team_name"));
        team.setheadquarters(rs.getString("headquarters"));
        team.setHistory(rs.getString("history"));
        return team;
    };

    /**
     * 全ての野球チームを取得する
     * @return 全ての野球チーム
     */
    public List<Team> findAll() {
        String sql = "SELECT id, league_name, team_name, headquarters, history FROM teams";
        List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);
        return teamList;
    }
}
