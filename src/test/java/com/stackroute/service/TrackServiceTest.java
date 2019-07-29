package com.stackroute.service;
import com.stackroute.domain.Track;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.exception.TrackAlreadyExistException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    Track track;
    @Mock
    TrackRepository trackRepository;
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("believer");
        track.setComment("Imaginer");
        list = new ArrayList<>();
        list.add(track);

    }
  @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistException{

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        Assert.assertEquals(track,savedUser);
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistException {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track saveTrack = trackService.saveTrack(track);
        System.out.println("savedUser" + saveTrack);
        Assert.assertEquals(track,saveTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void getAllTracks(){

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }
    @Test
    public void deleteTrack()throws TrackNotFoundException {
        trackRepository.deleteById(track.getId());
        verify(trackRepository).deleteById(anyInt());
    }

}
