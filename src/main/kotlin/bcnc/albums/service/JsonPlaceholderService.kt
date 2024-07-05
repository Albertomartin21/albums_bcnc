package bcnc.albums.service

import bcnc.albums.model.Album
import bcnc.albums.model.Photo

interface JsonPlaceholderService {
    fun getAllAlbums(): List<Album>

    fun getAllPhotos(): List<Photo>

    fun getAlbumById(id:Int): Album

    fun getPhotoById(id:Int): Photo

    fun getAllPhotosByAlbumId(id:Int): List<Photo>
}