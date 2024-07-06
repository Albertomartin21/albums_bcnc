package bcnc.albums.controller

import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.dto.AlbumDTO
import bcnc.albums.service.AlbumService
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
class AlbumController (private val albumService: AlbumService) {

    @Operation(summary = "Get all albums")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a list from all albums", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<Album>::class))]),
    ])
        @GetMapping("/albums")
    fun getAllAlbum(): List<Album> {
        return albumService.getAlbums()
    }
    @Operation(summary = "Get album by ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found the album", content = [Content(mediaType = "application/json", schema = Schema(implementation = Album::class))]),
        ApiResponse(responseCode = "400", description = "Invalid id supplied", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Album not found", content = [Content()])
    ])
    @GetMapping("/albums/{id}")
    fun getAlbumById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(albumService.getAlbumById(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get a album with photos")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found the photos from album", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<AlbumDTO>::class))]),
        ApiResponse(responseCode = "400", description = "Invalid id supplied", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Album not found", content = [Content()])
    ])
    @GetMapping("/albums/{id}/photos")
    fun getAllPhotosFromAlbum(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            return ResponseEntity.ok(albumService.getAllPhotosFromAlbum(id))
        } catch (jsonPlaceHolderException: JsonPlaceHolderException) {
            return ResponseEntity(jsonPlaceHolderException.message, HttpStatus.NOT_FOUND)
        }
    }
    @Operation(summary = "Get all albums with photos")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found list a from album with photos", content = [Content(mediaType = "application/json", schema = Schema(implementation = Array<Album>::class))]),
    ])
    @GetMapping("/albums/photos")
    fun getAllPhotosFromAlbum(): List<AlbumDTO> {
        return albumService.getAllAlbumAndPhotos()
    }
}