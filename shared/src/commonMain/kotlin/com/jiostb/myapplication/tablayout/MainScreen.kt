package com.jiostb.myapplication.tablayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jiostb.myapplication.viewmodel.TabViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
/**
 * Created by Mahesh Gubbi on 31-10-2023.
 * Bengaluru.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(tabViewmodel:TabViewModel) {
    val tabData = getTabList()

    val pagerState = rememberPagerState(initialPage = 0){
        5
    }
    val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(tabData, pagerState, tabViewmodel)
    }
    /*LaunchedEffect(isDraggedState) {
        // convert compose state into flow
        snapshotFlow { isDraggedState.value }
            .collectLatest { isDragged ->
                // if not isDragged start slide animation
                if (!isDragged) {
                    // infinity loop
                    while (true) {
                        // duration before each scroll animation
                        delay(60000)
                        runCatching {
                            pagerState.animateScrollToPage(pagerState.currentPage.inc() % pagerState.pageCount)
                        }
                    }
                }
            }
    }*/
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
/*     val tabColor = listOf(
         Color.Gray,
         Color.Yellow,
         Color.Blue,
         Color.Red,
         Color.Green
     )*/
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier =Modifier.height(5.dp))
        },
       /* indicator = { tabPositions ->
            tabPositions.getOrNull(selectedTabIndex.value)?.let {
                TabRowDefaults.PillIndicator(
                    currentTabPosition = it,
                    activeColor = Color(0xff28B028),
                    inactiveColor = Color(0xff28B028).copy(0.5f)
                )
            }
        },*/
        /*indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.Red
            )
        },
*/

        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, data ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
                icon = {
                    Icon(imageVector = data.second, contentDescription = null)
                },
//                Enable if you want Text
//                text = {
//                    Text(text = data.first, fontSize = 12.sp)
//                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState,  tabViewmodel: TabViewModel) {
    HorizontalPager(state = pagerState, flingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        snapVelocityThreshold = 5.dp
    ),) { index ->
        when (index) {
            0 -> {
                HomeScreen(tabViewmodel)
            }

            1 -> {
                HomeScreen(tabViewmodel)
            }

            2 -> {
                HomeScreen(tabViewmodel)
            }

            3 -> {
                AboutScreen(tabViewmodel)
            }
            4->{
                AboutScreen(tabViewmodel)
            }


        }

    }

}


private fun getTabList(): List<Pair<String, ImageVector>> {
    return listOf(
        "Message" to Icons.Default.Email,
        "Group" to Icons.Default.Build,
        "Home" to Icons.Default.Home,
        "Notification" to Icons.Default.Notifications,
        "Profile" to Icons.Default.Person,
    )
}