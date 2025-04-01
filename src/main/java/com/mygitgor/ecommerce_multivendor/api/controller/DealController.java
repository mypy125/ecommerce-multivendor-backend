package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.api.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Deal;
import com.mygitgor.ecommerce_multivendor.application.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal>createDeals(@RequestBody Deal deals)
    {
        Deal createDeals = dealService.createDeal(deals);
        return new ResponseEntity<>(deals, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal>updateDeal(@PathVariable Long id,
                                          @RequestBody Deal deal) throws Exception
    {
        Deal updateDeal = dealService.updateDeal(deal,id);
        return ResponseEntity.ok(deal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteDeals(@PathVariable Long id) throws Exception
    {
        dealService.deleteDeal(id);
        ApiResponse response =new ApiResponse();
        response.setMessage("deal deleted");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
