package Features.PaymentProcessing;

// Concrete Strategy 2
public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(float amount) {

        System.out.println("Paid " + amount + " using PayPal account " + email + "\n");
    }
}