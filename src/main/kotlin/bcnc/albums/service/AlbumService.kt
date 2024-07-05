package bcnc.albums.service

import bcnc.albums.model.Album
import bcnc.albums.model.dto.AlbumDTO


interface AlbumService {
    fun getAlbums(): List<Album>
    fun getAlbumById(id:Int): Album
    fun getAllPhotosFromAlbum(id:Int): AlbumDTO
}