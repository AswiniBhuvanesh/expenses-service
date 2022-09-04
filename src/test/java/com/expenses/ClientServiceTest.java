package com.expenses;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.expenses.model.Client;
import com.expenses.model.Expense;
import com.expenses.repository.ClientRepo;
import com.expenses.service.ClientService;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

  @Mock private ClientRepo clientRepo;

  @InjectMocks ClientService clientService;

  @Test
  public void findByClientId() {
    long id = 1L;
    Client client = new Client(id, "Sid");
    Expense expense = new Expense(3L, new Date(), new Double(10), client);
    // providing knowledge
    when(clientRepo.findById(id)).thenReturn(Optional.of(client));

    Optional<Client> clientFromSer = clientService.findById(id);
    assertThat(clientFromSer.get().getId()).isEqualTo(id);
  }
}
