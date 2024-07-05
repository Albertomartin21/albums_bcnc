package bcnc.albums.service

import bcnc.albums.model.Album

interface JsonPlaceholderService {
    fun getAllAlbums(): List<Album>
}