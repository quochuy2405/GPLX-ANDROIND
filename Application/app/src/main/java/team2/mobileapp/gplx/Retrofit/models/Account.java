package team2.mobileapp.gplx.Retrofit.models;

public class Account {
    private String id;
    private String Username;
    private String Password;
    private String Email;
    private String FirstName;
    private String LastName;
    private String Avatar;
    private String Status;
    private String roleId;

    public Account() {
    }

    public Account(String id, String username, String password, String email, String firstName, String lastName, String avatar, String status, String roleId) {
        this.id = id;
        Username = username;
        Password = password;
        Email = email;
        FirstName = firstName;
        LastName = lastName;
        Avatar = avatar;
        Status = status;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Avatar='" + Avatar + '\'' +
                ", Status='" + Status + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
