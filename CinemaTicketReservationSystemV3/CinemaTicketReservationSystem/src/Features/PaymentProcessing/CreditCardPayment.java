package Features.PaymentProcessing;

// Concrete Strategy 1
public class CreditCardPayment implements PaymentStrategy {
    private int cardNumber;
    private String name;

    public CreditCardPayment(int cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }


    @Override
    public void pay(float amount) {
        System.out.println("You have paid $" + amount + " using card ending in " + cardNumber + "\n");
    }
}
