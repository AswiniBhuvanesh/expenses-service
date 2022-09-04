package com.expenses;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.expenses.controller.ExpenseController;
import com.expenses.model.Client;
import com.expenses.model.Expense;
import com.expenses.repository.ExpenseRepo;
import com.expenses.service.ClientService;
import com.expenses.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ExpenseService expenseService;

  @MockBean private ClientService clientService;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private ExpenseRepo expenseRepo;

  @Test
  void shouldNotCreateExpense() throws Exception {
    Client client = new Client(1, "John");
    Long clientId = 1L;

    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    when(clientService.findById(clientId)).thenReturn(Optional.of(client));
    when(expenseService.save(expense)).thenReturn(expense);
    mockMvc
        .perform(
            post("/api/expenses/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void shouldReturnExpense() throws Exception {
    long clientId = 1L;
    Client client = new Client(clientId, "Alex");
    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    Expense expense1 = new Expense(4L, new Date(), new Double(20), client);
    List<Expense> expenseList = new ArrayList<Expense>();
    expenseList.add(expense);
    expenseList.add(expense1);

    when(expenseService.findByClientId(clientId)).thenReturn(expenseList);
    when(clientService.existsById(clientId)).thenReturn(true);
    mockMvc
        .perform(get("/api/expenses/{clientId}", clientId))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void getAmountSpent() {
    Expense expense = new Expense();
    expense.setAmountSpent(new Double(10));
    assertThat(expense.getAmountSpent()).isEqualTo(new Double(10));
  }

  @Test
  void shouldThrowErrorBlank() throws Exception {
    Client client = new Client(1, null);
    Long clientId = 1L;

    Expense expense = new Expense(3L, null, new Double(10), client);
    when(clientService.findById(clientId)).thenReturn(Optional.of(client));
    when(expenseService.save(expense)).thenThrow(RuntimeException.class);
    mockMvc
        .perform(
            post("/api/expenses/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void testGetClientExpense() throws Exception {
    Client client = new Client(1, "John");
    Long clientId = 1L;

    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    when(clientService.existsById(clientId)).thenReturn(false);
    when(expenseService.save(expense)).thenReturn(expense);
    mockMvc
        .perform(
            get("/api/expenses/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expense)))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void getName() {

    Client client = new Client();
    client.setName("Test");
    Assertions.assertThat(client.getName()).isEqualTo("Test");
  }
}
