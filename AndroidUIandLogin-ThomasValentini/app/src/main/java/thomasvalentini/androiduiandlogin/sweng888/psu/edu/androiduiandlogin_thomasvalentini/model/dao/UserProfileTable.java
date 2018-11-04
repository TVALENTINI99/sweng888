package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao;

public class UserProfileTable {

    /** Defining the Table Content **/
    public static final String TABLE_NAME = "profile";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_SURNAME = "surname";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_BIRTHDAY = "birthday";
    public static final String COLUMN_NAME_MOBILE_PHONE = "mobile_phone";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_NAME + " TEXT," +
                COLUMN_NAME_SURNAME + " TEXT," +
                COLUMN_NAME_USERNAME + " TEXT," +
                COLUMN_NAME_BIRTHDAY + " TEXT," +
                COLUMN_NAME_MOBILE_PHONE + " TEXT,"+
                COLUMN_NAME_EMAIL + " TEXT," +
                COLUMN_NAME_PASSWORD + " TEXT)" );
    }

    public static String select_all(){
        return new String("SELECT * FROM "+TABLE_NAME);

    }
    public static String select_unique (String username){
        return new String("SELECT * FROM "+TABLE_NAME
                +" WHERE "+TABLE_NAME +"."+COLUMN_NAME_EMAIL+"='"+username+"' OR "
                +TABLE_NAME +"."+COLUMN_NAME_USERNAME+"='"+username+"'" );

    }
    public static String check_login (String username, String password){
        return new String("SELECT * FROM "+TABLE_NAME
                +" WHERE "+TABLE_NAME +"."+COLUMN_NAME_EMAIL+"='"+username+"' AND "+TABLE_NAME+"."+COLUMN_NAME_PASSWORD+"='"+password
                +"' OR "+TABLE_NAME +"."+COLUMN_NAME_USERNAME+"='"+username+"' AND "+TABLE_NAME+"."+COLUMN_NAME_PASSWORD+"='"+password+"'" );

    }
    public static String check_users(String username, String email){
        return new String("SELECT * FROM "+TABLE_NAME
                +" WHERE "+TABLE_NAME +"."+COLUMN_NAME_EMAIL+"='"+email+"' OR "
                +TABLE_NAME +"."+COLUMN_NAME_USERNAME+"='"+username+"'" );
    }
    public static final String delete(){
        return "DROP TABLE IF EXISTS " +TABLE_NAME;
    }
}
