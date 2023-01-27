package dev.esteban.rappi.views.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.esteban.movies.domain.model.Movie
import dev.esteban.rappi.R
import dev.esteban.rappi.composecommon.DefaultButton
import dev.esteban.rappi.composecommon.TextExpandable
import dev.esteban.rappi.composecommon.TextWithBox
import dev.esteban.rappi.composecommon.TextWithIcon
import dev.esteban.rappi.ui.theme.RappiTheme

@Composable
fun MovieScreen(
    movie: Movie?,
    viewModel: MovieViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    onClickBackButton: () -> Unit
) {
    LaunchedEffect(viewModel.uiState.keyVideo) {
        viewModel.uiState.keyVideo?.let { keyVideo ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.youtube")
            intent.data = Uri.parse("https://www.youtube.com/watch?v=$keyVideo")
            context.startActivity(intent)
        }
    }

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
                .padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        movie?.let {
            MovieContent(
                movie = movie,
                onClickWatchTrailer = { movieId ->
                    viewModel.getMovieVideo(movieId)
                }
            )
        }
    }
}

@Composable
fun MovieContent(
    movie: Movie,
    onClickWatchTrailer: (movieId: Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .clickable { expanded = !expanded }
                .align(Alignment.BottomCenter)
        ) {
            TextExpandable(
                text = movie.title,
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
                onClickWatchTrailer(movie.id)
            }

            TextExpandable(
                text = movie.overview,
                expanded = expanded
            )
        }
    }
}
