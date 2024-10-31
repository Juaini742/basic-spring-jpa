package com.core.jpatraining2.service;

import com.core.jpatraining2.entity.Tasks;
import com.core.jpatraining2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void createProduct(Tasks products) {
        taskRepository.save(products);
    }

    public List<Tasks> getAllProduct() {
        return taskRepository.findAll();
    }
}
