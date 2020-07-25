package exceptionhandler;

import devdungeon.httperror.SignUpException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SignUpHandler {

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<String> handleSignUpException(SignUpException suex) throws JSONException {
        System.out.println(suex.getErrCode());
        String json = new JSONObject()
                .put("ErrCode",suex.getErrCode())
                .put("ErrMsg",suex.getErrMsg()).toString();

        return new ResponseEntity<String>(json,HttpStatus.BAD_REQUEST);
    }
}
