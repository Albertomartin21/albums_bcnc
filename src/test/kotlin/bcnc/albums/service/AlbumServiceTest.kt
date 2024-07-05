package bcnc.albums.service

import bcnc.albums.model.Album
import bcnc.albums.service.AlbumService
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.service.impl.AlbumServiceImpl
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ExtendWith(MockitoExtension::class)
class AlbumServiceTest {

    private lateinit var albumService: AlbumService
    @Mock
    private lateinit var jsonPlaceholderService: JsonPlaceholderService

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setUp() {
        albumService = AlbumServiceImpl(jsonPlaceholderService)
    }


    @Test
    fun getAllAlbumsTest(){
        val json = javaClass.getResource("/albums.json")!!.readText()
        val listAlbum: List<Album> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderService.getAllAlbums()).thenReturn(listAlbum)

        val result = albumService.getAlbums()

        verify(jsonPlaceholderService).getAllAlbums()
        assertEquals(10,result.size)
        assertEquals(listAlbum,result)

    }

    @Test
    fun getAlbumsWithEmptyResultTest(){
        Mockito.`when`(jsonPlaceholderService.getAllAlbums()).thenReturn(listOf())

        val result = albumService.getAlbums()

        verify(jsonPlaceholderService).getAllAlbums()
        assertEquals(0,result.size)
        assertEquals(listOf(),result)
    }

    @Test
    fun getAlbumsByIdTest(){
        val json = javaClass.getResource("/albums.json")!!.readText()
        val listAlbum: List<Album> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderService.getAlbumById(1)).thenReturn(listAlbum.get(0))

        val result = albumService.getAlbumById(1)

        verify(jsonPlaceholderService).getAlbumById(1)
        assertNotNull(result)
        assertEquals(1,result.getUserId())
        assertEquals(1,result.getId())
        assertEquals("quidem molestiae enim",result.getTitle())
    }



}