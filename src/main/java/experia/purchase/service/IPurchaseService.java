package experia.purchase.service;

import experia.purchase.domain.Purchase;
import experia.purchase.domain.PurchaseDetails;

import java.util.List;

/**
 * Created by thiago on 22/06/16.
 */
public interface IPurchaseService {

    List<PurchaseDetails> getValidPurchaseDetailsByComapanyId(Integer max, Integer offset, Long comapanyId);

    List<Purchase> getPurchasesByCompanyId(Integer max, Integer offset, Long comapanyId) throws Exception;

    List<Long> getValidPurchaseIdsFromList(List<Purchase> purchases);

    List<PurchaseDetails> getDetailsByPurchaseIds(Integer max, Integer offset, List<Long> purchaseIds);

    Purchase createPurchase(Purchase purchase);

    void updatePurchase(Long purchaseId, Purchase purchase);

    void deletePurchase(Long purchaseId);
}
