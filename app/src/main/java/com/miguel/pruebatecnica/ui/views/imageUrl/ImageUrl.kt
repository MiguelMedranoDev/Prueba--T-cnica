package com.miguel.pruebatecnica.ui.views.imageUrl

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.Locale

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ImageUrlButton(
    url: String,
    name: String,
    onChangeUrl: (String) -> Unit,
    onChangeName: (String) -> Unit
) {
    var _url by rememberSaveable {
        mutableStateOf("")
    }
    var _name by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = MaterialTheme.shapes.small,
        ) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .border(5.dp, Color.Blue, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            if (name.isNotEmpty()) {
                                val nameSplit = name.split(" ")
                                Text(
                                    text = if (nameSplit.size > 1) "${
                                        name.split(" ")[0].substring(0, 1)
                                            .uppercase(Locale.getDefault())
                                    } ${
                                        name.split(" ")[1].substring(0, 1)
                                            .uppercase(Locale.getDefault())
                                    }" else name.split(" ")[0].substring(0, 1)
                                        .uppercase(Locale.getDefault()),
                                    textAlign = TextAlign.Center,
                                    fontSize = 90.sp,
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                        GlideImage(
                            modifier = Modifier
                                .width(250.dp)
                                .height(250.dp)
                                .clip(CircleShape),
                            model = url,
                            contentDescription = "imagen URL",
                            loading = null,
                            failure = null,
                            contentScale = ContentScale.Crop
                        )

                    }

                    Text(
                        text = name,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 48.sp
                    )
                }

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)) {
                    OutlinedTextField(
                        value = _url,
                        onValueChange = { _url = it },
                        label = { Text(text = "Url de imagen") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = _name,
                        onValueChange = { _name = it },
                        label = { Text(text = "Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            onChangeName(_name)
                            onChangeUrl(_url)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(text = "Cargar Imagen")
                    }

                }
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageUrl(
    url: String,
    name: String
) {
    Box(modifier = Modifier
        .width(150.dp)
        .height(150.dp)) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        modifier = Modifier.fillMaxSize()
                            .clip(CircleShape),
                        model = url,
                        contentDescription = "imagen URL",
                        loading = null,
                        failure = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Text(
                    text = name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp
                )
            }
        }
    }
}