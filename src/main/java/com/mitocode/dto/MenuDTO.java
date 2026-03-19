package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Integer idMenu;
    private String icon;
    private String user;
    private String url;

}
