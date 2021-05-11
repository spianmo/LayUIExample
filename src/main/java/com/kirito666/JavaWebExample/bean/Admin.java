package com.kirito666.JavaWebExample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Finger
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private String username;
    private String password;
}