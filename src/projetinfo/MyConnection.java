
package projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Permet l'accès à la base de données
 *
 * Respecte le pattern Singleton (une connexion au macimum)
 * @author formation
 *
 */

public class MyConnection {

        static final String DB_URL = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
        static final String DB_USER = "dbuse";
        static final String DB_PASS = "pass";

        static Connection connection;

        /**
         * Constructeur
         *
         * Privé pour ne pas être appelé de l'extérieur
         *
         */

        private MyConnection (){
                try{
                        Class.forName("org.sqlite.JDBC");
                        connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
                } catch (Exception e){
                        e.printStackTrace();
                }
        }

        /**
         * Récupére ou construit si elle n'existe pas l'instance connexion
         * @return
         */
        public static Connection getinstance(){
                if (connection == null){
                        new MyConnection();
                }
                return connection;
        }
}

