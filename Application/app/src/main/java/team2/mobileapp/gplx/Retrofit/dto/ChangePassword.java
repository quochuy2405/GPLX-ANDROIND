package team2.mobileapp.gplx.Retrofit.dto;

public class ChangePassword {

    private String newPassword;
    public ChangePassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "newPassword='" + newPassword + '\'' +
                '}';
    }
}
