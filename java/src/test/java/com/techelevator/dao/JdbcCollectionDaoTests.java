package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcCollectionDaoTests extends BaseDaoTests{

    private static final Collection COLLECTION_1 = new Collection(2, "testCollection1", 4, true);
    private static final Collection COLLECTION_2 = new Collection(3, "testCollection2", 5, false);
    private static final Collection COLLECTION_3 = new Collection(4, "testCollection3", 6, true);

    private Collection testCollection;
    private ComicDTO testComicDTO;

    private JdbcCollectionDao sut;

    @Before
    public void setup() {
        sut = new JdbcCollectionDao((JdbcTemplate) dataSource);
        testCollection = new Collection(0, "Test Collection", 99, true);
    }

    @Test
    public void getCollectionById_returns_correct_collection_for_id(){
        Collection collection = sut.getCollectionById(2);
        assertCollectionsMatch(COLLECTION_1, collection);


        collection = sut.getCollectionById(3);
        assertCollectionsMatch(COLLECTION_2, collection);
    }

    @Test
    public void getCollectionById_returns_null_when_id_not_found() {
        Collection collection = sut.getCollectionById(999);
        Assert.assertNull(collection);
    }

    @Test
    public void getCollectionByUserId_returns_correct_collection_for_id(){
        List<Collection> collections = sut.getCollectionsByUserId(4);
        Assert.assertEquals(2, collections.size());
        assertCollectionsMatch(COLLECTION_1, collections.get(0));
        assertCollectionsMatch(COLLECTION_3, collections.get(0));

        collections = sut.getCollectionsByUserId(5);
        Assert.assertEquals(3, collections.size());
        assertCollectionsMatch(COLLECTION_2, collections.get(0));
    }

    public void updated_collection_has_expected_values_when_retrieved(){
        Collection collectionToUpdate = sut.getCollectionById(2);

        collectionToUpdate.setCollectionName("Hello I am updated");
        collectionToUpdate.setUserId(4);
        collectionToUpdate.setPrivate(false);
        sut.updateCollection(collectionToUpdate);

        Collection retrieveCollection = sut.getCollectionById(2);
        assertCollectionsMatch(collectionToUpdate, retrieveCollection);
    }

    @Test
    public void addComicToCollection_returns_expected_value(){
        sut.addComicToCollection(122, "Oh neat a comic", "imageURLTHINGY", "This description is a description", 4);

        List<Collection> collections = sut.getCollectionsByUserId(4);
        Assert.assertEquals(1, collections.size());
        assertCollectionsMatch(COLLECTION_1, collections.get(0));
    }

    private void assertCollectionsMatch(Collection expected, Collection actual) {
        Assert.assertEquals(expected.getCollectionId(), actual.getCollectionId());
        Assert.assertEquals(expected.getCollectionName(), actual.getCollectionName());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.isPrivate(), actual.isPrivate());
    }


}
