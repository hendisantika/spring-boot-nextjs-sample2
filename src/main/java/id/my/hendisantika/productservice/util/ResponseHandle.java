package id.my.hendisantika.productservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 09:53
 * To change this template use File | Settings | File Templates.
 */
public class ResponseHandle {
    public static ResponseEntity<Object> generate(
            String message,
            HttpStatus statusCode
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);

        return new ResponseEntity<Object>(map, statusCode);
    }
}
