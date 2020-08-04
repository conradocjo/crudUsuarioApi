package io.swagger.api;

@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-03T22:31:34.730Z")

public class ApiException extends Exception{
    @SuppressWarnings("unused")
	private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
