package experia.purchase.controller;

import experia.purchase.domain.Purchase;
import experia.purchase.domain.PurchaseDetails;
import experia.purchase.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by thiago on 20/06/16.
 */
@RestController
@RequestMapping(value = "purchase")
public class PurchaseController {

    private IPurchaseService purchaseService;

    @Autowired
    public PurchaseController(IPurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @RequestMapping(value="/details/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET)
    public ResponseEntity<List<PurchaseDetails>> listByCompanyId(
            @RequestParam(name = "max", defaultValue = "20") Integer max,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @PathVariable Long id){

        if(id == null)
            throw new IllegalArgumentException("Company id can not be null!");

        List<PurchaseDetails> details  = purchaseService.getValidPurchaseDetailsByComapanyId(max, offset, id);

        return new ResponseEntity(details, HttpStatus.OK);
    }

    @RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
    public ResponseEntity<Purchase> create(@RequestBody Purchase purchase){

        Purchase purchaseCreated =  purchaseService.createPurchase(purchase);

        return new ResponseEntity(purchaseCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Purchase purchase){

        if(id == null)
            throw new IllegalArgumentException("Purchase id can not be null!");

        purchaseService.updatePurchase(id, purchase);

        return new ResponseEntity("successfully update", HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id){

        if(id == null)
            throw new IllegalArgumentException("Invalid argument id");

        purchaseService.deletePurchase(id);

        return  new ResponseEntity("successfully delete", HttpStatus.OK);
    }

}
