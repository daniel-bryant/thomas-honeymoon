package com.honeymoon.core.services;

import com.honeymoon.events.albums.*;

public interface AlbumService {
	AllAlbumsEvent requestAllAlbums(RequestAllAlbumsEvent requestAllAlbumsEvent);
	AlbumDetailsEvent requestAlbumDetails(RequestAlbumDetailsEvent requestAlbumDetailsEvent);
	AlbumCreatedEvent createAlbum(CreateAlbumEvent createAlbumEvent);
	AlbumUpdatedEvent updateAlbum(UpdateAlbumEvent updateAlbumEvent);
	AlbumDeletedEvent deleteAlbum(DeleteAlbumEvent deleteAlbumEvent);
}
