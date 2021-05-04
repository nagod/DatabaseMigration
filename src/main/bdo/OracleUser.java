package main.bdo;

public class OracleUser implements User{

    private static OracleUser instance;
    private String username;
    private String password;

    // constructor
    private OracleUser() {};

    public static OracleUser getInstance(){
        if(instance == null){
            instance = new OracleUser();
        }
        return instance;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setData(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Override
    public User getUser() {
        return instance;
    }

}
