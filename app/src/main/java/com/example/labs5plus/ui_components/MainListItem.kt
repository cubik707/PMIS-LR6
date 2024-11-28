package com.example.labs5plus.ui_components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.labs5plus.di.MainViewModel
import com.example.labs5plus.ui.theme.BgTransp
import com.example.labs5plus.ui.theme.MyPurple
import com.example.labs5plus.ui.theme.Pink40
import com.example.labs5plus.ui.theme.Pink80
import com.example.labs5plus.utils.ListItem

@Composable
fun MainListItem(
    mainViewModel: MainViewModel = hiltViewModel(),
    item: ListItem, onClick : (ListItem) -> Unit) {

    Card(modifier = Modifier.fillMaxWidth()
        .height(300.dp)
        .padding(5.dp)
        .clickable {
            onClick(item)
        },
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(2.dp, MyPurple)
    ){
        ConstraintLayout(modifier = Modifier.fillMaxSize()
        ) {
            val (image, text, fav) = createRefs()
            AssetImage(
                imageName =item.imageName,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(text=item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MyPurple)
                    .padding(5.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color= Color.White
            )
            IconButton(onClick  = {
                mainViewModel.insertItem(item.copy(isfav = !item.isfav))
            },
                modifier = Modifier.constrainAs(fav){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                })
            {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription ="Favorite",
                    tint = if(item.isfav) MyPurple else Gray,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BgTransp)
                        .padding(5.dp))
            }
        }

    }
}

@Composable
fun AssetImage(imageName: String, contentDescription: String, modifier: Modifier){
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)

    Image(bitmap = bitMap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillHeight,
        modifier = modifier)
}