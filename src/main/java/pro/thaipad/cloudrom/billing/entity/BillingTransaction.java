/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.billing.entity;

import pro.thaipad.cloudrom.common.entity.AbstractBaseEntity;

import java.time.LocalDateTime;

public class BillingTransaction extends AbstractBaseEntity {

    private Integer idUser;
    private LocalDateTime dateTime;
    private int sum;

    public BillingTransaction(Integer id, String name, Integer idUser, LocalDateTime dateTime, int sum) {
        super(id, name);
        this.idUser = idUser;
        this.dateTime = dateTime;
        this.sum = sum;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getSum() {
        return sum;
    }

}
