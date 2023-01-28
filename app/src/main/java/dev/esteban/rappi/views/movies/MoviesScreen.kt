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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import dev.esteban.movies.domain.model.Movie
import dev.esteban.rappi.R
import dev.esteban.rappi.composecommon.DefaultButton
import dev.esteban.rappi.composecommon.Title
import dev.esteban.rappi.ui.theme.RappiTheme
import dev.esteban.rappi.utils.conditional

val placeholderMovies = listOf(
    Movie(), Movie(), Movie(), Movie(), Movie(), Movie()
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (movie: Movie) -> Unit
) {

    var selectedLanguageIndex by remember { mutableStateOf(0) }
    var selectedYearIndex by remember { mutableStateOf(0) }

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
                ) { movie ->
                    onMovieClick(movie)
                }
            }

            item {
                MovieSection(
                    title = stringResource(id = R.string.title_top_rated_movies),
                    movies = viewModel.uiState.topRatedMovies
                ) { movie ->
                    onMovieClick(movie)
                }
            }

            val recommendedMovies = viewModel.uiState.recommendedMovies ?: placeholderMovies
            item {
                RecommendedMoviesHeader(
                    selectedLanguageIndex = selectedLanguageIndex,
                    selectedYearIndex = selectedYearIndex,
                    languageFilter = viewModel.uiState.languageFilter,
                    yearsFilter = viewModel.uiState.yearsFilter,
                    onLanguageFilterClick = { text, index ->
                        selectedLanguageIndex = index
                        viewModel.filterRecommendedMoviesByLanguage(text)
                    },
                    onYearFilterClick = { text, index ->
                        selectedYearIndex = index
                        viewModel.filterRecommendedMoviesByYear(text)
                    },
                )
            }

            if (recommendedMovies.isNotEmpty()) {
                items(1 + (recommendedMovies.size - 1) / 2) { rowIndex ->
                    RecommendedMoviesRow(
                        rowIndex = rowIndex,
                        recommendedMovies = recommendedMovies
                    ) { movie ->
                        onMovieClick(movie)
                    }
                }
            } else {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.filter_empty_list),
                            style = RappiTheme.typography.h2,
                            textAlign = TextAlign.Center
                        )
                        DefaultButton(text = stringResource(id = R.string.button_reset_filters)) {
                            selectedYearIndex = 0
                            selectedLanguageIndex = 0
                            viewModel.resetFilters()
                        }
                    }
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
    onMovieClick: (movie: Movie) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Title(
            text = title,
            modifier = Modifier
                .padding(16.dp)
                .placeholder(
                    visible = movies == null,
                    color = RappiTheme.colors.gray3,
                    shape = RappiTheme.shapes.card,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = RappiTheme.colors.gray2,
                    )
                )
        )
        MoviesRow(movies = movies ?: placeholderMovies) { movie ->
            onMovieClick(movie)
        }
    }
}

@Composable
fun MoviesRow(
    movies: List<Movie>?,
    modifier: Modifier = Modifier,
    onMovieClick: (movie: Movie) -> Unit
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
                ) { movie ->
                    onMovieClick(movie)
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
            .placeholder(
                visible = movie.posterPath.isNullOrEmpty(),
                color = RappiTheme.colors.gray3,
                shape = RappiTheme.shapes.card,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = RappiTheme.colors.gray2,
                )
            )
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
    selectedIndex: Int,
    defaultText: String,
    concatText: String,
    filtersList: List<String>?,
    onFilter: (String, Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
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
                            expanded = false
                            onFilter(filtersList[index], index)
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
    selectedLanguageIndex: Int,
    selectedYearIndex: Int,
    languageFilter: List<String>?,
    yearsFilter: List<String>?,
    onLanguageFilterClick: (String, Int) -> Unit,
    onYearFilterClick: (String, Int) -> Unit,
) {
    Column {
        Title(
            text = stringResource(id = R.string.title_recommended_movies),
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MovieFilter(
                selectedIndex = selectedLanguageIndex,
                stringResource(id = R.string.filter_language_default),
                stringResource(id = R.string.filter_language),
                languageFilter
            ) { text, index ->
                onLanguageFilterClick(text, index)
            }
            MovieFilter(
                selectedIndex = selectedYearIndex,
                stringResource(id = R.string.filter_released_at_default),
                stringResource(id = R.string.filter_released_at),
                yearsFilter
            ) { text, index ->
                onYearFilterClick(text, index)
            }
        }
    }
}

@Composable
fun RecommendedMoviesRow(
    recommendedMovies: List<Movie>,
    rowIndex: Int,
    onMovieClick: (movie: Movie) -> Unit
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
                ) { movie ->
                    onMovieClick(movie)
                }
            } else {
                Spacer(Modifier.weight(1F, fill = true))
            }
        }
    }
}
