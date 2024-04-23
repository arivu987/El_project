package com.mlbeez.feeder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlbeez.feeder.model.Feed;
import com.mlbeez.feeder.repository.FeedRepository;

@Service
public class FeedService {
	
	@Autowired
	FeedRepository feedRepository;
	
	public List<Feed> getAllFeeds()
	{
		return feedRepository.findAll();
	}
	
	public Optional<Feed>getFeedById(Long id)
	{
		return feedRepository.findById(id);
	}
	
	public void deleteFeedId(Long id)
	{
		if(feedRepository.existsById(id))
		{
		 	 feedRepository.deleteById(id);
		
		}
		
	}
	
	

}
