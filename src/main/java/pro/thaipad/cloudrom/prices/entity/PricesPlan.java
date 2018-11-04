/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.prices.entity;

import pro.thaipad.cloudrom.common.entity.AbstractBaseEntity;

import java.time.LocalDate;

public class PricesPlan extends AbstractBaseEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean publish;

    public PricesPlan(Integer id, String name) {
        super(id, name);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
