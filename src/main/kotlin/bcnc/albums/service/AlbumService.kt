package bcnc.albums.service

import bcnc.albums.model.Album


interface AlbumService {
    fun getAlbums(): List<Album>
}