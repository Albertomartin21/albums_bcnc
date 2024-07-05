package com.example.pruebatecnicakotlin.service

import bcnc.albums.client.JsonPlaceholderFeignClient
import bcnc.albums.model.Album
import bcnc.albums.service.JsonPlaceholderService
import bcnc.albums.service.impl.JsonPlaceholderServiceImpl
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals


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

        assertEquals(10,result.size,)
        assertEquals(listAlbums,result)
    }

    @Test
    fun getAlbumWithEmptyResultTest(){
        Mockito.`when`(jsonPlaceholderFeignClient.getAllAlbumsFromJsonPlaceholder()).thenReturn(listOf())

        val result = jsonPlaceholderService.getAllAlbums()

        assertEquals(result.size,0)
        assertEquals(result,listOf())
    }



}