package com.example.uistatewithmvvmexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uistatewithmvvmexample.flowlearning.data.model.User
import com.example.uistatewithmvvmexample.flowlearning.ui.MainViewModel
import com.example.uistatewithmvvmexample.flowlearning.ui.State
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("MVVM StateManagement") },
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        )
                    },
                ) {
                    MainView(viewModel)
                }

        }
    }
}
@Composable
fun MainView(viewModel: MainViewModel) {

    //val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()
    when (state) {
        State.START -> {
        }
        State.LOADING -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        is State.FAILURE -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Something went wrong...",fontSize = 16.sp)
            }
        }
        is State.SUCCESS -> {
            val users = (state as State.SUCCESS).users
            UserListScreen(users)
        }
    }
}
@Composable
fun UserListScreen(users: List<User>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {

        items(items = users) { item ->
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier
                    .background(Color.Black, CircleShape)
                    .size(50.dp),
                    contentAlignment = Alignment.Center ){
                    Text(
                        text = item.name.substring(0, 1),
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 6.dp)
                ) {
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                    Text(
                        text = item.name, fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 2.dp))
                }
            }
        }

    }
}