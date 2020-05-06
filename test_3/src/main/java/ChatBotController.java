import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;


@ServerEndpoint("/chatbot")
public class ChatBotController {

    @OnOpen
    public void handleOpen() {
        System.out.println("Connected ...");
    }

    @OnMessage
    public String handleMessage(String message) throws ServiceException, RemoteException {
        System.out.println("MSG From Client : " + message);
        String replayMessage = ChatBotService.answer(message);
        System.out.println("MSG From Server : " + replayMessage);
        return replayMessage;
    }

    @OnClose
    public void handleClose() {
        System.out.println("End Connection ...");
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

}
