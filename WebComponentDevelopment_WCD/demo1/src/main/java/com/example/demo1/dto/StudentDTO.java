package com.example.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private Integer class_id;
    private String class_name;
}
