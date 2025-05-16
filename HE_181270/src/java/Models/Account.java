package Models;

public class Account {

    private int accountID;
    private String username;
    private String password;
    private boolean isSeller;
    private boolean isAdmin;
    private boolean isActive;

    public Account() {
    }

    public Account(int accountID, String username, String password, boolean isSeller, boolean isAdmin, boolean isActive) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.isSeller = isSeller;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsSeller() {
        return isSeller;
    }

    public void setIsSeller(boolean isSeller) {
        this.isSeller = isSeller;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", username=" + username + ", password=" + password + ", isSeller=" + isSeller + ", isAdmin=" + isAdmin + ", isActive=" + isActive + '}';
    }

}
