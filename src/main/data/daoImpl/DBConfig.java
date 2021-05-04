package main.data.daoImpl;

public class DBConfig {

/*
     TODO handle case for VPN and SSH
     maybe with java.util.Properties  ??
 */
    /*Postgres Database Config */
    public static final String pgDatabaseURL = "jdbc:postgresql://localhost:5432/imdb";

    /*Oracle Database Config */
    public static  final String oracleDatabaseURL = "jdbc:oracle:thin:@localhost:1521/reldb";
}


