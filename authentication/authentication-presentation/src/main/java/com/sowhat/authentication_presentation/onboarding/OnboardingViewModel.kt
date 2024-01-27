package com.sowhat.authentication_presentation.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sowhat.authentication_domain.model.SignIn
import com.sowhat.authentication_domain.use_case.UserSignInUseCase
import com.sowhat.common.model.Resource
import com.sowhat.common.model.SignInEvent
import com.sowhat.common.model.UiState
import com.sowhat.datastore.AuthDataRepository
import com.sowhat.network.util.toBearerToken
import com.sowhat.authentication_presentation.common.Platform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val signInUseCase: UserSignInUseCase,
    private val authDataStore: AuthDataRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<SignIn>> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState<SignIn>>
        get() = _uiState

    private val _uiEvent = Channel<SignInEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val tokens = viewModelScope.async {
        authDataStore.authData.first()
    }

    fun signIn(
        platform: Platform,
        derivedPlatformToken: String
    ) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)

        val platformToken = tokens.await().platformToken
        Log.i("OnboardingScreen", "platformToken : $platformToken")

        if (platformToken.isNullOrBlank()) {
            Log.i("OnboardingScreen", "platformToken is null : $platformToken")
            authDataStore.apply {
                updatePlatform(platform = platform.title)
                updatePlatformToken(platformToken = derivedPlatformToken)
            }
        }

        // 만약 액세스 토큰이 없을 때 갱신되었을 수 있기 때문에 여기에 새로 선언
        val newPlatformToken = authDataStore.authData.first().platformToken
        val signInData = signInUseCase(newPlatformToken)

        consumeResources(signInData)
    }

    private suspend fun consumeResources(signInData: Resource<SignIn>) {
        when (signInData) {
            is Resource.Success -> {
                val data = signInData.data
                consumeSuccessResources(data, signInData)
            }
            is Resource.Error -> {
                consumeErrorResources(signInData)
            }
        }
    }

    private suspend fun consumeErrorResources(signInData: Resource<SignIn>) {
        terminateLoading(data = signInData.data)
        _uiEvent.send(
            SignInEvent.Error(message = signInData.message ?: "예상치 못한 오류가 발생했습니다.")
        )
    }

    private suspend fun consumeSuccessResources(
        data: SignIn?,
        signInData: Resource<SignIn>
    ) {
        if (data?.memberId != null && data.isJoined) {
            data.accessToken?.let {
                authDataStore.updateAccessToken(it)
            }
            authDataStore.updateMemberId(data.memberId!!)
            terminateLoading(data = signInData.data)
            _uiEvent.send(SignInEvent.NavigateToMain)
            return
        }

        if (data?.isJoined == false) {
            terminateLoading(data = signInData.data)
            _uiEvent.send(SignInEvent.NavigateToSignUp)
            return
        }

        terminateLoading(data = signInData.data)
        _uiEvent.send(SignInEvent.Error(message = "서버로부터 토큰을 받아올 수 없습니다."))
    }

    private fun terminateLoading(data: SignIn?) {
        _uiState.value = _uiState.value.copy(isLoading = false, data = data)
    }
}