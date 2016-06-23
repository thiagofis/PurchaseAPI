package experia.purchase.service;

import experia.purchase.domain.Purchase;
import experia.purchase.domain.PurchaseDetails;
import experia.purchase.factory.IPaginationMapFactory;
import experia.purchase.handler.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiago on 22/06/16.
 */
public class PurchaseRestService implements IPurchaseRestService {

    private RestTemplate restTemplate;
    private IPaginationMapFactory paginationMapFactory;

    @Value("url.get.company.purchases")
    private String urlGetCompanyPurchases;

    @Value("url.get.purchases.details")
    private String urlGetDetailsPurchases;

    @Value("url.delete.purchase")
    private String urlDeletePurchase;

    @Value("url.create.purchase")
    private String urlCreatePurchase;

    @Value("url.update.purchase")
    private String urlUpdatePurchase;

    @Autowired
    public PurchaseRestService(IPaginationMapFactory paginationMapFactory) {
        restTemplate = new RestTemplate();
        this.paginationMapFactory = paginationMapFactory;
    }

    @Override
    public Purchase[] getPurchasesByCompanyId(Integer max, Integer offset, Long comapanyId) throws RequestException {
        Map requestVariables = paginationMapFactory.create(max, offset);
        requestVariables.put("comapanyId", comapanyId.toString());

        ResponseEntity<Purchase[]> responseEntity =  restTemplate.getForEntity(urlGetCompanyPurchases, Purchase[].class, requestVariables, comapanyId);

        if(responseEntity.getStatusCode() != HttpStatus.OK){
            throw new RequestException("Could not load the purchase list");
        }

        return responseEntity.getBody();

    }

    @Override
    public PurchaseDetails[] getDetailsByPurchaseIds(Integer max, Integer offset, List<Long> purchaseIds) throws RequestException {
        Map requestVariables = paginationMapFactory.create(max, offset);
        requestVariables.put("purchaseIds", purchaseIds.toString());

        ResponseEntity<PurchaseDetails[]> responseEntity =  restTemplate.getForEntity(urlGetDetailsPurchases, PurchaseDetails[].class, requestVariables);

        if(responseEntity.getStatusCode() != HttpStatus.OK){
            throw new RequestException("Could not load the details purchase list");
        }

        return responseEntity.getBody();

    }

    @Override
    public void deletePurchase(Long purchaseId) {
        Map requestVariables = new HashMap<String, String>();
        requestVariables.put("id", purchaseId.toString());

        restTemplate.delete(urlDeletePurchase, requestVariables);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        ResponseEntity<Purchase> responseEntity = restTemplate.postForEntity(urlCreatePurchase, purchase, Purchase.class);

        if(responseEntity.getStatusCode() != HttpStatus.OK){
            throw new RequestException("Could not create new purchase");
        }

        return responseEntity.getBody();
    }

    @Override
    public void updatePurchase(Long purchaseId, Purchase purchase) {
        Map requestVariables = new HashMap<String, String>();
        requestVariables.put("id", purchaseId.toString());

        restTemplate.put(urlUpdatePurchase, purchase, requestVariables);
    }


}
