package clientApp;

import org.apache.log4j.Logger;
import wbServerApp.IRemoteWb;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApplication {
    private final static Logger logger = Logger.getLogger(ClientApplication.class);

    private String serverIP = null;
    private int serverPort = 0;

    private IRemoteWb remoteWb = null;

    /**
     * constructor
     */
    public ClientApplication() {}

    /**
     * Connect to whiteboard server
     * @param ip IP address
     * @return True if connect successfully
     */
    public boolean connectWbServer(String ip) {
        try {
            //Connect to the rmiregistry that is running on localhost
            Registry registry = LocateRegistry.getRegistry(ip);

            //Retrieve the stub/proxy for the remote math object from the registry
            remoteWb = (IRemoteWb) registry.lookup("Whiteboard");
            return true;
        } catch (Exception e) {
            logger.fatal(e.toString());
            logger.fatal("Obtain remote service from whiteboard server(" + ip + ") failed");
            return false;
        }
    }

    /**
     * Register new user on server
     * @param username Username
     * @param password Password
     * @return Register information
     */
    public String register(String username, String password) {
        try {
            return remoteWb.register(username, password);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Register new users service from whiteboard server fail to execute");
            return "[ERROR]: Register new users service from whiteboard server fail to execute";
        }
    }

    /**
     * Existing user log in
     * @param username Username
     * @param password Password
     * @return Login information
     */
    public String login(String username, String password) {
        try {
            return remoteWb.login(username, password);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Existing user login service from whiteboard server fail to execute");
            return "[ERROR]: Existing user login service from whiteboard server fail to execute";
        }
    }

    /**
     * Create new whiteboard and set the user to be the manager
     * @param username Username
     * @param wbName Whiteboard name
     * @return Created whiteboard information
     */
    public String createWb(String username, String wbName) {
        try {
            return remoteWb.createWb(username, wbName);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Create whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Create whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Get all available whiteboards
     * @return List of available whiteboards
     */
    public String getAvailableWb() {
        try {
            return remoteWb.getAvailableWb();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Get available whiteboards service from whiteboard server fail to execute");
            return "[ERROR]: Get available whiteboards service from whiteboard server fail to execute";
        }
    }

    /**
     * Join specific whiteboard
     * @param wbID Whiteboard id
     * @param username Username
     * @return join feedback
     */
    public String joinWb(String wbID, String username) {
        try {
            return remoteWb.joinWb(wbID, username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Join whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Join whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Close specific whiteboard
     * @param wbID Whiteboard id
     * @param username Username
     * @return Closing feedback
     */
    public String closeWb(String wbID, String username) {
        try {
            return remoteWb.closeWb(wbID, username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Close whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Close whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Save specific whiteboard online
     * @param wbID Whiteboard id
     * @param username Username
     * @return Saving feedback
     */
    public String saveWbOnline(String wbID, String username) {
        try {
            return remoteWb.saveWbOnline(wbID, username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Save whiteboard online service from whiteboard server fail to execute");
            return "[ERROR]: Save whiteboard online service from whiteboard server fail to execute";
        }
    }

    /**
     * Save specific whiteboard locally
     * @param wbID Whiteboard id
     * @param username Username
     * @param format File format
     * @return Saving feedback
     */
    public String saveWbLocally(String wbID, String username, String format) {
        try {
            return remoteWb.saveWbLocally(wbID, username, format);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Save whiteboard locally service from whiteboard server fail to execute");
            return "[ERROR]: Save whiteboard locally service from whiteboard server fail to execute";
        }
    }

    /**
     * Get all online-stored whiteboard files for a specific user
     * @param username Username
     * @return All whiteboard files
     */
    public String getAllStoredFiles(String username) {
        try {
            return remoteWb.getAllStoredFiles(username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Get all online stored whiteboard files service from whiteboard server fail to execute");
            return "[ERROR]: Get all online stored whiteboard files service from whiteboard server fail to execute";
        }
    }

    /**
     * Open specific online-stored whiteboard
     * @param wbID Whiteboard id
     * @param username Username
     * @return Open feedback
     */
    public String openWbOnline(String wbID, String username) {
        try {
            return remoteWb.openWbOnline(wbID, username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Open online stored whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Open online stored whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Open specific locally-stored whiteboard
     * @param username Username
     * @param wbContent Whiteboard content
     * @return Open feedback
     */
    public String openWbLocally(String username, String wbContent) {
        try {
            return remoteWb.openWbLocally(username, wbContent);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Open locally stored whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Open locally stored whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Render all the whiteboards
     * @param wbID Whiteboard id
     * @param username Username
     * @return Whiteboard content
     */
    public String render(String wbID, String username) {
        try {
            return remoteWb.render(wbID, username);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Render distributed whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Render distributed whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Draw diagram
     * @param wbID Whiteboard id
     * @param username Username
     * @param content Drawing content
     * @return Drawing feedback
     */
    public String draw(String wbID, String username, String content) {
        try {
            return remoteWb.draw(wbID, username, content);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Draw distributed whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Draw distributed whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Erase diagram
     * @param wbID Whiteboard id
     * @param username Username
     * @param content Erasing content
     * @return Erasing feedback
     */
    public String erase(String wbID, String username, String content) {
        try {
            return remoteWb.erase(wbID, username, content);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Erase distributed whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Erase distributed whiteboard service from whiteboard server fail to execute";
        }
    }

    /**
     * Send message
     * @param wbID Whiteboard id
     * @param username Username
     * @param msg Message
     * @return Sending feeback
     */
    public String sendMsg(String wbID, String username, String msg) {
        try {
            return remoteWb.sendMsg(wbID, username, msg);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error("Send message to whiteboard service from whiteboard server fail to execute");
            return "[ERROR]: Send message to whiteboard service from whiteboard server fail to execute";
        }
    }
}
