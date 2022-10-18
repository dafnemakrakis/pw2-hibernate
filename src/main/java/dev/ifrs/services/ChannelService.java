package dev.ifrs.services;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.ifrs.entity.Channel;
import dev.ifrs.entity.User;

@Path("/channel")
@Transactional
public class ChannelService {
    
    @GET
    @Path("/add/{hash}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Channel add(@PathParam("hash") String hash) {

        Channel channel = new Channel();

        channel.setHash(hash);
        channel.persist();

        return channel;
    }
    
    @GET
    @Path("/add/{channelId}/{userId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public User add(@PathParam("channelId") Long channelId, @PathParam("userId") Long userId) {

        Channel channel = Channel.findById(channelId);
        User user = User.findById(userId);

        channel.addUser(user);
        user.addChannel(channel);

        return user;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Channel> list() {
        
        return Channel.listAll();
    }
}
