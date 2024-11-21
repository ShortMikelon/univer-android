package com.github.shortmikelon.rgr3

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.material3.Button
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun ThirdRGRHomeScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ThirdRGRButton(text = "Camera", onClick = {
            val intent = Intent(context, ThirdRGRCameraActivity::class.java)
            context.startActivity(intent)
        })
        ThirdRGRButton(text = "Show Audio Player", onClick = {
            navController.navigate("THIRD_RGR_AUDIO_PLAYER")
        })
        ThirdRGRButton(text = "Show Video Player", onClick = {
            navController.navigate("THIRD_RGR_VIDEO_PLAYER")
        })
        ThirdRGRButton(text = "Show Picture", onClick = {
            navController.navigate("THIRD_RGR_PICTURES")
        })
    }
}

@Composable
fun ThirdRGRAudioPlayerScreen() {
    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableIntStateOf(0) }
    val (currentUriIndex, setCurrentUriIndex) = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val audioFiles = getAllAudioFiles(context)

    val currentUri = audioFiles[currentUriIndex].uri

    LaunchedEffect(currentUri) {
        mediaPlayer.apply {
            reset()
            setDataSource(context, currentUri)
            prepare()
            start()
            isPlaying = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = currentPosition.toFloat(),
            onValueChange = {
                mediaPlayer.seekTo(it.toInt())
                currentPosition = it.toInt()
            },
            valueRange = 0f.. audioFiles[currentUriIndex].duration.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                } else {
                    mediaPlayer.start()
                    isPlaying = true
                }
            },
            modifier = Modifier.size(80.dp),
            shape = CircleShape
        ) {
            Text(if (isPlaying) "Pause" else "Play")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next/Previous Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                if (currentUriIndex > 0) {
                    setCurrentUriIndex(currentUriIndex - 1)
                }
            }) {
                Text("Previous")
            }
            Button(onClick = {
                if (currentUriIndex < audioFiles.size - 1) {
                    setCurrentUriIndex(currentUriIndex + 1)
                }
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Track Name
        Text(
            text = "Playing: ${currentUri.lastPathSegment}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        LaunchedEffect(Unit) {
            while (true) {
                if (isPlaying) {
                    currentPosition = mediaPlayer.currentPosition
                }
                delay(500)
            }
        }
    }
}

@Composable
fun ThirdRGRVideoPlayerScreen() {
    val context = LocalContext.current
    var currentVideoUri by remember { mutableStateOf(Uri.EMPTY) }
    val pickVideoLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) currentVideoUri = uri
    }
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    LaunchedEffect(currentVideoUri) {
        currentVideoUri?.let {
            exoPlayer.setMediaItem(MediaItem.fromUri(it))
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                }
            }
        )

        Button(onClick = { pickVideoLauncher.launch("video/*") }) {
            Text(text = "Pick Video", fontSize = 20.sp)
        }
    }
}

@Composable
fun ThirdRGRShowPictureScreen() {
    var imageUri: Uri? by remember { mutableStateOf(null) }
    val picturePicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Selected image",
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            androidx.compose.material3.Button(
                onClick = {
                    picturePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }
        }
    }
}

@Composable
private fun ThirdRGRButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}