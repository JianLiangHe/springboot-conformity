package edu.conformity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.conformity.websocket.WebSocketServer;

/**
 * 消息Controller
 * @author hejianliang
 *
 */
@RestController
@RequestMapping("/message/")
public class MessageController {

	/**
	 * 系统给指定用户发送消息
	 * @param userId
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("systemSendToUser")
	public String systemSendToUser(String userId, String message) throws Exception {
		if (userId == "" || userId == null) {
            return "发送用户id不能为空";
        }
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        new WebSocketServer().systemSendToUser(userId, message);
        return "发送成功！";
	}
	
	/**
	 * 系统给所有用户发送消息
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("systemSendAllUser")
	public String systemSendAllUser(String message) throws Exception {
		if (message == "" || message == null) {
			return "发送信息不能为空";
        }
        new WebSocketServer().systemSendAllUser(message);
        return "发送成功！";
	}
	
}
