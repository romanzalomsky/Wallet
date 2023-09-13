package com.zalomsky.wallet.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    onCategoryAdd: () -> Unit,
    onCategoryEdit: (Long) -> Unit
){
    val viewModel: CategoryDetailViewModel = hiltViewModel()
    val categories = viewModel.categories.observeAsState(listOf()).value

    val category = viewModel.category.observeAsState().value

    Scaffold(
        topBar = { CategoriesTopBar(onCategoryAdd) },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimatedPieChart()
/*            StatementBody(
                items = listOf(categories),
                colors = { category.circleColor },
                amounts = { category.amount.toFloat() },
            )*/
            LazyColumn{
                viewModel.getAllCategories()
                items(categories){ category ->
                    CategoryListItem(category = category, onCategoryEdit = onCategoryEdit)
                }
            }
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category,
    onCategoryEdit: (Long) -> Unit
){
    val paddingModifier = Modifier.padding(3.dp)
    Card(
        elevation = 0.dp,
        modifier = paddingModifier
            .width(355.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { onCategoryEdit(category.id) })
    ){
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .size(45.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(category.circleColor))
            ){
                Icon(
                    painter = painterResource(id = category.icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Column {
                Text(
                    text = category.name,
                    fontFamily = splineSansMedium,
                    fontSize = 20.sp,
                    color = systemTextColor,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = category.amount.toString() + " $",
                    color = systemTextColor,
                    fontFamily = splineSansMedium,
                )
            }
        }
    }
}

@Composable
fun <T> StatementBody(
    items: List<T>,
    colors: (T) -> Int,
    amounts: (T) -> Float,
) {
    val accountsProportion = items.extractProportions{ amounts(it) }
    val circleColors = items.map { colors(it) }
    AnimatedCircle(
        accountsProportion,
        circleColors,
        Modifier
            .height(300.dp)
            .fillMaxWidth()
    )
}

fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = this.sumOf { selector(it).toDouble() }
    return this.map { (selector(it) / total).toFloat() }
}

@Composable
fun CategoriesTopBar(
    onCategoryAdd: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White
    ){
        IconButton(
            onClick = {  },
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Menu icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = "Categories",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor,
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = onCategoryAdd) {
            Icon(Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
        }
    }
}


@Composable
fun AnimatedPieChart() {
    Spacer(Modifier.height(10.dp))
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .width(355.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = {})
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            PieChart(
                data = mapOf(
                    Pair("s", 12.2),
                    Pair("q", 13.2),
                    Pair("w", 14.2),
                    Pair("d", 12.2),
                    Pair("a", 15.2)
                )
            )
        }
    }
    Spacer(Modifier.height(10.dp))
}

private const val DividerLengthInDegrees = 1.8f

@Composable
fun AnimatedCircle(
    proportions: List<Float>,
    colors: List<Int>,
    modifier: Modifier = Modifier
) {
    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val stroke = with(LocalDensity.current) { Stroke(5.dp.toPx()) }
    val transition = updateTransition(currentState)
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        }
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            360f
        }
    }
    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        }
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        var startAngle = shift - 90f
        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = Color(colors[index]),
                startAngle = startAngle + DividerLengthInDegrees / 2,
                sweepAngle = sweep - DividerLengthInDegrees,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            startAngle += sweep
        }
    }
}
private enum class AnimatedCircleProgress { START, END }

