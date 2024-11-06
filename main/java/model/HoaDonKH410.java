package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoaDonKH410 {
	private String id;
	private Date  ngayxuat;
	private boolean trangthai;
	private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private KhachHang410 client;
	private NVBanHang410 seller;
	private NVGiaoHang410 shipper;
	private float tongTien;
	
	public HoaDonKH410() {
	}
	
	public HoaDonKH410(String id, Date ngayxuat, boolean trangthai,KhachHang410 client, NVBanHang410 seller, NVGiaoHang410 shipper, float tongTien) {
		this.id = id;
		this.ngayxuat = ngayxuat;
		this.trangthai = trangthai;
        this.client = client;
        this.seller = seller;
        this.shipper = shipper;
        this.tongTien = tongTien;
	}
	public KhachHang410 getClient() {
		return client;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getNgayxuat() {
		return ngayxuat;
	}
	
	public boolean isTrangthai() { // Thêm phương thức isTrangthai()
        return trangthai;
    }
	
	public NVBanHang410 getSeller() {
		return seller;
	}
	
	public NVGiaoHang410 getShipper() {
		return shipper;
	}
	
	public float getTongTien() {
		return tongTien;
	}
	
	public HoaDonKH410 setClient(KhachHang410 client) {
		this.client = client;
		return this;
	}
	
	public HoaDonKH410 setId(String id) {
		this.id = id;
		return this;
	}
	
	public HoaDonKH410 setNgayxuat(Date ngayxuat) {
		this.ngayxuat = ngayxuat;
		return this;
	}
	
	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}
	
	public HoaDonKH410 setSeller(NVBanHang410 seller) {
		this.seller = seller;
		return this;
	}
	
	public HoaDonKH410 setShipper(NVGiaoHang410 shipper) {
		this.shipper = shipper;
		return this;
	}
	
	public HoaDonKH410 setTongTien(float tongTien) {
		this.tongTien = tongTien;
		return this;
	}
}
