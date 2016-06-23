package experia.purchase.service;

import experia.purchase.domain.Purchase;
import experia.purchase.domain.PurchaseDetails;
import experia.purchase.handler.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by thiago on 22/06/16.
 */
@Service
public class PurchaseService implements IPurchaseService {

    private IPurchaseRestService purchaseRestService;

    @Autowired
    public PurchaseService(IPurchaseRestService purchaseRestService) {
        this.purchaseRestService = purchaseRestService;
    }

    @Override
    public List<PurchaseDetails> getValidPurchaseDetailsByComapanyId(Integer max, Integer offset, Long comapanyId) {
        List<Purchase> purchases = getPurchasesByCompanyId(max, offset, comapanyId);
        List<Long> purchasesIds = getValidPurchaseIdsFromList(purchases);
        List<PurchaseDetails> details = getDetailsByPurchaseIds(max, offset, purchasesIds);

        return details;
    }

    @Override
    public List<Purchase> getPurchasesByCompanyId(Integer max, Integer offset, Long comapanyId) throws RequestException {
        Purchase[] purchaseArray = purchaseRestService.getPurchasesByCompanyId(max, offset, comapanyId);
        return Arrays.asList(purchaseArray);
    }

    @Override
    public List<Long> getValidPurchaseIdsFromList(List<Purchase> purchases) {
        List<Long> purchasesIds = purchases.stream()
                .filter(p -> p.getExpires().before(new Date()))
                .map(Purchase::getId)
                .collect(Collectors.toList());
        return purchasesIds;
    }

    @Override
    public List<PurchaseDetails> getDetailsByPurchaseIds(Integer max, Integer offset, List<Long> purchaseIds) throws RequestException {
        PurchaseDetails[] detailsArray = purchaseRestService.getDetailsByPurchaseIds(max, offset, purchaseIds);
        return Arrays.asList(detailsArray);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRestService.createPurchase(purchase);
    }

    @Override
    public void updatePurchase(Long purchaseId, Purchase purchase) {
        purchaseRestService.updatePurchase(purchaseId, purchase);
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        purchaseRestService.deletePurchase(purchaseId);
    }
}
