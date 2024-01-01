package com.jiostb.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSSelectorFromString
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIControlEventEditingChanged
import platform.UIKit.UITextField
import platform.UIKit.UIView

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun VideoPlayer(modifier: Modifier,url: String, plaing:()->Unit){

    val player = remember { url?.let { NSURL.URLWithString(it)?.let { AVPlayer(uRL = it) } } }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }
    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true

    playerLayer.player = player
    // Use a UIKitView to integrate with your existing UIKit views
    UIKitView(
        factory = {
            // Create a UIView to hold the AVPlayerLayer
            val playerContainer = UIView()
            playerContainer.addSubview(avPlayerViewController.view)
            // Return the playerContainer as the root UIView
            playerContainer
        },
        onResize = { view: UIView, rect: CValue<CGRect> ->
            CATransaction.begin()
            CATransaction.setValue(true, kCATransactionDisableActions)
            view.layer.setFrame(rect)
            playerLayer.setFrame(rect)
            avPlayerViewController.view.layer.frame = rect
            CATransaction.commit()
        },
        update = { view ->
            player?.play()
            plaing()
            avPlayerViewController.player!!.play()
        },
        modifier = modifier)
}
@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun ComposeUITextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    UIKitView(
        factory = {
            val textField = object : UITextField(CGRectMake(0.0, 0.0, 0.0, 0.0)) {
                @ObjCAction
                fun editingChanged() {
                    onValueChange(text ?: "")
                }
            }
            textField.addTarget(
                target = textField,
                action = NSSelectorFromString(textField::editingChanged.name),
                forControlEvents = UIControlEventEditingChanged
            )
            textField
        },
        modifier = modifier,
        update = { textField ->
            textField.text = value
        },
        onRelease = { textField ->
            textField.removeTarget(
                target = textField,
                action = NSSelectorFromString(textField::editingChanged.name),
                forControlEvents = UIControlEventEditingChanged
            )
        },
    )
}

