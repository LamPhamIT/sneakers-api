package com.example.sneaker.service.branch;

import com.example.sneaker.mapper.branch.BranchMapper;
import com.example.sneaker.model.response.branch.BranchResponse;
import com.example.sneaker.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService implements IBranchService{

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public List<BranchResponse> getAllBranches() {
        List<BranchResponse> result = branchRepository.findAll().stream()
                .map(branchMapper::toDto)
                .collect(Collectors.toList());
        return result;
    }
}
