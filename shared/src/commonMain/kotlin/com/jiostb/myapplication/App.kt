package com.jiostb.myapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.fetch.NetworkFetcher
import coil3.request.ImageRequest
import com.jiostb.myapplication.data.model.videos.Item
import com.jiostb.myapplication.data.model.videos.Youtube
import com.jiostb.myapplication.di.appModule
import com.jiostb.myapplication.di.registerScreenModule
import com.jiostb.myapplication.domain.usecases.YoutubeState
import com.jiostb.myapplication.tablayout.AboutScreen
import com.jiostb.myapplication.tablayout.MainScreen
import com.jiostb.myapplication.tablayout.SettingsScreen
import com.jiostb.myapplication.viewmodel.MainViewModel
import com.jiostb.myapplication.viewmodel.TabViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject


@Composable
fun App() {

    KoinApplication(application = {
        modules(appModule())
    }) {
        val tabViewmodel:TabViewModel = koinInject()

        ScreenRegistry {
            screenModule { registerScreenModule() }
        }
        //Navigator(screen = HomeScreen())
        val selectedTab = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        val closeDrrawer = {
            scope.launch {
                drawerState.close()
            }
        }

        Scaffold (
            topBar = {  TopBar(
                title = selectedTab.value.title,
                buttonIcon = if(drawerState.isClosed) Icons.Filled.Menu else Icons.Filled.ArrowBack,
                onButtonClicked = { if(drawerState.isClosed) openDrawer() else closeDrrawer() }
            )},
            bottomBar = {
                BottomNavViewE(selectedTab.value, ){
                    selectedTab.value = it
                }
        }){
            navDV(drawerState, scope){
                if(selectedTab.value.title == BottomNavItem.Home.title)
                TabViewE()
                else if(selectedTab.value.title == BottomNavItem.MyNetwork.title)
                    SettingsScreen(tabViewmodel)
                else
                    AboutScreen(tabViewmodel)
            }


           // TabLayout(viewModel = tabViewmodel )
           // NavigationDrawerTheme { AppMainScreen(scope, drawerState)}
           // val scope = rememberCoroutineScope()
           // val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        }


         /*VideoPlayer(
             modifier = Modifier.fillMaxWidth()
                 .height(200.dp),
             url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
             {
                 Logger.i("playingvideo")
             }
         )*/
    }


    /* val repository = remember { Repository() }
     val viewModel = remember { MainViewModel(repository) }
     var state by remember { mutableStateOf<YoutubeState>(YoutubeState.LOADING) }

     LaunchedEffect(Unit) {
         viewModel.getVideosList()
     }
     state = viewModel.videos.collectAsState().value

     when (state) {
         is YoutubeState.LOADING -> {
             //LoadingBox()
         }

         is YoutubeState.SUCCESS -> {
             val data = (state as YoutubeState.SUCCESS).youtube
             println("data  ${data}")
             //VideosList(data)
         }

         is YoutubeState.ERROR -> {
             val error = (state as YoutubeState.ERROR).error
            // ErrorBox(error)
             println("data  ${error}")
         }
     }*/
    /*VideoPlayer(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp),
        url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    )*/
}

@Composable
fun TabViewE(){
    val tabViewmodel:TabViewModel = koinInject()
    MainScreen(tabViewmodel)
}
@Composable
fun navDV(drawerState: DrawerState, scope: CoroutineScope, contentview:@Composable ()->Unit ) {
    Sidebar(drawerState = drawerState,closeDrawer={
        scope.launch {
            drawerState.open()
        }
    }) {
        contentview()
    }
}
@Composable
fun BottomNavViewE(selectedTab: BottomNavItem ,bottomTabClicked :  (BottomNavItem) ->Unit){

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs
    )



    BottomNavigation(
        contentColor = Color.Black
    ) {
        // val navBackStackEntry by navController.currentBackStackEntryAsState()
        // val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = selectedTab.title == item.title,
                onClick = {
                    bottomTabClicked(item)
                    /* navController.navigate(item.screen_route) {

                         navController.graph.startDestinationRoute?.let { screen_route ->
                             popUpTo(screen_route) {
                                 saveState = true
                             }
                         }
                         launchSingleTop = true
                         restoreState = true
                     }*/
                }
            )
        }
    }
}

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        //backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun verticalGridView(cols: Int, itemList: List<Item>, itemClick: (Item) -> Unit) {

    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(cols),
        state = lazyGridState
    ) {
        items(itemList, span = { item ->
            val lowercase = item.etag
            val span = if (lowercase.startsWith("a") || lowercase.lowercase()
                    .startsWith("b") || lowercase.lowercase().startsWith("d")
            ) {
                cols
            } else {
                1
            }
            GridItemSpan(1)
        }) { item ->
            // Logger.i {item?.snippet?.thumbnails?.high?.url?:"error" }
            /*KamelImage(
                resource = asyncPainterResource(data = item.snippet.thumbnails.high.url),
                contentDescription = "description",
                modifier = Modifier.padding(5.dp).size(150.dp).clickable { itemClick(item) }

            )*/
            AsyncImage( model =
            ImageRequest.Builder(LocalPlatformContext.current)
                .fetcherFactory(NetworkFetcher.Factory())
                .data(item.snippet.thumbnails.high.url)
                .build(),"",
                modifier = Modifier.padding(5.dp).width(150.dp).clickable { itemClick(item) })

            /* Box(modifier = Modifier
                 .fillMaxWidth()
                 .height(150.dp)
                 .padding(10.dp)
                 .background(Color.Black)
                 .padding(2.dp)
                 .background(Color.White)
             ) {
                 Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = item.kind,
                    fontSize = 18.sp
                )

               *//* KamelImage(
                    resource = asyncPainterResource(data = item.snippet.thumbnails.high),
                    contentDescription = "description",
                    modifier = Modifier.size(150.dp)

                )*//*
            }*/
        }
    }

}

