package com.sowhat.presentation.configuration

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sowhat.designsystem.R
import com.sowhat.designsystem.component.AppBar
import com.sowhat.designsystem.component.DefaultTextField
import com.sowhat.designsystem.theme.Gray200
import com.sowhat.designsystem.theme.Gray400
import com.sowhat.designsystem.common.COMPLETE
import com.sowhat.designsystem.common.CONFIG_ID_PLACEHOLDER
import com.sowhat.designsystem.common.CONFIG_ID_TITLE
import com.sowhat.designsystem.component.ProfileImage
import com.sowhat.presentation.navigation.navigateToMain

@Composable
fun UserConfigRoute(
    viewModel: UserConfigViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val maxLength = 12

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // the returned result from the file picker : Uri
            // to display -> read the uri and convert it into a bitmap -> Coil Image Library
            viewModel.hasImage = uri != null
            viewModel.imageUri = uri
        }
    )

    UserConfigScreen(
        id = viewModel.id,
        navController = navController,
        isValid = viewModel.isValid,
        onIdChange = { changedId ->
            if (changedId.length <= maxLength) viewModel.id = changedId
        },
        onProfileClick = {
            imagePicker.launch("image/*")
        },
        profileUri = viewModel.imageUri
    )
}

@Composable
fun UserConfigScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    id: String,
    isValid: Boolean,
    profileUri: Uri?,
    onIdChange: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = null,
                navigationIcon = null,
                actionText = COMPLETE,
                isValid = isValid,
                onActionTextClick = {
                    navController.navigateToMain()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ProfileImage(
                profileUri = profileUri ?: "https://github.com/kmkim2689/Android-Wiki/assets/101035437/88d7b249-ad72-4be9-8d79-38dc942e0a7f",
                badgeDrawable = R.drawable.ic_camera_24,
                badgeBackgroundColor = Gray200,
                badgeIconTint = Gray400,
                onClick = onProfileClick
            )

            DefaultTextField(
                title = CONFIG_ID_TITLE,
                placeholder = CONFIG_ID_PLACEHOLDER,
                value = id,
                onValueChange = onIdChange
            )
        }
    }
}

@Preview
@Composable
fun UserConfigScreenPreview() {
    var id by rememberSaveable {
        mutableStateOf("")
    }

    var isValid by rememberSaveable {
        mutableStateOf(false)
    }

    val navController = rememberNavController()

    UserConfigScreen(
        id = id,
        onIdChange = { changedId ->
            id = if (changedId.length <= 12) changedId else id
            isValid = id.length in (2..12)
        },
        isValid = isValid,
        onProfileClick = {},
        profileUri = null,
        navController = navController
    )
}