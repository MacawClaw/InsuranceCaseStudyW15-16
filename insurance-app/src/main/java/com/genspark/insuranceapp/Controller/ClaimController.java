package com.genspark.insuranceapp.Controller;

import com.genspark.insuranceapp.Entity.Claim;
import com.genspark.insuranceapp.Service.ClaimService;
import com.genspark.insuranceapp.Service.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/claims")
    public Claim saveClaim(@RequestBody Claim claim) {
        return claimService.saveClaim(claim);
    }

    @PutMapping("/claims/{claimId}")
    public Claim updateClaim(@RequestBody Claim claim, @PathVariable int claimId) {
        System.out.println(claim.getClaimId()== claimId);
        return claimService.updateClaim(claim);
    }

    @GetMapping("/claims")
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/claims/users/{username}")
    public List<Claim> getClaimsByUsername(@PathVariable String username) {
        return claimService.getClaimsByUsername(username);
    }

    @GetMapping("/claims/vehicles/{vin}")
    public List<Claim> getClaimByVin(@PathVariable String vin) {
        return claimService.getClaimsByVehicleVin(vin);
    }

    @GetMapping("/claims/status/{statusId}")
    public List<Claim> getClaimByStatusId(@PathVariable long statusId) {
        return claimService.getClaimsByClaimStatus(statusId);
    }

    @GetMapping("/claims/{claimId}")
    public Claim getClaimByVin(@PathVariable int claimId) {
        return claimService.getClaimByClaimId(claimId);
    }

    @DeleteMapping("/claims/{claimId}")
    public void deleteClaimByVin(@PathVariable int claimId) {
        claimService.deleteClaim(claimId);
    }

/*
    @PostMapping("claims/upload/{claimId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String claimId) throws IOException {
        //int clientId = 1;

        Claim claim = claimService.getClaimByClaimId(Integer.parseInt(claimId));

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        claim.setAgreement(fileName);
        claimService.saveClaim(claim);
        String uploadDir = "client-agreement/" + claimId;

        FileUploadUtil.cleanDir(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        return ResponseEntity.ok("File uploaded successfully");
    }

 */
}