class HomeScreen1 : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailModel: MainViewModel = koinInject()

        //val state by detailModel.episodeUiData.collectAsState()
        val data = remember { mutableStateOf<Youtube?>(null) }
        LaunchedEffect(Unit) {
            detailModel.getVideosList()
        }
        val state = detailModel.videos.collectAsState().value
        LaunchedEffect(state) {
            when (state) {
                is YoutubeState.LOADING -> {
                    //LoadingBox()
                }

                is YoutubeState.SUCCESS -> {
                    Logger.e("YoutubeState.SUCCESS")
                     data.value = (state as YoutubeState.SUCCESS).youtube
                    println("data  ${data}")
                    //VideosList(data)
                    //Text(data.items.toString())

                }

                is YoutubeState.ERROR -> {
                    val error = (state as YoutubeState.ERROR).error
                    // ErrorBox(error)
                    Logger.e("YoutubeState.ERROR ${error}")
                    println("data  ${error}")
                }
            }
        }
        data.value?.items?.let{ verticalGridView(3, it) {
            navigator.push(DetailScreen())
        }}
    }

}

class DetailScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var value = remember { mutableStateOf("") }
        Column {
           // ComposeUITextField("ramesh", {}, modifier = Modifier.width(300.dp).height(60.dp))
            TextField(
                value = value.value,
                onValueChange = {
                    value.value = it
                },
                modifier = Modifier.width(300.dp).height(60.dp)
            )
            Text("hhhhhhhh", modifier = Modifier.clickable { navigator.pop() })
        }

    }

}

@Composable
fun AppMainScreen(scope:CoroutineScope, drawerState:androidx.compose.material3.DrawerState) {
   // val navController = rememberNavController()
    //Surface() {


        ModalNavigationDrawer(
            modifier = if (drawerState.currentValue == DrawerValue.Open)
                Modifier
                    .animateContentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color(0xFF111111),
                                Color(0xFF111111),
                            )
                        )
                    )
                    .fillMaxSize()
            else Modifier
                .background(color = Color(0xFF111111))
                .width(0.dp)
                .fillMaxHeight(),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                       /* navController.navigate(route) {
                            popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }*/
                    }
                )
            }
        ) {

        }
   // }
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .background(Color.Blue)
            .fillMaxSize()
            .padding( top = 48.dp)
    ) {
        Image(
            Icons.Default.Home,
            contentDescription = "App icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }
            )
        }
    }
}
private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)
sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Account : DrawerScreens("Account", "account")
    object Help : DrawerScreens( "Help", "help")
}
internal expect fun openUrl(url: String?)

@Composable
internal expect fun VideoPlayer(modifier: Modifier, url: String, plaing:()->Unit)

@Composable
internal expect fun ComposeUITextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier)
