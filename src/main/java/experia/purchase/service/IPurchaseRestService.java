package experia.purchase.service;

import experia.purchase.domain.Purchase;
import experia.purchase.domain.PurchaseDetails;
import experia.purchase.handler.exception.RequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by thiago on 22/06/16.
 */
public interface IPurchaseRestService {

    Purchase[] getPurchasesByCompanyId(Integer max, Integer offset, Long comapanyId) throws RequestException;

    PurchaseDetails[] getDetailsByPurchaseIds(Integer max, Integer offset, List<Long> purchaseIds) throws RequestException;

    void deletePurchase(Long purchaseId);

    Purchase createPurchase(Purchase purchase);

    void updatePurchase(Long purchaseId, Purchase purchase);
}
