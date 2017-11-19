package com.ralap.chat.controller;

import com.ralap.chat.domain.ChatMessage;

import com.ralap.chat.domain.UserModel;
import com.ralap.chat.service.TLService;
import com.ralap.chat.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ChatController
 *
 * @author: ralap
 * @date: created at 2017/11/12 12:55
 */
@Controller
@Log4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private TLService tlService;

    @Autowired
    private UserService userService;

    @MessageMapping("/sendToAll")
    @SendTo("/groupChat/chat")
    public ChatMessage say(Principal principal, ChatMessage message) throws InterruptedException {

        log.info("-------------SENT START-------------");

        ChatMessage responsetCaht = new ChatMessage(principal.getName(), "All",
                message.getContent(),
                new Date(), ChatMessage.TYPE_GROUP);
        log.info("-------------SENT END-------------");

        return responsetCaht;
    }

    @MessageMapping("/sendToPoint")
    public void handleChat(Principal principal, ChatMessage message) {
        log.info("-------------SENT START-------------");

        String sender;
        String reciver;
        String content;

        if ("robot".equals(message.getReciver())) {
            sender = "robot";
            reciver = principal.getName();
            content = tlService.getInfo(message.getContent());
        } else {
            sender = principal.getName();
            reciver = message.getReciver();
            content = message.getContent();

        }

        {
            messagingTemplate
                    .convertAndSendToUser(reciver, "/pointChat/chat", content);
        }
        log.info("-------------SENT START-------------");

    }

    @RequestMapping("/chat")
    public String chat(Principal principal, HttpServletRequest request) {

        List<UserModel> all = new ArrayList<>();
        UserModel groupUser = new UserModel();
        groupUser.setName("群聊");

        UserModel robot = new UserModel();
        robot.setName("小R [机器人]");
        all.add(groupUser);
        all.add(robot);

        List<UserModel> friendList = userService.getAll();

        for (UserModel model : friendList) {
            if (principal.getName().equals(model.getName())) {
                friendList.remove(model);
            }
        }
        all.addAll(friendList);
        request.setAttribute("friendList", all);
        return "/chat";

    }
}
