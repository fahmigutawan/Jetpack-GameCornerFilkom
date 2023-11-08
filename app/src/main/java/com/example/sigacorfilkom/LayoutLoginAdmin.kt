package com.example.sigacorfilkom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayoutLoginAdmin(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 1080.dp)
            .requiredHeight(height = 2400.dp)
            .background(color = Color.White)
    ) {
        Surface(
            color = Color.White,
            border = BorderStroke(1.dp, Color(0xff0f172a)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 0.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 1080.dp)
                    .requiredHeight(height = 80.dp)
            ) {
                Text(
                    text = "Notification",
                    color = Color(0xff0f172a),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = (-270.5).dp,
                            y = (-0.5).dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        Text(
            text = "SiGACOR",
            color = Color(0xff0f172a),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-0.5).dp,
                    y = 800.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 96.dp,
                    y = 1819.dp)
                .requiredWidth(width = 888.dp)
                .requiredHeight(height = 144.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 0.dp)
                    .requiredWidth(width = 888.dp)
                    .requiredHeight(height = 144.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(brush = Brush.radialGradient(
                        0f to Color(0xffff9e3a),
                        1f to Color(0xffff8b13),
                        center = Offset(444f, 72f),
                        radius = 846.38f)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 377.dp,
                        y = 36.dp)
                    .requiredWidth(width = 133.dp)
                    .requiredHeight(height = 72.dp)
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.dp,
                            y = 0.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 96.dp,
                    y = 1183.dp)
                .requiredWidth(width = 888.dp)
                .requiredHeight(height = 144.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 888.dp)
                    .requiredHeight(height = 144.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(4.dp, Color(0xffe47400)),
                        shape = RoundedCornerShape(20.dp)))
            Text(
                text = "NIP",
                color = Color(0xffe47400),
                style = TextStyle(
                    fontSize = 48.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-247).dp,
                        y = 36.dp))
            Box(
                modifier = Modifier
                    .requiredSize(size = 144.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconuser),
                    contentDescription = "icon-user",
                    colorFilter = ColorFilter.tint(Color(0xffe47400)),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredSize(size = 64.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 96.dp,
                    y = 1407.dp)
                .requiredWidth(width = 888.dp)
                .requiredHeight(height = 144.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 888.dp)
                    .requiredHeight(height = 144.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(4.dp, Color(0xffe47400)),
                        shape = RoundedCornerShape(20.dp)))
            Text(
                text = "Password",
                color = Color(0xffe47400),
                style = TextStyle(
                    fontSize = 48.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-168.5).dp,
                        y = 36.dp))
            Box(
                modifier = Modifier
                    .requiredSize(size = 144.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconpassword),
                    contentDescription = "icon-password",
                    colorFilter = ColorFilter.tint(Color(0xffe47400)),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredSize(size = 64.dp))
            }
//            Image(
//                painter = painterResource(id = R.drawable.frame),
//                contentDescription = "Frame",
//                colorFilter = ColorFilter.tint(Color(0xffe47400)),
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(x = 783.dp,
//                        y = 40.dp)
//                    .requiredSize(size = 64.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 277.dp,
                    y = 337.dp)
                .requiredWidth(width = 527.dp)
                .requiredHeight(height = 583.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 84.655029296875.dp)
                    .requiredSize(size = 359.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffd9d9d9)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 84.655029296875.dp)
                    .requiredSize(size = 359.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff38a0f5)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 59.001953125.dp,
                        y = 143.656982421875.dp)
                    .requiredSize(size = 241.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 120.5693359375.dp,
                        y = 0.dp)
                    .requiredSize(size = 359.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffff8b13)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 179.5714111328125.dp,
                        y = 59.00201416015625.dp)
                    .requiredSize(size = 241.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 280.1451416015625.dp,
                        y = 93.7298583984375.dp)
                    .requiredWidth(width = 171.dp)
                    .requiredHeight(height = 39.dp)
                    .rotate(degrees = -90f)
                    .background(color = Color(0xffff8b13)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 214.0389404296875.dp,
                        y = 159.835693359375.dp)
                    .requiredWidth(width = 171.dp)
                    .requiredHeight(height = 39.dp)
                    .background(color = Color(0xffff8b13)))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 131.2994384765625.dp,
                        y = 196.0244140625.dp)
                    .requiredWidth(width = 396.dp)
                    .requiredHeight(height = 387.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.polygon1),
//                    contentDescription = "Polygon 1",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 0.dp,
//                            y = 0.00018310546875.dp)
//                        .requiredWidth(width = 299.dp)
//                        .requiredHeight(height = 264.dp)
//                        .rotate(degrees = 34.19f))
//                Image(
//                    painter = painterResource(id = R.drawable.polygon2),
//                    contentDescription = "Polygon 2",
//                    modifier = Modifier
//                        .align(alignment = Alignment.TopStart)
//                        .offset(x = 109.68408203125.dp,
//                            y = 107.16046142578125.dp)
//                        .requiredWidth(width = 133.dp)
//                        .requiredHeight(height = 118.dp)
//                        .rotate(degrees = 34.19f))
            }
        }
        Text(
            text = "SiGACOR",
            color = Color(0xff0f172a),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-0.5).dp,
                    y = 800.dp))
    }
}

@Preview(widthDp = 1080, heightDp = 2400)
@Composable
private fun HalamanLoginAdminPreview() {
    LayoutLoginAdmin(Modifier)
}