package main.bdo.User;
/**
 * BDO for PostgreSQL User
 */

public class PgUser implements User{

    private static PgUser instance;
    private String username;
    private String password;
    private static int loginTrys = 0;

    // constructor
    private PgUser() {};

    public static PgUser getInstance(){
        if(instance == null){
            instance = new PgUser();
        }
        return instance;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getLoginTrys(){
        return loginTrys;
    }

    @Override
    public void incrementLoginTry(){
        loginTrys++;
    }

    @Override
    public void setData(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
}
