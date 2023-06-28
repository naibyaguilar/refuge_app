package com.example.refuge.ui.home.restrooms

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.refuge.data.db.entities.Restroom
import com.example.refuge.ui.home.components.RestroomCard


@Composable
fun RestroomsScreen(
    modifier: Modifier = Modifier,
    restrooms:  List<Restroom>

) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = restrooms) { restroom ->
            RestroomCard(restroom = restroom)
        }

    }
}
//
//@Preview(showBackground = true, widthDp = 320)
//@Composable
//private fun GreetingsPreview() {
//    RestroomsScreen(restroom)
//}