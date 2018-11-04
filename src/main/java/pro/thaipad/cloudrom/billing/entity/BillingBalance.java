/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.billing.entity;

public class BillingBalance {
    private Integer idUser;
    private int sum;

    public BillingBalance(Integer idUser, int sum) {
        this.idUser = idUser;
        this.sum = sum;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public int getSum() {
        return sum;
    }

}
