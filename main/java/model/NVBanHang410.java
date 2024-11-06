package model;

public class NVBanHang410 extends NhanVien410 {
	public NVBanHang410() {
	}
	
	public NVBanHang410(String id, String username, String password, String name, String diachi, String email, String sdt, String vitri) {
        super(id, username, password, name, diachi, email, sdt, "Ban hang");
    }
}
