package constansts;

public enum Apps {
    YOUR_ACCOUNT("Your Account"),
    HEALTH_CONNECT_SUPPLY_CONSOLE("Health Connect - Supply Console"),
    IN_CLINIC_EXPERIENCE("In-Clinic Experience"),
    CLINIC_IN_BOX("Clinic in a Box (IPM)"),
    CALL_CENTER_CONSOLE("Call Center Console"),
    ELIGIBILITY_MANAGEMENT("Eligibility Management"),
    BCH_VACCINATION_PORTAL("BCH Vaccination Portal");
    public final String value;
    private Apps(String value) {
        this.value = value;
    }
}
