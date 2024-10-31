package com.core.jpatraining2.validation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TaskDto {

    @NotEmpty(message = "Name is required")
    @Size(min = 1, message = "Name must have at least 1 character")
    private String name;

    @NotEmpty(message = "Category is required")
    private String category;

    @NotNull(message = "StartAt is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startAt;


    @NotEmpty(message = "FinishAt is required")
    @Future(message = "Finish time must be in the future")
    private LocalDateTime finishAt;

    @NotNull(message = "User Id is required")
    private int user_id;

    public @NotEmpty(message = "Name is required") @Size(min = 1, message = "Name must have at least 1 character") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name is required") @Size(min = 1, message = "Name must have at least 1 character") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Category is required") String getCategory() {
        return category;
    }

    public void setCategory(@NotEmpty(message = "Category is required") String category) {
        this.category = category;
    }

    public @NotNull(message = "StartAt is required") @Future(message = "Start time must be in the future") LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(@NotNull(message = "StartAt is required") @Future(message = "Start time must be in the future") LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public @NotEmpty(message = "FinishAt is required") @Future(message = "Finish time must be in the future") LocalDateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(@NotEmpty(message = "FinishAt is required") @Future(message = "Finish time must be in the future") LocalDateTime finishAt) {
        this.finishAt = finishAt;
    }

    @NotNull(message = "User Id is required")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull(message = "User Id is required") int user_id) {
        this.user_id = user_id;
    }
}
