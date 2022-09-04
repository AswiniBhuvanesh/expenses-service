package com.expenses.service;

import com.expenses.model.Client;
import com.expenses.repository.ClientRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired private ClientRepo clientRepo;

  public Optional<Client> findById(Long clientId) {
    return clientRepo.findById(clientId);
  }

  public boolean existsById(Long clientId) {
    return clientRepo.existsById(clientId);
  }
}
