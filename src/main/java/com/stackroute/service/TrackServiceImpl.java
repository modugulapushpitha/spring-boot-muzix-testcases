package com.stackroute.service;
import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrackServiceImpl implements TrackService{
    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){

        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistException("track already exists");
        }
        Track saveTrack= trackRepository.save(track);
        if(saveTrack==null)
            throw new TrackAlreadyExistException(" track already exists");
        return saveTrack;
    }
    @Override
    public boolean deleteTrackById(int id) throws TrackNotFoundException {
        if (getTrackById(id)== null) {
            throw new TrackNotFoundException("The track not found");
        } else {
        trackRepository.delete(getTrackById(id));
            return true;
        }
    }
    @Override
    public boolean updateTrack(Track track) {
        Track updatetrack = trackRepository.findById(track.getId()).get();
        if (updatetrack != null) {
            updatetrack.setId(track.getId());
            updatetrack.setName(track.getName());
            updatetrack.setComment(track.getComment());
            trackRepository.save(updatetrack);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Track getTrackById(int id) {
        Optional<Track> trackbyid=trackRepository.findById(id);
        if(trackbyid.isPresent()){
            return trackbyid.get();

        }
        else {
            return null;
        }
    }
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
}
