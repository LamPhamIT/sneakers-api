package com.example.sneaker.model.response.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorityResponse implements Serializable {
    private static final Long serialVersionUID = 1L;
    private UUID id;
    private String name;
}
