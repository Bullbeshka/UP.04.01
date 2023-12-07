package com.example.itog.repositories;

import com.example.itog.model.Ord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdRepository extends JpaRepository<Ord, Long> {
    @EntityGraph(attributePaths = {"cart", "address"})
    List<Ord> findAll();
}

