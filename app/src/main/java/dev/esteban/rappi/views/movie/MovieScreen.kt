package dev.esteban.rappi.views.movie

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.esteban.movies.domain.model.Movie
import dev.esteban.rappi.R
import dev.esteban.rappi.composecommon.DefaultButton
import dev.esteban.rappi.composecommon.TextWithBox
import dev.esteban.rappi.composecommon.TextWithIcon
import dev.esteban.rappi.ui.theme.RappiTheme

@Composable
fun MovieScreen(
    movie: Movie?,
    onClickBackButton: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = movie?.posterPath,
            placeholder = painterResource(id = R.drawable.ic_placeholder_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_background_movie_detail),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        IconButton(
            onClick = onClickBackButton,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 32.dp, start = 16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        movie?.let {
            MovieContent(it)
        }
    }
}

@Composable
fun MovieContent(movie: Movie) {
    Box(modifier = Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .clickable { expanded = !expanded }
                .align(Alignment.BottomCenter)
        ) {
            TextExpandable(
                text = movie.title.toString(),
                textStyle = RappiTheme.typography.h4,
                textAlign = TextAlign.Center,
                expanded = expanded
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextWithBox(text = movie.releaseYear)

                TextWithBox(text = movie.originalLanguage)

                TextWithIcon(icon = Icons.Filled.Star, text = movie.voteAverage.toString())
            }

            DefaultButton(text = stringResource(id = R.string.button_watch)) {

            }

            TextExpandable(
                text = movie.overview,
                expanded = expanded
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TextExpandable(
    text: String,
    textStyle: TextStyle = RappiTheme.typography.h2,
    textAlign: TextAlign = TextAlign.Justify,
    expanded: Boolean
) {
    Surface(color = Color.Transparent) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(
                    text = text,
                    style = textStyle,
                    textAlign = textAlign,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = text,
                    maxLines = 2,
                    style = textStyle,
                    textAlign = textAlign,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
