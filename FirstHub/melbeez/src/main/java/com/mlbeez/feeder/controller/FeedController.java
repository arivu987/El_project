package com.mlbeez.feeder.controller;

import com.mlbeez.feeder.model.Feed;
import com.mlbeez.feeder.repository.FeedRepository;
import com.mlbeez.feeder.service.FeedService;
import com.mlbeez.feeder.service.MediaStoreService;
import com.mlbeez.feeder.service.awss3.S3Service;

import com.mlbeez.feeder.service.exception.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController

public class FeedController {

    @Autowired
    S3Service Services;

    @Autowired
    MediaStoreService service;

    @Autowired
    FeedService feedService;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }


    @DeleteMapping("/feeds/id/{id}")
    public ResponseEntity<?> deleteFeedById(@PathVariable("id") Long id) {


        Optional<Feed> optionalFeed = feedService.getFeedById(id);

        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            if (!feed.getImg().isEmpty()) {
                service.getMediaStoreService().deleteFile(feed.getImg());
            }
            feedService.deleteFeedId(id);
            return ResponseEntity.ok().build(); // Return 200 OK if deletion is successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if feed with given ID doesn't exist
        }
    }

    @GetMapping("/feeds")
    public List<Feed> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @GetMapping("/id/{id}")
    public String handleGet(@PathVariable String id) {

        return service.getMediaStoreService().getFileLocation(id + ".jpeg");
    }


//	@GetMapping("image/all")
//	public List<String> getAllImageFileKeys() {
//
//
//		return Services.getAllImageFileKeys();
//	}

//	@GetMapping("/feeds/id/{id}")
//	public ResponseEntity<Feed> getIdFeeds(@PathVariable("id") Long id) {
//		Optional<Feed> feedId = feedService.getIdFeeds(id);
//		if (feedId.isPresent()) {
//			return new ResponseEntity<>(feedId.get(), HttpStatus.OK);
//		} else {
//
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//		}
//	}


}
