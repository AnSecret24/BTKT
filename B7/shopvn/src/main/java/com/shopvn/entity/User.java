package com.shopvn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 50)
    private String fullName;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Email(message = "Email không hợp lệ")
    @Column(unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "0\\d{9}", message = "SĐT phải 10 số và bắt đầu bằng 0")
    private String phone;

    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    private LocalDate birthDate;

    private String gender;

    @Pattern(regexp = "^[A-Z0-9]{8}$",
            message = "Mã giới thiệu gồm 8 ký tự chữ hoa và số")
    private String referralCode;

    @AssertTrue(message = "Bạn phải đồng ý điều khoản")
    private Boolean agreeTerms = false;
}