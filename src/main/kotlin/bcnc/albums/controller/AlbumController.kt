package bcnc.albums.controller

import bcnc.albums.model.Album
import bcnc.albums.service.AlbumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AlbumController (private val albumService: AlbumService) {


    @GetMapping("/albums")
    fun getAllPosts(): List<Album> {
        return albumService.getAlbums()
    }
}