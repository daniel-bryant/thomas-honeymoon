package com.honeymoon.persistence.services;

import java.util.LinkedList;
import java.util.List;

import com.honeymoon.events.albums.AlbumCreatedEvent;
import com.honeymoon.events.albums.AlbumDeletedEvent;
import com.honeymoon.events.albums.AlbumDetails;
import com.honeymoon.events.albums.AlbumDetailsEvent;
import com.honeymoon.events.albums.AlbumUpdatedEvent;
import com.honeymoon.events.albums.AllAlbumsEvent;
import com.honeymoon.events.albums.CreateAlbumEvent;
import com.honeymoon.events.albums.DeleteAlbumEvent;
import com.honeymoon.events.albums.RequestAlbumDetailsEvent;
import com.honeymoon.events.albums.RequestAllAlbumsEvent;
import com.honeymoon.events.albums.UpdateAlbumEvent;
import com.honeymoon.persistence.domain.Album;
import com.honeymoon.persistence.repository.AlbumRepository;

public class AlbumPersistenceEventHandler implements AlbumPersistenceService{
	
	private AlbumRepository albumRepository;
	
	public AlbumPersistenceEventHandler(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	@Override
	public AllAlbumsEvent requestAllAlbums(RequestAllAlbumsEvent requestAllAlbumsEvent) {
		List<Album> albumList = albumRepository.findAll();
		
		List<AlbumDetails> details = new LinkedList<AlbumDetails>();
		
		for(Album album: albumList) {
			details.add(album.toAlbumDetails());
		}
		
		return new AllAlbumsEvent(details);
	}

	@Override
	public AlbumDetailsEvent requestAlbumDetails(RequestAlbumDetailsEvent requestAlbumDetailsEvent) {
		Album album = albumRepository.findById(requestAlbumDetailsEvent.getId());
		
		if (album == null) {
			return AlbumDetailsEvent.notFound(requestAlbumDetailsEvent.getId());
		}
		
		return new AlbumDetailsEvent(
				requestAlbumDetailsEvent.getId(),
				album.toAlbumDetails());
	}

	@Override
	public AlbumCreatedEvent createAlbum(CreateAlbumEvent createAlbumEvent) {
		Album album = albumRepository.save(
				Album.fromAlbumDetails(createAlbumEvent.getDetails()));
		
		return new AlbumCreatedEvent(album.getId(), album.toAlbumDetails());
	}

	@Override
	public AlbumUpdatedEvent updateAlbum(UpdateAlbumEvent updateAlbumEvent) {
		Album album = albumRepository.findById(updateAlbumEvent.getId());
		
		if (album == null) {
			return AlbumUpdatedEvent.notFound(updateAlbumEvent.getId());
		}
		
		album.setTitle(updateAlbumEvent.getDetails().getTitle());
		album.setDescription(updateAlbumEvent.getDetails().getDescription());
		album.setPhotos(updateAlbumEvent.getDetails().getPhotos());
		
		album = albumRepository.save(album);
		
		return new AlbumUpdatedEvent(album.getId(), album.toAlbumDetails());
	}
	
	@Override
	public AlbumDeletedEvent deleteAlbum(DeleteAlbumEvent deleteAlbumEvent) {
		Album album = albumRepository.findById(deleteAlbumEvent.getKey());
		  
		  if (album == null) {
		      return AlbumDeletedEvent.notFound(deleteAlbumEvent.getKey());
		    }

		  albumRepository.delete(deleteAlbumEvent.getKey());

		  return new AlbumDeletedEvent(deleteAlbumEvent.getKey(), album.toAlbumDetails());
	}

}
