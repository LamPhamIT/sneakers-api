package com.example.sneaker.mapper.branch;

import com.example.sneaker.model.entity.Branch;
import com.example.sneaker.model.request.branch.BranchRequest;
import com.example.sneaker.model.response.branch.BranchResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T02:09:33+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchResponse toDto(Branch entity) {
        if ( entity == null ) {
            return null;
        }

        BranchResponse branchResponse = new BranchResponse();

        branchResponse.setName( entity.getName() );
        branchResponse.setSlug( entity.getSlug() );

        return branchResponse;
    }

    @Override
    public Branch toEntity(BranchRequest branchRequest) {
        if ( branchRequest == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setName( branchRequest.getName() );
        branch.setSlug( branchRequest.getSlug() );

        return branch;
    }
}
