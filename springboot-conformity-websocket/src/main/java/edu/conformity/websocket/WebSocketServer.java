package edu.conformity.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * WebSocket服务
 * @author hejianliang
 *
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

	// 记录当前在线连接数
	private static int onlineCount = 0;
	
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户id
	private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>();
	
	// 会话
	private Session session;
	
	// 当前发消息的人员编号
	private String userId = "";
	
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	
	public static synchronized void addOnlineCount() {
		onlineCount++;
	}
	
	public static synchronized void subOnlineCount() {
		onlineCount--;
	}
	
	/**
	 * 连接建立成功调用的方法
	 * @param param
	 * @param session
	 */
	@OnOpen
	public void onOpen(
			@PathParam(value = "userId") String param,
			Session session
			) {
		userId = param; // 接收到发送消息人员的编号
		this.session = session;
		webSocketSet.put(param, this); // 加入线程安全map
		addOnlineCount(); // 在线人数+1
		LOG.info("用户id:[" + param + "]加入连接，当前在线人数为:[" + getOnlineCount() + "]");
	}
	
	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose() {
		if (!userId.equals("")) {
			webSocketSet.remove(userId); // 根据用户id从map中删除
			subOnlineCount(); // 在线人数-1
			LOG.info("用户id:[" + userId + "]关闭连接，当前在线人数为:[" + getOnlineCount() + "]");
		}
	}
	
	/**
	 * 发送消息
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		LOG.info("来自客户端的消息:[" + message +"]");
		String[] split = message.split(",");
		// 要发送人的用户id
		String sendUserId = split[1];
		// 发送的消息
		String sendMessage = split[0];
		// 给指定用户发消息
		sendToUser(sendUserId, sendMessage);
	}
	
	/**
	 * 给指定用户发送消息
	 * @param sendUserId
	 * @param message
	 */
	public void sendToUser(String sendUserId, String message) {
		try {
			if (webSocketSet.get(sendUserId) != null) {
				webSocketSet.get(sendUserId).sendMessage("[" + userId + "]给我发来消息，消息内容：[" + message + "]");
			} else {
				if (webSocketSet.get(userId) != null) {
					webSocketSet.get(sendUserId).sendMessage("用户[" + sendUserId + "]已离线，未接收到你的消息");
				}
				LOG.error("消息接收人[" + sendUserId + "]已离线");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		LOG.error("发生错误");
		error.printStackTrace();
	}
	
	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		// 同步发送
		this.session.getBasicRemote().sendText(message);
		// 异步发送
		//this.session.getAsyncRemote().sendText(message);
	}
	
	/**
	 * 管理员发送消息
	 * @param sendUserId
	 * @param message
	 */
	public void systemSendToUser(String sendUserId, String message) {
		try {
            if (webSocketSet.get(sendUserId) != null) {
                webSocketSet.get(sendUserId).sendMessage("系统给我发来消息，消息内容为 --->> " + message);
                LOG.info("系统给用户 {} 发送了消息 --->> {}", sendUserId, message);
            } else {
                LOG.error("消息接受人:" + sendUserId + "已经离线！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 给所有人发送消息
	 * @param sendMessage
	 */
	public void systemSendAllUser(String sendMessage) {
        //遍历HashMap
        for (String sendUserId : webSocketSet.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!userId.equals(sendUserId)) {
                    webSocketSet.get(sendUserId).sendMessage("系统给我发来消息，消息内容为 --->> " + sendMessage);
                    LOG.info("系统给用户 {} 发送了消息 --->> {}", sendUserId, sendMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
}
