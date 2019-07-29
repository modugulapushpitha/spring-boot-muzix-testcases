package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistException;
    public boolean deleteTrackById(int id) throws TrackNotFoundException;
    public boolean updateTrack(Track track);
    public Track getTrackById(int id);
    public List<Track> getAllTracks();
}
