package Model;

public class Size {
    private int idSize;
    private String tenSize;
    private int trangThai;

    public Size() {
    }
    

    public Size(int idSize, String tenSize, int trangThai) {
        this.idSize = idSize;
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // toString() method to display information
    @Override
    public String toString() {
        return tenSize;
    }
}

