package Models;

import java.util.List;

public class Cart {

    private Account account;
    private List<Tour> cartDetail;

    public Cart() {
    }

    public Cart(Account account, List<Tour> cartDetail) {
        this.account = account;
        this.cartDetail = cartDetail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Tour> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<Tour> cartDetail) {
        this.cartDetail = cartDetail;
    }

}
