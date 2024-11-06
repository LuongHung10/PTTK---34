package model;

public class KhachHang410 extends NguoiDung410{
	private boolean laKH = false;
	
	public KhachHang410() {
	}
	
    public KhachHang410(String id, String username, String password, String name, String diachi, String email, String sdt) {
        super(id, username, password, name, diachi, email, sdt);
    }

    public boolean laKH() { 
        return laKH; 
    }

    public void setKH(boolean laKH) { 
        this.laKH = laKH; 
    }
    

}
