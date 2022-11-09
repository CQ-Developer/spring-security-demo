package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    @Query("select w from t_workout w where w.user = ?#{authentication.name}")
    List<Workout> findAllByUser();

}
