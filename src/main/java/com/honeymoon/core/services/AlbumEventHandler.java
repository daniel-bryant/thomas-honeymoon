package com.honeymoon.core.services;

import com.honeymoon.events.albums.*;
import com.honeymoon.persistence.services.AlbumPersistenceService;

public class AlbumEventHandler implements AlbumService {
	
	private AlbumPersistenceService albumPersistenceService;
	
	public AlbumEventHandler(AlbumPersistenceService albumPersistenceService) {
		this.albumPersistenceService = albumPersistenceService;
	}

	@Override
	public AllAlbumsEvent requestAllAlbums(RequestAllAlbumsEvent requestAllAlbumsEvent) {
		return albumPersistenceService.requestAllAlbums(requestAllAlbumsEvent);
	}

	@Override
	public AlbumDetailsEvent requestAlbumDetails(RequestAlbumDetailsEvent requestAlbumDetailsEvent) {
		return albumPersistenceService.requestAlbumDetails(requestAlbumDetailsEvent);
	}

	@Override
	public AlbumCreatedEvent createAlbum(CreateAlbumEvent createAlbumEvent) {
		return albumPersistenceService.createAlbum(createAlbumEvent);
	}

	@Override
	public AlbumUpdatedEvent updateAlbum(UpdateAlbumEvent updateAlbumEvent) {
		return albumPersistenceService.updateAlbum(updateAlbumEvent);
	}
	
	@Override
	public AlbumDeletedEvent deleteAlbum(DeleteAlbumEvent deleteAlbumEvent) {
		return albumPersistenceService.deleteAlbum(deleteAlbumEvent);
	}

}
