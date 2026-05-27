package com.it211_ss12_04.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String studentCode;
    private String fullName;
    private String major;
    private Double gpa;
}