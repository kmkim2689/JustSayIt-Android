package com.sowhat.designsystem.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sowhat.designsystem.R
import com.sowhat.designsystem.common.ActionButtonItem
import com.sowhat.designsystem.common.Emotion
import com.sowhat.designsystem.common.bottomBorder
import com.sowhat.designsystem.theme.Gray500
import com.sowhat.designsystem.theme.JustSayItTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String?,
    navigationIcon: Int?,
    actionIcon: Int?,
    onNavigationIconClick: () -> Unit = {},
    onActionIconClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .height(48.dp)
            .bottomBorder(strokeWidth = 1.dp, color = JustSayItTheme.Colors.subSurface),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JustSayItTheme.Colors.mainSurface,
        ),
        title = { 
            title?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = JustSayItTheme.Typography.body1
                    )
                }
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                DefaultIconButton(
                    iconDrawable = navigationIcon,
                    onClick = onNavigationIconClick
                )
            }
        },
        actions = {
            actionIcon?.let {
                DefaultIconButton(
                    iconDrawable = actionIcon,
                    onClick = onActionIconClick
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String?,
    textStyle: TextStyle = JustSayItTheme.Typography.body1,
    navigationIcon: Int?,
    actionText: String?,
    onNavigationIconClick: () -> Unit = {},
    onActionTextClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .height(48.dp)
            .bottomBorder(strokeWidth = 1.dp, color = JustSayItTheme.Colors.subSurface),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JustSayItTheme.Colors.mainSurface,
        ),
        title = {
            title?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = JustSayItTheme.Typography.body1
                    )
                }
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                DefaultIconButton(
                    iconDrawable = navigationIcon,
                    onClick = onNavigationIconClick
                )
            }
        },
        actions = {
            actionText?.let {
                DefaultTextButton(
                    text = actionText,
                    onClick = onActionTextClick,
                    textStyle = textStyle
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String?,
    textStyle: TextStyle = JustSayItTheme.Typography.body1,
    navigationIcon: Int?,
    actionText: String?,
    isValid: Boolean,
    onNavigationIconClick: () -> Unit = {},
    onActionTextClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .height(48.dp)
            .bottomBorder(strokeWidth = 1.dp, color = JustSayItTheme.Colors.subSurface),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JustSayItTheme.Colors.mainSurface,
        ),
        title = {
            title?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = JustSayItTheme.Typography.body1
                    )
                }
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                DefaultIconButton(
                    iconDrawable = navigationIcon,
                    onClick = onNavigationIconClick
                )
            }
        },
        actions = {
            val textColor = if (isValid) textStyle.color else Gray500
            actionText?.let {
                DefaultTextButton(
                    text = actionText,
                    onClick = onActionTextClick,
                    textStyle = textStyle,
                    textColor = textColor,
                    enabled = isValid
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome(
    modifier: Modifier = Modifier,
    title: String?,
    actions: List<ActionButtonItem>
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .height(48.dp)
            .bottomBorder(strokeWidth = 1.dp, color = JustSayItTheme.Colors.subSurface),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JustSayItTheme.Colors.mainSurface,
        ),
        title = {},
        navigationIcon = {
            title?.let {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = JustSayItTheme.Spacing.spaceNormal),
                        text = title,
                        style = JustSayItTheme.Typography.headlineB,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        actions = {
            actions.forEach {
                DefaultIconButton(
                    iconDrawable = it.icon,
                    onClick = it.onClick
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val emotions = Emotion.values().toList()

    CenterAlignedTopAppBar(
        modifier = modifier
            .height(48.dp)
            .bottomBorder(strokeWidth = 1.dp, color = JustSayItTheme.Colors.subSurface),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = JustSayItTheme.Colors.mainSurface,
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = JustSayItTheme.Spacing.spaceExtraLarge,
                        vertical = JustSayItTheme.Spacing.spaceSmall
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                emotions.forEachIndexed { index, emotion ->
                    EmotionButton(
                        modifier = Modifier,
                        emotion = emotion,
                        onClick = onClick
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
class ScrollBehavior: TopAppBarScrollBehavior {
    override val flingAnimationSpec: DecayAnimationSpec<Float> = TODO()
    override val isPinned: Boolean = TODO()
    override val nestedScrollConnection: NestedScrollConnection = TODO()
    override val snapAnimationSpec: AnimationSpec<Float> = TODO()
    override val state: TopAppBarState = TODO()
}

@Preview
@Composable
fun AppBarPreview() {
    Column {
        AppBar(title = "설정", navigationIcon = R.drawable.ic_back_24, actionIcon = R.drawable.ic_menu_24)
        Spacer(modifier = Modifier.height(2.dp))
        AppBar(title = "앱바 미리보기", navigationIcon = R.drawable.ic_back_24, actionText = "완료")
        Spacer(modifier = Modifier.height(2.dp))
        AppBar(title = "앱바 미리보기", navigationIcon = null, actionText = null)
        AppBarHome(onClick = {})
        AppBarHome(title = "그냥, 그렇다고", actions = listOf(
            ActionButtonItem(icon = R.drawable.ic_camera_24, onClick = {}),
            ActionButtonItem(icon = R.drawable.ic_menu_24, onClick = {})
        ))
    }
}