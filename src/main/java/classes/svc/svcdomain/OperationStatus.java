package classes.svc.svcdomain;


public class OperationStatus {
    private String exeption = "";
    private boolean status;

    public String getExeption() {
        return exeption;
    }

    public void setExeption(String exeption) {
        this.exeption = exeption;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
