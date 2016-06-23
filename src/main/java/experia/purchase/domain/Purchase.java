package experia.purchase.domain;

import java.util.Date;

/**
 * Created by thiago on 20/06/16.
 */
public class Purchase {

    private Long id;

    private String productType;

    private Date expires;

    private PurchaseDetails details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public PurchaseDetails getDetails() {
        return details;
    }

    public void setDetails(PurchaseDetails details) {
        this.details = details;
    }

}
