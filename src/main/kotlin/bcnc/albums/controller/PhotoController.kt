package bcnc.albums.controller

import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import bcnc.albums.model.dto.AlbumDTO
import bcnc.albums.service.PhotosService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PhotoController(private val photosService: PhotosService) {

    @Operation(summary = "Get all photos")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a list from all photos", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<Album>::class))]),
    ])
    @GetMapping("/photos")
    fun getAllPosts(): List<Photo> {
        return photosService.getPhotos()
    }

    @Operation(summary = "Get photo by id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found the photo", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<AlbumDTO>::class))]),
        ApiResponse(responseCode = "400", description = "Invalid id supplied", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Photo not found", content = [Content()])
    ])
    @GetMapping("/photos/{id}")
    fun getAlbumById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(photosService.getPhotoById(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }
}