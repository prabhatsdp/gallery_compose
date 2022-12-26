package dev.prabhatpandey.gallerycompose.core.data.dto.image


import com.google.gson.annotations.SerializedName
import dev.prabhatpandey.gallerycompose.features.gallery.domain.ImageModel

data class ImageDto(
    @SerializedName("alt_description")
    val altDescription: Any,
    @SerializedName("blur_hash")
    val blurHash: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    @SerializedName("description")
    val description: Any?,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("promoted_at")
    val promotedAt: Any,
    @SerializedName("sponsorship")
    val sponsorship: Sponsorship,
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("user")
    val user: User,
    @SerializedName("width")
    val width: Int
) {

    fun asDomainModel() : ImageModel {
        return ImageModel(
            id = id,
            likes = likes,
            thumbUrl = urls.thumb,
            fullUrl = urls.full,
            description = description?.toString() ?: "No Description"
        )
    }
}