package com.payxpert.connect2pay.client.response;

import java.util.List;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.constants.ResultCode;

/**
 * Response of the export transactions API call.
 */
public class TransactionsExportResponse extends GenericResponse<TransactionsExportResponse> {

  private ResultCode code;

  private List<TransactionAttempt> transactions;

  public List<TransactionAttempt> getTransactions() {
    return this.transactions;
  }

  public void setTransactions(List<TransactionAttempt> transactions) {
    this.transactions = transactions;
  }

  public ResultCode getCode() {
    return this.code;
  }

  public void setCode(ResultCode code) {
    this.code = code;
  }

}
