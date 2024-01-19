package com.sowhat.post_presentation.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sowhat.designsystem.common.noRippleClickable
import com.sowhat.designsystem.common.rippleClickable
import com.sowhat.designsystem.component.ImageContainer
import com.sowhat.designsystem.component.SquaredIconButton
import com.sowhat.designsystem.component.SquaredImageContainer
import com.sowhat.designsystem.theme.Gray200
import com.sowhat.designsystem.theme.JustSayItTheme
import com.sowhat.post_presentation.R

@Composable
fun ImageSelection(
    modifier: Modifier = Modifier,
    images: List<Uri>,
    onAddClick: () -> Unit,
    onImagesChange: (List<Uri>) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(
                start = JustSayItTheme.Spacing.spaceMedium,
                end = JustSayItTheme.Spacing.spaceMedium,
                bottom = JustSayItTheme.Spacing.spaceMedium,
            ),
        horizontalArrangement = Arrangement
            .spacedBy(JustSayItTheme.Spacing.spaceExtraSmall)
    ) {
        SquaredIconButton(
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                    width = 0.5.dp,
                    color = JustSayItTheme.Colors.subSurface,
                    shape = JustSayItTheme.Shapes.medium
                )
                .clip(JustSayItTheme.Shapes.medium)
                .background(Gray200, shape = JustSayItTheme.Shapes.medium),
            icon = com.sowhat.designsystem.R.drawable.ic_camera_24,
            onClick = onAddClick
        )

        images.forEachIndexed { removeIndex, uri ->
            SquaredPostImageContainer(
                modifier = modifier
                    .aspectRatio(1f)
                    .border(
                        width = 0.5.dp,
                        color = JustSayItTheme.Colors.subSurface,
                        shape = JustSayItTheme.Shapes.medium
                    )
                    .clip(JustSayItTheme.Shapes.medium)
                    .noRippleClickable {
                        val changedList = images.filterIndexed { index, _ ->
                            removeIndex != index
                        }
                        onImagesChange(changedList)
                    },
                model = uri
            )
        }
    }
}


@Composable
fun SquaredPostImageContainer(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String? = null
) {
    Box(
        modifier = modifier.aspectRatio(1f),
        contentAlignment = Alignment.TopEnd,
    ) {
        Image(
            modifier = Modifier.padding(JustSayItTheme.Spacing.spaceExtraSmall),
            painter = painterResource(id = com.sowhat.designsystem.R.drawable.ic_close_20),
            contentDescription = "delete"
        )

        ImageContainer(
            modifier = Modifier,
            borderWidth = 0.5.dp,
            borderColor = JustSayItTheme.Colors.subSurface,
            shape = JustSayItTheme.Shapes.medium,
            model = model,
            contentDescription = contentDescription
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun ImageSelectionPreview() {
    Column {
        ImageSelection(images = listOf(), onAddClick = { /*TODO*/ }, onImagesChange = {})

        SquaredPostImageContainer(modifier = Modifier.size(72.dp), model = "")
    }


}