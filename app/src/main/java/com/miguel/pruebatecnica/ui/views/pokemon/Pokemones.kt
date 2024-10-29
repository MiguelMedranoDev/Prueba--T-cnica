package com.miguel.pruebatecnica.ui.views.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.miguel.pruebatecnica.R
import com.miguel.pruebatecnica.data.models.PokemonObject
import com.miguel.pruebatecnica.ui.views.imageUrl.ImageUrl


@Composable
fun Pokemon(lista: List<PokemonObject>, onPokeSelected: (Int, String) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(4.dp)) {
        items(lista.size) {
            PokemonItemGeneral(
                lista[it].id,
                lista[it].name,
                lista[it].foto,
                onPokeSelected
            )
        }
        item { Text(text = "Cargar Mas Items") }
    }
}


@Composable
fun PokemonItemGeneral(
    id: Int,
    nombre: String,
    imagen: String,
    onPokeSelected: (Int, String) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ), shape = MaterialTheme.shapes.medium, onClick = {

        }) {
        ConstraintLayout(modifier = Modifier.background(Color(0xCD00B8D4))) {
            val (tvName, tvHp, tvDefense, tvSpeed, imageUrl) = createRefs()
            Text(
                text = "$id",
                modifier = Modifier.padding(10.dp),
                color = Color.Black,
                fontSize = 24.sp
            )

            Box(modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(imageUrl) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            ) {
                ImageUrl(url = imagen, name = nombre)
            }



            Text(
                text = nombre,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .constrainAs(tvName) {
                        top.linkTo(imageUrl.bottom)
                        start.linkTo(imageUrl.start)
                        end.linkTo(imageUrl.end)
                    },
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            MyTvAttibutes(modifier = Modifier
                .constrainAs(tvHp) {
                    top.linkTo(tvName.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 20.dp), "Ataque (24)", R.drawable.ic_attack
            )

            MyTvAttibutes(modifier = Modifier
                .constrainAs(tvDefense) {
                    top.linkTo(tvHp.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 20.dp, top = 10.dp), "Defensa (15)", R.drawable.ic_defense
            )

            MyTvAttibutes(modifier = Modifier
                .constrainAs(tvSpeed) {
                    top.linkTo(tvDefense.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                "Velocidad (15)",
                R.drawable.ic_speed
            )
        }

    }

}

@Composable
fun MyTvAttibutes(modifier: Modifier, text: String, icon: Int) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "attack",
            colorFilter = ColorFilter.tint(Color.White)
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }

}