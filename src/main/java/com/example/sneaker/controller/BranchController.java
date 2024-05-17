package com.example.sneaker.controller;

import com.example.sneaker.framework.handle.model.ResponseData;
import com.example.sneaker.framework.support.ResponseSupport;
import com.example.sneaker.service.branch.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/branches")
public class BranchController {

    private final ResponseSupport responseSupport;

    private final BranchService branchService;

    public BranchController(ResponseSupport responseSupport, BranchService branchService) {
        this.responseSupport = responseSupport;
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllBranches() {
        return responseSupport.success(ResponseData.builder().data(branchService.getAllBranches()).build());
    }

}
