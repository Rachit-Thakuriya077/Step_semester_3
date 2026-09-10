public class M4 {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public M4() {
        name = "";
        confirmed = false;
        otp = null;
    }

    // Constructor with name
    public M4(String name) {
        this();
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    // Boolean getter
    public boolean isConfirmed() {
        return confirmed;
    }

    // Setter
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only OTP
    public void setOtp(String otp) {

        if (otp != null && otp.matches("\\d{4,6}")) {
            this.otp = otp;
        }
    }

    public static void main(String[] args) {

        M4 p = new M4("Rahul Dev");

        System.out.println(p.getName());

        p.setConfirmed(true);

        System.out.println(p.isConfirmed());

        p.setOtp("4471");

        System.out.println("OTP set successfully");
    }
}
