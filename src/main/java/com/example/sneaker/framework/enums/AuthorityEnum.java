package com.example.sneaker.framework.enums;

import com.example.sneaker.framework.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityEnum implements CodeEnum {

    USER("USER", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN");

    private final String name;
    private final String value;


}
