import wbServerPre.wbServerViewControllers.WbServerGUIController;

public class runWbServer {
    public static void main(String[] args) {
        // log setting
        System.setProperty("my.log", "resources/log/wbServer.log");
        // rmi setting
        System.setProperty("java.rmi.server.hostname", args[0]);
        // security settings
        System.setProperty("java.security.policy","file:./security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        WbServerGUIController.getInstance().runWbServerGUI();
    }
}
