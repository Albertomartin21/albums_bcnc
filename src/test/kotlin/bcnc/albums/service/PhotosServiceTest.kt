package bcnc.albums.service

import bcnc.albums.model.Photo
import bcnc.albums.service.impl.PhotosServiceImpl
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

@ExtendWith(MockitoExtension::class)
class PhotosServiceTest {
    private lateinit var photosService: PhotosService
    @Mock
    private lateinit var jsonPlaceholderService: JsonPlaceholderService

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setUp() {
        photosService = PhotosServiceImpl(jsonPlaceholderService)
    }


    @Test
    fun getAllPhotosTest(){
        val json = javaClass.getResource("/photos.json")!!.readText()
        val listPhotos: List<Photo> = mapper.readValue(json)
        Mockito.`when`(jsonPlaceholderService.getAllPhotos()).thenReturn(listPhotos)

        val result = photosService.getPhotos()

        verify(jsonPlaceholderService).getAllPhotos()
        assertEquals(9,result.size)
        assertEquals(listPhotos,result)

    }

    @Test
    fun getPhotosWithEmptyResultTest(){
        Mockito.`when`(jsonPlaceholderService.getAllPhotos()).thenReturn(listOf())

        val result = photosService.getPhotos()

        verify(jsonPlaceholderService).getAllPhotos()
        assertEquals(0,result.size)
        assertEquals(listOf(),result)
    }
}