package SmartSplit;

public class Payment {
  private int payer;
  private int payee;
  private float amount;

  Payment(int payer, int payee, float amount) {
    assert amount >= 0 : "Payments must be positive";
    this.payer = payer;
    this.payee = payee;
    this.amount = amount;
  }

  int getPayer() {
    return payer;
  }

  int getPayee() {
    return payee;
  }

  float getAmount() {
    return amount;
  }

  void setAmount(float amount) {
    assert amount >= 0 : "Payments must be positive";
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Person " + payer + " to pay £" + String.valueOf(amount) +
        " to person " + payee;
  }
}
