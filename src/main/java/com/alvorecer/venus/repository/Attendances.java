package com.alvorecer.venus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvorecer.venus.model.Attendance;

@Repository
public interface Attendances extends JpaRepository<Attendance, Long> {

}
