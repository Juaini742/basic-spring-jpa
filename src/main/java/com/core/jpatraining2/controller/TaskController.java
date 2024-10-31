package com.core.jpatraining2.controller;

import com.core.jpatraining2.entity.Tasks;
import com.core.jpatraining2.service.TaskService;
import com.core.jpatraining2.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Tasks product = JsonUtils.getObjectMapper().readValue(req.getReader(), Tasks.class);
            taskService.createProduct(product);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println(JsonUtils.getObjectMapper().writeValueAsString(product));
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping
    public void getAllProduct(HttpServletResponse resp) throws IOException {
        try {
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println(JsonUtils.getObjectMapper().writeValueAsString(taskService.getAllProduct()));
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Something went wrong: " + e.getMessage());
        }
    }
}
