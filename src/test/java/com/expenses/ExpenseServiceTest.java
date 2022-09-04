package com.expenses;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.expenses.model.Client;
import com.expenses.model.Expense;
import com.expenses.repository.ExpenseRepo;
import com.expenses.service.ExpenseService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

  @Mock private ExpenseRepo expenseRepo;

  @InjectMocks ExpenseService expenseService;

  @Test
  public void clientFindById() {
    long id = 1L;
    Client client = new Client(id, "Alex");
    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    Expense expense1 = new Expense(4L, new Date(), new Double(20), client);
    List<Expense> expenseList = new ArrayList<Expense>();
    expenseList.add(expense);
    expenseList.add(expense1);
    // providing knowledge
    when(expenseRepo.findByClientId(id)).thenReturn(expenseList);

    List<Expense> expenseListFromSer = expenseService.findByClientId(id);
    assertThat(expenseListFromSer.get(0).getExpenseId()).isEqualTo(3L);
  }

  @Test
  public void saveExpense() {
    long id = 1L;
    Client client = new Client(id, "Sid");
    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    // providing knowledge
    when(expenseRepo.save(expense)).thenReturn(expense);

    Expense expenseFromSer = expenseService.save(expense);
    assertThat(expenseFromSer).isEqualTo(expense);
  }
}
