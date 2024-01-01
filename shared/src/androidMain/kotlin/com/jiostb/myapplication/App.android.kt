package com.jiostb.myapplication

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlaybackException
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.jiostb.myapplication.App
import com.jiostb.myapplication.di.initKoin
import kotlinx.coroutines.delay
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/*class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initKoin {
            androidLogger(Level.NONE)
            androidContext(this@AndroidApp)
        }
    }
}*/

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
   // AndroidApp.INSTANCE.startActivity(intent)
}
@OptIn(UnstableApi::class)
@Composable
internal actual fun VideoPlayer(modifier: Modifier, url: String, plaing:()->Unit) {

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))

                setMediaSource(source)
                prepare()
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        Log.d("TAG", "onPlaybackStateChanged: "+playbackState)
                    }
                    override fun onPlayerError(error: PlaybackException) {
                        when (error.errorCode) {
                            ExoPlaybackException.TYPE_REMOTE -> {

                                /*Utils.appToast(
                                    requireActivity(),
                                    error.localizedMessage
                                )*/
                            }

                            ExoPlaybackException.TYPE_RENDERER -> {
                                /* Utils.appToast(
                                     requireActivity(),
                                     error.rendererException.message
                                 )*/
                            }

                            ExoPlaybackException.TYPE_SOURCE -> {
                                /*Utils.appToast(
                                    requireActivity(),
                                    error.sourceException.message
                                )*/
                            }

                            ExoPlaybackException.TYPE_UNEXPECTED -> {
                                /*Utils.appToast(
                                    requireActivity(),
                                    error.unexpectedException.message
                                )*/
                            }
                        }
                    }
                })
            }
    }

    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
    LaunchedEffect(Unit) {
        while (true) {
            delay(300)
           // contentCurrentPosition = exoPlayer.currentPosition
        }
    }

    LaunchedEffect(Unit) {
        with(exoPlayer) {
            playWhenReady = true
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            repeatMode = Player.REPEAT_MODE_OFF
        }
    }
    DisposableEffect(
        AndroidView(factory = {
            PlayerView(context).apply {
                hideController()
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                plaing()
            }
        })
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
actual fun ComposeUITextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier=modifier
    )

}
