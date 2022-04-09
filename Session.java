import java.net.*;
import java.io.*;

class Session extends Thread {
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;
    public int id;

    public Session(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.start();
    }

    public void sendMessageToClient(String message) {
        out.println(message);
    }

    // 読み込み待ちするスレッド
    @Override
    public void run() {
        System.out.println("Established new session: " + this.socket);
        try {
            while(true) {
                String newText = in.readLine();
                if (newText.equals("END")) break;

                System.out.println("received new message on " + socket);
                System.out.println("content: " + newText);
                SessionManager.updateText(newText);
            }
        } catch(Exception e) {
            System.out.println("an error occurred.");
            e.printStackTrace();
        } finally {
            System.out.println("closing connection " + socket);
            try {
                SessionManager.deleteSession(this);
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}