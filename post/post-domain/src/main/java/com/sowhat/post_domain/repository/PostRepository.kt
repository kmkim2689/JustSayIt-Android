package com.sowhat.post_domain.repository

import com.sowhat.common.model.Resource
import com.sowhat.network.model.ResponseBody
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PostRepository {
    suspend fun submitPost(
        accessToken: String,
        memberId: Long,
        storyInfo: RequestBody,
        storyImg: List<MultipartBody.Part?>?
    ): Resource<Unit?>
}