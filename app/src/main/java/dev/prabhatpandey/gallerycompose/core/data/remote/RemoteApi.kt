package dev.prabhatpandey.gallerycompose.core.data.remote

import dev.prabhatpandey.gallerycompose.core.data.dto.image.ImageDto
import dev.prabhatpandey.retrofitadapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET(DataConsts.GET_PHOTOS)
    suspend fun getPhotos(
        @Query(DataConsts.CLIENT_ID_KEY) clientId: String,
        @Query(DataConsts.PAGE) page: Int,
        @Query(DataConsts.PER_PAGE) perPage: Int,
    ) : NetworkResponse<List<ImageDto>, Any>

}