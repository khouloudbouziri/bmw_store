package com.example.advanced_java_project.Security;

import com.example.advanced_java_project.Enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role = Role.USER.name(); // Default role is USER
}
