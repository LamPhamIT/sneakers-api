package com.example.sneaker.mapper.branch;

import com.example.sneaker.model.entity.Branch;
import com.example.sneaker.model.request.branch.BranchRequest;
import com.example.sneaker.model.response.branch.BranchResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchResponse toDto(Branch entity);

    Branch toEntity(BranchRequest branchRequest);

}
