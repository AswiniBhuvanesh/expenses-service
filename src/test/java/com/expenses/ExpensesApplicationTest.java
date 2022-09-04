package com.expenses;

import static org.assertj.core.api.Assertions.assertThat;

import com.expenses.controller.ExpenseController;
import com.expenses.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpensesApplicationTest {

  @Autowired private ExpenseController expenseController;

  @Autowired private ExpenseService expenseService;

  @Test
  void contextLoads() throws Exception {
    assertThat(expenseController).isNotNull();
    assertThat(expenseService).isNotNull();
  }

  @Test
  void main() {
    ExpensesApplication.main(new String[] {});
  }
}
