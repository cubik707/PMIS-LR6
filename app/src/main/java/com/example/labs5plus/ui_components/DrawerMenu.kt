package com.example.labs5plus.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labs5plus.R
import com.example.labs5plus.ui.theme.BgTransp
import com.example.labs5plus.ui.theme.MyPurple
import com.example.labs5plus.utils.DrawerEvents

@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit, selectedIndex: Int){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backimage),
            contentDescription = "Основной фон выдвижного меню",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            Body(onEvent = onEvent, selectedIndex = selectedIndex)
        }
    }
}

@Composable
fun Body(onEvent: (DrawerEvents) -> Unit, selectedIndex: Int) {
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { index, title ->
            val isSelected = index == selectedIndex
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(containerColor = if (isSelected) MyPurple else BgTransp) // Изменить цвет фона
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isSelected) Color.White else Color.Black // Изменить цвет текста
                )
            }
        }
    }
}


@Composable
fun Header() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, MyPurple)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = "Header Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Винчестеры во всех сезонах",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MyPurple)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}
