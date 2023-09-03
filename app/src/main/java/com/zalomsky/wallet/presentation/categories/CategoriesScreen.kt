package com.zalomsky.wallet.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.blue
import com.zalomsky.wallet.presentation.common.color.lime
import com.zalomsky.wallet.presentation.common.color.magenta
import com.zalomsky.wallet.presentation.common.color.orange
import com.zalomsky.wallet.presentation.common.color.red
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(
    onCategoryAdd: () -> Unit,
){
    val viewModel = hiltViewModel<CategoriesScreenViewModel>()
    val categories = viewModel.categories.observeAsState(listOf()).value

    Scaffold(
        topBar = {
            CategoriesTopBar(onCategoryAdd)
        },
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(10.dp))
            Card(
                elevation = 0.dp,
                modifier = Modifier
                    .width(355.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable(onClick = {})
            ) {
/*                LazyColumn(
                    verticalArrangement = Arrangement.Center
                ) {
                    items(categories){category ->
                        PieChart(
                            data = mapOf(
                                Pair(category.name, category.amount)
                            )
                        )
                    }
                }*/
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
            LazyColumn{
                items(categories){ category ->
                    CategoryListItem(
                        category = category
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category
){
    val paddingModifier = Modifier.padding(3.dp)
    Card(
        elevation = 0.dp,
        modifier = paddingModifier
            .width(355.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = {})
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
fun PieChart(
    data: Map<String, Double>,
    radiusOuter: Dp = 120.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 1000
) {
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360*values.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        Color(orange),
        Color(lime),
        Color(blue),
        Color(magenta),
        Color(red)
    )

    var animationPlayed by remember{ mutableStateOf(false)}

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ){
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }
/*        DetailsPieChart(
            data = data,
            colors = colors
        )*/
    }
}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>,
    colors: List<Any>
) {
    Column(
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxWidth()
    ) {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index] as Color
            )
        }
    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 45.dp,
    color: Color
) {
    Surface(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 30.dp),
        color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color = Color.Gray
                )
            }
        }
    }
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
