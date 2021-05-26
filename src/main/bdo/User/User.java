package main.bdo.User;

/**
 * User intergace
 */
public interface User {
     void setData(String username, String password);
     int getLoginTrys();
     void incrementLoginTry();
}


