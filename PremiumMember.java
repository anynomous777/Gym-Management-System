 

public class PremiumMember extends GymMember {
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    public PremiumMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Accessor methods
    public double getPremiumCharge() { return premiumCharge; }
    public String getPersonalTrainer() { return personalTrainer; }
    public boolean getIsFullPayment() { return isFullPayment; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }

    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
    }

    public String payDueAmount(double amount) {
        if (this.isFullPayment) {
            return "Payment is already complete.";
        }

        if (this.paidAmount + amount > premiumCharge) {
            return "Payment amount exceeds the premium charge.";
        }

        this.paidAmount += amount;
        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
        }

        double remainingAmount = premiumCharge - this.paidAmount;
        return "Payment successful. Remaining amount: " + remainingAmount;
    }

    public void calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = premiumCharge * 0.10;
            System.out.println("Discount calculated: " + discountAmount);
        } else {
            System.out.println("No discount available. Complete payment first.");
        }
    }

    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}
