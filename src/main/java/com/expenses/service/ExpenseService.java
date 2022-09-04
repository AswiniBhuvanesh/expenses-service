package com.expenses.service;

import com.expenses.model.Expense;
import com.expenses.repository.ExpenseRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  @Autowired private ExpenseRepo expenseRepo;

  public Expense save(Expense expense) {
    return expenseRepo.save(expense);
  }

  public List<Expense> findByClientId(Long clientId) {
    return expenseRepo.findByClientId(clientId);
  }
}
