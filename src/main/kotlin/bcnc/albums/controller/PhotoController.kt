package bcnc.albums.controller

import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Photo
import bcnc.albums.service.PhotosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PhotoController(private val photosService: PhotosService) {

    @GetMapping("/photos")
    fun getAllPosts(): List<Photo> {
        return photosService.getPhotos()
    }

    @GetMapping("/photos/{id}")
    fun getAlbumById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(photosService.getPhotoById(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }
}