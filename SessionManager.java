import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class SessionManager {
    public static final int PORT = 8080;
    private static ArrayList<Session> sessions = new ArrayList<>();
    private static String text = "";
    
    public static String getText() {
        return text;
    }

    public static void deleteSession(Session removeTarget) {
        boolean removed = sessions.remove(removeTarget);
        if (removed) {
            System.out.println("successfully deleted session: " + removeTarget);
        } else {
            System.out.println("failed to remove session from the session list");
        }
    }

    public static void updateText(String newText) {
        text = newText;
        notifyChangesToAllSession();
    }

    private static void notifyChangesToAllSession() {
        // TODO 各SessionにModelが変わったことを通知する。
        sessions.forEach(session -> session.sendMessageToClient(text));
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Started to wait for new connection on " + serverSocket);

        try {
            // TODO: ここ怪しい。Ctrl-Cとかで強制終了した時にちゃんとServerSocket.close()が呼ばれるかとか知らずに書いてる。
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted: " + socket);
                Session newSession = new Session(socket);
                sessions.add(newSession);
            }
        } finally {
            serverSocket.close();
        }
    }
}
