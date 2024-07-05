package bcnc.albums.service

import bcnc.albums.client.JsonPlaceholderFeignClient
import bcnc.albums.exception.JsonPlaceHolderException
import bcnc.albums.model.Album
import bcnc.albums.model.Photo
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.service.impl.JsonPlaceholderServiceImpl
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import feign.FeignException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.cloud.openfeign.FeignClient
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ExtendWith(MockitoExtension::class)
class JsonPlaceholderServiceTest {


    private lateinit var jsonPlaceholderService: JsonPlaceholderService

    @Mock
    private lateinit var jsonPlaceholderFeignClient: JsonPlaceholderFeignClient

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setUp() {
        jsonPlaceholderService = JsonPlaceholderServiceImpl(jsonPlaceholderFeignClient)
    }


    @Test
    fun getAllAlbumsTest(){
        val json = javaClass.getResource("/albums.json")!!.readText()
        val listAlbums: List<Album> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderFeignClient.getAllAlbumsFromJsonPlaceholder()).thenReturn(listAlbums)

        val result = jsonPlaceholderService.getAllAlbums()

        verify(jsonPlaceholderFeignClient).getAllAlbumsFromJsonPlaceholder()
        assertEquals(10,result.size)
        assertEquals(listAlbums,result)
    }

    @Test
    fun getAlbumWithEmptyResultTest(){
        Mockito.`when`(jsonPlaceholderFeignClient.getAllAlbumsFromJsonPlaceholder()).thenReturn(listOf())

        val result = jsonPlaceholderService.getAllAlbums()

        verify(jsonPlaceholderFeignClient).getAllAlbumsFromJsonPlaceholder()
        assertEquals(result.size,0)
        assertEquals(result,listOf())
    }

    @Test
    fun getAllPhotoTest(){
        val json = javaClass.getResource("/photos.json")!!.readText()
        val listPhoto: List<Photo> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderFeignClient.getAllPhotosFromJsonPlaceholder()).thenReturn(listPhoto)

        val result = jsonPlaceholderService.getAllPhotos()

        verify(jsonPlaceholderFeignClient).getAllPhotosFromJsonPlaceholder()
        assertEquals(9,result.size)
        assertEquals(listPhoto,result)
    }

    @Test
    fun getPhotosWithEmptyResultTest(){
        Mockito.`when`(jsonPlaceholderFeignClient.getAllPhotosFromJsonPlaceholder()).thenReturn(listOf())

        val result = jsonPlaceholderService.getAllPhotos()

        verify(jsonPlaceholderFeignClient).getAllPhotosFromJsonPlaceholder()
        assertEquals(result.size,0)
        assertEquals(result,listOf())
    }

    @Test
    fun getAlbumsByIdTest(){
        val json = javaClass.getResource("/albums.json")!!.readText()
        val listAlbum: List<Album> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderFeignClient.getAlbumByIdFromJsonPlaceholder(1)).thenReturn(listAlbum.get(0))

        val result = jsonPlaceholderService.getAlbumById(1)

        verify(jsonPlaceholderFeignClient).getAlbumByIdFromJsonPlaceholder(1)
        assertNotNull(result)
        assertEquals(1,result.getUserId())
        assertEquals(1,result.getId())
        assertEquals("quidem molestiae enim",result.getTitle())
    }


    @Test
    fun getPhotoByIdTest(){
        val json = javaClass.getResource("/photos.json")!!.readText()
        val listPhoto: List<Photo> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderFeignClient.getPhotoByIdFromJsonPlaceholder(1)).thenReturn(listPhoto.get(0))

        val result = jsonPlaceholderService.getPhotoById(1)

        verify(jsonPlaceholderFeignClient).getPhotoByIdFromJsonPlaceholder(1)
        assertNotNull(result)
        assertEquals(1,result.getId())
        assertEquals(1,result.getAlbumId())
        assertEquals("accusamus beatae ad facilis cum similique qui sunt",result.getTitle())
        assertEquals("https://via.placeholder.com/600/92c952",result.getUrl())
        assertEquals("https://via.placeholder.com/150/92c952",result.getThumbnailUrl())
    }

    @Test
    fun getAllPhotoByAlbumIdTest(){
        val json = javaClass.getResource("/photos.json")!!.readText()
        val listPhotos: List<Photo> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderFeignClient.getAllPhotosFromAlbumFromJsonPlaceholder(1)).thenReturn(listPhotos)

        val result = jsonPlaceholderService.getAllPhotosByAlbumId(1)

        assertEquals(9,result.size)
        assertEquals(listPhotos,result)
    }
}