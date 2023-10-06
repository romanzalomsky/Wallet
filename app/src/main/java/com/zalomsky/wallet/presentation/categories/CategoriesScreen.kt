package com.zalomsky.wallet.presentation.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zalomsky.wallet.domain.model.Category
import com.zalomsky.wallet.presentation.WalletIconButton
import com.zalomsky.wallet.presentation.common.color.backgroundColor
import com.zalomsky.wallet.presentation.common.color.systemTextColor
import com.zalomsky.wallet.presentation.common.fonts.splineSansMedium

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoriesScreen(
    onCategoryAdd: () -> Unit,
    onCategoryEdit: (Long) -> Unit
){
    val viewModel: CategoriesScreenViewModel = hiltViewModel()
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
            CategoryPieChart()
            Spacer(Modifier.height(10.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2)){
                viewModel.getAllCategories()
                items(categories){ category ->
                    CategoryListItem(
                        category = category,
                        onCategoryEdit = onCategoryEdit
                    )
                }
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
        WalletIconButton(
            icon = Icons.Outlined.Menu,
            description = "Menu icon",
            onClick = {}
        )
        Text(
            text = "Categories",
            fontFamily = splineSansMedium,
            fontSize = 20.sp,
            color = systemTextColor,
        )
        Spacer(Modifier.weight(1f, true))
        WalletIconButton(
            icon = Icons.Filled.Add,
            description = "Add",
            onClick = onCategoryAdd
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryListItem(
    onCategoryEdit: (Long) -> Unit,
    category: Category
){
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if(showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            BottomSheetContent(category)
        }
    }
    val paddingModifier = Modifier.padding(3.dp)
    Card(
        elevation = 0.dp,
        modifier = paddingModifier
            .padding(horizontal = 15.dp)
            .width(200.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .combinedClickable(
                onClick = { onCategoryEdit(category.id) },
                onLongClick = {
                    showBottomSheet = true
                }
            )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(category.circleColor))
            ){
                Icon(
                    painter = painterResource(id = category.icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Column {
                Text(
                    text = category.name,
                    fontFamily = splineSansMedium,
                    fontSize = 15.sp,
                    color = systemTextColor
                )
                Text(
                    text = category.amount.toString() + " $",
                    color = systemTextColor,
                    fontFamily = splineSansMedium,
                )
            }
        }
        CategorySettings(onClick = {})
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun BottomSheetContent(
    category: Category
) {
    Column(modifier = Modifier.height(500.dp)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "${category.name}",
                fontFamily = splineSansMedium,
                fontSize = 23.sp,
                color = systemTextColor
            )
            var value by remember { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = {
                    value = it
                }
            )
        }
    }
}

@Composable
fun CategoryPieChart() {
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
}

@Composable
fun CategorySettings(
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
