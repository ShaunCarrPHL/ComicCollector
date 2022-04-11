package com.techelevator.dao;

import com.techelevator.model.Publisher;

import java.util.List;

public interface PublisherDao {
    List<Publisher> listAllPublishers();
    Publisher getPublisherByName(String publisherName);
    Publisher createPublisher(Publisher publisher);
    Publisher getPublisherById(int publisherId);
}
