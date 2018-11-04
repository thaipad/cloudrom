/**
 * created by Thaipad 2018
 */

package pro.thaipad.cloudrom.billing.entity;

import pro.thaipad.cloudrom.common.entity.AbstractBaseEntity;

public class TypeTransaction extends AbstractBaseEntity {
    private int inOrOut;

    public TypeTransaction(int id, String name, int inOrOut) {
        super(id, name);
        this.inOrOut = inOrOut;
    }

    public int getInOrOut() {
        return inOrOut;
    }
}
