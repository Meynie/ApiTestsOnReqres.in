package api.pojoClass;

public class UnSuccessRegister {
    private String error;

    public UnSuccessRegister(String error) {
        this.error = error;
    }

    public UnSuccessRegister() {
    }

    public String getError() {
        return error;
    }
}
