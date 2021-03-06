package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ResultData;

@Repository
public interface ResultJdbcRepository extends CrudRepository<ResultData, Integer> {
	public ResultData findByLocationAndBranch(String loc, String br);
}

