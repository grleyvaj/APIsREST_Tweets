package com.example.api.twitter.interfaces.assembler;

import com.example.api.twitter.interfaces.model.TweetModel;
import com.example.api.twitter.interfaces.rest.MyTweetApiRest;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class TweetModelAssembler implements RepresentationModelAssembler<TweetModel, RepresentationModel<TweetModel>> {


    @Override
    public RepresentationModel<TweetModel> toModel(TweetModel entity) {
        entity.add(linkTo(methodOn(MyTweetApiRest.class).getTweetById(entity.getId())).withSelfRel());
        return entity;
    }
}
