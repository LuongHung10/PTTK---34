package model;

public class NhanVien410 extends NguoiDung410{
	private String vitri;

    public NhanVien410() {
    }

    public NhanVien410(String id, String username, String password, String name, String diachi, String email, String sdt, String vitri) {
        super(id, username, password, name, diachi, email, sdt);
        this.vitri = vitri;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }
}
