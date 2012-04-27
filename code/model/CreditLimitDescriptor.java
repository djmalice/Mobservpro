public class CreditLimitDescriptor {
  private int creditLimit;
  
  public CreditLimitDescriptor(int percentage) {
    creditLimit = percentage;
  }
  
  public int getCreditLimit() { return creditLimit; }
  public void setCreditLimit(int x) { creditLimit = x; }  
}