package com.honeymoon.persistence.services;

import com.honeymoon.events.albums.*;

public interface AlbumPersistenceService {
	
	AllAlbumsEvent requestAllAlbums(RequestAllAlbumsEvent requestAllAlbumsEvent);
	AlbumDetailsEvent requestAlbumDetails(RequestAlbumDetailsEvent requestAlbumDetailsEvent);
	AlbumCreatedEvent createAlbum(CreateAlbumEvent createAlbumEvent);
	AlbumUpdatedEvent updateAlbum(UpdateAlbumEvent updateAlbumEvent);
	AlbumDeletedEvent deleteAlbum(DeleteAlbumEvent deleteAlbumEvent);
}
