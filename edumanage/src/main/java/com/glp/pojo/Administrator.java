package com.glp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Administrator {
    private Integer id;
    private String admin_id;
    private String password;
}
