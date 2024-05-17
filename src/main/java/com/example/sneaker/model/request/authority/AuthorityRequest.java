package com.example.sneaker.model.request.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.annotations.NotNull;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorityRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotNull
    private String name;
}
