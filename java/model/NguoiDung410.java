package model;

public class NguoiDung410 {
    private String id, username, password, name, diachi, email, sdt;

    public NguoiDung410() {
    }

    public NguoiDung410(String id, String username, String password, String name, String diachi, String email, String sdt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
     
    public String getSdt() {
    	return sdt;
    }

    public NguoiDung410 setDiachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public NguoiDung410 setEmail(String email) {
        this.email = email;
        return this;
    }

    public NguoiDung410 setId(String id) {
        this.id = id;
        return this;
    }

    public NguoiDung410 setName(String name) {
        this.name = name;
        return this;
    }

    public NguoiDung410 setPassword(String password) {
        this.password = password;
        return this;
    }

    public NguoiDung410 setSdt(String sdt) {
        this.sdt = sdt;
        return this;
    }

    public NguoiDung410 setUsername(String username) {
        this.username = username;
        return this;
    }
}
