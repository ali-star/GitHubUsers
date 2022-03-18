package alistar.sample.githubusers.libraries.core.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.clickableWithNoRipple(onClick: () -> Unit) = clickable(
    indication = null,
    interactionSource = MutableInteractionSource(),
    onClick = onClick
)
