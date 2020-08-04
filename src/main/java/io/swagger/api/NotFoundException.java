package io.swagger.api;

@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-03T22:31:34.730Z")

public class NotFoundException extends ApiException {
    @SuppressWarnings("unused")
	private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
