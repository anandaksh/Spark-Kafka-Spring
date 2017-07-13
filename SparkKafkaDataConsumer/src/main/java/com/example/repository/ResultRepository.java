package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.model.ResultData;

@Repository
public class ResultRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ResultData> findAll() {
		List<ResultData> result = jdbcTemplate.query("SELECT * FROM result",
				(rs, rowNum) -> new ResultData(rs.getString("location"), rs.getString("branch"), rs.getInt("numberofbranch")));
		return result;
	}
}
