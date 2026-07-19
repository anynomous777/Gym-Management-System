public class RegularMember extends GymMember {
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    public RegularMember(int id, String name, String location, String phone, String email,
                        String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    // Accessor methods
    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean isEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 2; // Regular members get fewer points than premium
        checkUpgradeEligibility();
    }

    private void checkUpgradeEligibility() {
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }
    }

    public double getPlanPrice(String plan) {
        switch(plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: throw new IllegalArgumentException("Invalid plan: " + plan);
        }
    }

    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade. Need more attendance (current: " + attendance + "/" + attendanceLimit + ").";
        }

        if (newPlan.equalsIgnoreCase(this.plan)) {
            return "Already subscribed to the " + this.plan + " plan.";
        }

        try {
            double newPrice = getPlanPrice(newPlan);
            this.plan = newPlan.toLowerCase();
            this.price = newPrice;
            return "Plan upgraded successfully to " + newPlan + " (Price: " + newPrice + ")";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public void revertRegularMember(String removalReason) {
        if (removalReason == null || removalReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Removal reason cannot be empty");
        }

        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason.trim();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("--- Regular Membership Details ---");
        System.out.println("Plan: " + plan);
        System.out.println("Monthly Price: " + price);
        System.out.println("Attendance: " + attendance + "/" + attendanceLimit);
        System.out.println("Upgrade Eligible: " + (isEligibleForUpgrade ? "Yes" : "No"));
        System.out.println("Referral Source: " + referralSource);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }

    // Additional methods
    public String getUpgradeStatus() {
        return isEligibleForUpgrade ? 
               "Eligible for upgrade" : 
               "Needs " + (attendanceLimit - attendance) + " more visits to qualify";
    }

    public boolean canUpgradeToPremium() {
        return isEligibleForUpgrade && attendance >= attendanceLimit && loyaltyPoints >= 30;
    }
}
