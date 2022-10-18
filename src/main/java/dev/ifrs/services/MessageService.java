package dev.ifrs.services;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.ifrs.entity.Channel;
import dev.ifrs.entity.Message;
import dev.ifrs.entity.User;

@Path("/message")
@Transactional
public class MessageService {
    
    @GET
    @Path("/add/{userId}/{channelId}/{text}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Channel add(
        @PathParam("userId") Long userId,
        @PathParam("channelId") Long channelId,
        @PathParam("text") String text
    ) {

        User user = User.findById(userId);
        Channel channel = Channel.findById(channelId);
        Message message = new Message();

        message.setText(text);
        message.persist();

        user.addMessage(message);

        return channel;
    }

}
