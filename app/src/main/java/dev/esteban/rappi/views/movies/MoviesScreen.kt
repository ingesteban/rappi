package dev.esteban.rappi.views.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.esteban.movies.domain.model.Movie
import dev.esteban.rappi.R
import dev.esteban.rappi.composecommon.Title
import dev.esteban.rappi.ui.theme.RappiTheme
import dev.esteban.rappi.utils.conditional

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    Scaffold(
        topBar = { MoviesHeader() }, backgroundColor = Color.Black
    ) {
        LazyColumn {
            item {
                MovieSection(
                    title = stringResource(id = R.string.title_up_coming_movies),
                    movies = viewModel.uiState.upcomingMovies
                )
            }
            item {
                MovieSection(
                    title = stringResource(id = R.string.title_top_rated_movies),
                    movies = viewModel.uiState.topRatedMovies
                )
            }
            viewModel.uiState.recommendedMovies?.let { recommendedMovies ->
                item {
                    RecommendedMoviesHeader(
                        languageFilter = viewModel.uiState.languageFilter,
                        yearsFilter = viewModel.uiState.yearsFilter,
                        onLanguageFilterClick = {
                            viewModel.filterRecommendedMoviesByLanguage(it)
                        },
                        onYearFilterClick = {
                            viewModel.filterRecommendedMoviesByYear(it)
                        }
                    )
                }
                items(if (recommendedMovies.isEmpty()) 0 else 1 + (recommendedMovies.size - 1) / 2) { rowIndex ->
                    RecommendedMoviesRow(
                        rowIndex = rowIndex,
                        recommendedMovies = recommendedMovies
                    )
                }
            }

        }
    }
}

@Composable
fun MoviesHeader() {
    TopAppBar(modifier = Modifier.padding(top = 20.dp), elevation = 4.dp, title = {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(88.dp)
                    .height(33.dp)
            )
        }
    })
}

@Composable
fun MovieSection(
    title: String,
    movies: List<Movie>?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Title(text = title)
        MoviesRow(movies = movies)
    }
}

@Composable
fun MoviesRow(
    movies: List<Movie>?,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        movies?.forEachIndexed { index, movie ->
            item {
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .conditional(index != (movies.size - 1)) { padding(end = 12.dp) }
                        .width(138.dp)
                        .height(180.dp)
                ) {

                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (movie: Movie) -> Unit
) {
    Card(
        shape = RappiTheme.shapes.small,
        modifier = modifier
            .clickable { onMovieClick(movie) }
    ) {
        Box {
            AsyncImage(
                model = movie.posterPath,
                placeholder = painterResource(id = R.drawable.ic_placeholder_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun MovieFilter(
    defaultText: String,
    concatText: String,
    filtersList: List<String>?,
    onFilter: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    filtersList?.let {
        Box(modifier = Modifier.padding(end = 12.dp)) {
            val textRelease = if (selectedIndex == 0) {
                defaultText
            } else {
                "$concatText ${filtersList[selectedIndex]}"
            }
            Text(
                text = textRelease,
                style = RappiTheme.typography.h2,
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, RappiTheme.colors.gray1),
                        RappiTheme.shapes.extraLarge
                    )
                    .padding(vertical = 4.dp, horizontal = 10.dp)
                    .clickable(onClick = { expanded = true })
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                filtersList.forEachIndexed { index, filter ->
                    DropdownMenuItem(
                        onClick = {
                            selectedIndex = index
                            expanded = false
                            onFilter(filtersList[selectedIndex])
                        }
                    ) {
                        Text(
                            text = filter,
                            style = RappiTheme.typography.h2.copy(
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendedMoviesHeader(
    languageFilter: List<String>?,
    yearsFilter: List<String>?,
    onLanguageFilterClick: (String) -> Unit,
    onYearFilterClick: (String) -> Unit,
) {
    Column {
        Title(
            text = stringResource(id = R.string.title_recommended_movies),
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MovieFilter(
                stringResource(id = R.string.filter_language_default),
                stringResource(id = R.string.filter_language),
                languageFilter
            ) {
                onLanguageFilterClick(it)
            }
            MovieFilter(
                stringResource(id = R.string.filter_released_at_default),
                stringResource(id = R.string.filter_released_at),
                yearsFilter
            ) {
                onYearFilterClick(it)
            }
        }
    }
}

@Composable
fun RecommendedMoviesRow(
    recommendedMovies: List<Movie>,
    rowIndex: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        for (columnIndex in 0 until 2) {
            val itemIndex = rowIndex * 2 + columnIndex
            if (itemIndex < recommendedMovies.size) {
                MovieItem(
                    movie = recommendedMovies[itemIndex],
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                        .width(156.dp)
                        .height(205.dp)
                ) {

                }
            } else {
                Spacer(Modifier.weight(1F, fill = true))
            }
        }
    }
}
