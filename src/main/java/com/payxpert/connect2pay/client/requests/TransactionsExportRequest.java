package com.payxpert.connect2pay.client.requests;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.PaymentNetwork;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.exception.BadRequestException;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * Request for the export transactions API call.
 */
public class TransactionsExportRequest extends GenericRequest<TransactionsExportRequest> {

  private static final Set<PaymentMethod> FORBIDDEN_PAYMENT_METHODS = new HashSet<>(asList( //
      PaymentMethod.CHARGEBACK, //
      PaymentMethod.REVERSAL //
  ));

  private static final Set<TransactionOperation> FORBIDDEN_TRANSACTION_OPERATIONS = new HashSet<>(asList( //
      TransactionOperation.CHARGEBACK, //
      TransactionOperation.REVERSAL //
  ));

  @NotNull
  private Date startTime;

  @NotNull
  private Date endTime;

  private TransactionOperation operation;

  private PaymentMethod paymentMethod;

  private PaymentNetwork paymentNetwork;

  @Min(0)
  @Max(999)
  private Integer transactionResultCode;

  @Override
  public Map<String, String> getRequestParameters() {
    Map<String, String> parameters = super.getRequestParameters();
    parameters.put("startTime", Long.toString(this.unixTimestamp(this.startTime)));
    parameters.put("endTime", Long.toString(this.unixTimestamp(this.endTime)));
    if (this.transactionResultCode != null) {
      parameters.put("transactionResultCode", Integer.toString(this.transactionResultCode));
    }
    if (this.operation != null) {
      parameters.put("operation", this.operation.valueToString());
    }
    if (this.paymentMethod != null) {
      parameters.put("paymentMethod", this.paymentMethod.valueToString());
    }
    if (this.paymentNetwork != null) {
      parameters.put("paymentNetwork", this.paymentNetwork.valueToString());
    }
    return parameters;
  }

  /**
   * @return the minimum processing time of transactions included in the export; inclusive
   */
  public Date getStartTime() {
    return this.startTime;
  }

  /**
   * <p>
   * Sets the minimum processing time of transactions included in the export; inclusive
   * <p>
   * <b>Note</b> Supports only seconds precision. Milliseconds are ignored.
   * 
   * @param startTime
   *          the startTime to set
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * @return the maximum processing time of transactions included in the export; exclusive
   */
  public Date getEndTime() {
    return this.endTime;
  }

  /**
   * <p>
   * Sets the maximum processing time of transactions included in the export; exclusive
   * <p>
   * <b>Note</b> Supports only seconds precision. Milliseconds are ignored.
   * 
   * @param endTime
   *          the endTime to set
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * @return the operation of transactions included in the export
   */
  public TransactionOperation getOperation() {
    return this.operation;
  }

  /**
   * Sets the operation of transactions included in the export
   * 
   * @param operation
   *          the transaction operation to set
   */
  public void setOperation(TransactionOperation operation) {
    this.operation = operation;
  }

  /**
   * @return the method of transactions included in the export
   */
  public PaymentMethod getPaymentMethod() {
    return this.paymentMethod;
  }

  /**
   * Sets the method of transactions included in the export
   * 
   * @param paymentMethod
   *          the transaction method to set
   */
  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   * @return the payment network of transactions included in the export
   */
  public PaymentNetwork getPaymentNetwork() {
    return this.paymentNetwork;
  }

  /**
   * Sets the payment network of transactions included in the export
   * 
   * @param paymentNetwork
   *          the payment network method to set
   */
  public void setPaymentNetwork(PaymentNetwork paymentNetwork) {
    this.paymentNetwork = paymentNetwork;
  }

  /**
   * Sets the result code of transactions included in the export
   * 
   * @param transactionResultCode
   *          the resultCode to set, range from 0 to 999
   */
  public void setTransactionResultCode(int transactionResultCode) {
    this.transactionResultCode = transactionResultCode;
  }

  /**
   * @return the result code of transactions included in the export
   */
  public Integer getTransactionResultCode() {
    return this.transactionResultCode;
  }

  @Override
  protected TransactionsExportRequest getThis() {
    return this;
  }

  @Override
  public void validate() throws Exception {
    super.validate();
    this.assertStartBeforeEnd();
    if (FORBIDDEN_PAYMENT_METHODS.contains(this.paymentMethod)) {
      throw new BadRequestException("Value " + this.paymentMethod + " not allowed");
    }
    if (FORBIDDEN_TRANSACTION_OPERATIONS.contains(this.operation)) {
      throw new BadRequestException("Value " + this.operation + " not allowed");
    }
    if (this.paymentMethod != null //
        && this.paymentNetwork != null //
        && !this.paymentNetwork.supports(this.paymentMethod)) {
      throw new BadRequestException(format( //
          "Payment method %s not supported by paymentNetwork %s", this.paymentMethod, this.paymentNetwork));
    }
  }

  private void assertStartBeforeEnd() throws BadRequestException {
    if (this.unixTimestamp(this.startTime) >= this.unixTimestamp(this.endTime)) {
      throw new BadRequestException("startTime must be before endTime");
    }
  }

  private long unixTimestamp(Date date) {
    return date.getTime() / 1000L;
  }

}
