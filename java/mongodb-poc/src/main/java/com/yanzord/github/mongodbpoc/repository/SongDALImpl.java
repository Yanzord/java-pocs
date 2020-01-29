package com.yanzord.github.mongodbpoc.repository;

import com.yanzord.github.mongodbpoc.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDALImpl implements SongDAL {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Song> getAllSongs() {
        return mongoTemplate.findAll(Song.class);
    }

    @Override
    public Song getSongById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return mongoTemplate.findOne(query, Song.class);
    }

    @Override
    public Song addNewSong(Song song) {
        return mongoTemplate.save(song);
    }

    @Override
    public List<Song> getSongByTitle(String songTitle) {
        Query query = new Query();
        query.addCriteria(Criteria.where("songTitle").is(songTitle));

        return mongoTemplate.find(query, Song.class);
    }
}
