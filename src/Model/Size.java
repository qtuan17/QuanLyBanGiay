package Model;

public class Size {
    private int ID_Size;
    private String TenSize;
    private int TrangThai;

    public Size() {
    }

    public Size(int ID_Size, String TenSize, int TrangThai) {
        this.ID_Size = ID_Size;
        this.TenSize = TenSize;
        this.TrangThai = TrangThai;
    }

    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenSize;  // Trả về tên size thay vì thông tin mặc định
    }
}
