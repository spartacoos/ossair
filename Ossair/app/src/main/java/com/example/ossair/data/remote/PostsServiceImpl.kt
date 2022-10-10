package com.example.ossair.data.remote

import com.example.ossair.data.remote.dto.PostRequest
import com.example.ossair.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostsServiceImpl (
    private val client: HttpClient

): PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            //client.get("http://192.168.1.212")
            client.get("http://192.168.1.147")
            //client.get{
            //println("HEREEEEEE: ${url(HttpRoutes.POSTS)}")
            //url(HttpRoutes.POSTS)
            //HttpRoutes.POSTS

            //}
        } catch(e: RedirectResponseException){
            // 3xx - responses
            println("Problem: ${e.response.status.description}")
            println("Error: ${e.response}")
            emptyList()
        } catch(e: ClientRequestException){
            // 4xx - responses
            println("Problem: ${e.response.status.description}")
            println("Error: ${e.response}")
            emptyList()
        } catch(e: ServerResponseException){
            // 5xx - responses
            println("Problem: ${e.response.status.description}")
            println("Error: ${e.response.status}")
            emptyList()
        } catch(e: Exception){
            // general issues
            println("Problem: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>{
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch(e: RedirectResponseException){
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException){
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException){
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception){
            // general issues
            println("Error: ${e.message}")
            null
        }
    }
}