package com.core.jpatraining2.repository;

import com.core.jpatraining2.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    // create custom query for pagination
    Page<Tasks> findAll(Pageable pageable);

    // create custom query for sorting
    List<Tasks> findAll(Sort sort);

}
