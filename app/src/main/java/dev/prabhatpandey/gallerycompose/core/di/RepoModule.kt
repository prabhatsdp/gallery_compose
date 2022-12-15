package dev.prabhatpandey.gallerycompose.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prabhatpandey.gallerycompose.features.gallery.data.repo.ImageRepositoryImpl
import dev.prabhatpandey.gallerycompose.features.gallery.domain.repo.ImageRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindImageRepository(repo: ImageRepositoryImpl) : ImageRepository
}