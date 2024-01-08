package com.sowhat.datastore.model

import kotlinx.coroutines.flow.Flow

interface AuthDataRepository {
    val authData: Flow<AuthData>
    suspend fun updatePlatform(platform: String)
    suspend fun updatePlatformToken(platformToken: String)
    suspend fun updateAccessToken(accessToken: String)
    suspend fun updateRefreshToken(refreshToken: String)
    suspend fun updateFcmToken(fcmToken: String)
    suspend fun updateDeviceNumber(deviceNumber: String)
    suspend fun resetData()
}

interface AppSettingsRepository {

}