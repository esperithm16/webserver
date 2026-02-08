

public class Client {
    public Runnable runnable(){
        return new Runnable() {
            @Override
            publicvoid 
        };
    }
    public static void main(String[] args) {
        Client client = new Client();
        for(int i = 0 ;i<100;i++){
        try {
          Thread thread=new Thread(client.getRunnable());
    thread.start();  
        } catch (Exception exception) {
            return;
        }
        }
    }
}
