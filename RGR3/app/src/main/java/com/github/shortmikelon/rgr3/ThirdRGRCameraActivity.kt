package com.github.shortmikelon.rgr3

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.github.shortmikelon.rgr3.ui.theme.RGR3Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ThirdRGRCameraActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navController.navigate(
                    "THIRD_RGR_LAUNCH_CAMERA",
                    navOptions = navOptions {
                        popUpTo("THIRD_RGR_CAMERA_HOME") {
                            inclusive = true
                        }
                    })
            } else {
                navController.navigate("THIRD_RGR_CAMERA_PERMISSION_DENIED",
                    navOptions = navOptions {
                        popUpTo("THIRD_RGR_CAMERA_HOME") {
                            inclusive = true
                        }
                    })
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            RGR3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "THIRD_RGR_CAMERA_HOME"
                    ) {
                        composable("THIRD_RGR_CAMERA_HOME") {
                            ThirdRGRCameraHomeScreen(navController = navController)
                        }

                        composable("THIRD_RGR_LAUNCH_CAMERA") {
                            ThirdRGRCameraScreen()
                        }

                        composable("THIRD_RGR_CAMERA_PERMISSION_DENIED") {
                            ThirdRGRCameraPermissionDenied()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ThirdRGRCameraHomeScreen(
        navController: NavController
    ) {
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
            Text(text = "Please allow the use of the camera", fontSize = 25.sp)
        }

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) -> {
                navController.navigate(
                    "THIRD_RGR_LAUNCH_CAMERA",
                    navOptions = navOptions {
                        popUpTo("THIRD_RGR_CAMERA_HOME") {
                            inclusive = true
                        }
                    }
                )
            }

            else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }
    }

    @Composable
    private fun ThirdRGRCameraScreen() {
        val lensFacing = CameraSelector.LENS_FACING_BACK

        val lifecycleOwner = LocalLifecycleOwner.current

        val context = LocalContext.current

        val preview = Preview.Builder().build()
        val previewView = remember { PreviewView(context) }

        val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        val imageCapture = remember { ImageCapture.Builder().build() }

        LaunchedEffect(lensFacing) {
            val cameraProvider = context.getCameraProvider()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)

            preview.surfaceProvider = previewView.surfaceProvider
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
            Button(onClick = { captureImage(imageCapture, context) }) {
                Text("Capture Image", fontSize = 20.sp)
            }
        }
    }

    @Composable
    private fun ThirdRGRCameraPermissionDenied() {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Camera Permission Denied", fontSize = 35.sp)
        }
    }

    private fun captureImage(imageCapture: ImageCapture, context: Context) {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US)
        val name = format.format(Date())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/RGR3-Image")
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d("LOX", exception.message ?: exception.toString())
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider {
    return suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }
}