package com.example.Project3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 3, max = 30, message = "Название сенсора должно быть от 3 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
