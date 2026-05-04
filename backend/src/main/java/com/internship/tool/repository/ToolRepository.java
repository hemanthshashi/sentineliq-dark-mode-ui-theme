package com.internship.tool.repository;

import com.internship.tool.entity.Tool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Long> {


    Page<Tool> findByNameContainingIgnoreCase(String name, Pageable pageable);

   
    Page<Tool> findByCategoryIgnoreCase(String category, Pageable pageable);

  
    Page<Tool> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
            String name,
            String category,
            Pageable pageable
    );
}