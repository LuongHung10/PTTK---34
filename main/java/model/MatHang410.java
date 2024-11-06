package model;

public class MatHang410 {
	private int id, soluong;
    private String name;
    private float price;

    public MatHang410() {
    }
    
    public MatHang410(int id, String name, float price, int soluong) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    
    public int getSoluong() {
		return soluong;
	}
    
    public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
