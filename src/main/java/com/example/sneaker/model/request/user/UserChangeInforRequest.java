package com.example.sneaker.model.request.user;

import com.example.sneaker.model.request.authority.AuthorityRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChangeInforRequest  implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotNull
    private UUID id;

    private String fullName;

    private Boolean isDeleted;

    private List<AuthorityRequest> authorities;
}
