package bcnc.albums.service.impl

import bcnc.albums.service.AlbumService
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.model.Album
import org.springframework.stereotype.Service

@Service
class AlbumServiceImpl(private val jsonPlaceholderService: JsonPlaceholderService) : AlbumService {


    override fun getAlbums(): List<Album> {
        return jsonPlaceholderService.getAllAlbums()
    }

    override fun getAlbumById(id:Int): Album {
        return jsonPlaceholderService.getAlbumById(id)
    }

}