package main.Config;

public class DBConfig {

    /*Postgres Database Config */
    public static final String pg_Database_SSH_URL = "jdbc:postgresql://localhost:5432/imdb";
    public static final String pg_Database_VPN_URL = "jdbc:postgresql://dbvm05.iai.uni-bonn.de:5432/imdb";

    /*
     *Ppstgres Schemaname
     */
    public static final String pg_SCHEMA = "public";



    /*Oracle Database Config */
    public static  final String oracle_Database_SSH_URL = "jdbc:oracle:thin:@localhost:1521/reldb";
    public static  final String oracle_Database_VPN_URL = "jdbc:oracle:thin:@dbvm02.iai.uni-bonn.de:1521/reldb";

    public static  String oracle_SCHEMA ="";



}


