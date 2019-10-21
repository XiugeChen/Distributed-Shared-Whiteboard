package dataServerApp;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Authenticator {
    private final static Logger logger = Logger.getLogger(Authenticator.class);

    private HashMap<String, String> passbook = null;
//    private String username = null;
//    private String password = null;

    private static String USER_NOT_FOUND = "User not found in passbook";
    private static String AUTHENTICATION_FAILED = "Authentication failed";
    private static String USER_REGISTER_DUPLICATION = "User duplication";
    private static String USER_REGISTER_SUCCESS = "User register success";
    private static String USER_AUTHENTICATION_SUCCESS = "User authentication success";
    private static String USER_NULL= "User name not provided";
    private static String PASSWORD_NULLL= "Password not provided";

    public static String FAIL_HEADER = "Fail";
    public static String SUCCESS_HEADER = "Success";

    // private Singleton instance.
    private static Authenticator authenticator = null;
    private final String encrypPassword;
    private Cypher cypher;
    // Authenticator should be a singleton, since passbook should be kept unique.
    private Authenticator(){
        this.passbook = new HashMap<String, String>();
        this.encrypPassword = readPassword();
        this.cypher = Cypher.getInstance();
        logger.info("Cypher created: "+this.cypher);
    }
    public static Authenticator getInstance(){
        if (authenticator == null){
            authenticator = new Authenticator();
        }
        return authenticator;
    }
    public void syncStorage(HashMap<String, String> locaStorage){
        this.passbook = locaStorage;
    }
    private String readPassword(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("password.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            // Ignore the line separator.
            while(line!=null){
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    private String encryptPassword(String password){
        return cypher.encrypt(password);
    }
    private String decryptPassword(String encodedText){
        return cypher.decrypt(encodedText);
    }

    /**
     * Register new user. Check if the username or password null first.
     * @param username  New username that the user wants to add.
     * @param password  New password that the user wants to add.
     * @return
     */
    public JSONObject registerUser(String username, String password){
        if (username == null){
            logger.info("The user has not provided the username. ");
            return jsonParse(FAIL_HEADER, USER_NULL,"");
        }
        if (password == null) {
            logger.info("The user has not provided the password.");
            return jsonParse(FAIL_HEADER, PASSWORD_NULLL,"");
        }
        if (passbook.containsKey(username)){
            logger.info("The username entered has already been used by others. Fail to register the username!!");
            return jsonParse(FAIL_HEADER, USER_REGISTER_DUPLICATION,"");
        }
        else {
            // Put encrypted password.
            passbook.put(username,encryptPassword(password));
            logger.info("Successfully registered. Password has been encoded. ");
            return jsonParse(SUCCESS_HEADER, USER_REGISTER_SUCCESS,encryptPassword(password));
        }
    }

    /**
     * Authenticate the user login. Check if the user exist first, then check if the
     * username and password are previously stored in the passbook.
     * @param username  New username that the user wants to add.
     * @param password  New password that the user wants to add.
     * @return
     */
    public JSONObject authenticate(String username, String password){
        // If passbook not contain the user name
        if (!passbook.containsKey(username)){
            logger.info("There is no such user exist in our database. ");
            return  jsonParse(FAIL_HEADER, USER_NOT_FOUND,"");
        }
        // If the password under the user name is not the same as that in passbook
        else if(!decryptPassword(passbook
                .get(username)).
                equals
                        (
                        password)){
            logger.info("The password or the username entered are INCORRECT. Please check the username or password. ");
            return  jsonParse(FAIL_HEADER, AUTHENTICATION_FAILED,"");
        }
        // Successfully authenticated
        else {
            return jsonParse(SUCCESS_HEADER,USER_AUTHENTICATION_SUCCESS,"");
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject jsonParse(String header, String message,String encodedPassword){
        JSONObject object = new JSONObject();
        object.put("header", header);
        object.put("message", message);
        object.put("encoded_password",encodedPassword);
        return object;
    }
    // This method is for testing purpose.
    public void iteratePassbook(){
        Iterator it = passbook.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            logger.info(pair.getKey().toString()+pair.getValue().toString());
            System.out.println("User name : "+ pair.getKey()+" User passowrd: "+pair.getValue());
        }
    }
//    public HashMap<String, String> getPassbook(){
//        return passbook;
//    }
}
