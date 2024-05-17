package com.example.sneaker.service.branch;

import com.example.sneaker.model.response.branch.BranchResponse;

import java.util.List;

public interface IBranchService {
    List<BranchResponse> getAllBranches();
}
