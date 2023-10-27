package com.example.uipage.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uipage.R
import com.example.uipage.models.Feature
import com.example.uipage.ui.theme.*

import com.example.uipage.utils.standardQuadFromTo
import java.time.LocalTime

@Composable
fun HomeScreen() {

    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    )
    {
        Column {
            GreentingSection(name = "Gustavo")
            ChipSection(chips = listOf("Gustavo","Teste"))
            CurrentMeditation()

            FeatureSection(features = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming sounds",
                    R.drawable.ic_headphone,
                    Beige1,
                    Beige2,
                    Beige3
                )
            ))
        }

    }
}

@Composable
fun GreentingSection( name: String ) {
    val hours = LocalTime.now()
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodyMedium
            )

        }

        Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

    }
}

@Composable
fun ChipSection(chips: List<String>) {
    var selectedChipIndex by remember{
        mutableStateOf(0)
    }

    LazyRow{
        items(chips.size){
            val obj = chips[it];

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 0.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ){
                Text(text = obj, color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {
    Row (
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

    ){
        Column (
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Meditation 3-10 min",
                style = MaterialTheme.typography.bodyMedium,
                color = TextWhite
            )

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
            
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp))
        }

    }
}

@Composable
fun FeatureSection(features: List<Feature>) {

    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = "Feature",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(15.dp)
            )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight(),
            content = {
                items(features.size){
                    val feat = features[it];

                    FeatureItem(feature = feat)
                }
            }
        )
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth;
        val heigth = constraints.maxHeight


        val mediumColoredPoint1 = Offset(0f,heigth*0.3f)
        val mediumColoredPoint2 = Offset(width*.1f,heigth*0.35f)
        val mediumColoredPoint3 = Offset(width*0.4f,heigth*0.05f)
        val mediumColoredPoint4 = Offset(width*0.75f,heigth*0.7f)
        val mediumColoredPoint5 = Offset(width*1.4f,-heigth.toFloat())

        val mediumColoredPath = Path().apply{
            moveTo(mediumColoredPoint1.x , mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)
            lineTo(width.toFloat()+100f,heigth.toFloat()*100f)
            lineTo(-100f,heigth.toFloat()*100f)
            close()
        }

        //Light colored path
        val lightColoredPoint1 = Offset(0f,heigth*0.35f)
        val lightColoredPoint2 = Offset(width*.1f,heigth*0.4f)
        val lightColoredPoint3 = Offset(width*0.3f,heigth*0.35f)
        val lightColoredPoint4 = Offset(width*0.65f,heigth.toFloat())
        val lightColoredPoint5 = Offset(width*1.4f,-heigth.toFloat()/3f)

        val lightColoredPath = Path().apply{
            moveTo(lightColoredPoint1.x , lightColoredPoint1.y)
            standardQuadFromTo(lightColoredPoint1,lightColoredPoint2)
            standardQuadFromTo(lightColoredPoint2,lightColoredPoint3)
            standardQuadFromTo(lightColoredPoint3,lightColoredPoint4)
            standardQuadFromTo(lightColoredPoint4,lightColoredPoint5)
            lineTo(width.toFloat()+100f,heigth.toFloat()*100f)
            lineTo(-100f,heigth.toFloat()*100f)
            close()
        }

        Canvas(
            modifier = Modifier.fillMaxSize()){
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ){
            Text(
                text = feature.title,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(painter = painterResource(id = feature.icon), contentDescription =feature.title, tint = Color.White,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}