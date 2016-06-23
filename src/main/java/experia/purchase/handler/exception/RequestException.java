package experia.purchase.handler.exception;

/**
 * Created by thiago on 22/06/16.
 */
public class RequestException extends RuntimeException {

    public RequestException(){
        super();
    }

    public RequestException(String message){
        super(message);
    }

}
