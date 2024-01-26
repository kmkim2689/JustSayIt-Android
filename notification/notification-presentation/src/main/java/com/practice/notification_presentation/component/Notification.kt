package com.practice.notification_presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.sowhat.designsystem.theme.Gray400
import com.sowhat.designsystem.theme.JustSayItTheme

@Composable
fun Notification(
    // TODO api 나오면 데이터 클래스로 만들어서 매개변수 수 줄이기
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    time: String,
    drawable: Int,
    statusText: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(JustSayItTheme.Spacing.spaceBase)
            .background(JustSayItTheme.Colors.mainBackground)
    ) {
        // 감정 상태
        MoodStatus(
            drawable = drawable,
            statusText = statusText
        )

        // 알림 내용
        NotificationContent(
            title = title,
            content = content,
            time = time
        )
    }
}

@Composable
private fun NotificationContent(title: String, content: String, time: String) {
    Column(
        modifier = Modifier
            .padding(top = JustSayItTheme.Spacing.spaceXXS)
    ) {
        Text(
            text = title,
            style = JustSayItTheme.Typography.body1,
            color = JustSayItTheme.Colors.mainTypo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(JustSayItTheme.Spacing.spaceXXS))

        Text(
            text = content,
            style = JustSayItTheme.Typography.detail1,
            color = JustSayItTheme.Colors.subTypo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(JustSayItTheme.Spacing.spaceTiny))

        Text(
            text = time,
            style = JustSayItTheme.Typography.detail1,
            color = Gray400,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun MoodStatus(drawable: Int, statusText: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(JustSayItTheme.Spacing.spaceXXS)
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "status"
        )

        Text(
            text = statusText,
            style = JustSayItTheme.Typography.detail1,
            color = JustSayItTheme.Colors.mainTypo,
            textAlign = TextAlign.Center
        )
    }
}