package com.example.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.model.Yearbook;


@Repository
public interface YearbookRepository extends JpaRepository<Yearbook, Long> {

}
