package com.example.hotelbooking.statistics.repository;

import com.example.hotelbooking.statistics.model.UserStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatisticsUser extends MongoRepository<UserStatistics, String > {

}
