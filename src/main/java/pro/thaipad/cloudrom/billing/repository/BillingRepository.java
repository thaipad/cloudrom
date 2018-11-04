package pro.thaipad.cloudrom.billing.repository;

import pro.thaipad.cloudrom.billing.entity.BillingTransaction;
import pro.thaipad.cloudrom.billing.entity.TypeTransaction;

import java.time.LocalDate;
import java.util.List;

public interface BillingRepository {

    int addBillingTransaction(BillingTransaction transaction);

    int getBalance(int id);

    List<BillingTransaction> getBillingTransaction(LocalDate startDate,
                                                   LocalDate endDate,
                                                   boolean income,
                                                   boolean outcome,
                                                   TypeTransaction type);

    int recalcBalance(int id);

}
