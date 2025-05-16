package Models;

public class Log {

    private String logID;
    private String accountID;
    private String action;
    private String time;

    public Log() {
    }

    public Log(String logID, String accountID, String action, String time) {
        this.logID = logID;
        this.accountID = accountID;
        this.action = action;
        this.time = time;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" + "logID=" + logID + ", accountID=" + accountID + ", action=" + action + ", time=" + time + '}';
    }

}
