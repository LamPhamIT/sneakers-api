package com.example.sneaker.model.response.user;

import com.example.sneaker.framework.base.BaseDTO;
import com.example.sneaker.model.entity.Authority;
import com.example.sneaker.model.response.authority.AuthorityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Email(regexp = ".+[@].+[\\\\.].+")
    @Size(min = 5, max = 254)
    private String email;

    @NotBlank()
    @Size(max = 50)
    private String fullName;

    @Size(max = 256)
    private String avatar;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastModifiedBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant lastModifiedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AuthorityResponse> authorities;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isDeleted;
}
