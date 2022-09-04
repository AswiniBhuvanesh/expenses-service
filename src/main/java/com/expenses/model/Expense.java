package com.expenses.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Expense")
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty(readOnly = true, hidden = true)
  private long expenseId;

  @Column(name = "expenseDate", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(
      required = true,
      example = "2022-09-01",
      notes = "Reservation date as YYYY-MM-DD")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date expenseDate;

  @Column(name = "amountSpent", nullable = false)
  @ApiModelProperty(required = true)
  private Double amountSpent;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "clientId", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Client client;

  public Expense(long expenseId, Date expenseDate, Double amountSpent, Client client) {

    this.expenseId = expenseId;
    this.expenseDate = expenseDate;
    this.amountSpent = amountSpent;
    this.client = client;
  }

  public Expense() {}

  public long getExpenseId() {
    return expenseId;
  }

  public void setExpenseId(long expenseId) {
    this.expenseId = expenseId;
  }

  public Date getExpenseDate() {
    return expenseDate;
  }

  public void setExpenseDate(Date expenseDate) {
    this.expenseDate = expenseDate;
  }

  public Double getAmountSpent() {
    return amountSpent;
  }

  public void setAmountSpent(Double amountSpent) {
    this.amountSpent = amountSpent;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
