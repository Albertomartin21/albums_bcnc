package bcnc.albums.controller

import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.dto.AlbumDTO
import bcnc.albums.service.AlbumService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class AlbumController (private val albumService: AlbumService) {


    @GetMapping("/albums")
    fun getAllAlbum(): List<Album> {
        return albumService.getAlbums()
    }

    @GetMapping("/albums/{id}")
    fun getAlbumById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(albumService.getAlbumById(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/albums/{id}/photos")
    fun getAllPhotosFromAlbum(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(albumService.getAllPhotosFromAlbum(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/albums/photos")
    fun getAllPhotosFromAlbum(): List<AlbumDTO> {
        return albumService.getAllAlbumAndPhotos()
    }
}