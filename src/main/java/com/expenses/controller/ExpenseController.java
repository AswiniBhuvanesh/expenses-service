package com.expenses.controller;

import com.expenses.exception.ResourceNotFoundException;
import com.expenses.model.Expense;
import com.expenses.service.ClientService;
import com.expenses.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpenseController {

  @Autowired private ExpenseService expenseService;

  @Autowired private ClientService clientService;

  @ApiOperation(value = "Get all expense by a Client")
  @GetMapping("/expenses/{clientId}")
  public ResponseEntity<List<Expense>> getAllExpensesByClientId(
      @PathVariable(value = "clientId") Long clientId) throws ResourceNotFoundException {
    if (!clientService.existsById(clientId)) {
      throw new ResourceNotFoundException("Client Not available = " + clientId);
    }

    List<Expense> expenseList = expenseService.findByClientId(clientId);
    return ResponseEntity.ok().body(expenseList);
  }

  @ApiOperation(value = "Create Expense by a Client Id")
  @PostMapping("/expenses/{clientId}")
  public ResponseEntity<Expense> createExpense(
      @PathVariable(value = "clientId") Long clientId, @Valid @RequestBody Expense expenseRequest)
      throws ResourceNotFoundException {
    Expense expense =
        clientService
            .findById(clientId)
            .map(
                client -> {
                  expenseRequest.setClient(client);
                  return expenseService.save(expenseRequest);
                })
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Client Details not found for this id :: " + clientId));
    return ResponseEntity.ok().body(expense);
  }
}
