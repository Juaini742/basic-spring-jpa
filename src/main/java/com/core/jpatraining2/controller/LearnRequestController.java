package com.core.jpatraining2.controller;


import com.core.jpatraining2.utils.JsonUtils;
import com.core.jpatraining2.validation.DataDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/public")
public class LearnRequestController {

    @PostMapping("/request-param")
    public ResponseEntity<String> requestParam(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + name);
    }

    @PostMapping("/request-path/{id}")
    public ResponseEntity<Integer> requestParameter(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping("/request-path-parameter/{id}")
    public ResponseEntity<String> requestPathParameter(@PathVariable int id, @RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.status(HttpStatus.OK).body("ID: " + id + " Hello " + name);
        }
        return ResponseEntity.status(HttpStatus.OK).body("ID: " + id);
    }

    @PostMapping("/request-param-default-value")
    public ResponseEntity<String> requestParamDefaultValue(@RequestParam(defaultValue = "John") String name) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + name);
    }

    /**
     * Request body with DTO
     * first you have to create DTO, in this sample is dataDTO
     * attribute of dataDTO is name(string), value(int), isAdmin(boolean)
     */
    @PostMapping("/request-body-dto")
    public ResponseEntity<String> requestBodyDto(@RequestBody DataDTO dataDTO) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + dataDTO.getName() + " Value is: " + dataDTO.getValue() + " status: " + dataDTO.getIsAdmin());
    }

    /**
     * if you want to use HttpServletRequest to get all request body from json,
     * first you have to make model or entity, but for this sample I will use dataDto
     * secondly you have to make JsonUtils to handle json requests
     * @param req
     * @return
     */
    @PostMapping("/request-body-httpServletRequest")
    public void requestBodyHttpServletRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataDTO dataDTO = JsonUtils.getObjectMapper().readValue(req.getReader(), DataDTO.class);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().write(JsonUtils.getObjectMapper().writeValueAsString(dataDTO));
    }

}
